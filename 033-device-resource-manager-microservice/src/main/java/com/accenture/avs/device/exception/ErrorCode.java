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
package com.accenture.avs.device.exception;

/**
 * The Enum ErrorCode
 * 
 * @author Singh.Saurabh
 */
public enum ErrorCode implements IErrorCode {

	/** GENERIC_ERROR. */
	GENERIC_ERROR("ERROR_BE_ACTION_300_GENERIC_ERROR"),

	/** RESOURCE_NOT_FOUND */
	RESOURCE_NOT_FOUND("ERROR_BE_ACTION_3304_RESOURCE_NOT_FOUND"),

	/** BAD_REQUEST */
	BAD_REQUEST("ERROR_BE_ACTION_3305_BAD_REQUEST"),
	
	/** MISSING_PARAMETER */
	MISSING_PARAMETER("ERROR_BE_ACTION_3000_MISSING_PARAMETER"),
	
	/** INVALID_PARAM */
	INVALID_PARAM("ERROR_BE_ACTION_3019_INVALID_PARAMETER"),

	/** JSON_PARSING_FAILED */
	JSON_PARSING_FAILED("ERROR_BE_ACTION_3258_JSON_PARSING_FAILED"),

	/** DEVICE_ALREADY_EXISTS */
	DEVICE_ALREADY_EXISTS("ERROR_BE_ACTION_9000_DEVICE_ALREADY_EXISTS"),

	/** INVALID_MODEL */
	INVALID_MODEL("ERROR_BE_ACTION_9001_INVALID_MODEL"),

	/** DEVICE_NOT_FOUND */
	DEVICE_NOT_FOUND("ERROR_BE_ACTION_9002_DEVICE_NOT_FOUND"),

	/** DRM_ID_MUST_BE_UNIQUE */
	DRM_ID_MUST_BE_UNIQUE("ERROR_BE_ACTION_9007_DRM_ID_MUST_BE_UNIQUE"),

	/** TRIPLET_MUST_BE_UNIQUE */
	TRIPLET_MUST_BE_UNIQUE("ERROR_BE_ACTION_9008_TRIPLET_MUST_BE_UNIQUE"),

	/** DEVICE_MODEL_ACTIVE */
	DEVICE_MODEL_ACTIVE("ERROR_BE_ACTION_9009_DEVICE_MODEL_ACTIVE"),

	/** DEVICE_ASSIGNED_CANNOT_UPDATE_MODEL */
	DEVICE_ASSIGNED_CANNOT_UPDATE_MODEL("ERROR_BE_ACTION_9013_DEVICE_ASSIGNED_CANNOT_UPDATE_MODEL"),

	/** DEVICE_MODEL_UPDATE_NOT_ALLOWED */
	DEVICE_MODEL_UPDATE_NOT_ALLOWED("ERROR_BE_ACTION_9014_DEVICE_MODEL_NOT_UPDATE_ALLOWED"),

	/** DEVICE_NOT_ASSIGNED_FOR_USER */
	DEVICE_NOT_ASSIGNED_FOR_USER("ERROR_BE_ACTION_9015_DEVICE_NOT_ASSIGNED_FOR_USER"),

	/** DUPLICATE_DEVICE_NAME */
	DUPLICATE_DEVICE_NAME("ERROR_BE_ACTION_9016_DUPLICATE_DEVICE_NAME"),
	
	/** MAX_IPTV_DEVICE_LIMIT_REACHED */
	MAX_IPTV_DEVICE_LIMIT_REACHED("ERROR_BE_ACTION_9017_MAX_IPTV_DEVICE_LIMIT_REACHED"),

	/** DEVICE_SHOULD_BE_UNASSIGNED */
	DEVICE_SHOULD_BE_UNASSIGNED("ERROR_BE_ACTION_9038_DEVICE_SHOULD_BE_UNASSIGNED"),
	
	/** USER_DOES_NOT_EXIST */
	USER_DOES_NOT_EXIST("ERROR_BE_ACTION_9018_USER_DOES_NOT_EXIST"),
	
	/** DUPLICATE_DEVICE_MODEL */
	DUPLICATE_DEVICE_MODEL("ERROR_BE_ACTION_9039_DULICATE_DEVICE_MODEL"),
	
	/** MAX_IPTV_DEVICE_NOT_LESS_ASSINGED_DEVICE */
	MAX_IPTV_DEVICE_NOT_LESS_ASSINGED_DEVICE("ERROR_BE_ACTION_9040_MAX_IPTV_DEVICE_NOT_LESS_ASSINGED_DEVICE");
	
	/** The error code. */
	private String errorCode;

	/**
	 * Instantiates a new error code.
	 *
	 * @param errorCode
	 *            the error code
	 */
	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.accenture.avs.common.exception.IErrorCode#getCode()
	 */
	@Override
	public String getCode() {
		return this.errorCode;
	}

}
