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
package com.accenture.avs.device.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author surendra.kumar
 *
 */
@Component
public class ConfigurationProperties {

	@Value("${spring.datasource.read.jndi-name}")
	private String readJndiName;

	@Value("${spring.datasource.write.jndi-name}")
	private String writeJndiName;

	@Value("${resource.name.general.parameters}")
	private String systemParamResourceName;	
	
	@Value("${resource.name.general.messages}")
	private String systemMsgResourceName;
	
	/**
	 * destinationTopicName is used to set topic-name (for VTP & BWP) from
	 * CacheManager of configuration MS
	 */
	private String destinationTopicName;
	
	public String getReadJndiName() {
		return readJndiName;
	}

	public void setReadJndiName(String readJndiName) {
		this.readJndiName = readJndiName;
	}

	public String getWriteJndiName() {
		return writeJndiName;
	}

	public void setWriteJndiName(String writeJndiName) {
		this.writeJndiName = writeJndiName;
	}

	/**
	 * @return the destinationTopicName
	 */
	public String getDestinationTopicName() {
		return destinationTopicName;
	}

	/**
	 * @param destinationTopicName the destinationTopicName to set
	 */
	public void setDestinationTopicName(String destinationTopicName) {
		this.destinationTopicName = destinationTopicName;
	}

	/**
	 * @return the systemParamResourceName
	 */
	public String getSystemParamResourceName() {
		return systemParamResourceName;
	}

	/**
	 * @param systemParamResourceName the systemParamResourceName to set
	 */
	public void setSystemParamResourceName(String systemParamResourceName) {
		this.systemParamResourceName = systemParamResourceName;
	}

	/**
	 * @return the systemMsgResourceName
	 */
	public String getSystemMsgResourceName() {
		return systemMsgResourceName;
	}

	/**
	 * @param systemMsgResourceName the systemMsgResourceName to set
	 */
	public void setSystemMsgResourceName(String systemMsgResourceName) {
		this.systemMsgResourceName = systemMsgResourceName;
	}
	
}
