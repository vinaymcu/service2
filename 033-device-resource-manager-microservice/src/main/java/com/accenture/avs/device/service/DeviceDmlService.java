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

import com.accenture.avs.device.json.object.devicemanager.DeviceDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceRegistration;
import com.accenture.avs.device.json.object.devicemanager.UnAssignDeviceRequest;

/**
 * Interface for DeviceDmlServiceImpl
 * 
 * @author Singh.Saurabh
 *
 */
public interface DeviceDmlService {

	/**
	 * This method validates and creates Device
	 * 
	 * @param deviceDto
	 * @param lastUpdateUserName
	 * 
	 */
	void createDevice(DeviceDto deviceDto, String lastUpdateUserName);
	
	/**
	 * This method validates and updates the device.
	 * 
	 * @param deviceDto
	 * @param lastUpdateUserName
	 * 
	 * @return userNameForPushNotification 
	 */
	String updateDevice(DeviceDto deviceDto, String lastUpdateUserName);
	
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
	String registerDevice(DeviceRegistration deviceRegistration, String lastUpdateUserName, Boolean acidTransaction);
	
	/**
	 * This method validates and Unassigns/Unregisters Device for the intended Subscriber.
	 * 
	 * @param unAssignDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @param userNameForPushNotification
	 */
	String unAssignDevice(UnAssignDeviceRequest unAssignDeviceRequest, String lastUpdateUserName);
	
	/**
	 * This method validates and deletes the Device.
	 * 
	 * @param deviceId
	 */
	void deleteDevice(String deviceId);
}
