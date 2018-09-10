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

package com.accenture.avs.device.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Utility class used to get bean and handling of context
 * 
 * @author SUMIT
 * @since 1.0
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

	/** APPLICATION_CONTEXT */
	private static ApplicationContext APPLICATION_CONTEXT;

	/**
	 * Gets beans instance
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return APPLICATION_CONTEXT.getBean(clazz);
	}

	/**
	 * This method sets the application context.
	 * 
	 * @param applicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtils.doSetApplicationContext(applicationContext);
	}

	/**
	 * Method to get application context
	 * 
	 * @return ApplicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return APPLICATION_CONTEXT;
	}

	/**
	 * Set the app context
	 * 
	 * @param applicationContext
	 */
	private static void doSetApplicationContext(ApplicationContext applicationContext) {
		SpringBeanUtils.APPLICATION_CONTEXT = applicationContext;
	}
}