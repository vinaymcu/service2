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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceProperty;
import com.accenture.avs.device.entity.DevicePropertyId;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.DevicePropertyDto;
import com.accenture.avs.device.json.object.devicemanager.SetDevicePropertiesRequest;
import com.accenture.avs.device.repository.DevicePropertyRepository;
import com.accenture.avs.device.service.DevicePropertiesService;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * This class will perform Device Properties Operations like (SETPROPERTIES,
 * GETPROPERTIES)
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class DevicePropertiesServiceImpl implements DevicePropertiesService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DevicePropertiesServiceImpl.class);

	/** devicePropertyRepository */
	@Autowired
	private DevicePropertyRepository devicePropertyRepository;

	/** deviceServiceHelper */
	@Autowired
	private DeviceServiceHelper deviceServiceHelper;

	/** interProcessCommunicationService */
	@Autowired
	private InterProcessCommunicationService interProcessCommunicationService;

	/**
	 * This method validates and create/update/delete Device properties.
	 * 
	 * @param deviceId
	 * @param deviceProperties
	 * 
	 * @return List<DeviceProperty>
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void setDeviceProperties(String deviceId, SetDevicePropertiesRequest deviceProperties) {
		LOGGER.logMessage("Set Device Properties :: deviceId = " + deviceId);

		// Device Validation
		Device device = getDeviceForProperty(deviceId);

		// fetch old device properties for updating.
		List<DeviceProperty> oldDevicePropList = devicePropertyRepository.findById(device.getId());
		List<DeviceProperty> toUpdateDevicePropList = generateUpdateDevicePropertiesList(deviceProperties,
				device.getId());
		filterAndDeleteDeviceProperties(oldDevicePropList, toUpdateDevicePropList, device.getId());
		devicePropertyRepository.save(toUpdateDevicePropList);

		// Send to trigger on Message Queue
		if (Constants.AssignmentStatus.ASSIGNED.equals(device.getAssignmentStatus())) {
			LOGGER.logMessage("Send Trigger on Group MS for operation = " + Constants.ApiType.UPDATEDEVICE
					+ ", User Name = " + device.getAssignedToUserName());
			notifyGroupMsQueue(device);
		}
		LOGGER.logMessage("Set Device Properties Successful :: deviceId = " + deviceId);
	}

	/**
	 * This method validates and gets Device properties.
	 * 
	 * @param deviceId
	 * 
	 * @return List<DeviceProperty>
	 */
	@Override
	public List<DeviceProperty> getDeviceProperties(String deviceId) {
		LOGGER.logMessage("Get Device Properties :: deviceId=" + deviceId);
		// Device Validation
		Device device = getDeviceForProperty(deviceId);
		return devicePropertyRepository.findById(device.getId());
	}

	/**
	 * @param oldDevicePropList
	 * @param toUpdateDevicePropList
	 * @param id
	 */
	private void filterAndDeleteDeviceProperties(List<DeviceProperty> oldDevicePropList,
			List<DeviceProperty> toUpdateDevicePropList, Long id) {
		// Filter the device properties for deleting
		for (DeviceProperty deviceProperty : toUpdateDevicePropList) {
			oldDevicePropList.remove(deviceProperty);
		}

		List<DeviceProperty> toDeleteDeviceProps = new ArrayList<>();
		for (DeviceProperty deviceProperty : oldDevicePropList) {
			toDeleteDeviceProps.add(deviceProperty);
		}

		// Deleting old properties
		if (!toDeleteDeviceProps.isEmpty()) {
			List<String> propertyNameList = new ArrayList<>();
			for (DeviceProperty deviceProperty : toDeleteDeviceProps) {
				propertyNameList.add(deviceProperty.getId().getPropertyName());
			}
			devicePropertyRepository.deleteByIdAndName(id, propertyNameList);
		}
	}

	/**
	 * Generates the list of device property
	 * 
	 * @param deviceProperties
	 * @param deviceId
	 * @return List<DeviceProperty>
	 */
	private List<DeviceProperty> generateUpdateDevicePropertiesList(SetDevicePropertiesRequest deviceProperties,
			Long id) {
		List<DevicePropertyDto> updatedDeviceProps = deviceProperties.getDeviceProperties();
		List<DeviceProperty> toUpdateDevicePropList = new ArrayList<>();
		for (DevicePropertyDto devicePropDto : updatedDeviceProps) {
			DeviceProperty deviceProperty = new DeviceProperty();
			deviceProperty.setId(new DevicePropertyId(id, devicePropDto.getName()));
			deviceProperty.setPropertyValue(devicePropDto.getValue());
			toUpdateDevicePropList.add(deviceProperty);
		}
		return toUpdateDevicePropList;
	}

	/**
	 * This method gets and validates the device for get/set property.
	 * 
	 * @param deviceId
	 * @return
	 */
	private Device getDeviceForProperty(String deviceId) {
		Device device = deviceServiceHelper.getDevice(deviceId, Boolean.FALSE);
		if (DeviceManagerUtil.checkNullObject(device)) {
			throw new DeviceManagerException(ErrorCode.RESOURCE_NOT_FOUND);
		}
		return device;
	}

	/**
	 * This method send trigger on JSM Queue when the Device Properties are changed.
	 * 
	 * @param deviceEntity
	 */
	private void notifyGroupMsQueue(Device deviceEntity) {
		List<String> userNames = new ArrayList<>();
		userNames.add(deviceEntity.getAssignedToUserName());
		interProcessCommunicationService.notifyGroupMs(userNames, Constants.ApiType.UPDATEDEVICE);
	}
}
