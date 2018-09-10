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
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.DeviceAudit;
import com.accenture.avs.device.repository.CustomDeviceAuditRepository;
import com.accenture.avs.device.service.DeviceAuditLogsService;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.PaginationUtils;

/**
 * This class will perform all the Device Audit Log related operations.
 * 
 * @author Singh.Saurabh
 *
 */
@Service
public class DeviceAuditLogsServiceImpl implements DeviceAuditLogsService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceAuditLogsServiceImpl.class);

	/** customDeviceAuditRepository */
	@Autowired
	private CustomDeviceAuditRepository customDeviceAuditRepository;

	/**
	 * This method gets the audit logs of all the updates done on the requested
	 * devices within the given time frame.
	 * 
	 * @param deviceId
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * 
	 * @return List<DeviceAudit>
	 */
	@Override
	public List<DeviceAudit> getDeviceAuditLogs(String deviceId, Integer startIndex, Integer maxResults, String sortBy, String sortOrder) {
		long currentTime = System.currentTimeMillis();
		String sortByValue = PaginationUtils.GetDeviceAuditsSortByValues.getSortByValue(sortBy);
		String sortOrderValue = PaginationUtils.SortOrderValues.getSortOrderValue(sortOrder);
		LOGGER.logMessage("GET DeviceAuditLogs with following parameters :: deviceId=" + deviceId + ", startIndex="
				+ startIndex + ", maxResults=" + maxResults + ", sortBy=" + sortByValue + ", sortOrder=" + sortOrderValue);
		
		List<DeviceAudit> deviceAuditList = customDeviceAuditRepository.findByDeviceIdAndStartIndexAndMaxResults(deviceId, startIndex, maxResults, sortByValue, sortOrderValue);
		
		LOGGER.logMessage("GET DeviceAuditLogs Successful :: deviceId=" + deviceId);
		LOGGER.logMethodEnd(System.currentTimeMillis() - currentTime);
		return deviceAuditList;
	}
	
	/**
	 * This method returns audit logs for the 
	 * given time frame
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public List<DeviceAudit> getDeviceAuditLogsByStartDateAndEndDate(Long startDate, Long endDate) {
		long currentTime = System.currentTimeMillis();
		LOGGER.logMessage("GET DeviceAuditLogs with following parameters ::  startDate="
				+ startDate + ", endDate=" + endDate);
		List<DeviceAudit> deviceAuditList = customDeviceAuditRepository.findByStartDateAndEndDate(startDate, endDate);
		LOGGER.logMethodEnd(System.currentTimeMillis() - currentTime);
		return deviceAuditList;
	}
}
