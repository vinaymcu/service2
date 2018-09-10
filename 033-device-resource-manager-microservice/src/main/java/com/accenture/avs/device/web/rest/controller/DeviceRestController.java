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
package com.accenture.avs.device.web.rest.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.commons.lib.LoggerWrapper.BodyContentType;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.device.json.object.devicemanager.CreateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.CreateDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.DeviceDetails;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceAuditLogsResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceModelListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDevicePropertiesResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUserDevicesResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVendorListResponse;
import com.accenture.avs.device.json.object.devicemanager.RegisterDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.SetDevicePropertiesRequest;
import com.accenture.avs.device.json.object.devicemanager.UnAssignDeviceRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceRequest;
import com.accenture.avs.device.json.object.swupgradecount.SoftwareUpgradeCountResponse;
import com.accenture.avs.device.service.DeviceModelService;
import com.accenture.avs.device.service.DeviceService;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.service.SoftwareUpgradeCountService;
import com.accenture.avs.device.tenant.TenantContext;
import com.accenture.avs.device.tenant.Tenants;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.validation.JsonSchema;

/**
 * The Class DeviceRestController.
 *
 * @author Sumit Kunmar Sahrma
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DeviceRestController {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceRestController.class);

	/** deviceService */
	@Autowired
	private DeviceService deviceService;
	
	/** deviceModelService */
	@Autowired
	private DeviceModelService deviceModelService;

	/** softwareUpgradeCountService */
	@Autowired
	private SoftwareUpgradeCountService softwareUpgradeCountService;
	
	/** deviceManagerValidator */
	@Autowired
	private DeviceManagerValidator deviceManagerValidator;
	
	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and create a new Device.
	 *
	 * @param createDeviceRequest
	 * @param lastUpdateUserName
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_CREATE_UPDATE_DEVICE_URL, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> createDevice(@RequestBody CreateDeviceRequest createDeviceRequest,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "createDevice");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(createDeviceRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(createDeviceRequest, JsonSchema.CREATEDEVICE.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceService.createDevice(createDeviceRequest, lastUpdateUserName);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation for register device.
	 * 
	 * @param registerDeviceRequest
	 * @param lastUpdateUserName
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_REGISTER_DEVICE_URL, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> registerDevice(@RequestBody RegisterDeviceRequest registerDeviceRequest,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			@RequestHeader(value = Constants.HeaderParams.ACID_TRANSACTION, required = false) Boolean acidTransaction,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "registerDevice");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(registerDeviceRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(registerDeviceRequest,
				JsonSchema.REGISTERDEVICE.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceService.registerDevice(registerDeviceRequest, lastUpdateUserName,
				acidTransaction);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation for Device Unassignment.
	 * 
	 * @param unassignDevice
	 * @param lastUpdateUserName
	 * @return
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_UNASSIGN_DEVICE_URL, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> unassignDevice(@RequestBody UnAssignDeviceRequest unassignDevice,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "unassignDevice");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(unassignDevice));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(unassignDevice, JsonSchema.UNASSIGNDEVICE.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceService.unassignDevice(unassignDevice, lastUpdateUserName);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation for creating/updating/deleting Device
	 * Properties.
	 * 
	 * @param deviceId
	 * @param deviceProperties
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_SET_GET_DEVICE_PROPERTIES_URL, method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> setDeviceProperties(
			@PathVariable(Constants.PATH_PARAM_DEVICE_ID) String deviceId,
			@RequestBody SetDevicePropertiesRequest deviceProperties, HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "setDeviceProperties");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(deviceProperties));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(deviceProperties, JsonSchema.DEVICEPROPERTIES.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceService.setDeviceProperties(deviceId, deviceProperties);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation for getting Device Properties.
	 * 
	 * @param deviceId
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GetDevicePropertiesResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_SET_GET_DEVICE_PROPERTIES_URL, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetDevicePropertiesResponse> getDeviceProperties(
			@PathVariable(Constants.PATH_PARAM_DEVICE_ID) String deviceId, HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getDeviceProperties");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.READ);
		GetDevicePropertiesResponse response = deviceService.getDeviceProperties(deviceId);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method sends the request to service bean to get the audit logs of
	 * all the updates done on the requested set top boxes using deviceId,
	 * offset and max result size
	 * 
	 * @param deviceId
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * 
	 * @return ResponseEntity<SetTopBoxAuditLogs>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_GET_DEVICE_AUDIT_LOGS_URL, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetDeviceAuditLogsResponse> getDeviceAuditLogs(
			@PathVariable(Constants.PATH_PARAM_DEVICE_ID) String deviceId,
			@RequestParam(value = Constants.PaginationParams.PARAM_START_INDEX, defaultValue = Constants.PaginationParams.DEFAULT_START_INDEX, required = false) Integer startIndex,
			@RequestParam(value = Constants.PaginationParams.PARAM_MAX_RESULTS, defaultValue = Constants.PaginationParams.DEFAULT_MAX_RESULTS, required = false) Integer maxResults,			
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_BY, defaultValue = Constants.PaginationParams.DEFAULT_SORT_BY_DEVICE_ID, required = false) String sortBy,
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_ORDER, defaultValue = Constants.PaginationParams.DEFAULT_SORT_ORDER, required = false) String sortOrder,
			HttpServletRequest httpRequest) {

		long sTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getDeviceAuditLogs");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		LOGGER.logMessage("deviceId:{}, startIndex:{}, maxResults:{}, sortBy:{}, sortOrder:{}", deviceId, startIndex, maxResults,sortBy, sortOrder);
		TenantContext.setCurrentTenant(Tenants.READ);
		GetDeviceAuditLogsResponse response = deviceService.getDeviceAuditLogs(deviceId,
				startIndex < 0 ? Integer.valueOf(Constants.PaginationParams.DEFAULT_START_INDEX) : startIndex,
				maxResults < 0 ? Integer.valueOf(Constants.PaginationParams.DEFAULT_MAX_RESULTS) : maxResults,sortBy, sortOrder);
		Long executionTime = System.currentTimeMillis() - sTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and updates the Device.
	 * 
	 * @param updateDeviceRequest
	 * @param lastUpdateUserName
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(method = RequestMethod.PUT, value = Constants.RequestUrl.OPERATION_CREATE_UPDATE_DEVICE_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> updateDevice(@RequestBody UpdateDeviceRequest updateDeviceRequest,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "updateDevice");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(updateDeviceRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(updateDeviceRequest, JsonSchema.UPDATEDEVICE.getFileName());
		GenericResponse response = deviceService.updateDevice(updateDeviceRequest, lastUpdateUserName);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * Delete device.
	 * 
	 * @param deviceId
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_REMOVE_DEVICE_URL, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> deleteDevice(@PathVariable(Constants.PATH_PARAM_DEVICE_ID) String deviceId,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			HttpServletRequest httpRequest) {
		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "deleteDevice");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(deviceId));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		GenericResponse response = deviceService.deleteDevice(deviceId);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());
		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * Gets user devices.
	 * 
	 * @param userName
	 * @param limitDeviceFields
	 * @param httpRequest
	 * @return ResponseEntity<GetUserDevicesResponse>
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.RequestUrl.OPERATION_GET_USER_DEVICES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetUserDevicesResponse> getUserDevices(
			@RequestParam(value = Constants.REQ_PARAM_USER_NAME, required = true) List<String> userName,
			@RequestParam(value = Constants.REQ_PARAM_LIMIT_DEVICE_FIELDS, required = false, defaultValue = "true") Boolean limitDeviceFields,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getUserDevices");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		LOGGER.logMessage("userName:{}, limitDeviceFields:{} ", userName, limitDeviceFields);

		TenantContext.setCurrentTenant(Tenants.READ);
		GetUserDevicesResponse response = deviceService.getUserDevices(userName, limitDeviceFields);
		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());
		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and to get device details.
	 * 
	 * @param deviceId
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_GET_DEVICE_DETAILS_URL, method = RequestMethod.GET)
	public ResponseEntity<DeviceDetails> getDeviceDetails(@PathVariable(Constants.PATH_PARAM_DEVICE_ID) String deviceId,
			HttpServletRequest httpRequest) {
		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getDeviceDetails");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.READ);
		DeviceDetails response = deviceService.getDeviceDetails(deviceId);

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param searchBy
	 * @param searchValue
	 * @param searchOperation
	 * @param sortBy
	 * @param startIndex
	 * @param maxResults
	 * @return
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_GET_DEVICE_LIST_URL, method = RequestMethod.GET)
	public ResponseEntity<GetDeviceListResponse> getDeviceList(
			@RequestParam(value = Constants.REQ_PARAM_SEARCH_BY, required = false) String searchBy,
			@RequestParam(value = Constants.REQ_PARAM_SEARCH_VALUE, required = false) String searchValue,
			@RequestParam(value = Constants.REQ_PARAM_SEARCH_OPERATION, required = false) String searchOperation,
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_BY, defaultValue = Constants.PaginationParams.DEFAULT_SORT_BY_DEVICE_ID, required = false) String sortBy,
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_ORDER, defaultValue = Constants.PaginationParams.DEFAULT_SORT_ORDER, required = false) String sortOrder,
			@RequestParam(value = Constants.PaginationParams.PARAM_START_INDEX, defaultValue = Constants.PaginationParams.DEFAULT_START_INDEX, required = false) Integer startIndex,
			@RequestParam(value = Constants.PaginationParams.PARAM_MAX_RESULTS, defaultValue = Constants.PaginationParams.DEFAULT_MAX_RESULTS, required = false) Integer maxResults,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getDeviceList");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		LOGGER.logMessage("searchBy:{}, searchValue:{}, searchOperation:{} ", searchBy, searchValue, searchOperation);
		LOGGER.logMessage("sortBy:{}, startIndex:{}, pageSize:{} ", sortBy, startIndex, maxResults);

		GetDeviceListResponse response = deviceService.getDeviceList(searchBy, searchValue, searchOperation, sortBy, sortOrder,
				startIndex < 0 ? Integer.parseInt(Constants.PaginationParams.DEFAULT_START_INDEX) : startIndex,
				maxResults < 0 ? Integer.parseInt(Constants.PaginationParams.DEFAULT_MAX_RESULTS) : maxResults);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());
		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
		return ResponseEntity.ok(response);
	}

	/**
	 * This interface supposed to get count of software versions.
	 * 
	 * @param swVersions
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_GET_SW_UPGRADE_COUNT_URL)
	public ResponseEntity<SoftwareUpgradeCountResponse> getSoftwareUpgradeCount(
			@RequestParam(value = Constants.REQ_PARAM_SW_VERSIONS, required = true) String swVersions,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdateUserName,
			HttpServletRequest httpRequest) {
		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getSoftwareUpgradeCount");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		LOGGER.logMessage("swVersions:{}", swVersions);

		SoftwareUpgradeCountResponse response = softwareUpgradeCountService.getSoftwareUpgradeCount(swVersions);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);

	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and create a new Device Model.
	 *
	 * @param deviceModel
	 * @param lastUpdatedUserName
	 * @param lastUpdatedInterface
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_CREATE_UPDATE_DEVICE_MODEL_URL, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> createDeviceModel(@RequestBody CreateDeviceModelRequest deviceModel,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdatedUserName,
			@RequestHeader(value = Constants.HeaderParams.LAST_UPDATED_INTERFACE, defaultValue = Constants.DEFAULT_LASTINTERFACE) String lastUpdatedInterface,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "createDeviceModel");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(deviceModel));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(deviceModel, JsonSchema.CREATEDEVICEMODEL.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceModelService.createDeviceModel(deviceModel, lastUpdatedUserName,
				lastUpdatedInterface);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and update a Device Model.
	 *
	 * @param deviceModel
	 * @param lastUpdatedUserName
	 * @param lastUpdatedInterface
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_CREATE_UPDATE_DEVICE_MODEL_URL, method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> updateDeviceModel(@RequestBody UpdateDeviceModelRequest deviceModel,
			@RequestHeader(value = Constants.HeaderParams.X_AVS_USER_ID, defaultValue = Constants.DEFAULT_LASTUPDATEDUSERNAME) String lastUpdatedUserName,
			@RequestHeader(value = Constants.HeaderParams.LAST_UPDATED_INTERFACE, defaultValue = Constants.DEFAULT_LASTINTERFACE) String lastUpdatedInterface,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "updateDeviceModel");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(deviceModel));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(deviceModel, JsonSchema.UPDATEDEVICEMODEL.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceModelService.updateDeviceModel(deviceModel, lastUpdatedUserName,
				lastUpdatedInterface);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and delete model.
	 *
	 * @param deviceModel
	 * @param vendor
	 * @param httpRequest
	 * @return ResponseEntity<GenericResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_DELETE_DEVICE_MODEL_URL, method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> deleteDeviceModel(
			@PathVariable(Constants.REQ_PARAM_DEVICE_MODEL) String deviceModel,
			@PathVariable(Constants.REQ_PARAM_VENDOR) String vendor, HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "deleteDeviceModel");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = deviceModelService.deleteDeviceModel(deviceModel, vendor);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method get device model list and specific model details.
	 * 
	 * @param status
	 * @param deviceModel
	 * @param vendor
	 * @param httpRequest
	 * @return ResponseEntity<GetDeviceModelListResponse>
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_LIST_MODEL_URL, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetDeviceModelListResponse> getDeviceModelList(
			@RequestParam(value = Constants.REQ_PARAM_STATUS, required = false) String status,
			@RequestParam(value = Constants.REQ_PARAM_DEVICE_MODEL, required = false) String deviceModel,
			@RequestParam(value = Constants.REQ_PARAM_VENDOR, required = false) String vendor,
			@RequestParam(value = Constants.PaginationParams.PARAM_START_INDEX, defaultValue=Constants.PaginationParams.DEFAULT_START_INDEX, required = false) Integer startIndex,
			@RequestParam(value = Constants.PaginationParams.PARAM_MAX_RESULTS, defaultValue=Constants.PaginationParams.DEFAULT_MAX_RESULTS, required = false) Integer maxResults,
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_BY, defaultValue=Constants.PaginationParams.DEFAULT_SORT_BY_DEVICE_MODEL, required = false) String sortBy,
			@RequestParam(value = Constants.PaginationParams.PARAM_SORT_ORDER, defaultValue=Constants.PaginationParams.DEFAULT_SORT_ORDER, required = false) String sortOrder,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getDeviceModelList");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		
		// TenantContext.setCurrentTenant(Tenants.WRITE);
		
		GetDeviceModelListResponse response = deviceModelService.getDeviceModelList(status, deviceModel, vendor,
				startIndex < 0 ? Integer.valueOf(Constants.PaginationParams.DEFAULT_START_INDEX) : startIndex,
				maxResults < 0 ? Integer.valueOf(Constants.PaginationParams.DEFAULT_MAX_RESULTS) : maxResults,
				sortBy,
				sortOrder);

		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * This method get vendor list.
	 * 
	 * @return ResponseEntity<GetVendorListResponse>
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_GET_VENDOR_LIST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetVendorListResponse> getVendorList(
			HttpServletRequest httpRequest) {
		
		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getVendorList");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		GetVendorListResponse response = deviceModelService.getVendorList();
		
		Long executionTime = System.currentTimeMillis() - startTime;
		response.setExecutionTime(executionTime.intValue());

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Triggers mass resource calculation
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value = Constants.RequestUrl.OPERATION_MASS_RECALCULATION, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> massRecalculation(HttpServletRequest httpRequest) {
		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "massRecalculation");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		
		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = massRecalculationService.massRecalculation(false);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
		
	}
	
}
