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

package com.accenture.avs.device.repository;

import java.util.List;

import com.accenture.avs.device.entity.Model;

/**
 * The Interface CustomModelRepository.
 */
public interface CustomModelRepository {
  
	/**
	 * Geta Device Model List By Status
	 * @param status
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * @return List<Model>
	 */
	List<Model> getDeviceModelListByStatus(Boolean status, Integer startIndex, Integer maxResults, String sortBy, String sortOrder);
	
	/**
	 * Gets All Device Models
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * @return List<Model>
	 */
	List<Model> getAllDeviceModels(Integer startIndex, Integer maxResults, String sortBy, String sortOrder);

}
