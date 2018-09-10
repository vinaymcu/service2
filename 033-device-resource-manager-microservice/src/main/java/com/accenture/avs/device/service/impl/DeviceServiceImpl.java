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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceAudit;
import com.accenture.avs.device.entity.DeviceProperty;
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
import com.accenture.avs.device.service.DeviceAuditLogsService;
import com.accenture.avs.device.service.DeviceDmlService;
import com.accenture.avs.device.service.DevicePropertiesService;
import com.accenture.avs.device.service.DeviceService;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.service.UserDevicesService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * The Class DeviceServiceImpl.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceServiceImpl.class);

	/** deviceDmlService */
	@Autowired
	private DeviceDmlService deviceDmlService;

	/** devicePropertiesService */
	@Autowired
	private DevicePropertiesService devicePropertiesService;

	/** userDevicesService */
	@Autowired
	private UserDevicesService userDevicesService;

	/** stbAuditLogsService */
	@Autowired
	private DeviceAuditLogsService deviceAuditLogsService;

	/** deviceServiceHelper */
	@Autowired
	private DeviceServiceHelper deviceServiceHelper;
	
	/** interProcessCommunicationService */
	@Autowired
	private InterProcessCommunicationService interProcessCommunicationService;
	
	/**
	 * This method validates and creates a new Device
	 * 
	 * @param createDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return
	 */
	@Override
	public GenericResponse createDevice(CreateDeviceRequest createDeviceRequest, String lastUpdateUserName) {
		long startTime = System.currentTimeMillis();
		deviceDmlService.createDevice(createDeviceRequest.getDevice(), lastUpdateUserName);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method validates and registers Device
	 * 
	 * @param registerDeviceRequest
	 * @param lastUpdateUserName
	 * @param acidTransaction
	 * 
	 * @return GenericResponse
	 */
	@Override
	public GenericResponse registerDevice(RegisterDeviceRequest registerDeviceRequest, String lastUpdateUserName,
			Boolean acidTransaction) {
		long startTime = System.currentTimeMillis();

		String userNameForPushNotification = deviceDmlService
				.registerDevice(registerDeviceRequest.getDeviceRegistration(), lastUpdateUserName, acidTransaction);

		// Notify Push Message MS
		if (!DeviceManagerUtil.checkNullObject(userNameForPushNotification)) {
			interProcessCommunicationService.notifyPushMessageMs(registerDeviceRequest.getDeviceRegistration().getDeviceId(), userNameForPushNotification,
					Constants.Trigger.T_SUBSCRIBER_INFO);
		}

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method validates and Unassigns/Unregister Device.
	 * 
	 * @param unassignDeviceRequest
	 * @param lastUpdateUserName
	 * 
	 * @return GenericResponse
	 */
	@Override
	public GenericResponse unassignDevice(UnAssignDeviceRequest unassignDeviceRequest, String lastUpdateUserName) {
		long startTime = System.currentTimeMillis();

		// Unassign Device
		String userNameForPushNotification = deviceDmlService.unAssignDevice(unassignDeviceRequest, lastUpdateUserName);

		// Notify Push Message MS
		if (!DeviceManagerUtil.checkNullObject(userNameForPushNotification)) {
			interProcessCommunicationService.notifyPushMessageMs(null, userNameForPushNotification,
					Constants.Trigger.T_SUBSCRIBER_INFO);
		}
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method validates and create/update/delete Device properties.
	 * 
	 * @param deviceId
	 * @param deviceProperties
	 * 
	 * @return SetDevicePropertyResponse
	 */
	@Override
	public GenericResponse setDeviceProperties(String deviceId, SetDevicePropertiesRequest deviceProperties) {
		long startTime = System.currentTimeMillis();
		devicePropertiesService.setDeviceProperties(deviceId, deviceProperties);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method validates and gets Device properties.
	 * 
	 * @param extIpAddress
	 * @param authenticationCookie
	 * 
	 * @return STBPropertiesResponse
	 */
	@Override

	public GetDevicePropertiesResponse getDeviceProperties(String deviceId) {
		long startTime = System.currentTimeMillis();
		List<DeviceProperty> deviceProperties = devicePropertiesService.getDeviceProperties(deviceId);
		Long executionTime = System.currentTimeMillis() - startTime;
		GetDevicePropertiesResponse response = RequestResponseGenerator.getDevicePropertiesResponse(deviceProperties,
				executionTime);
		LOGGER.logMethodEnd(executionTime);
		return response;
	}

	/**
	 * This method gets the audit logs of all the updates done on the requested
	 * devices using offset and max results.
	 * 
	 * @param deviceId
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * 
	 * @return GetDeviceAuditLogsResponse
	 */
	@Override
	public GetDeviceAuditLogsResponse getDeviceAuditLogs(String deviceId, Integer startIndex, Integer maxResults, String sortBy, String sortOrder) {
		long methodStartTime = System.currentTimeMillis();
		List<DeviceAudit> deviceAuditList = deviceAuditLogsService.getDeviceAuditLogs(deviceId, startIndex, maxResults,sortBy,sortOrder);
		Long executionTime = System.currentTimeMillis() - methodStartTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getDeviceAuditLogsResponse(deviceAuditList);
	}

	/**
	 * This method updates the device.
	 * 
	 * @param updateDeviceRequest
	 * @param lastUpdateUserName
	 * @return GenericResponse
	 */
	@Override
	public GenericResponse updateDevice(UpdateDeviceRequest updateDeviceRequest, String lastUpdateUserName) {
		long startTime = System.currentTimeMillis();

		String userNameForPushNotification = deviceDmlService.updateDevice(updateDeviceRequest.getDevice(),
				lastUpdateUserName);

		// Notify Push Message MS
		if (!DeviceManagerUtil.checkNullObject(userNameForPushNotification)) {
			interProcessCommunicationService.notifyPushMessageMs(updateDeviceRequest.getDevice().getDeviceId(), userNameForPushNotification,
					Constants.Trigger.T_SUBSCRIBER_INFO);
		}

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * Delete device.
	 * 
	 * @param deviceDelete
	 * @return GenericResponse
	 */
	@Override
	public GenericResponse deleteDevice(String deviceId) {
		long startTime = System.currentTimeMillis();
		deviceDmlService.deleteDevice(deviceId);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * Gets user devices response.
	 * 
	 * @param userNames
	 * @param limitDeviceFields
	 * @return GetUserDevicesResponse
	 */
	@Override
	public GetUserDevicesResponse getUserDevices(List<String> userName, Boolean limitDeviceFields) {
		long startTime = System.currentTimeMillis();
		GetUserDevicesResponse response = userDevicesService.getUserDevices(userName, limitDeviceFields);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return response;
	}

	/**
	 * This method returns the Device details for the provided deviceId.
	 * 
	 * @param deviceId
	 * @return
	 */
	@Override
	public DeviceDetails getDeviceDetails(String deviceId) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("GetDeviceDetails for deviceId={}", deviceId);
		Device deviceEntity = deviceServiceHelper.getDevice(deviceId, Boolean.TRUE);
		LOGGER.logMessage("GetDeviceDetails for deviceId={} successful", deviceId);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getDeviceDetailsResponse(deviceEntity, executionTime);
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
	 * @return
	 */
	@Override
	public GetDeviceListResponse getDeviceList(String searchBy, String searchValue, String searchOperation,
			String sortBy, String sortOrder, Integer startIndex, Integer pageSize) {
		long startTime = System.currentTimeMillis();
		GetDeviceListResponse response = deviceServiceHelper.getDeviceList(searchBy, searchValue, searchOperation, sortBy, sortOrder, startIndex, pageSize);	
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return response;
	}
}
