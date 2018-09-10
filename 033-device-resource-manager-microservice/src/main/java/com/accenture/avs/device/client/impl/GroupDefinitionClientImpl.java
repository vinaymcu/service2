/****************************************************************************
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
package com.accenture.avs.device.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.AvsJmsProducer;
import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.client.GroupDefinitionClient;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.util.Constants;

/**
 * This is the Client to communicate with the Group Definition MS.
 * 
 * @author Singh.Saurabh
 *
 */
@Component
public class GroupDefinitionClientImpl implements GroupDefinitionClient {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(GroupDefinitionClientImpl.class);
	
	/** configurationClient */
	@Autowired
	ConfigurationClient configurationClient;
	
	/** avsBeQueueEndPoint */
	private String avsBeQueueEndPoint;

	/** avsBeQueuePassword */
	private String avsBeQueuePassword;

	/** avsBeQueueUserName */
	private String avsBeQueueUserName;
	
	/** avsBeGroupmsQueue */
	private String avsBeGroupmsQueue;

	/** avsGroupQueueInteractionEnabled */
	private String avsGroupQueueInteractionEnabled;
	
	/** messagePriority */
	private final int messagePriority = 5;

	
	/**
	 * PostConstruct
	 */
	@PostConstruct
	public void init() {
		try {
			avsBeGroupmsQueue = configurationClient.getSystemParameterValue(Constants.Jms.GROUP_MS_QUEUE_NAME);
			avsBeQueueEndPoint = configurationClient.getSystemParameterValue(Constants.Jms.GROUP_MS_QUEUE_ENDPOINT);
			avsBeQueueUserName = configurationClient.getSystemParameterValue(Constants.Jms.GROUP_MS_QUEUE_USERNAME);
			avsBeQueuePassword = configurationClient.getSystemParameterValue(Constants.Jms.GROUP_MS_QUEUE_PASSWORD);
			avsGroupQueueInteractionEnabled = configurationClient.getSystemParameterValue(Constants.Jms.GROUP_MS_QUEUE_INTERACTION_ENABLED);
			AvsJmsProducer.initConnectionPoolFactory(avsBeGroupmsQueue, avsBeQueueEndPoint, avsBeQueueUserName, avsBeQueuePassword, 100, 10);
		} catch (Exception e) {
			LOGGER.logError(e);
		}
	}

	/**
	 * This method sends message for the Group Definition MS.
	 * 
	 * @param userNames
	 * @param operation
	 */
	@Override
	public void sendMessage(List<String> userNames, String operation) {
		if ("Y".equalsIgnoreCase(avsGroupQueueInteractionEnabled)) {
			Map<String, Object> messageMap = new HashMap<>();
			messageMap.put("userNames", userNames);
			AvsMessage avsMessage = new AvsMessage(operation, messageMap);
			try {
				LOGGER.logMessage("Sending message to Group Definition Ms for user(s)={}", userNames);
				AvsJmsProducer.sendMessage(avsBeGroupmsQueue, messagePriority, avsMessage);
				LOGGER.logMessage("Message has been successfully sent to Group Ms.");
			} catch (Exception e) {
				LOGGER.logError(e);
			}
		}
	}
}
