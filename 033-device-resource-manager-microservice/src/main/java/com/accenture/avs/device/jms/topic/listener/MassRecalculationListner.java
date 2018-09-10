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
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;

/**
 * @author surendra.kumar
 *
 */
@Component
public class MassRecalculationListner  implements MessageListener {
	
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(MassRecalculationListner.class); 
	
	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;
	
	/** configurationClient */
	@Autowired
	private ConfigurationClient configurationClient;
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		LOGGER.logMessage("Start: Receive JMS message for MassCalculation.");
		AvsMessage avsMessage = null;

		try {
			TextMessage textMessage = (TextMessage) message;
			String mes = textMessage.getText();
			LOGGER.logMessage("Receive JMS message as {} ", mes);
			avsMessage = JsonUtils.parseJson(AvsMessage.class, mes);
			String operation = avsMessage.getOperation();
			LOGGER.logMessage("avsMessage.operation {} ", avsMessage.getOperation());

			if (Constants.Jms.DRM_OPERATION.equalsIgnoreCase(operation)) {
				processCalculation();
			}
		} catch (IOException | JMSException e) {
			LOGGER.logError(e);
		}
		LOGGER.logMessage("Stop: Receive JMS message for MassCalculation.");
	}
	
	/**
	 * 
	 */
	private void  processCalculation() {
		Integer massCalculationThreads = configurationClient.getDrmResources().getParallelExecutionThreads();
		
		ExecutorService executorService = Executors.newFixedThreadPool(massCalculationThreads);
		massRecalculationService.processCalculation(generateIdentifier(), executorService);
	}
	
	private String generateIdentifier(){
		Long nano = System.nanoTime();
		Integer random = new Random().nextInt(99);
		
		return (nano.toString()).concat(random.toString());
	}

}
