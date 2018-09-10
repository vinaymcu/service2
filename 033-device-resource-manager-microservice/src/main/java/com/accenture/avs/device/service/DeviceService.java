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

import com.accenture.avs.device.json.object.devicemanager.CreateDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.DeviceDetails;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceAuditLogsResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDevicePropertiesResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUserDevicesResponse;
import com.accenture.avs.device.json.object.devicemanager.RegisterDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.SetDevicePropertiesRequest;
import com.accenture.avs.device.json.object.devicemanager.UnAssignDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceRequest;

/**
 * The Interface DeviceService
 * 
 * @author Singh.Saurabh
 *
 */
public interface DeviceService {

	/**
	 * This method validates and creates a new Device
	 * 
	 * @param createDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return
	 */
	GenericResponse createDevice(CreateDeviceRequest createDeviceRequest, String lastUpdateUserName);

	/**
	 * This method validates and registers Device.
	 * 
	 * @param registerDeviceRequest
	 * @param lastUpdateUserName
	 * @param acidTransaction
	 * 
	 * @return
	 */
	GenericResponse registerDevice(RegisterDeviceRequest registerDeviceRequest, String lastUpdateUserName,
			Boolean acidTransaction);

	/**
	 * This method updates the device.
	 * 
	 * @param updateDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return GenericResponse
	 */
	GenericResponse updateDevice(UpdateDeviceRequest updateDeviceRequest, String lastUpdateUserName);

	/**
	 * Delete Device.
	 * 
	 * @param deviceId
	 * @return GenericResponse
	 */
	GenericResponse deleteDevice(String deviceId);

	/**
	 * This method validates and Unassigns/Unregister Device.
	 * 
	 * @param unassignDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return GenericResponse
	 */
	GenericResponse unassignDevice(UnAssignDeviceRequest unassignDeviceRequest, String lastUpdateUserName);

	/**
	 * This method validates and create/update/delete Device properties.
	 * 
	 * @param deviceId
	 * @param deviceProperties
	 * @return GenericResponse
	 */
	GenericResponse setDeviceProperties(String deviceId, SetDevicePropertiesRequest deviceProperties);

	/**
	 * This method validates and gets Device properties.
	 * 
	 * @param deviceId
	 * @return GetDevicePropertiesResponse
	 */
	GetDevicePropertiesResponse getDeviceProperties(String deviceId);

	/**
	 * This method gets the audit logs of all the updates done on the requested
	 * devices using offset and maxResult
	 * 
	 * @param deviceId
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * @return GetDeviceAuditLogsResponse
	 */
	GetDeviceAuditLogsResponse getDeviceAuditLogs(String deviceId, Integer startIndex, Integer maxResults, String sortBy, String sortOrder);

	/**
	 * Gets user devices response.
	 * 
	 * @param userNames
	 * @param limitDeviceFields
	 * @return GetUserDevicesResponse
	 */
	GetUserDevicesResponse getUserDevices(List<String> userNames, Boolean limitDeviceFields);

	/**
	 * This method returns the Device details for the provided deviceId.
	 * 
	 * @param deviceId
	 * @return
	 */
	DeviceDetails getDeviceDetails(String deviceId);

	/**
	 * 
	 * @param searchBy
	 * @param searchValue
	 * @param searchOperation
	 * @param sortBy
	 * @param sortOrder
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	GetDeviceListResponse getDeviceList(String searchBy, String searchValue, String searchOperation, String sortBy, String sortOrder,
			Integer startIndex, Integer pageSize);
}
