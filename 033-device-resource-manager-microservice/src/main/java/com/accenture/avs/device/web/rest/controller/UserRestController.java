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

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.commons.lib.LoggerWrapper.BodyContentType;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUpdatedUsers;
import com.accenture.avs.device.json.object.devicemanager.GetUserResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVQEParameterUpdateStatusResponse;
import com.accenture.avs.device.json.object.devicemanager.SetUserRequest;
import com.accenture.avs.device.service.UserService;
import com.accenture.avs.device.tenant.TenantContext;
import com.accenture.avs.device.tenant.Tenants;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.validation.JsonSchema;

/**
 * The Class UserRestController.
 *
 * @author muhammad yunus
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserRestController {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(UserRestController.class);

	/** userService */
	@Autowired
	private UserService userService;

	/** deviceManagerValidator */
	@Autowired
	private DeviceManagerValidator deviceManagerValidator;

	/**
	 * This method validate request parameters and sends the request to service bean
	 * for further validation and create/update a user. If already user exist then
	 * same will be updated otherwise created in database.
	 *
	 * @param crmAccountId
	 * @param setUserRequest
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@PostMapping(value = Constants.RequestUrl.OPERATION_UPSERT_USER_URL, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> setUser(@PathVariable(Constants.PATH_PARAM_CRMACCOUNTID) String crmAccountId,
			@RequestBody SetUserRequest setUserRequest, HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "setUser");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(setUserRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(setUserRequest, JsonSchema.SETUSER.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = userService.setUser(setUserRequest, crmAccountId);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method validate request parameters and sends the request to service bean
	 * for further validation and delete user.
	 *
	 * @param userName
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@DeleteMapping(value = Constants.RequestUrl.OPERATION_DELETE_USER_URL, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> deleteUser(@PathVariable(Constants.PATH_PARAM_USERNAME) String userName,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "deleteUser");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = userService.deleteUser(userName);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This sends the request to service bean for validation and retrieve user.
	 *
	 * @param userName
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_GET_USER_URL, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetUserResponse> getUser(@PathVariable(Constants.PATH_PARAM_USERNAME) String userName,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getUser");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.READ);
		GetUserResponse response = userService.getUser(userName);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This sends the request to service bean for retrieving the VQE update status
	 *
	 * @param userName
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_GET_VQE_UPDATE_STATUS, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetVQEParameterUpdateStatusResponse> getVqeUpdateStatus(
			@RequestParam(value = Constants.REQ_PARAM_CRMACCOUNTIDS) String crmAccountIds,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getVqeUpdateStatus");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.READ);
		GetVQEParameterUpdateStatusResponse response = userService.getVQEUpdateStatus(crmAccountIds);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This sends the request to service bean for getting updated user.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param httpRequest
	 * @return
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_GET_UPDATED_USER_URL, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetUpdatedUsers> getUpdatedUser(
			@RequestParam(value = Constants.QUERY_PARAM_STARTDATE, required = true) Long startDate,
			@RequestParam(value = Constants.QUERY_PARAM_ENDDATE, required = true) Long endDate,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getUpdatedUser");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));

		TenantContext.setCurrentTenant(Tenants.READ);
		GetUpdatedUsers response = userService.getUpdatedUser(startDate, endDate);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}
}
