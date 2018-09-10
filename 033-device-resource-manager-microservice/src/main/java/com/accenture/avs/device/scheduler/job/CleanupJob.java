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
package com.accenture.avs.device.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.service.CleanupService;
import com.accenture.avs.device.util.SpringBeanUtils;

/**
 * @author surendra.kumar
 *
 */
@Service
public class CleanupJob  implements Job{

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(CleanupJob.class);
	
	/*@Autowired
	private CleanupService cleanupService;*/
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		LOGGER.logMessage("CleanupJob starts.. ");
		CleanupService cleanupService = SpringBeanUtils.getBean(CleanupService.class);
		 cleanupService.processCleanup();
		
		LOGGER.logMessage(" All records deleted from user_log table.");
		LOGGER.logMessage(" CleanupJob Done");
		
	}
	

}
