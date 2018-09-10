/**************************************************************************
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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelAssignedResourcesId;
import com.accenture.avs.device.entity.ModelVtpLink;

/**
 * Repository interface for Model entity.
 * 
 * @author 
 *
 */
@Repository
public interface ModelAssignedResourcesRepository extends JpaRepository<ModelAssignedResources, ModelAssignedResourcesId> {

	@Modifying
	@Query("delete ModelAssignedResources MAR where MAR.id.modelId = :id")
	int deleteByModelId(@Param("id") Long id);

	/**
	 * 
	 * This method returns ModelAssignedResources using model id.
	 * 
	 * @param modelId
	 * @return
	 */
	@Query("SELECT MVL FROM ModelAssignedResources MVL WHERE MVL.id.modelId= :modelId")
	List<ModelAssignedResources> findModelAssignedResourcesByModelId(@Param("modelId") Long modelId);
	
}
