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
package com.accenture.avs.device.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.DeviceAudit;
import com.accenture.avs.device.repository.CustomDeviceAuditRepository;
import com.accenture.avs.device.tenant.TenantContext;
import com.accenture.avs.device.tenant.Tenants;
import com.accenture.avs.device.util.Constants;

/**
 * A custom repository for DeviceAudit
 * 
 * @author Singh.Saurabh
 *
 */
@Repository
public class CustomDeviceAuditRepositoryImpl implements CustomDeviceAuditRepository {
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(CustomDeviceAuditRepositoryImpl.class);
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;	

	/**
	 * This method finds Device Audit Logs for the provided deviceId using offset and max result	
	 * 
	 * @param deviceId
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * 
	 * @return List<DeviceAudit>
	 */
	public List<DeviceAudit> findByDeviceIdAndStartIndexAndMaxResults(String deviceId, Integer startIndex, Integer maxResults, String sortBy, String sortOrder) {
		long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_DEVICEID, deviceId);
		queryDBParameters.put(Constants.DB_PARAM_STARTINDEX, startIndex);
		queryDBParameters.put(Constants.DB_PARAM_MAXRESULTS, maxResults);
		queryDBParameters.put(Constants.DB_PARAM_SORT_BY, sortBy);
		queryDBParameters.put(Constants.DB_PARAM_SORT_ORDER, sortOrder);		
		TenantContext.setCurrentTenant(Tenants.READ);
		List<DeviceAudit> deviceAuditList = null;
		StringBuilder queryBuilder = new StringBuilder(GET_DEVICES_AUDIT_BY_DEVICEID);
		try {
			
			queryBuilder.append(" ORDER BY ");
			queryBuilder.append(sortBy);
			queryBuilder.append(" ");
			queryBuilder.append(sortOrder);
			
			deviceAuditList = entityManager.createQuery(queryBuilder.toString(), DeviceAudit.class)
							.setParameter(Constants.DB_PARAM_DEVICEID, deviceId)
							.setFirstResult(startIndex).setMaxResults(maxResults).getResultList();			
		} catch (NoResultException noResultException) {
			LOGGER.logMessage("No devices audit log found for deviceId={},  startIndex= {} "
					+ ", maxResults={}, sortBy={}, sortOrder={}", deviceId, startIndex, maxResults, sortBy, sortOrder);		
		} finally {
			LOGGER.logDBQuery(queryBuilder.toString(), queryDBParameters, System.currentTimeMillis() - startTime);
		}		
		return deviceAuditList;
	}
	
	/**
	 * This method finds list of Device Audit Logs	
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return List<DeviceAudit>
	 */
	@Override
	public List<DeviceAudit> findByStartDateAndEndDate(Long startDate, Long endDate) {
		long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_STARTDATE, startDate);
		queryDBParameters.put(Constants.DB_PARAM_ENDDATE, endDate);
		String printQuery = GET_DEVICES_AUDIT_BY_STARTDATE_AND_ENDDATE;
		TenantContext.setCurrentTenant(Tenants.READ);
		List<DeviceAudit> deviceAuditList = null;
		try {
			deviceAuditList = entityManager.createQuery(GET_DEVICES_AUDIT_BY_STARTDATE_AND_ENDDATE, DeviceAudit.class)
					.setParameter(Constants.DB_PARAM_STARTDATE, startDate)
					.setParameter(Constants.DB_PARAM_ENDDATE, endDate).getResultList();
		} catch (NoResultException noResultException) {
			LOGGER.logMessage("No devices audit log found for startDate={},  endDate= {} ", startDate, endDate);
		} finally {
			LOGGER.logDBQuery(printQuery, queryDBParameters, System.currentTimeMillis() - startTime);
		}
		return deviceAuditList;
	}
}
