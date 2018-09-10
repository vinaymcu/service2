/**************************************************************************
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

package com.accenture.avs.device.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.client.GroupDefinitionClient;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.service.PushMessageService;
import com.accenture.avs.device.service.ResourceDistributionService;

/**
 * This class handles all the interprocess communications.
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class InterProcessCommunicationServiceImpl implements InterProcessCommunicationService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(InterProcessCommunicationServiceImpl.class);

	/** groupDefinitionClient */
	@Autowired
	private GroupDefinitionClient groupDefinitionClient;

	/** pushMessageService */
	@Autowired
	private PushMessageService pushMessageService;
	
	/** ResourceRedistributionService */
	@Autowired
	private ResourceDistributionService resourcedistributionService;

	/** taskExecutor */
	@Autowired
	TaskExecutor executor;

	/**
	 * This method notifies the Group MS
	 * 
	 * @param userNames
	 * @param operation
	 */
	@Override
	public void notifyGroupMs(List<String> userNames, String operation) {
		LOGGER.logMessage("Trigger Group Definition Message Notification for UserNames={}, Operation={}", userNames, operation);
		groupDefinitionClient.sendMessage(userNames, operation);
	}

	/**
	 * This method sends notifications to the PushMessage MS.
	 * 
	 * @param deviceId
	 * @param assignedUserName
	 * @param triggerType
	 * 
	 */
	@Override
	public void notifyPushMessageMs(String deviceId, String assignedUserName, String triggerType) {
		executor.execute(() -> {
			pushMessageService.sendNotification(deviceId, assignedUserName, triggerType);
		});

	}

	/**
	 * This method performs following operations:- 1. Call ResourceManager to "Get
	 * Device Profiles" 2. Update Device Profile in database
	 * 
	 * @param assignedToUserName
	 * @param device
	 * 
	 */
	@Override
	public Boolean triggerDeviceProfileCalculation(String assignedToUserName) {
		LOGGER.logMessage("Trigger Profile Calculation for UserName={}", assignedToUserName);
		return resourcedistributionService.getUserDeviceProfile(assignedToUserName, Boolean.FALSE);
	}

}
