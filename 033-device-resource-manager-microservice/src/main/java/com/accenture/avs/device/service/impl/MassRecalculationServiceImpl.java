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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.be.jms.client.AvsJmsProducer;
import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.service.MassRecalculationHelper;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * @author surendra.kumar
 *
 */
@Service
public class MassRecalculationServiceImpl implements MassRecalculationService{
	
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceServiceHelperImpl.class);
	
	/**userRepository*/
	@Autowired
	private UserRepository userRepository;
	
	/*configurationClient*/
	@Autowired
	ConfigurationClient configurationClient;
	
	/*massRecalculationHelper*/
	@Autowired
	MassRecalculationHelper massRecalculationHelper;
	
	/*CountDownLatch*/
    private CountDownLatch doneSignal;

	/**
	 * Triggers mass resource calculation
	 * @return
	 */
	@Override
	public GenericResponse massRecalculation(boolean isMassAtStartup) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("massRecalculation ");
		
		collectDataForMass(Constants.Jms.TIRGGER, null, isMassAtStartup);
		
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}
	
	
	/**
	 * This method will updated column mass_calculation_counter for mass calculation
	 * @param caller
	 * @param subIdentifier
	 */
	public void collectDataForMass(String caller, List<String> subIdentifier, boolean isMassAtStartup){
		long startTime = System.currentTimeMillis();
			
		Integer batchSize = configurationClient.getDrmResources().getBatchSize();
		
		LOGGER.logMessage("Collection data for mass calculation in batch size : " + batchSize);
		
		if(isMassAtStartup){
			int userCount = massRecalculationHelper.updateDataForStartupMassCalculator();
			LOGGER.logMessage("No of Users pending for mass calculation {}",userCount);
			/*No pending users for mass calculation*/
			if(userCount == 0){
				return;
			}
		}
		
		List<String> subscriberList = populateDataForMass(caller, subIdentifier);
		
		if(subscriberList == null) {
			return ;
		}
		
		List<String> crmAddountIdListForUpdate = new ArrayList<>();
		
		for(String crmAddountId : subscriberList){
			crmAddountIdListForUpdate.add(crmAddountId);
			if(crmAddountIdListForUpdate.size() == batchSize){
				massRecalculationHelper.updateMassCalculationCounter(crmAddountIdListForUpdate);
				LOGGER.logMessage("mass_calculation_counter updated of users : " + crmAddountIdListForUpdate);
				crmAddountIdListForUpdate.clear();
			}
		}
		if(crmAddountIdListForUpdate.size()>0){
			massRecalculationHelper.updateMassCalculationCounter(crmAddountIdListForUpdate);
			LOGGER.logMessage("mass_calculation_counter updated of users : " + crmAddountIdListForUpdate);
		}
		
		sendTriggerToMassCalculation();
		
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
	}
	
	
	/**
	 * This methods return list of Affectived crmaccountid
	 * @param caller
	 * @param subIdentifier
	 * @return
	 */
	private List<String> populateDataForMass(String caller, List<String> subIdentifier){
		List<String> subscriberList = null;
		if(caller.equals(Constants.Jms.VTP_CHANGE)){
			LOGGER.logMessage("get subscribers for mass calculation for {} ",Constants.Jms.VTP_CHANGE);
			/** Affectived Subscribers for vtp change*/
			subscriberList = userRepository.findAffectiveSubscribersForVtpChange(subIdentifier);
		}else if(caller.equals(Constants.Jms.HW_MODEL_CHANGE)){
			LOGGER.logMessage("get subscribers for mass calculation for  {}",Constants.Jms.HW_MODEL_CHANGE);
			/** Affectived Subscribers for model change */
			subscriberList = userRepository.findAffectiveSubscribersForModelChange(subIdentifier);
		} else if(caller.equals(Constants.Jms.BW_PROFILE_CHANGE)){
			LOGGER.logMessage("get subscribers for mass calculation for  {}",Constants.Jms.BW_PROFILE_CHANGE);
			/** Affectived Subscribers for bandwidth change*/
			subscriberList = userRepository.findAffectiveSubscribersForBwChange(subIdentifier);
		} else if(caller.equals(Constants.Jms.MMDDF_CHANGE) || caller.equals(Constants.Jms.VQE_CHANGE) ||
				caller.equals(Constants.Jms.TIRGGER)){
			LOGGER.logMessage("get All subscribers for mass calculation");
			/**All subscribers with active devices */
			subscriberList = userRepository.findAllSubscribersWithDevice();
		} 
		
		return subscriberList;
		
	}
	
	/**
	 * This method starts profile calculation process
	 * @param identifier
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void processCalculation(String identifier, ExecutorService executorService){

		LOGGER.logMessage("processCalculation starts...");
		if(doneSignal !=null && doneSignal.getCount()>0){
			LOGGER.logMessage("Pending threads {} ", doneSignal.getCount());
			return;
		}
		
		Integer batchSize = configurationClient.getDrmResources().getBatchSize();
		Integer massCalculationThreads = configurationClient.getDrmResources().getParallelExecutionThreads();
		
		try {
			Thread.sleep(1500);
		}  catch(InterruptedException ex) {
			LOGGER.logError(ex);
		}
		
		int maxRetry = 5;
		
		do {
		
			Integer updatedCounts = userRepository.updateMassIdentifier(identifier, batchSize);
			userRepository.flush();
			
			/* No data for mass calculation*/
			if (updatedCounts == 0) {
				executorService.shutdown();
				return;
			}
			
			LOGGER.logMessage("{} users updated for mass calculation by identifier {}", updatedCounts, identifier);
			List<String> userList = userRepository.getUserByMassIdentifier(identifier);
			
			int userListSize = userList.size();							
			LOGGER.logMessage("{} users will be processed", userListSize);
			
			if (userListSize == 0) {
				LOGGER.logMessage("System unable to retrieve user data for mass calculation, release the lock");
				int updatedRows = userRepository.clearMassIdentifier(identifier);
				LOGGER.logMessage("updatedRows {} ", updatedRows);
				
				try {
					Thread.sleep(200);
				}  catch(InterruptedException ex) {
					LOGGER.logError(ex);
				}
				
				continue;
			} else {
				maxRetry = 5;
			}
			
			int usersRequiredInThread = getNoOfUsersInThread(userListSize, massCalculationThreads);
			List<String> userListForThread = new ArrayList<>();
			int noOfThreadsForMsg = (userListSize % massCalculationThreads == 0) ? massCalculationThreads
					: (userListSize < massCalculationThreads) ? userListSize : massCalculationThreads + 1;
			
			LOGGER.logMessage("{} thread will be used to process mass calculation", noOfThreadsForMsg);
			
			doneSignal = new CountDownLatch(noOfThreadsForMsg);
			
			for (int i = 0; i < userListSize; i++) {
				userListForThread.add(userList.get(i));
				if (userListForThread.size() == usersRequiredInThread || i == userListSize - 1) {
					executorService.execute(new BackgroundJobForMass(userListForThread));
					userListForThread = new ArrayList<String>();
				}
			}
			try {
				doneSignal.await();
			} catch (InterruptedException e) {
				LOGGER.logError(e, "Exception in mass profile calculation, while waiting for threads to finish.");
			} 
		} while (maxRetry-- > 0);
		
	}
	
	/**
     * returns no of threads in in a thread
     * @param listSize
     * @return
     */
    private int getNoOfUsersInThread(int listSize, int massCalculationThreads) {
    	int noOfusers;
    	
    	if(massCalculationThreads ==1){
    		noOfusers = massCalculationThreads;
    	}else if (listSize < massCalculationThreads){
    		noOfusers = 1;
		} else {
			noOfusers = listSize/massCalculationThreads;
		}
    	
		return noOfusers;
	}


	/***
	 * BackgroundJob
	 */
	private class BackgroundJobForMass implements Runnable {
		
		private List<String> userListForThread;
		
		/**
		 * @param userListForThread
		 */
		public BackgroundJobForMass(List userListForThread) {
			this.userListForThread = userListForThread;
		}

		@Override
		public void run() {
			
			LOGGER.logMessage("MassRecalculation Thread starts for users {}",userListForThread);
			
			try {
				massRecalculationHelper.calculateProfile(userListForThread);
			} catch(Exception ex) {
				LOGGER.logError(ex, "Unable to finish profile calcuation for user(s) : " + userListForThread);
			}
			
			doneSignal.countDown();
		}
	}
	
	private void sendTriggerToMassCalculation(){
		
		if(!"Y".equals(configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_ENABLED))) {
			return;
		}
		String drm_topic_endpoint = configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_ENDPOINT);
		String drm_topic_userName = configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_USERNAME);
		String drm_topic_password = configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_PASSWORD);
		String drm_topic_topic_name = configurationClient.getSystemParameterValue(Constants.Jms.DRM_TOPIC_NAME);
		LOGGER.logMessage("register sender drm_topic_topic_name : {} ", drm_topic_topic_name);
		
        AvsMessage avsMessage = new AvsMessage();
        avsMessage.setOperation(Constants.Jms.DRM_OPERATION);
	
			AvsJmsProducer.initConnectionPoolFactory
					(drm_topic_topic_name, 
							drm_topic_endpoint, 
							drm_topic_userName, 
							drm_topic_password, 100,10);
			try {
				AvsJmsProducer.sendTopicMessage(drm_topic_topic_name, 1, new ObjectMapper().writeValueAsString(avsMessage), 10000L);
			} catch (JsonGenerationException jge) {
				LOGGER.logError(jge);
			} catch (JsonMappingException jme) {
				LOGGER.logError(jme);
			} catch (IOException ioe) {
				LOGGER.logError(ioe);
			} catch (Exception excp) {
				LOGGER.logError(excp);
			}
	}
}

