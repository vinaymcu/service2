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

package com.accenture.avs.device.service;

import com.accenture.avs.device.json.object.devicemanager.CreateOverrideVQEGroupsConditionsRequest;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetOverrideVQEGroupsConditionsResponse;
import com.accenture.avs.device.json.object.vqe.CreateVQEGroupsRequest;
import com.accenture.avs.device.json.object.vqe.GroupAttributesDto;

/**
 * The Interface VqeService
 * 
 * @author kumar rajesh
 *
 */
public interface VqeService {

	/**
	 * This method validates and set VQE Gropu
	 * 
	 * @param veqRequest
	 * @return
	 */
	GenericResponse createVQEGroup(CreateVQEGroupsRequest veqRequest);
	
	/**
	 * This method validates request and create default attributes for vqe groups
	 * 
	 * @param groupAttributes
	 * @return GenericResponse
	 */
	GenericResponse createVqeGroupsDefaultAttributes(GroupAttributesDto groupAttributes);
	
	/**
	 * This method create Override VQE Groups Conditions in database
	 * @param createOverrideVQEGroupsConditionsRequest
	 * @return
	 */
	GenericResponse createOverrideVQEGroupsConditions(
			CreateOverrideVQEGroupsConditionsRequest createOverrideVQEGroupsConditionsRequest);

	/**
	 * This method retrieves Override VQE Groups Conditions from the database
	 * @return
	 */
	GetOverrideVQEGroupsConditionsResponse getOverrideVQEGroupsConditions();

}
