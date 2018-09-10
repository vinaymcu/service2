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
package com.accenture.avs.device.config.model;

/**
 * 
 * @author singh.saurabh
 *
 */
public class DeviceResourceConfigProperty {

	/** batchSize */
	private Integer batchSize;
	
	/** parallel Execution Threads */
	private Integer parallelExecutionThreads;
	
	/** userLogTableCleanupInterval */
	private Integer userLogTableCleanupInterval;
    
	/**
	 * @return the batchSize
	 */
	public Integer getBatchSize() {
		return batchSize;
	}
	/**
	 * @param batchSize the batchSize to set
	 */
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	/**
	 * @return the parallelExecutionThreads
	 */
	public Integer getParallelExecutionThreads() {
		return parallelExecutionThreads;
	}
	/**
	 * @param parallelExecutionThreads the parallelExecutionThreads to set
	 */
	public void setParallelExecutionThreads(Integer parallelExecutionThreads) {
		this.parallelExecutionThreads = parallelExecutionThreads;
	}
	/**
	 * @return the userLogTableCleanupInterval
	 */
	public Integer getUserLogTableCleanupInterval() {
		return userLogTableCleanupInterval;
	}
	/**
	 * @param userLogTableCleanupInterval the userLogTableCleanupInterval to set
	 */
	public void setUserLogTableCleanupInterval(Integer userLogTableCleanupInterval) {
		this.userLogTableCleanupInterval = userLogTableCleanupInterval;
	}
    
	
}
