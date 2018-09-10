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

package com.accenture.avs.device.manager.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.entity.UserLog;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.UpdateVqeParamMessage;
import com.accenture.avs.device.manager.UserManager;
import com.accenture.avs.device.repository.UserLogRepository;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * The implementation class for {@link UserManager }.
 * 
 * @author
 *
 */
@Component
public class UserManagerImpl implements UserManager {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(UserManagerImpl.class);

	/** userRepository */
	@Autowired
	private UserRepository userRepository;

	/** deviceRepository */
	@Autowired
	private UserLogRepository userLogRepository;

	/**
	 * This method create user constraints along with user in database
	 * 
	 * @param user
	 * @param isCreate
	 * @return User entity
	 */
	@Override
	public User setUser(User user, boolean isCreate) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_CRMACCOUNTID, user.getCrmAccountId());
		queryDBParameters.put(Constants.DB_PARAM_USERNAME, user.getUserName());
		queryDBParameters.put(Constants.DB_PARAM_MAX_ALLOWED_IPTV_DEVICES, user.getMaxAllowedIptvDevices());
		queryDBParameters.put(Constants.DB_PARAM_BANDWIDTH_PROFILE,user.getBandwithProfile());
		queryDBParameters.put(Constants.DB_PARAM_OVERRIDE_BANDWIDTH,user.getOverrideBandwith());
		queryDBParameters.put(Constants.DB_PARAM_QOE_CONTROL_BANDWIDTH,user.getQoeControlBandwith());
		queryDBParameters.put(Constants.DB_PARAM_USER_LIMITS,user.getUserLimits());
		queryDBParameters.put(Constants.DB_PARAM_RETENABLE,user.getRetEnable());
		queryDBParameters.put(Constants.DB_PARAM_RCCENABLE,user.getRccEnable());
		queryDBParameters.put(Constants.DB_PARAM_NETWORK_BUFFER_SIZE,user.getNetworkBufferSize());
		queryDBParameters.put(Constants.DB_PARAM_NATBINDING_REFRESH_INTERVAL,user.getNatBindingRefreshInterval());
		queryDBParameters.put(Constants.DB_PARAM_LASTUPDATE_DATETIME,user.getLastUpdatedDatetime());
		User userEntity = null;
		try {
			userEntity = userRepository.save(user);
		} finally {
			if (isCreate) {
				LOGGER.logDBQuery(
						"INSERT INTO USER (CRMACCOUNTID, USER_NAME, MAX_ALLOWED_IPTV_DEVICES,...) VALUES ( ?1, ?2, ...)",
						queryDBParameters, System.currentTimeMillis() - startTime);
			} else {
				LOGGER.logDBQuery("UPDATE USER SET CRMACCOUNTID = ?1, USER_NAME = ?2, MAX_ALLOWED_IPTV_DEVICES =?3",
						queryDBParameters, System.currentTimeMillis() - startTime);
			}
		}
		return userEntity;
	}

	/**
	 * This method delete user from user table by providing userName and also
	 * un-assignment devices from device table
	 * 
	 * @param userName
	 */
	@Override
	public void deleteUser(User userName) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_USERNAME, userName.getUserName());
		try {
			userRepository.delete(userName);
		} finally {
			LOGGER.logDBQuery("DELETE FROM USER WHERE USER_NAME = ?", queryDBParameters,
					System.currentTimeMillis() - startTime);
		}
	}

	/**
	 * find user entity by providing user name
	 * 
	 * @param userName
	 * @return User
	 */
	public User findByUserNameWithUserLimits(String userName) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_USERNAME, userName);
		User user = null;
		try {
			user = userRepository.findByUserNameWithUserLimits(userName);
			if (Objects.isNull(user)) {
				throw new DeviceManagerException(ErrorCode.RESOURCE_NOT_FOUND);
			}
		} finally {
			LOGGER.logDBQuery("SELECT * FROM USER WHERE USER_NAME = ?", queryDBParameters,
					System.currentTimeMillis() - startTime);
		}
		return user;
	}

	/**
	 * Gets user by provided userName
	 * 
	 * @param userName
	 * @return User
	 */
	@Override
	public User getUserByUserName(String userName) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_USERNAME, userName);
		User user = null;
		try {
			user = userRepository.findByUserName(userName);
			if (Objects.isNull(user)) {
				throw new DeviceManagerException(ErrorCode.RESOURCE_NOT_FOUND);
			}
		} finally {
			LOGGER.logDBQuery("SELECT * FROM USER WHERE USER_NAME = ?", queryDBParameters,
					System.currentTimeMillis() - startTime);
		}
		return user;
	}

	/**
	 * Gets user by provided crmAccountId
	 * 
	 * @param crmAccountId
	 * @return UserTemp
	 */
	@Override
	public User getUserByCrmAccountId(String crmAccountId) {

		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_CRMACCOUNTID, crmAccountId);
		try {
			return userRepository.findByCrmAccountId(crmAccountId);
		} finally {
			LOGGER.logDBQuery("SELECT * FROM USER WHERE CRMACCOUNTID = ?", queryDBParameters,
					System.currentTimeMillis() - startTime);
		}
	}

	/**
	 * This method creates user_log in database.
	 * 
	 * @param updateVqeRequest
	 * @param failedReason
	 */
	@Override
	public void saveUserLog(UpdateVqeParamMessage updateVqeRequest, String failedReason) {

		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_CRMACCOUNTID, updateVqeRequest.getCrmAccountId());
		queryDBParameters.put(Constants.DB_PARAM_QOE_CONTROL_BANDWIDTH, updateVqeRequest.getQoeControlBandwidth());
		queryDBParameters.put(Constants.DB_PARAM_RETENABLE, updateVqeRequest.getRetEnable());
		queryDBParameters.put(Constants.DB_PARAM_RCCENABLE, updateVqeRequest.getRccEnable());
		queryDBParameters.put(Constants.DB_PARAM_NETWORK_BUFFER_SIZE, updateVqeRequest.getNetworkBufferSize());
		queryDBParameters.put(Constants.DB_PARAM_NATBINDING_REFRESH_INTERVAL,
				updateVqeRequest.getNatBindingRefreshInterval());
		UserLog userLogEntity = null;
		try {
			userLogEntity = new UserLog();
			userLogEntity.setCrmAccountId(updateVqeRequest.getCrmAccountId());
			if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getQoeControlBandwidth())) {
				userLogEntity.setQoeControlBandwith(updateVqeRequest.getQoeControlBandwidth().longValue());
			}
			if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getNetworkBufferSize())) {
				userLogEntity.setNetworkBufferSize(updateVqeRequest.getNetworkBufferSize().longValue());
			}
			if (DeviceManagerUtil.checkNotNullObject(updateVqeRequest.getNatBindingRefreshInterval())) {
				userLogEntity.setNatBindingRefreshInterval(updateVqeRequest.getNatBindingRefreshInterval().longValue());
			}
			userLogEntity.setRccEnable(updateVqeRequest.getRccEnable());
			userLogEntity.setRetEnable(updateVqeRequest.getRetEnable());
			userLogEntity.setFailedReason(failedReason);
			userLogEntity.setInsertDateTime(startTime);
			userLogRepository.save(userLogEntity);
		} finally {
			LOGGER.logDBQuery(
					"INSERT INTO USER_LOG (CRMACCOUNTID, QOE_CONTROL_BANDWIDTH, RET_ENABLE,...) VALUES ( ?1, ?2, ...)",
					queryDBParameters, System.currentTimeMillis() - startTime);
		}
	}
	
	/**
	 * Gets list of users
	 * @return List<User>
	 */
	@Override
	public List<User> getAllUsers() {
		Long startTime = System.currentTimeMillis();
		try {
			return userRepository.findAll();
		} finally {
			LOGGER.logDBQuery("SELECT u FROM User u ", null,
					System.currentTimeMillis() - startTime);
		}
	}
}
