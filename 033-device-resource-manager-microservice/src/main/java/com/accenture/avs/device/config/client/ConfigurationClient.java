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

package com.accenture.avs.device.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.device.config.ConfigCacheManager;
import com.accenture.avs.device.config.ConfigurationManager;
import com.accenture.avs.device.config.SystemMessage;
import com.accenture.avs.device.config.SystemsParameter;
import com.accenture.avs.device.config.model.BandwidthAndVQEProperty;
import com.accenture.avs.device.config.model.BandwidthProfile;
import com.accenture.avs.device.config.model.DeviceResourceConfigProperty;
import com.accenture.avs.device.config.model.MDCServerProperty;
import com.accenture.avs.device.config.model.QoESettingsProperty;
import com.accenture.avs.device.config.model.VTP;

/**
 * The Configuration client class for interacting with ConfigurationManager to
 * read system message and parameters.
 * 
 * @author Singh.Saurabh
 */
@Component
public class ConfigurationClient {

	/** ConfigurationManager instance */
	@Autowired
	private ConfigurationManager manager;

	/** ConfigurationManager instance */
	@Autowired
	private ConfigCacheManager cacheManager;

	/**
	 * Method to find system message based on given language and code
	 * 
	 * @param language
	 * @param msgKey
	 * 
	 * @return SystemMessage
	 */
	public SystemMessage findSystemMessage(String language, String msgKey) {
		return manager.findSystemMessage(language, msgKey);
	}

	/**
	 * Method to find system parameter based on given param name.
	 * 
	 * @param paramName
	 * 
	 * @return SystemParameter
	 */
	public SystemsParameter findSystemsParameter(String paramName) {
		return manager.findSystemsParameter(paramName);
	}

	/**
	 * This method returns parameter value based on parameter key.
	 */
	public String getSystemParameterValue(String sysParameterKey) {
		SystemsParameter sysParamer = findSystemsParameter(sysParameterKey);
		return sysParamer.getParamValue();
	}


	/**
	 * Find the VTP (Vedio Type Profile) object based on vtpName.
	 * 
	 * @param vtpName
	 * @return VTP
	 */
	public VTP findVideoTypeProfile(String vtpName) {
		return cacheManager.findVideoTypeProfile(vtpName);
	}

	/**
	 * Find the BWP (Bandwidth Profile) object based on bwName
	 * 
	 * @param bwName
	 * @return
	 */
	public BandwidthProfile findBandwidthProfile(String bwName) {
		return cacheManager.findBandwidthProfile(bwName);
	}

	/**
	 * gets useVQEBandwidth flag and MDCBandwidth.
	 * 
	 * @return
	 */
	public BandwidthAndVQEProperty getGlobalVqeAndMdcBandwidthFlag() {
		return manager.getGlobalVQEBandwidthConfig();
	}

	/**
	 * gets MMDDF server properties.
	 * 
	 * @return
	 */
	public MDCServerProperty getMdcServerResources() {
		return manager.getMMDDFResources();
	}

	/**
	 * gets DeviceResourceConfig properties as batchSize and Parallel execution
	 * threads.
	 * 
	 * @return MassCalculationProperty
	 */
	public DeviceResourceConfigProperty getDrmResources() {
		return manager.getDrmResources();
	}
	
	
	public QoESettingsProperty getQoESettingsResources() {
		return manager.getQoESettingsResources();
	}
}
