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

import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.entity.ModelVtpLinkId;

/**
 * Repository interface for Model entity.
 * 
 * @author
 *
 */
@Repository
public interface ModelVTPLinkRepository extends JpaRepository<ModelVtpLink, ModelVtpLinkId> {

	@Modifying
	@Query("delete ModelVtpLink MVL where MVL.id.modelId = :id")
	int deleteByModelId(@Param("id") Long id);

	/**
	 * 
	 * This method returns ModelVtpLink using model id.
	 * 
	 * @param modelId
	 * @return
	 */
	@Query("SELECT MVL FROM ModelVtpLink MVL WHERE MVL.id.modelId= :modelId")
	List<ModelVtpLink> findModelVtpLinkByModelId(@Param("modelId") Long modelId);
	
	/**
	 * 
	 * This method returns Model Vtp Names using model id.
	 * 
	 * @param modelId
	 * @return
	 */
	@Query("SELECT MVL.id.vtpName FROM ModelVtpLink MVL WHERE MVL.id.modelId= :modelId")
	List<String> findModelVtpNamesByModelId(@Param("modelId") Long modelId);
	
}
