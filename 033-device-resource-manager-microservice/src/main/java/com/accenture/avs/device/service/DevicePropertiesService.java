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

package com.accenture.avs.device.service;

import java.util.List;

import com.accenture.avs.device.entity.DeviceProperty;
import com.accenture.avs.device.json.object.devicemanager.SetDevicePropertiesRequest;

/**
 * Interface for DevicePropertiesServiceImpl
 * 
 * @author Singh.Saurabh
 *
 */
public interface DevicePropertiesService {

	/**
	 * This method validates and create/update/delete Device properties.
	 * 
	 * @param deviceProperties
	 * @param extIpAddress
	 * @param macAddress
	 * @param subscriberId
	 * 
	 * @return List<StbProperty>
	 * 
	 */
	void setDeviceProperties(String deviceId, SetDevicePropertiesRequest deviceProperties);

	/**
	 * This method validates and gets Device properties.
	 * 
	 * @param deviceId
	 * @return List<DeviceProperty>
	 * 
	 */
	List<DeviceProperty> getDeviceProperties(String deviceId);

}