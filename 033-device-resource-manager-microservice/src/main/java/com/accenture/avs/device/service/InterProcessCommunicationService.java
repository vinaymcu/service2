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

package com.accenture.avs.device.service;

import java.util.List;

/**
 * This class handles all the interprocess communications.
 * 
 * @author Singh.Saurabh
 *
 */
public interface InterProcessCommunicationService {

	/**
	 * This method notifies the Group MS
	 * 
	 * @param userNames
	 * @param operation
	 */
	void notifyGroupMs(List<String> userNames, String operation);

	/**
	 * This method sends notifications to the PushMessage MS.
	 * 
	 * @param deviceId
	 * @param userName
	 * @param triggerType
	 * 
	 */
	void notifyPushMessageMs(String deviceId, String userName, String triggerType);

	/**
	 * This method performs following operations:- 1. Call ResourceManager to "Get
	 * Device Profiles" 2. Update Device Profile in database
	 * 
	 * @param assignedToUserName
	 * @param device
	 * 
	 */
	Boolean triggerDeviceProfileCalculation(String assignedToUserName);

}
