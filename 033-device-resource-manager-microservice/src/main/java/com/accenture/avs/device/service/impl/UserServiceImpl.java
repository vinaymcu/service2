/***************************************************************************
 * Copyright (C) Accenture
 *
 * The reproduction, transmission or use of this document or its contents is not permitted without
 * prior express written consent of Accenture. Offenders will be liable for damages. All rights,
 * including but not limited to rights created by patent grant or registration of a utility model or
 * design, are reserved.
 *
 * Accenture reserves the right to modify technical specifications and features.
 *
 * Technical specifications and features are binding only insofar as they are specifically and
 * expressly agreed upon in a written contract.
 *
 **************************************************************************/

package com.accenture.avs.device.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.config.model.BandwidthProfile;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceAudit;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUpdatedUsers;
import com.accenture.avs.device.json.object.devicemanager.GetUserResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVQEParameterUpdateStatusResponse;
import com.accenture.avs.device.json.object.devicemanager.SetUserRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateVqeParamMessage;
import com.accenture.avs.device.json.object.devicemanager.UpdatedUserDTO;
import com.accenture.avs.device.manager.UserManager;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.service.DeviceAuditLogsService;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.service.UserService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.EntityObjectGenerator;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(UserServiceImpl.class);

	/** userManager */
	@Autowired
	private UserManager userManager;

	/** deviceServiceHelper */
	@Autowired
	DeviceServiceHelper deviceServiceHelper;

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;
	
	/** userRepository */
	@Autowired
	private UserRepository userRepository;
	
	/** deviceAuditLogsService */
	@Autowired
	DeviceAuditLogsService deviceAuditLogsService;

	/** deviceManagerValidator */
	@Autowired
	DeviceManagerValidator deviceManagerValidator;

	/** configurationClient */
	@Autowired
	ConfigurationClient configurationClient;

	/** resourceDistributionService */
	@Autowired
	InterProcessCommunicationService interProcessCommunicationService;

	/**
	 * This method validates and creates a new Device
	 * 
	 * @param setUserRequest
	 * @param crmAccountId
	 * 
	 * @return GenericResponse
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GenericResponse setUser(SetUserRequest setUserRequest, String crmAccountId) {
		boolean isProfileCalculationRequired = false;
		long startTime = System.currentTimeMillis();
		User user = userManager.getUserByCrmAccountId(crmAccountId);
		boolean isCreate = (Objects.isNull(user)) ? Boolean.TRUE : Boolean.FALSE;
		if (isCreate) {
			deviceManagerValidator.validateMissingParameterInSetUserRequest(setUserRequest);
		} else {
			isProfileCalculationRequired = deviceManagerValidator.checkProfileCalculationRequired(setUserRequest, user);
			LOGGER.logMessage("Changes found for profile calculation : {}", isProfileCalculationRequired);
		}

		if(setUserRequest.getMaxAllowedIPTVDevices() != null && !isCreate) {
			LOGGER.logMessage("Max allowed IPTV devices in request : {}", setUserRequest.getMaxAllowedIPTVDevices());
			deviceManagerValidator.validateMaxDeviceBasedOnAssignedDevice(user.getUserName(), setUserRequest.getMaxAllowedIPTVDevices());
		}
		
		userManager.setUser(EntityObjectGenerator.getUserEntityForSetUser(crmAccountId, setUserRequest, user, isCreate),
				isCreate);

		if (isProfileCalculationRequired) {
			LOGGER.logMessage("Changes found for resource profile calculation eligibility.");
			triggerProfileRecalculation(user.getUserName());
		}
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * Delete user.
	 * 
	 * @param userName
	 * @return GenericResponse
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GenericResponse deleteUser(String userName) {
		long startTime = System.currentTimeMillis();
		User user = userManager.findByUserNameWithUserLimits(userName);
		userManager.deleteUser(user);
		List<Device> userAssociatedDevices = deviceRepository.findByAssignedToUserName(user.getUserName());
		if (CollectionUtils.isNotEmpty(userAssociatedDevices)) {
			for (Device deviceEntity : userAssociatedDevices) {
				deviceServiceHelper.deassociateDeviceFromUser(deviceEntity, Constants.DEFAULT_LASTUPDATEDUSERNAME);
				deviceRepository.save(deviceEntity);
			}
		}
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * Gets user.
	 * 
	 * @param userName
	 * @return GetUserResponse
	 */
	@Override
	public GetUserResponse getUser(String userName) {
		long startTime = System.currentTimeMillis();
		User user = userManager.findByUserNameWithUserLimits(userName);
		Long freeBandwidth = user.getFreeBandwidth();

		if (deviceRepository.countByAssignedToUserNameAndPlatform(user.getUserName(),
				Constants.PlatformType.IPTV) == 0) {

			if (Objects.nonNull(user.getOverrideBandwith()) && user.getOverrideBandwith() > 0) {
				freeBandwidth = user.getOverrideBandwith();
			} else {
				BandwidthProfile bandwidthProfile = configurationClient.findBandwidthProfile(user.getBandwithProfile());
				LOGGER.logMessage("Bandwidth profile [{}] value in memory : {}", user.getBandwithProfile(),
						bandwidthProfile);
				if (Objects.nonNull(bandwidthProfile)) {
					freeBandwidth = bandwidthProfile.getBandwidth();
				}
			}
		}

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getUserResponse(user, executionTime, freeBandwidth);
	}

	/**
	 * This method is used to update VQE Parameters of the User.
	 * 
	 * @param updateVqeParamMessage
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateVqeParameter(UpdateVqeParamMessage updateVqeParamMessage) {
		long startTime = System.currentTimeMillis();
		
		// Validate and Save VQE parameters
		String userName = validateAndSaveVqeParameters(updateVqeParamMessage);
		
		// Call Resource Recalculation
		if (!DeviceManagerUtil.checkNullorBlankString(userName)) {
			triggerProfileRecalculation(userName);
		}
		
		LOGGER.logMethodEnd(System.currentTimeMillis() - startTime);
	}
	
	/**
	 * This method validates and saves the VQE Parameters in database. Also, throws
	 * a DeviceManagerException with the update failure reason so that it can be
	 * logged in USER_LOG table.
	 * 
	 * @param updateVqeParamMessage
	 * @return
	 */
	private String validateAndSaveVqeParameters(UpdateVqeParamMessage updateVqeParamMessage) {
		String userName = null;
		try {
			// Validate User
			User user = userManager.getUserByCrmAccountId(updateVqeParamMessage.getCrmAccountId());
			if (DeviceManagerUtil.checkNullObject(user)) {
				throw new DeviceManagerException(ErrorCode.GENERIC_ERROR, "User not found");
			}
			
			LOGGER.logMessage("Async UpdateVQEParameters :: oldQoeControlBandwidth={}, newQoeControlBandwidth={}",
					user.getQoeControlBandwith(), updateVqeParamMessage.getQoeControlBandwidth());
			
			// Old Qoe Control Bandwidth
			Long oldQoeControlBandwidth = DeviceManagerUtil.checkNullObject(user.getQoeControlBandwith()) ? 0L
					: user.getQoeControlBandwith();

			// Update User
			updateUserVqeParameters(user, updateVqeParamMessage);

			if (!DeviceManagerUtil.checkNullObject(updateVqeParamMessage.getQoeControlBandwidth())
					&& updateVqeParamMessage.getQoeControlBandwidth().longValue() != oldQoeControlBandwidth) {
				userName = user.getUserName();
			}
		} catch (DeviceManagerException exp) {
			throw exp;
		} catch (Exception exp) {
			throw new DeviceManagerException(ErrorCode.GENERIC_ERROR, "Database Operation Failed");
		}
		return userName;
	}
	
	/**
	 * This method updates the VQE Parameters for the intended user.
	 * 
	 * @param user
	 * @param updateVqeRequest
	 */
	private void updateUserVqeParameters(User user, UpdateVqeParamMessage updateVqeRequest) {
		if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getQoeControlBandwidth())) {
			user.setQoeControlBandwith(updateVqeRequest.getQoeControlBandwidth().longValue());
		}
		if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getNetworkBufferSize())) {
			user.setNetworkBufferSize(updateVqeRequest.getNetworkBufferSize().longValue());
		}
		if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getNatBindingRefreshInterval())) {
			user.setNatBindingRefreshInterval(updateVqeRequest.getNatBindingRefreshInterval().longValue());
		}
		user.setRccEnable(updateVqeRequest.getRccEnable());
		user.setRetEnable(updateVqeRequest.getRetEnable());
		user.setLastUpdatedDatetime(System.currentTimeMillis());
		userManager.setUser(user, Boolean.FALSE);
	}
	
	/**
	 * This methods checks and triggers profile recalculation. Also, throws a
	 * DeviceManagerException with the update failure reason so that it can be
	 * logged in USER_LOG table.
	 * 
	 * @param userName
	 */
	private void triggerProfileRecalculation(String userName) {
		try {
			if (deviceRepository.countByAssignedToUserNameAndPlatform(userName, Constants.PlatformType.IPTV) > 0) {
				if (interProcessCommunicationService.triggerDeviceProfileCalculation(userName)) {
					LOGGER.logMessage("Notify Push Message MS");
					// Notify Push Message
					interProcessCommunicationService.notifyPushMessageMs(null, userName, Constants.Trigger.T_SUBSCRIBER_INFO);
				}
			} else {
				LOGGER.logMessage("No valid IPTV device found for user, Skiping resource profile calculation.");
			}
		} catch (Exception e) {
			throw new DeviceManagerException(ErrorCode.GENERIC_ERROR, "Resource Redistribution Failed");
		}
	}

	/**
	 * This method returns the VQE parameter update status for each of the provided
	 * crmAccountId.
	 * 
	 * @param crmAccountIds
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GetVQEParameterUpdateStatusResponse getVQEUpdateStatus(String crmAccountIds) {

		long startTime = System.currentTimeMillis();

		// Request Data Validation
		if (DeviceManagerUtil.checkNullorBlankString(crmAccountIds)) {
			throw new DeviceManagerException(ErrorCode.BAD_REQUEST, "crmAccountIds not provided in the request");
		}

		// Get VQE Parameter Update Status
		List<String> crmAccountIdList = Arrays.asList(StringUtils.split(crmAccountIds, ','));
		String queryDelimiter = "|";
		String nullReplacerStr = "NA";
		List<String> resultList = userRepository.getVqeUpdateStatusForUsers(crmAccountIdList, queryDelimiter,
				nullReplacerStr);

		// Generate Response
		GetVQEParameterUpdateStatusResponse getVQEParameterUpdateStatusResponse = RequestResponseGenerator
				.getVQEParameterUpdateStatusResponse(resultList, queryDelimiter, nullReplacerStr);

		Long executionTime = System.currentTimeMillis() - startTime;
		getVQEParameterUpdateStatusResponse.setExecutionTime(executionTime.intValue());
		LOGGER.logMethodEnd(executionTime);
		return getVQEParameterUpdateStatusResponse;
	}

	/**
	 * Get UpdatedUsers.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return GetUpdatedUsers
	 */
	@Override
	public GetUpdatedUsers getUpdatedUser(Long startDate, Long endDate) {
		if (DeviceManagerUtil.checkNullObject(startDate) || DeviceManagerUtil.checkNullObject(endDate)) {
			throw new DeviceManagerException(ErrorCode.BAD_REQUEST);
		}
		long startTime = System.currentTimeMillis();
		List<User> user = userManager.getAllUsers();
		Map<String, UpdatedUserDTO> usermap = convertListToMap(user);
		Map<String, UpdatedUserDTO> resultmap = new HashedMap();
		List<DeviceAudit> deviceAudits = deviceAuditLogsService.getDeviceAuditLogsByStartDateAndEndDate(startDate,
				endDate);
		for (DeviceAudit deviceAudia : deviceAudits) {
			if (!resultmap.containsKey(deviceAudia.getAssignedToUsername())
					&& usermap.containsKey(deviceAudia.getAssignedToUsername())) {
				UpdatedUserDTO updatedUserDTO = usermap.get(deviceAudia.getAssignedToUsername());
				updatedUserDTO.setLastUpdateTime(deviceAudia.getLastUpdatedDatetime());
				resultmap.put(updatedUserDTO.getUsername(), updatedUserDTO);
			}
		}
		List<UpdatedUserDTO> userDto = new ArrayList<UpdatedUserDTO>(resultmap.values());
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getUpdatedUserResponse(userDto, executionTime);
	}

	private Map<String, UpdatedUserDTO> convertListToMap(List<User> users) {
		Map<String, UpdatedUserDTO> map = new HashedMap();
		for (User user : users) {
			UpdatedUserDTO updatedUserDTO = new UpdatedUserDTO(user.getCrmAccountId(), user.getUserName(),
					user.getLastUpdatedDatetime());
			map.put(user.getUserName(), updatedUserDTO);
		}
		return map;
	}

}
