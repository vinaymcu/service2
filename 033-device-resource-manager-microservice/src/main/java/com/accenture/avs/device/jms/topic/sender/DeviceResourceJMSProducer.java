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

package com.accenture.avs.device.jms.topic.sender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.AvsJmsProducer;
import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.be.jms.client.dto.TriggerServerMessage;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.util.Constants;

/**
 * DeviceResourceTopicProducer class
 *
 * @author kumar.rajesh
 *
 */
@Component
public class DeviceResourceJMSProducer {

	private static final String AVS_MESSAGE_RESOURCE = "resource";

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceResourceJMSProducer.class);

	@Autowired
	ConfigurationClient configurationClient;
	
	/**
	 * @param operation
	 */
	public void generateTriggerToGLDocument(String operation, String resource) {
		LOGGER.logMessage("Send message on topic for Model create/update/delete. ");
		if ("Y".equals(configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_ENABLED))) {
			String drm_topic_endpoint = configurationClient
					.getSystemParameterValue(Constants.Jms.DRM_JMS_ENDPOINT);
			String drm_topic_userName = configurationClient
					.getSystemParameterValue(Constants.Jms.DRM_JMS_USERNAME);
			String drm_topic_password = configurationClient
					.getSystemParameterValue(Constants.Jms.DRM_JMS_PASSWORD);
			String drm_topic_topic_name = configurationClient
					.getSystemParameterValue(Constants.Jms.DRM_TOPIC_NAME);
			LOGGER.logMessage("Start: Sending message for DRM Topic : '{}' ", drm_topic_topic_name);

			AvsMessage avsMessage = new AvsMessage();
			avsMessage.setOperation(operation);
			Map<String, Object> mapMessage = new HashMap<>();
			mapMessage.put(AVS_MESSAGE_RESOURCE, resource);
			avsMessage.setOperationMessage(mapMessage);

			AvsJmsProducer.initConnectionPoolFactory(drm_topic_topic_name, drm_topic_endpoint, drm_topic_userName,
					drm_topic_password, 100, 10);
			try {
				AvsJmsProducer.sendTopicMessage(drm_topic_topic_name, 1,
						new ObjectMapper().writeValueAsString(avsMessage), 10000L);
			} catch (IOException  jge) {
				LOGGER.logError(jge);
			} catch (Exception excp) {
				LOGGER.logError(excp);
			}
			LOGGER.logMessage("Stop: Sending message for DRM Topic : '{}' ", drm_topic_topic_name);
		}
	}
	
	/**
	 * Trigger PushMessage queue while Device register/unregister/update and mass update.
	 * 
	 * @param triggerServerMessage
	 */
	public void triggerPushMessage(TriggerServerMessage triggerServerMessage) {
		LOGGER.logMessage("Send message on queue for Device register/unregister/update. ");
		if ("Y".equals(configurationClient.getSystemParameterValue(Constants.PushMessage.PUSH_MESSAGE_MS_JMS_ENABLED))) {
			String push_message_topic_endpoint = configurationClient
					.getSystemParameterValue(Constants.PushMessage.PUSH_MESSAGE_MS_JMS_ENDPOINT);
			String push_message_topic_userName = configurationClient
					.getSystemParameterValue(Constants.PushMessage.PUSH_MESSAGE_MS_JMS_USERNAME);
			String push_message_topic_password = configurationClient
					.getSystemParameterValue(Constants.PushMessage.PUSH_MESSAGE_MS_JMS_PASSWORD);
			String push_message_topic_queue_name = configurationClient
					.getSystemParameterValue(Constants.PushMessage.PUSH_MESSAGE_MS_UNICAST_TRIGGER_QUEUE_NAME);
			String messagePriority = configurationClient
					.getSystemParameterValue(Constants.DEVICE_RESOURCE_MS_JMS_MESSAGE_PRIORITY);
			LOGGER.logMessage("Start: Sending message to Push Message Queue : '{}' ", push_message_topic_queue_name);

			try {
				String message = JsonUtils.writeAsJsonString(triggerServerMessage);

				AvsJmsProducer.initConnectionPoolFactory(push_message_topic_queue_name, push_message_topic_endpoint, push_message_topic_userName,
						push_message_topic_password, 100, 10);

				AvsJmsProducer.sendTextMessage(push_message_topic_queue_name, Integer.valueOf(messagePriority), message);
			} catch (IOException  jge) {
				LOGGER.logError(jge);
			} catch (Exception excp) {
				LOGGER.logError(excp);
			}
			LOGGER.logMessage("Stop: Sent message to Push Message Queue : '{}' ", push_message_topic_queue_name);
		}
	}
}
