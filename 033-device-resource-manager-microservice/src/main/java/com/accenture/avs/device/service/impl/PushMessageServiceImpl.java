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
package com.accenture.avs.device.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.avs.be.jms.client.dto.TriggerServerMessage;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.jms.topic.sender.DeviceResourceJMSProducer;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.service.PushMessageService;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * This class sends notifications to the PushMessage MS.
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(PushMessageServiceImpl.class);

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;

	/** deviceResourceJMSProducer */
	@Autowired
	DeviceResourceJMSProducer deviceResourceJMSProducer;

	/**
	 * This method sends notification to Push Message MS.
	 * 
	 * @param deviceId
	 * @param assignedToUserName
	 * @param triggerType
	 * 
	 */
	@Override
	public void sendNotification(String deviceId, String assignedToUserName, String triggerType) {
		List<Device> targetDevices = deviceRepository.findByAssignedToUserName(assignedToUserName);
		
		LOGGER.logMessage("Trigger Push Message Notification for UserName={}, DeviceId={}, TriggerType={}",
				assignedToUserName, deviceId, triggerType);
		
			TriggerServerMessage triggerServerMessage = RequestResponseGenerator.getTriggerServerMessageRequest(deviceId, 
					getTargetDevicesInArray(targetDevices, deviceId), triggerType);
			sendNotification(triggerServerMessage);
	}

	/**
	 * This method sends notification to UnicastNotifier.
	 * 
	 * @param triggerServerMessage
	 */
	private void sendNotification(TriggerServerMessage triggerServerMessage) {

		LOGGER.logMessage(
				"Sending notification for DeviceId = " + triggerServerMessage.getProducerDevice()
				+ " for UserName= " + triggerServerMessage.getUserName());

		deviceResourceJMSProducer.triggerPushMessage(triggerServerMessage);

		LOGGER.logMessage("Notification sent sucessfully for DeviceId = "
				+ triggerServerMessage.getTargetDevices() + " for UserName= "
				+ triggerServerMessage.getUserName());
	}
	
	/**
	 * GetTarget Devices in Array
	 * 
	 * @param targetDeviceList
	 */
	private String[] getTargetDevicesInArray(List<Device> targetDeviceList, String deviceId) {

		List<String> targetDevices = new ArrayList<>();

		for(int i=0; i<targetDeviceList.size(); i++) {
			String targetDeviceId = targetDeviceList.get(i).getDeviceId();
			if(!targetDeviceId.equals(deviceId)) {
				targetDevices.add(targetDeviceId);
			} 
		}
		LOGGER.logMessage("TargetDevices={}", targetDevices);
		return targetDevices.toArray(new String[0]);
	}

}
