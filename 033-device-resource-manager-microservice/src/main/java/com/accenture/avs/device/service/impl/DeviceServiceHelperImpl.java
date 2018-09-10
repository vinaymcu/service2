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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.enums.SearchOperator;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.DeviceListDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceListResultObject;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceListResponse;
import com.accenture.avs.device.repository.CustomDeviceRepository;

import com.accenture.avs.device.repository.DevicePropertyRepository;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.repository.ModelAssignedResourcesRepository;
import com.accenture.avs.device.repository.ModelRepository;
import com.accenture.avs.device.repository.ModelVTPLinkRepository;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.SearchFilter;

/**
 * This is a Helper Class for DeviceServiceBeanImpl.
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class DeviceServiceHelperImpl implements DeviceServiceHelper {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceServiceHelperImpl.class);

	/** modelRepository */
	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private ModelVTPLinkRepository modelVTPLinkRepository;

	@Autowired
	private ModelAssignedResourcesRepository assignedResourcesRepository;

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;

	/** customDeviceRepository */
	@Autowired
	private CustomDeviceRepository customDeviceRepository;
	
	/** devicePropertyRepository */
	@Autowired
	DevicePropertyRepository devicePropertyRepository;
	
	/**
	 * This method finds the device model in database. If found, then returns,
	 * else it throw error.
	 * 
	 * @param deviceModelName
	 * @param deviceVendor
	 * @param checkIsActive
	 * 
	 * @return
	 */
	@Override
	public Model getDeviceModel(String deviceModelName, String deviceVendor, boolean checkIsActive) {
		LOGGER.logMessage("Model={}, Vendor={}", deviceModelName, deviceVendor);

		Model deviceModel = null;

		if (DeviceManagerUtil.checkNotNullObject(deviceModelName)
				&& DeviceManagerUtil.checkNotNullObject(deviceVendor)) {
			deviceModel = modelRepository.findByModelNameAndVendor(deviceModelName, deviceVendor);
			if (DeviceManagerUtil.checkNullObject(deviceModel) || (checkIsActive && !deviceModel.getStatus())) {
				throw new DeviceManagerException(ErrorCode.INVALID_MODEL, "DeviceModel doesn't exists or inactive");
			}
		}

		return deviceModel;
	}

	/**
	 * This method returns Device according to provided device id
	 * 
	 * @param deviceId
	 * @param doValidation
	 * 
	 * @return
	 */
	@Override
	public Device getDevice(String deviceId, boolean doValidation) {
		Device device = deviceRepository.findByDeviceId(deviceId);
		if (doValidation && DeviceManagerUtil.checkNullObject(device)) {
			throw new DeviceManagerException(ErrorCode.DEVICE_NOT_FOUND, "Device doesn't exists");
		}
		return device;
	}

	/**
	 * This generates a unique device name for the Subscriber. The default name
	 * will be DEVICE_X where X shall be replaced incrementally in such way a
	 * subscriber shall not have two devices with same name
	 * 
	 * @param assignedToUserName
	 * @return
	 */
	@Override
	public String getUniqueDeviceName(String assignedToUserName) {
		String uniqueDeviceName = "DEVICE_";
		String queryDeviceName = uniqueDeviceName + "%";

		List<String> deviceNameList = deviceRepository
				.findDeviceNamesByDeviceNameAndAssignedToUserNameOrderByDeviceNameDesc(queryDeviceName,
						assignedToUserName);

		if (deviceNameList.isEmpty()) {
			return uniqueDeviceName + "1";
		} else {
			Long counter = Long.parseLong(deviceNameList.get(0).substring(7));
			uniqueDeviceName = uniqueDeviceName + (counter + 1);
		}
		return uniqueDeviceName;
	}
	
	/**
	 * De-associate device From User
	 * @param deviceEntity
	 * @param lastUpdateUserName
	 */
	public void deassociateDeviceFromUser(Device deviceEntity, String lastUpdateUserName) {
		// Delete Device Properties
		devicePropertyRepository.deleteById(deviceEntity.getId());
		
		// Unassign Device
		deviceEntity.setAssignmentStatus(Constants.AssignmentStatus.UNASSIGNED);
		deviceEntity.setAssignedToUserName(null);
		deviceEntity.setDatetimeOfAssignment(null);
		deviceEntity.setDeviceName(null);
		deviceEntity.setLastUpdatedOn(System.currentTimeMillis());
		deviceEntity.setLastUpdatedBy(lastUpdateUserName);

		// Delete Device Profile
		deviceEntity.setDeviceProfile(null);
		deviceEntity.setDeviceProfileBandwidth(null);
		deviceEntity.setQoeBandwidth(null);
		deviceEntity.setTvQualityInterest(null);
		
		deviceEntity.setDeviceMaxBWAllowedPerQuality(null);
				
	}

	/**
	 * 
	 * @param searchBy
	 * @param searchValue
	 * @param searchOperation
	 * @param sortBy
	 * @param sortOrder
	 * @param startIndex
	 * @param pageSize
	 * @return GetDeviceListResponse
	 */
	@Override
	public GetDeviceListResponse getDeviceList(String searchBy, String searchValue, String searchOperation,
			String sortBy, String sortOrder, Integer startIndex, Integer pageSize) {
		long startTime = System.currentTimeMillis();

		GetDeviceListResponse response = new GetDeviceListResponse();
		DeviceListResultObject devicesList = new DeviceListResultObject();
		devicesList.setTotalResults(0);
		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setResultObj(devicesList);
		if (StringUtils.isEmpty(searchValue)) {
			return response;
		}

		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchBy(searchBy);
		searchFilter.setSearchValue(searchValue);
		searchFilter.setSearchOperation(searchOperation == null ? null : SearchOperator.getOperation(searchOperation));
		searchFilter.setSortBy(sortBy);
		searchFilter.setSortOrder(sortOrder);
		searchFilter.setStartIndex(startIndex);
		searchFilter.setPageSize(pageSize);
		List<Device> devices = customDeviceRepository.search(searchFilter);
		devicesList.setTotalResults(devices.size());
		for (Device device : devices) {
			DeviceListDto deviceListDto = new DeviceListDto();
			deviceListDto.setDeviceId(device.getDeviceId());
			deviceListDto.setSerialNumber(device.getSerialNumber());
			deviceListDto.setDrmId(device.getDrmId());
			deviceListDto.setUserName(device.getAssignedToUserName());
			deviceListDto.setIpAddress(device.getInternalIpAddress());
			deviceListDto.setAssignmentStatus(device.getAssignmentStatus());
			if (!DeviceManagerUtil.checkNullObject(device.getModel())) {
				deviceListDto.setModel(device.getModel().getModelName());
				deviceListDto.setVendor(device.getModel().getVendor());
			}
			deviceListDto.setDeviceType(device.getDeviceType());
			deviceListDto.setPlatform(device.getPlatform());
			deviceListDto.setSoftwareVersion(device.getSoftwareVersion());

			devicesList.getDeviceList().add(deviceListDto);
		}
		LOGGER.logMessage("getDeviceList with total Devices : " + devices.size());
		LOGGER.logMethodEnd(System.currentTimeMillis() - startTime);
		return response;
	}

	@Override
	public List<ModelVtpLink> getModelVtpLink(Long modelId) {
		LOGGER.logMessage("ModelId={}", modelId);

		List<ModelVtpLink> deviceModel = null;

		deviceModel = modelVTPLinkRepository.findModelVtpLinkByModelId(modelId);

		return deviceModel;
	}

	@Override
	public List<ModelAssignedResources> getModelAssignedResources(Long modelId) {
		LOGGER.logMessage("ModelId={}", modelId);

		List<ModelAssignedResources> deviceModel = null;

		deviceModel = assignedResourcesRepository.findModelAssignedResourcesByModelId(modelId);

		return deviceModel;
	}
	/**
	 * This method retrieve all distinct vendors from model in database.
	 * @return List<String>
	 */
	@Override
	public List<String> getVendorList() {
		return modelRepository.getVendorList();
	}

}
