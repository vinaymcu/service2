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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceProperty;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.GetUserDevicesResponse;
import com.accenture.avs.device.json.object.devicemanager.Profile;
import com.accenture.avs.device.json.object.devicemanager.UserDeviceDto;
import com.accenture.avs.device.json.object.devicemanager.UserDevicesListDto;
import com.accenture.avs.device.json.object.devicemanager.UserDevicesResultObject;
import com.accenture.avs.device.repository.DevicePropertyRepository;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.service.UserDevicesService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * @author rajesh.karna
 *
 */
@Service
public class UserDevicesServiceImpl implements UserDevicesService {
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(UserDevicesServiceImpl.class);
	
	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;
	
	/** devicePropertyRepository */
	@Autowired
	private DevicePropertyRepository devicePropertyRepository;
	
	/** UI_LANGUAGE */
	private static final String UI_LANGUAGE = "uiLanguage";
	
	/** AUDIO_LANGUAGE */
	private static final String AUDIO_LANGUAGE = "audioLanguage";

	/**
	 * Gets user devices on the basis of comma seperated userName
	 * @param userName
	 * @param limitDeviceFields
	 * @return GetUserDevicesResponse
	 */
	@Override
	public GetUserDevicesResponse getUserDevices(List<String> userNames, Boolean limitDeviceFields) {
		LOGGER.logMessage("getUserDevices++");
		Long startTime = System.currentTimeMillis();
		
		if (CollectionUtils.isEmpty(userNames)) {
			throw new DeviceManagerException(ErrorCode.BAD_REQUEST);
		}
		
		GetUserDevicesResponse getUserDevicesResponse =  new GetUserDevicesResponse();
		
		Map<String, List<Device>> userNameDeviceMap = getDevices(userNames);		
		UserDevicesResultObject userDevices = getUserDevices(userNameDeviceMap, limitDeviceFields);		
		
		getUserDevicesResponse.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		getUserDevicesResponse.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);		
		getUserDevicesResponse.setResultObj(userDevices);
		
