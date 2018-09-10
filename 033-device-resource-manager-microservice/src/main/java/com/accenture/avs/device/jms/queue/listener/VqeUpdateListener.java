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
package com.accenture.avs.device.jms.queue.listener;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.json.object.devicemanager.UpdateVqeParamMessage;
import com.accenture.avs.device.manager.UserManager;
import com.accenture.avs.device.service.UserService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the listener for Asynchronous VQE Parameters Update.
 * 
 * @author singh.saurabh
 *
 */
@Component
public class VqeUpdateListener implements MessageListener {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(VqeUpdateListener.class);

	/** userService */
	@Autowired
	private UserService userService;

	/** userManager */
	@Autowired
	private UserManager userManager;
	
	/** mapper */
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	/**
	 * @param message
	 */
	@Override
	public void onMessage(Message message) {
		LOGGER.logMessage("Start: Receive JMS message for VQE Update from Queue");

		AvsMessage avsMessage = null;
		UpdateVqeParamMessage updateVqeParamMessage = null;
		try {
			TextMessage textMessage = (TextMessage) message;

			if (!DeviceManagerUtil.checkNullorBlankString(textMessage.getText())) {
				LOGGER.logMessage("Received message = {} ", textMessage.getText().toString());
				
				avsMessage = JsonUtils.parseJson(AvsMessage.class, textMessage.getText());
				Object messageObj = avsMessage.getOperationMessage().get(Constants.Jms.KEY_VQE_UPDATE);
				updateVqeParamMessage = JsonUtils.parseJson(UpdateVqeParamMessage.class,
						mapper.writeValueAsString(messageObj));
				LOGGER.logMessage("VQEParams = {} ", updateVqeParamMessage.toString());
				
				if (!DeviceManagerUtil.checkNullorBlankString(updateVqeParamMessage.getCrmAccountId())) {
					userService.updateVqeParameter(updateVqeParamMessage);
				}
			}
		} catch (DeviceManagerException exp) {
			// Save in the USER_LOG table in case of any failure
			userManager.saveUserLog(updateVqeParamMessage, exp.getMessage());
		} catch (IOException | JMSException exp) {
			LOGGER.logError(exp);
		}
		LOGGER.logMessage("Stop: Receive JMS message for VQE Update from Queue");
	}

}
