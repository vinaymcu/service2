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
package com.accenture.avs.device.jms.topic.listener;

import java.io.IOException;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.ConfigurationManager;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;

/**
 * @author kumar.rajesh
 *
 */
@Component
public class ConsumerMessageListener implements MessageListener {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(ConsumerMessageListener.class);

	/** configurationManager */
	@Autowired
	private ConfigurationManager configurationManager;
	
	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;

	/** configurationClient */
	@Autowired
	private ConfigurationClient configurationClient;

	/** Config Name as VTP (Video Type Profile) */
	private static final String RES_PREFIX = "res_";

	/** Config Name as VTP (Video Type Profile) */
	private static final String APP_PREFIX = "app_iptv-app_stb-app_";

	/** SERVER_RESOURCE for MMDDF */
	private static final String SERVER_RESOURCE = RES_PREFIX.concat("servers");

	/** VQE_RESOURCE for app_iptv-app_stb-app_useglobalvqebwflag */
	private static final String VQE_RESOURCE = APP_PREFIX.concat("useglobalvqebwflag");

	/** SERVER_DEVICE_RESOURCE_CONFIG for res_deviceResourceConfig */
	private static final String SERVER_DEVICE_RESOURCE_CONFIG = RES_PREFIX.concat("deviceResourceConfig");

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		LOGGER.logMessage("Start: Receive JMS message for MDCBandwidth/VQE from Topic.");
		AvsMessage avsMessage = null;

		try {
			TextMessage textMessage = (TextMessage) message;
			String mes = textMessage.getText();
			LOGGER.logMessage("Receive JMS message as {} ", mes);

			if(mes != null) {
				
				avsMessage = JsonUtils.parseJson(AvsMessage.class, mes);
				
				Map<String,Object> operationMessage = avsMessage.getOperationMessage();
				
				if(operationMessage != null) {
					
					String resource_messages = operationMessage.get("resource").toString();
					LOGGER.logMessage("avsMessage.operation {} ", avsMessage.getOperation());
					LOGGER.logMessage("avsMessage.resource {} ", resource_messages);
					Thread.sleep(1500L);
					if (SERVER_RESOURCE.equalsIgnoreCase(resource_messages)) {
						// MMDDF properties
						reloadMDCBWAndMassCalculation();
					} else if (SERVER_DEVICE_RESOURCE_CONFIG.equalsIgnoreCase(resource_messages)) {
						// deviceResourceConfig for mass-calculation properties
						configurationManager.findMassCalculationResourceCongig();
		
					} else if (VQE_RESOURCE.equalsIgnoreCase(resource_messages)) {
						// VQE properties
						reloadVqeAndMassCalculation();
					}
				}
			}
		} catch (IOException | JMSException | InterruptedException e) {
			LOGGER.logError(e);
		}
		LOGGER.logMessage("Stop: Receive JMS message for MDCBandwidth/VQE from Topic.");
	}

	/**
	 * Reload VQE and mass recalculation.
	 */
	private void reloadVqeAndMassCalculation() {
		Boolean oldVqeFlag = configurationClient.getGlobalVqeAndMdcBandwidthFlag().getUseGlobalVqeBW();
		//reload VQE 
		configurationManager.findGlobalVQEFlagCongig();
		Boolean newVqeFlag = configurationClient.getGlobalVqeAndMdcBandwidthFlag().getUseGlobalVqeBW();
		LOGGER.logMessage(" Old VQE Flag = {}, Updated VQE Flag = {} ",oldVqeFlag, newVqeFlag);
		if(!oldVqeFlag.equals(newVqeFlag)) {
			LOGGER.logMessage("Mass calculation starts for VQE settings update");
			massRecalculationService.collectDataForMass(Constants.Jms.VQE_CHANGE, null, false); 
		}
	}

	/**
	 * Reload MDCBandwidth and mass recalculation
	 */
	private void reloadMDCBWAndMassCalculation() {
		Integer oldBW = configurationClient.getGlobalVqeAndMdcBandwidthFlag().getMdcBandwidthValue();
		//load updated MDC server properties
		configurationManager.findMMDDFResourceConfig();
		Integer updateBW = configurationClient.getGlobalVqeAndMdcBandwidthFlag().getMdcBandwidthValue();
		LOGGER.logMessage(" Old Bandwidth = {}, Updated Bandwidth = {} ",oldBW, updateBW);
		if(!oldBW.equals(updateBW)) {
			LOGGER.logMessage("Mass calculation starts for MMDDF Bandwidth update");
			massRecalculationService.collectDataForMass(Constants.Jms.MMDDF_CHANGE, null, false); 
		}
	}
}
