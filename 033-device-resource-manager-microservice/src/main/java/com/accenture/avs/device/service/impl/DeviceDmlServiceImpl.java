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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.DeviceManagerExceptionNonTransactional;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.DeviceDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceRegistration;
import com.accenture.avs.device.json.object.devicemanager.UnAssignDeviceRequest;
import com.accenture.avs.device.repository.DevicePropertyRepository;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.service.DeviceDmlService;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.EntityObjectGenerator;

/**
 * This class will perform Device management Operations like (CREATE, UPDATE,
 * DELETE, REGISTER and UNASSIGN)
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class DeviceDmlServiceImpl implements DeviceDmlService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceDmlServiceImpl.class);

	/** deviceServiceHelper */
	@Autowired
	private DeviceServiceHelper deviceServiceHelper;

	/** interProcessCommunicationService */
	@Autowired
	private InterProcessCommunicationService interProcessCommunicationService;

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;

	/** devicePropertyRepository */
	@Autowired
	private DevicePropertyRepository devicePropertyRepository;

	/** deviceManagerValidator */
	@Autowired
	private DeviceManagerValidator deviceManagerValidator;

	/**
	 * This method validates and creates Device
	 * 
	 * @param deviceDto
	 * @param lastUpdateUserName
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createDevice(DeviceDto deviceDto, String lastUpdateUserName) {

		LOGGER.logMessage("Create Device :: DeviceId=" + deviceDto.getDeviceId());

		// Device Validation
		deviceManagerValidator.validateDeviceExistence(deviceDto.getDeviceId());

		// Device Model Validation
		Model deviceModel = deviceServiceHelper.getDeviceModel(deviceDto.getModel(), deviceDto.getVendor(),
				Boolean.TRUE);

		// Drm Id Validation
		deviceManagerValidator.validateDrmIdUniqueness(deviceDto.getDrmId(), deviceDto.getDeviceId());

		// Device Triplet Validation
		deviceManagerValidator.validateDeviceTripletUniqueness(deviceDto.getSerialNumber(), deviceDto.getModel(),
				deviceDto.getVendor(), deviceDto.getDeviceId());

		// Save Device
		deviceRepository.save(EntityObjectGenerator.getDeviceEntity(deviceDto, null, deviceModel,
				deviceDto.getPlatform(), deviceDto.getDeviceType(), null, lastUpdateUserName));

		LOGGER.logMessage("Create Device Successful:: DeviceId=" + deviceDto.getDeviceId());
	}

	/**
	 * This method validates and updates the device.
	 * 
	 * @param deviceDto
	 * @param lastUpdateUserName
	 * 
	 * @return userNameForPushNotification
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String updateDevice(DeviceDto deviceDto, String lastUpdateUserName) {

		LOGGER.logMessage("Update Device :: deviceId={}", deviceDto.getDeviceId());

		String userNameForPushNotification = null;

		// Device Validation
		Device deviceEntity = deviceServiceHelper.getDevice(deviceDto.getDeviceId(), Boolean.TRUE);

		// Model Validation
		Model deviceModelEntity = deviceServiceHelper.getDeviceModel(deviceDto.getModel(), deviceDto.getVendor(),
				Boolean.TRUE);

		// DrmId Validation
		deviceManagerValidator.validateDrmIdUniqueness(deviceDto.getDrmId(), deviceDto.getDeviceId());

		// Device Triplet Validation
		deviceManagerValidator.validateDeviceTripletUniqueness(deviceDto.getSerialNumber(), deviceDto.getModel(),
				deviceDto.getVendor(), deviceDto.getDeviceId());

		boolean isTvQualityUpdated = !DeviceManagerUtil.checkNullObject(deviceDto.getTvQualityInterest())
				&& !deviceDto.getTvQualityInterest().equals(deviceEntity.getTvQualityInterest()) ? Boolean.TRUE
						: Boolean.FALSE;

		boolean isDeviceAssigned = deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.ASSIGNED)
				? Boolean.TRUE
				: Boolean.FALSE;

		if (isDeviceAssigned) {
			// Model Updation Validation

			if (!DeviceManagerUtil.checkNullObject(deviceModelEntity)
					&& !deviceModelEntity.getId().equals(deviceEntity.getModel().getId())) {
				throw new DeviceManagerException(ErrorCode.DEVICE_ASSIGNED_CANNOT_UPDATE_MODEL,
						"Model can not be updated since the device is assigned to a subscriber");

			}

			// Device Name validation
			deviceManagerValidator.validateDeviceNameUniqueness(deviceDto.getDeviceName(),
					deviceEntity.getAssignedToUserName(), deviceDto.getDeviceId());
		}

		// Save Device
		deviceEntity = deviceRepository.save(EntityObjectGenerator.getDeviceEntity(deviceDto, deviceEntity,
				deviceModelEntity, deviceDto.getPlatform(), deviceDto.getDeviceType(), deviceDto.getDeviceName(),
				lastUpdateUserName));

		// Post operations
		if (isTvQualityUpdated && isDeviceAssigned) {

			Boolean isProfileCalculationSucessful = Boolean.FALSE;
			// Profile Recalculation and Push Message Notification
			if (deviceEntity.getPlatform().equals(Constants.PlatformType.IPTV)) {
				isProfileCalculationSucessful = interProcessCommunicationService
						.triggerDeviceProfileCalculation(deviceEntity.getAssignedToUserName());
			}

			userNameForPushNotification = deviceEntity.getAssignedToUserName();

		}
		
		// Notify Group MS
		if (isDeviceAssigned) {
			List<String> userNames = new ArrayList<>();
			userNames.add(deviceEntity.getAssignedToUserName());
			interProcessCommunicationService.notifyGroupMs(userNames, Constants.ApiType.UPDATEDEVICE);
		}

		LOGGER.logMessage("Update Device Successful :: deviceId={}", deviceDto.getDeviceId());

		return userNameForPushNotification;
	}

	/**
	 * This method validates and registers the Device.
	 * 
	 * @param deviceRegistration
	 * @param lastUpdateUserName
	 * @param acidTransaction
	 * 
	 * @param userNameForPushNotification
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = {
			DeviceManagerExceptionNonTransactional.class })
	public String registerDevice(DeviceRegistration deviceRegistration, String lastUpdateUserName,
			Boolean acidTransaction) {

		LOGGER.logMessage("RegisterDevice for deviceId={}", deviceRegistration.getDeviceId());

		if (DeviceManagerUtil.checkNullObject(acidTransaction)) {
			acidTransaction = Boolean.TRUE;
			LOGGER.logMessage("acidTransaction not provided, taking default value acidTransaction={}", acidTransaction);
		}

		String userNameForPushNotification = null;
		try {
			Boolean isNewDevice = Boolean.FALSE;

			// Model validation
			Model deviceModel = deviceServiceHelper.getDeviceModel(deviceRegistration.getModel(),
					deviceRegistration.getVendor(), Boolean.TRUE);

			// DrmId Validation
			deviceManagerValidator.validateDrmIdUniqueness(deviceRegistration.getDrmId(),
					deviceRegistration.getDeviceId());

			// Device Triplet Validation
			deviceManagerValidator.validateDeviceTripletUniqueness(deviceRegistration.getSerialNumber(),
					deviceRegistration.getModel(), deviceRegistration.getVendor(), deviceRegistration.getDeviceId());

			// Get Device
			Device deviceEntity = deviceServiceHelper.getDevice(deviceRegistration.getDeviceId(), Boolean.FALSE);

			// Validate Device AutoRegistration
			if (DeviceManagerUtil.checkNullObject(deviceEntity)) {
				if (!deviceModel.getDeviceAutoRegistration()) {
					throw new DeviceManagerException(ErrorCode.DEVICE_NOT_FOUND, "Auto-Registration not allowed");
				}
				isNewDevice = Boolean.TRUE;
			} 

			// Max limit for IPTV devices Validation
			deviceManagerValidator.validateMaxIptvDeviceLimit(deviceRegistration, deviceEntity, isNewDevice);
			
			// Device Name Validation
			String deviceName = deviceRegistration.getDeviceName();
			if (StringUtils.isNotEmpty(deviceRegistration.getDeviceName())) {
				deviceManagerValidator.validateDeviceNameUniqueness(deviceRegistration.getDeviceName(),
						deviceRegistration.getUserName(), deviceRegistration.getDeviceId());
			} else if (isNewDevice || StringUtils.isBlank(deviceEntity.getDeviceName())) {
				deviceName = deviceServiceHelper.getUniqueDeviceName(deviceRegistration.getUserName());
			}

			if (!isNewDevice && deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.ASSIGNED)
					&& !deviceRegistration.getUserName().equals(deviceEntity.getAssignedToUserName())) {
				// Unassign Device
				LOGGER.logMessage("Clearing previous assignment for userName={}", deviceEntity.getAssignedToUserName());
				devicePropertyRepository.deleteById(deviceEntity.getId());
				//clear device data as only new parameters value should be set
				clearDeviceData(deviceEntity);
			}
			
			// Register Device
			deviceEntity = deviceRepository.save(EntityObjectGenerator.getDeviceEntity(deviceRegistration, deviceEntity,
					deviceModel, deviceRegistration.getPlatform(), deviceRegistration.getDeviceType(), deviceName,
					lastUpdateUserName));


			// Profile Recalculation and Push Message Notification
			Boolean isProfileCalculationSuccessfull = Boolean.FALSE;
			if (deviceEntity.getPlatform().equals(Constants.PlatformType.IPTV)) {
				isProfileCalculationSuccessfull = interProcessCommunicationService
						.triggerDeviceProfileCalculation(deviceEntity.getAssignedToUserName());
			}

			userNameForPushNotification = deviceEntity.getAssignedToUserName();

			// Notify Group MS
			List<String> userNames = new ArrayList<>();
			userNames.add(deviceEntity.getAssignedToUserName());
			interProcessCommunicationService.notifyGroupMs(userNames, Constants.ApiType.UPDATEDEVICE);

			return userNameForPushNotification;

		} catch (DeviceManagerException deviceManagerException) {
			if (acidTransaction) {
				LOGGER.logMessage("acidTransaction={}, Transaction Rollback Required", acidTransaction);
				throw deviceManagerException;
			} else {
				LOGGER.logMessage("acidTransaction={}, Transaction Rollback Not Required", acidTransaction);
				throw new DeviceManagerExceptionNonTransactional(deviceManagerException.getErrorCode(),
						deviceManagerException.getMessage());
			}
		} catch (Exception exception) {
			if (acidTransaction) {
				LOGGER.logMessage("acidTransaction={}, Transaction Rollback Required", acidTransaction);
				throw exception;
			} else {
				LOGGER.logMessage("acidTransaction={}, Transaction Rollback Not Required", acidTransaction);
				throw new DeviceManagerExceptionNonTransactional(ErrorCode.GENERIC_ERROR, exception.getMessage());
			}
		}
	}

	/**
	 * Clear DeviceData
	 * @param deviceEntity
	 */
	private void clearDeviceData(Device deviceEntity) {
		deviceEntity.setDeviceName(null);
		deviceEntity.setAssignmentStatus(Constants.AssignmentStatus.UNASSIGNED);
		deviceEntity.setAssignedToUserName(null);
		deviceEntity.setConnectionMode(null);
		deviceEntity.setDatetimeOfAssignment(null);
		deviceEntity.setDeviceMaxBWAllowedPerQuality(null);
		deviceEntity.setDeviceName(null);
		deviceEntity.setDeviceProfile(null);
		deviceEntity.setDeviceProfileBandwidth(null);
		deviceEntity.setDeviceType(null);
		deviceEntity.setDrmId(null);
		deviceEntity.setExternalIpAddress(null);
		deviceEntity.setInternalIpAddress(null);
		deviceEntity.setLastUpdatedBy(null);
		deviceEntity.setLastUpdatedOn(null);
		deviceEntity.setMaxBandwidthUpdate(null);
		deviceEntity.setPlatform(null);
		deviceEntity.setQoeBandwidth(null);
		deviceEntity.setSoftwareVersion(null);
		deviceEntity.setSupportedMode(null);
		deviceEntity.setTvQualityInterest(null);
		deviceEntity.setUiVersion(null);
	}

	/**
	 * This method validates and Unassigns/Unregisters Device for the intended
	 * Subscriber.
	 * 
	 * @param unAssignDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return userNameForPushNotification
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String unAssignDevice(UnAssignDeviceRequest unAssignDeviceRequest, String lastUpdateUserName) {

		LOGGER.logMessage("Unassign Device for :: " + "DeviceId={}, userName={}", unAssignDeviceRequest.getDeviceId(),
				unAssignDeviceRequest.getUserName());

		// Device Validation
		Device deviceEntity = deviceServiceHelper.getDevice(unAssignDeviceRequest.getDeviceId(), Boolean.TRUE);

		// Device Assignment Validation
		if (DeviceManagerUtil.checkNullObject(unAssignDeviceRequest.getUserName())) {
			if (deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.UNASSIGNED)) {
				LOGGER.logMessage("DeviceId={} already unassigned", unAssignDeviceRequest.getDeviceId());
				return null;
			}
		} else if (deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.UNASSIGNED)
				|| !deviceEntity.getAssignedToUserName().equals(unAssignDeviceRequest.getUserName())) {
			throw new DeviceManagerException(ErrorCode.DEVICE_NOT_ASSIGNED_FOR_USER,
					"Device is not assigned to the requested user");
		}

		String assignedToUserName = deviceEntity.getAssignedToUserName();

		deviceServiceHelper.deassociateDeviceFromUser(deviceEntity, lastUpdateUserName);

		String userNameForPushNotification = null;
		Boolean isProfileCalculationSucessful = Boolean.FALSE;
		// Profile Recalculation and Push Message Notification
		if (deviceEntity.getPlatform().equals(Constants.PlatformType.IPTV)) {
			isProfileCalculationSucessful = interProcessCommunicationService
					.triggerDeviceProfileCalculation(assignedToUserName);

		}
		
		userNameForPushNotification = assignedToUserName;
		
		// Notify Group MS
		List<String> userNames = new ArrayList<>();
		userNames.add(assignedToUserName);
		interProcessCommunicationService.notifyGroupMs(userNames, Constants.ApiType.UPDATEDEVICE);

		LOGGER.logMessage("Unassign Device successful for :: " + "DeviceId={}, userName={}",
				unAssignDeviceRequest.getDeviceId(), unAssignDeviceRequest.getUserName());

		return userNameForPushNotification;
	}

	/**
	 * This method validates and deletes the Device.
	 * 
	 * @param deviceId
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteDevice(String deviceId) {

		LOGGER.logMessage("Delete Device for :: " + "DeviceId={}", deviceId);

		// Device Validation
		Device deviceEntity = deviceServiceHelper.getDevice(deviceId, Boolean.TRUE);

		// Device Assignment Validation
		if (deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.ASSIGNED)) {
			throw new DeviceManagerException(ErrorCode.DEVICE_SHOULD_BE_UNASSIGNED,
					"Device should be in unassigned state");
		}

		// Delete Device
		deviceRepository.delete(deviceEntity.getId());
	}

}
