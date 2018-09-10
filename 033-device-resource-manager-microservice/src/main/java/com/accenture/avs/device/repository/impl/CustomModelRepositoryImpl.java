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
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.repository.CustomModelRepository;
import com.accenture.avs.device.util.PaginationUtils;

/**
 * The Class CustomModelRepositoryImpl.
 *
 * @author muhammad.yunus
 * @since 1.0
 */

@Repository
public class CustomModelRepositoryImpl implements CustomModelRepository {
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(CustomModelRepositoryImpl.class);
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Model> getDeviceModelListByStatus(Boolean status, Integer startIndex, Integer maxResults, String sortBy,
			String sortOrder) {		
		Long startTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<>();
		Query query = null;
		StringBuilder queryBuilder = new StringBuilder();
		try {
			queryBuilder.append(" SELECT M FROM Model M ");
			addStatusFilter(queryBuilder, params, status);
			addSortByAndSortOrder(queryBuilder, sortBy, sortOrder);
			query = entityManager.createQuery(queryBuilder.toString(), Model.class);
			buindParameter(query, params);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxResults);
			return query.getResultList();

		} finally {
			LOGGER.logDBQuery(queryBuilder.toString(), params, System.currentTimeMillis() - startTime);
		}
	}
	
	/**
	 * Gets All Device Models
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * @return List<Model>
	 */
	public List<Model> getAllDeviceModels(Integer startIndex, Integer maxResults, String sortBy, String sortOrder) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<>();
		Query query = null;
		StringBuilder queryBuilder = new StringBuilder();
		try {
			queryBuilder.append(" SELECT M FROM Model M ");		
			addSortByAndSortOrder(queryBuilder, sortBy, sortOrder);
			query = entityManager.createQuery(queryBuilder.toString(), Model.class);
			buindParameter(query, params);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxResults);
			return query.getResultList();

		} finally {
			LOGGER.logDBQuery(queryBuilder.toString(), params, System.currentTimeMillis() - startTime);
		}	
	}

	
	
	/**
	 * Add sortBy and sortOrder
	 * @param queryBuilder
	 * @param sortBy
	 * @param sortOrder
	 */
	private void addSortByAndSortOrder(StringBuilder queryBuilder, String sortBy, String sortOrder) {
		queryBuilder.append(" ORDER BY ");
		queryBuilder.append(PaginationUtils.GetDeviceModelsSortByValues.getSortByValue(sortBy));
		queryBuilder.append(" ");
		queryBuilder.append(PaginationUtils.SortOrderValues.getSortOrderValue(sortOrder));
	}
	/**
	 * Add Status Filter
	 * @param queryBuilder
	 * @param params
	 * @param status
	 * 
	 */
	private void addStatusFilter(StringBuilder queryBuilder, Map<String, Object> params, Boolean status) {
			queryBuilder.append(" WHERE ");
			queryBuilder.append(" M.status=:status ");
			params.put("status", status);
	}

	/**
	 * @param query
	 * @param params
	 */
	private void buindParameter(Query query, Map<String, Object> params) {
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
	}
	

}
