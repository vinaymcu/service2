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

package com.accenture.avs.device.manager;

import java.util.List;

import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.json.object.devicemanager.UpdateVqeParamMessage;

/**
 * The manger interface for interacting with UserRepository.
 *
 * @author muhammad.yunus
 */
public interface UserManager {

	/**
	 * This method create user constraints along-with user in database
	 * 
	 * @param user
	 * @param isCreate
	 * @return User
	 */
	User setUser(User user, boolean isCreate);

	/**
	 * This method delete user from database
	 * 
	 * @param userName
	 */	
	void deleteUser(User userName);
			
	/**
	 * Get User
	 * 
	 * @param userName
	 * @return User
	 */
	User getUserByUserName(String userName);
	
	/**
	 * Get User
	 * 
	 * @param CrmAccountId
	 * @return User
	 */
	User getUserByCrmAccountId(String crmAccountId);
	
	/**
	 * Find by userName with userLimits
	 * @param useName
	 * @return User
	 */	
	User findByUserNameWithUserLimits(String useName);
	
	/**
	 * This method creates user_log in database.
	 * 
	 * @param updateVqeRequest
	 * @param failedReason
	 */
	void saveUserLog(UpdateVqeParamMessage updateVqeRequest, String failedReason);
	
	/**
	 * Gets list of users
	 * @return
	 */
	List<User> getAllUsers();
}
