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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.service.InterProcessCommunicationService;
import com.accenture.avs.device.service.MassRecalculationHelper;
import com.accenture.avs.device.service.ResourceDistributionService;
import com.accenture.avs.device.util.Constants;

/**
 * @author surendra.kumar
 *
 */
@Service
public class MassRecalculationHelperImpl implements MassRecalculationHelper{
	
	private static final LoggerWrapper LOGGER = new LoggerWrapper(MassRecalculationHelperImpl.class);
	
	/**userRepository*/
	@Autowired
	private UserRepository userRepository;
	
	/** ResourceRedistributionService */
	@Autowired
	private ResourceDistributionService resourcedistributionService;
	
	/** interProcessCommunicationService */
	@Autowired
	private InterProcessCommunicationService interProcessCommunicationService;
	
	/** taskExecutor */
	@Autowired
	TaskExecutor executor;
	
	/**
	 * 
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateDataForStartupMassCalculator() {
		return userRepository.updateDataForStartupMassCalculator();
	}
	
	
	/**
	 * 
	 * @param crmAddountIdListForUpdate
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateMassCalculationCounter(List<String> crmAddountIdListForUpdate){
		userRepository.updateMassCalculationCounter(crmAddountIdListForUpdate);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void calculateProfile(List<String> userListForThread) {

		for(String userName : userListForThread) {

			Boolean profileCalcDone = resourcedistributionService.getUserDeviceProfile(userName, Boolean.TRUE);			
			LOGGER.logMessage("profileCalculationDone {} for user {} ", profileCalcDone, userName);

			// Notify Push Message MS
			interProcessCommunicationService.notifyPushMessageMs(null, userName,
					Constants.Trigger.T_SUBSCRIBER_INFO);
		}
	}

}
