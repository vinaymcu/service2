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
package com.accenture.avs.device.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.scheduler.job.CleanupJob;



/**
 * @author surendra.kumar
 *
 */
@Component
public class CleanupServiceScheduler {
	/** The logger. */
	private final static LoggerWrapper LOGGER = new LoggerWrapper(CleanupServiceScheduler.class);
	
	@Autowired
	private ConfigurationClient configurationClient;

	public void scheduleCleanup() {
		Integer cleanupInterval = configurationClient.getDrmResources().getUserLogTableCleanupInterval();

		LOGGER.logMessage("schedule Cleanup with interval in days : {}" , cleanupInterval);
		try {
		JobDetail cleanupJob = JobBuilder.newJob(CleanupJob.class).withIdentity("userLogCleanup", "Cleanup").build();
		
		Integer intervalInHours = cleanupInterval*24;
		
		String expression = "0 0 0/"+intervalInHours+ " * * ?";

		 Trigger cleanupTrigger = TriggerBuilder.newTrigger().withIdentity("userLogCleanupTrigger", "Cleanup").withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
		 
		 Scheduler cleanupScheduler = new StdSchedulerFactory().getScheduler();
		 cleanupScheduler.start();
		 cleanupScheduler.scheduleJob(cleanupJob, cleanupTrigger);
         
		} catch (SchedulerException se) {
			LOGGER.logError(se, "Error in schedule Cleanup");
		}
		
		LOGGER.logMessage(" Cleanup scheduled with interval in days : {}" , cleanupInterval);
		
	}
}