		LOGGER.logMethodEnd(System.currentTimeMillis() - startTime);
		return getUserDevicesResponse;
	}
	

	/**
	 * Gets UserDevices response on the basis of userNames, user properties and devices.	
	 * @param userNameDevices
	 * @param limitDeviceFields
	 * @return UserDevices
	 */
	private UserDevicesResultObject getUserDevices(Map<String,List<Device>> userNameDevices, Boolean limitDeviceFields) {
		List<UserDevicesListDto> userDevicesList = new ArrayList<>();
		UserDevicesResultObject userDevices = new UserDevicesResultObject();
		userDevices.setTotalResults(userNameDevices.size());		
		userDevices.setUserDevicesList(userDevicesList);
		for (Map.Entry<String,List<Device>> entry : userNameDevices.entrySet()) {
			userDevicesList.add(getUserDevicesList(entry.getKey(), entry.getValue(), limitDeviceFields));
		}
		return userDevices;
	}
	
	
	/**
	 * Gets getUserDevicesList on the basis of userName and device.
	 * @param userName
	 * @param devices
	 * @param limitDeviceFields
	 * @return UserDevicesListDto
	 */
	private UserDevicesListDto getUserDevicesList(String userName, List<Device> devices, Boolean limitDeviceFields) {
		UserDevicesListDto userDevicesListDto =  new UserDevicesListDto();
		List<UserDeviceDto> userDeviceDtoList = new ArrayList<>();
		for (Device device : devices) {			
			userDeviceDtoList.add(getUserDeviceDto(limitDeviceFields, device));
		}
		userDevicesListDto.setUserName(userName);
		userDevicesListDto.setDevices(userDeviceDtoList);
		return userDevicesListDto;
	}

	/**
	 * Get UserDeviceDto
	 * @param limitDeviceFields
	 * @param device
	 * @return
	 */
	private UserDeviceDto getUserDeviceDto(Boolean limitDeviceFields, Device device) {
		UserDeviceDto userDeviceDto = new UserDeviceDto();
		userDeviceDto.setDeviceId(device.getDeviceId());
		userDeviceDto.setSoftwareVersion(device.getSoftwareVersion());
		
		if (!DeviceManagerUtil.checkNullObject(device.getModel())) {
			userDeviceDto.setModel(device.getModel().getModelName());
			userDeviceDto.setHdCapable(device.getModel().getHdCapable());
			userDeviceDto.setVendor(device.getModel().getVendor());
		}
		userDeviceDto.setDeviceType(device.getDeviceType());
		List<DeviceProperty> deviceProp =  devicePropertyRepository.findById(device.getId());
		if (!CollectionUtils.isEmpty(deviceProp)) {
			userDeviceDto.setUiLanguage(getProperty(deviceProp, UI_LANGUAGE));
			userDeviceDto.setAudioLanguage(getProperty(deviceProp, AUDIO_LANGUAGE));
		}
		
		if (BooleanUtils.isFalse(limitDeviceFields)) {
			userDeviceDto.setSerialNumber(device.getSerialNumber());
			userDeviceDto.setIpAddress(device.getInternalIpAddress());
			userDeviceDto.setExtIPAddress(device.getExternalIpAddress());
			userDeviceDto.setDeviceName(device.getDeviceName());
			userDeviceDto.setPlatform(device.getPlatform());
			userDeviceDto.setUiVersion(device.getUiVersion());
			device.setAssignmentStatus(device.getAssignmentStatus());	
			if (!DeviceManagerUtil.checkNullObject(device.getLastUpdatedOn())) {
				userDeviceDto.setLastUpdateTime(DeviceManagerUtil.getStringFromLong(device.getLastUpdatedOn()));
			}
			userDeviceDto.setLastUpdateUserName(device.getLastUpdatedBy());
			userDeviceDto.setMaxBandwidthUpdates(DeviceManagerUtil.getIntegerFromLong(device.getMaxBandwidthUpdate()));
			userDeviceDto.setTvQualityInterest(device.getTvQualityInterest());
			userDeviceDto.setDrmId(device.getDrmId());
			if (Objects.nonNull(device.getDeviceProfile()) || Objects.nonNull(device.getQoeBandwidth()) || Objects.nonNull(device.getDeviceProfileBandwidth())) {
				Profile profile = new Profile();
				profile.setName(device.getDeviceProfile());
				profile.setAssignedVQEOverheadBW(DeviceManagerUtil.getIntegerFromLong(device.getQoeBandwidth()));
				profile.setNetworkBandwidth(DeviceManagerUtil.getIntegerFromLong(device.getDeviceProfileBandwidth()));
				userDeviceDto.setProfile(profile);
			}
			
		}
		
		//Forcefully set null to avoid adding tag in response, because generic setting removed from IA but still in json
		userDeviceDto.setGenericDeviceSettings(null);
		
		return userDeviceDto;
	}
	
	/**
	 * Get property
	 * 
	 * @param deviceProp
	 * @param property
	 * @return
	 */
	private String getProperty(List<DeviceProperty> deviceProp, String property) {
		String propertyValue = null;
		for (DeviceProperty deviceProperty : deviceProp) {
			if (property.equals(deviceProperty.getId().getPropertyName())) {
				propertyValue = deviceProperty.getPropertyValue();
				break;
			}
		}
		return propertyValue;
	}

	
	/**
	 * Get list devices of all provided userNames
	 * Devices.	
	 * @param userNameList
	 * @return Map<String, List<Device>>
	 */
	private Map<String, List<Device>> getDevices(List<String> userNameList) {
		Map<String, List<Device>> userNameDevicesMap = new HashMap<>();
		List<Device> devices = deviceRepository.findByAssignedToUserNameList(userNameList);
		for (String userName : userNameList) {
			List<Device> deviceList = new ArrayList<>();
			for (Device device : devices) {
				if (userName.equals(device.getAssignedToUserName())) {
					deviceList.add(device);
				}
			}
			userNameDevicesMap.put(userName, deviceList);
		}	
		return userNameDevicesMap;

	}
}
