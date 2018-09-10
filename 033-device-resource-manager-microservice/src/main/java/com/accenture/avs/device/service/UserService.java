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

import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUpdatedUsers;
import com.accenture.avs.device.json.object.devicemanager.GetUserResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVQEParameterUpdateStatusResponse;
import com.accenture.avs.device.json.object.devicemanager.SetUserRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateVqeParamMessage;

/**
 * The Interface Upsert user
 * 
 * @author muhammad yunus
 *
 */
public interface UserService {

	/**
	 * This method validates and set user constraints
	 * 
	 * @param setUserRequest
	 * @param crmAccountId
	 * 
	 * @return GenericResponse
	 */
	GenericResponse setUser(SetUserRequest setUserRequest, String crmAccountId);

	/**
	 * Delete user
	 * 
	 * @param userName
	 * @return GenericResponse
	 */
	GenericResponse deleteUser(String userName);

	/**
	 * This method validates and gets user
	 * 
	 * @param userName
	 * @return GetUserResponse
	 */
	GetUserResponse getUser(String userName);

	/**
	 * This method is used to update VQE Parameters of the User.
	 * 
	 * @param updateVqeRequest
	 */
	void updateVqeParameter(UpdateVqeParamMessage updateVqeRequest);

	/**
	 * This method returns the VQE parameter update status for each of the provided
	 * crmAccountId.
	 * 
	 * @param crmAccountIds
	 * @return
	 */
	GetVQEParameterUpdateStatusResponse getVQEUpdateStatus(String crmAccountIds);
	
	/**
	 * This method gets updated users
	 * within the given time frame
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	GetUpdatedUsers getUpdatedUser(Long startDate, Long endDate);

}
