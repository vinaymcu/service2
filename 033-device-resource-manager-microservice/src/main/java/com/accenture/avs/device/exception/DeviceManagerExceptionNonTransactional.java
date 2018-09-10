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
 * This is a non transactional exception class.
 * 
 * @author Singh.Saurabh
 *
 */
public class DeviceManagerExceptionNonTransactional extends BaseException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Device exception
	 * 
	 * @param errorCode
	 * @param msg
	 * @param msgParams
	 */
	public DeviceManagerExceptionNonTransactional(final IErrorCode errorCode, final String msg, final Object[] msgParams) {
		super(errorCode, msg, msgParams);
	}

	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param msgParams
	 *            the msg params
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode, Object[] msgParams) {
		super(errorCode, msgParams);
	}

	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param msgParams
	 *            the msg params
	 * @param throwable
	 *            the throwable
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode, Object[] msgParams, Throwable throwable) {
		super(errorCode, msgParams, throwable);
	}

	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param throwable
	 *            the throwable
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
	
	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param msgParams
	 *            the msg params
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode, String msg) {
		super(errorCode, msg);
	}

	/**
	 * Instantiates a new Device exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param msgParams
	 *            the msg params
	 * @param throwable
	 *            the throwable
	 */
	public DeviceManagerExceptionNonTransactional(IErrorCode errorCode, String msg, Throwable throwable) {
		super(errorCode, msg, throwable);
	}
}
