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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.commons.lib.LoggerWrapper.BodyContentType;
import com.accenture.avs.device.json.object.devicemanager.CreateOverrideVQEGroupsConditionsRequest;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetOverrideVQEGroupsConditionsResponse;
import com.accenture.avs.device.json.object.vqe.CreateVQEGroupsRequest;
import com.accenture.avs.device.json.object.vqe.GroupAttributesDto;
import com.accenture.avs.device.service.VqeService;
import com.accenture.avs.device.tenant.TenantContext;
import com.accenture.avs.device.tenant.Tenants;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.validation.JsonSchema;

/**
 * The Class VqeRestController.
 *
 * @author kumar rajesh
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
public class VqeRestController {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(VqeRestController.class);
	
	/** deviceManagerValidator */
	@Autowired
	private DeviceManagerValidator deviceManagerValidator;
	
	/** vqeService */
	@Autowired
	private VqeService vqeService;
	
	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and create VQE Group.
	 * 
	 * @param createVQEGroupsRequest
	 * @param httpRequest
	 * @return GenericResponse
	 */
	@PostMapping(value = Constants.RequestUrl.OPERATION_CREATE_VQE_GROUP_URL, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> createVQEGroup(@RequestBody CreateVQEGroupsRequest createVQEGroupsRequest,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "createVQEGroup");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(createVQEGroupsRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(createVQEGroupsRequest, JsonSchema.CREATEVQEGROUP.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = vqeService.createVQEGroup(createVQEGroupsRequest);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and create VQE Group.
	 * 
	 * @param createVQEGroupsRequest
	 * @param httpRequest
	 * @return
	 */
	@PostMapping(value = Constants.RequestUrl.OPERATION_CREATE_VQE_GROUPS_DEFAULT_ATTRIBUTES, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> createDefaultGroupAttributes(@RequestBody GroupAttributesDto groupAttributes,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "defaultAttributes");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(groupAttributes));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(groupAttributes, JsonSchema.CREATEVQEGROUPSDEFAULTATTRIBUTESREQUEST.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = vqeService.createVqeGroupsDefaultAttributes(groupAttributes);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * This method validate request parameters and sends the request to service
	 * bean for further validation and create the Override VQEGroups Conditions.
	 *
	 * @param createOverrideVQEGroupsConditionsRequest
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GenericResponse>
	 */
	@PostMapping(value = Constants.RequestUrl.OPERATION_OVERRIDE_VQEGROUPS_CONDITIONS, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> createOverrideVQEGroupsConditions(
			@RequestBody CreateOverrideVQEGroupsConditionsRequest createOverrideVQEGroupsConditionsRequest,
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "createOverrideVQEGroupsConditions");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		try {
			LOGGER.logRequestBody(JsonUtils.writeAsJsonString(createOverrideVQEGroupsConditionsRequest));
		} catch (IOException e) {
			LOGGER.logError(e);
		}

		deviceManagerValidator.validateRequestParameters(createOverrideVQEGroupsConditionsRequest,
				JsonSchema.CREATEOVERRIDEVQEGROUPSCONDITIONS.getFileName());

		TenantContext.setCurrentTenant(Tenants.WRITE);
		GenericResponse response = vqeService
				.createOverrideVQEGroupsConditions(createOverrideVQEGroupsConditionsRequest);

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}

	/**
	 * This method sends the request to service
	 * bean for getting the Override VQEGroups Conditions.
	 *
	 * @param httpRequest
	 * 
	 * @return ResponseEntity<GetOverrideVQEGroupsConditionsResponse>
	 */
	@GetMapping(value = Constants.RequestUrl.OPERATION_OVERRIDE_VQEGROUPS_CONDITIONS, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GetOverrideVQEGroupsConditionsResponse> getOverrideVQEGroupsConditions(
			HttpServletRequest httpRequest) {

		long startTime = System.currentTimeMillis();
		ThreadContext.put(Constants.LoggingParams.API, "getOverrideVQEGroupsConditions");
		LOGGER.logStartApi();
		LOGGER.logRequestHeader(DeviceManagerUtil.getHeaderMapFormat(httpRequest));
		TenantContext.setCurrentTenant(Tenants.READ);
		GetOverrideVQEGroupsConditionsResponse response = vqeService.getOverrideVQEGroupsConditions();

		Long executionTime = System.currentTimeMillis() - startTime;

		LOGGER.logResponseBody(response, BodyContentType.JSON);
		LOGGER.logEndApi(httpRequest.getHeader(Constants.LoggingParams.USER_AGENT_HEADER_NAME),
				httpRequest.getParameterMap(), Integer.toString(HttpStatus.OK.value()),
				Constants.Status.SUCCESS_RESULT_CODE, Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);

		return ResponseEntity.ok(response);
	}
	
}
