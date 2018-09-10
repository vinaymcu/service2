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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.DefaultGroupAttributes;
import com.accenture.avs.device.entity.GroupAttributes;
import com.accenture.avs.device.entity.OverrideVQEGroupsCondition;
import com.accenture.avs.device.entity.VQEGroup;
import com.accenture.avs.device.json.object.devicemanager.CreateOverrideVQEGroupsConditionsRequest;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetOverrideVQEGroupsConditionsResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVQEGroupsConditionDTO;
import com.accenture.avs.device.json.object.vqe.CreateVQEGroupsRequest;
import com.accenture.avs.device.json.object.vqe.GroupAttributesDto;
import com.accenture.avs.device.repository.DefaultGroupAttributesRepository;
import com.accenture.avs.device.repository.GroupAttributeRepository;
import com.accenture.avs.device.repository.GroupConidionRepository;
import com.accenture.avs.device.repository.VqeGroupRepository;
import com.accenture.avs.device.service.VqeService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.EntityObjectGenerator;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * The Class UserServiceImpl.
 */
@Service
public class VqeServiceImpl implements VqeService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(VqeServiceImpl.class);

	/** defaultGroupAttributesRepository */
	@Autowired
	private DefaultGroupAttributesRepository defaultGroupAttributesRepository;

	/** groupConitionRepository */
	@Autowired
	private GroupConidionRepository groupConidionRepository;

	/** vqeGroupRepository */
	@Autowired
	VqeGroupRepository vqeGroupRepository;

	/** groupAttributeRepository */
	@Autowired
	GroupAttributeRepository groupAttributeRepository;

	/** 
	 * This method creates VQE Group 
	 * 
	 * @see com.accenture.avs.device.service.VqeService#createVQEGroup(com.accenture.avs.device.json.object.vqe.CreateVQEGroupsRequest)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GenericResponse createVQEGroup(CreateVQEGroupsRequest veqRequest) {
		long startTime = System.currentTimeMillis();
		// set VQE-Group data
		VQEGroup vqeGroupEntity = new VQEGroup();
		vqeGroupEntity.setRetEnable(veqRequest.getRetEnable());
		vqeGroupEntity.setRccEnable(veqRequest.getRccEnable());
		vqeGroupEntity.setNetworkBufferSize(veqRequest.getNetworkBufferSize());
		vqeGroupEntity.setMaxRecvBwSD(veqRequest.getMaxRecvBwSD());
		vqeGroupEntity.setMaxRecvBwHD(veqRequest.getMaxRecvBwHD());
		vqeGroupEntity.setMaxRecvRccBwSD(veqRequest.getMaxRecvRccBwSD());
		vqeGroupEntity.setMaxRecvRccBwHD(veqRequest.getMaxRecvRccBwHD());

		// set group attributes data
		GroupAttributes groupAttributesEntity = buildGroupAttribute(veqRequest);

		// save vqeGroupEntity data into table VQE_GROUP.
		VQEGroup vqeGroup = vqeGroupRepository.save(vqeGroupEntity);
		// save groupAttributesEntity data into table GROUP_ATTRIBUTES.
		if (groupAttributesEntity != null) {
			groupAttributesEntity.setGroupId(vqeGroup.getGroupId());
			groupAttributeRepository.save(groupAttributesEntity);
		}
		LOGGER.logMessage("Id of VQE Group : {}", vqeGroup.getGroupId());
		
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * @param veqRequest
	 * @return
	 */
	private GroupAttributes buildGroupAttribute(CreateVQEGroupsRequest veqRequest) {
		LOGGER.logMessage("Start: Set Group Attribute data.");
		GroupAttributes groupAttributesEntity = null;
		if (Objects.nonNull(veqRequest.getGroupAttributes())) {
			groupAttributesEntity = EntityObjectGenerator.buildGroupAttributeObject(veqRequest.getGroupAttributes());
		}
		
		LOGGER.logMessage("End: Set Group Attribute data.");
		return groupAttributesEntity;
	}

	/**
	 * create/update VqeGroups DefaultAttributes
	 * 
	 * @param groupAttributes
	 * @param GenericResponse
	 */
	@Override
	@Transactional
	public GenericResponse createVqeGroupsDefaultAttributes(GroupAttributesDto groupAttributes) {
		long startTime = System.currentTimeMillis();
		DefaultGroupAttributes defaultGroupAttributesEntity = defaultGroupAttributesRepository
				.getDefaultGroupAttributes();
		if (Objects.isNull(defaultGroupAttributesEntity)) {
			defaultGroupAttributesEntity = new DefaultGroupAttributes();
		}
		DefaultGroupAttributes defaultGroupAttributes = defaultGroupAttributesRepository.save(
				EntityObjectGenerator.getDefaultGroupAttributesEntity(defaultGroupAttributesEntity, groupAttributes));
		LOGGER.logMessage("Id of defaultGroupAttributes : {}", defaultGroupAttributes.getId());
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method creates the vqe conditions for the given group id
	 * 
	 * @param createOverrideVQEGroupsConditionsRequest
	 * @return GenericResponse
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public GenericResponse createOverrideVQEGroupsConditions(
			CreateOverrideVQEGroupsConditionsRequest createOverrideVQEGroupsConditionsRequest) {
		long startTime = System.currentTimeMillis();
		OverrideVQEGroupsCondition overrideVQEGroupsCondition = EntityObjectGenerator
				.getVQEGroupConditionEntity(createOverrideVQEGroupsConditionsRequest);
		createOverrideVQEGroupsConditions(overrideVQEGroupsCondition);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method retrieves all the vqe conditions
	 * 
	 * @return GetOverrideVQEGroupsConditionsResponse
	 */
	@Override
	public GetOverrideVQEGroupsConditionsResponse getOverrideVQEGroupsConditions() {
		long startTime = System.currentTimeMillis();
		List<OverrideVQEGroupsCondition> groupsConditions = null;
		try {
			groupsConditions = groupConidionRepository.findAll();
		} finally {
			LOGGER.logDBQuery("SELECT * FROM OVERRIDE_VQE_GROUPS_CONDITION", null,
					System.currentTimeMillis() - startTime);
		}
		List<GetVQEGroupsConditionDTO> conditionDTOs = new ArrayList<GetVQEGroupsConditionDTO>();
		EntityObjectGenerator.getVQEGroupConsitionDTOFromEntity(conditionDTOs, groupsConditions);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getVQEGroupsConditionsResponse(conditionDTOs, executionTime);
	}

	private void createOverrideVQEGroupsConditions(OverrideVQEGroupsCondition overrideVQEGroupsCondition) {
		Long startTime = System.currentTimeMillis();
		Map<String, Object> queryDBParameters = new HashMap<>();
		queryDBParameters.put(Constants.DB_PARAM_GROUPID, overrideVQEGroupsCondition.getGroupId());
		queryDBParameters.put(Constants.DB_PARAM_MACADDRESS, overrideVQEGroupsCondition.getMacAddress());
		queryDBParameters.put(Constants.DB_PARAM_NETWORK_BUFFER_SIZE,
				overrideVQEGroupsCondition.getNetworkBufferSize());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVDEVICES, overrideVQEGroupsCondition.getNumSubIptvDevices());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVQOEHDSTBS,
				overrideVQEGroupsCondition.getNumSubIptvQoeHDStbs());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVQOESDSTBS,
				overrideVQEGroupsCondition.getNumSubIptvQoeSDStbs());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVQOESTBS, overrideVQEGroupsCondition.getNumSubIptvQoeStbs());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVVQEHDSTBS,
				overrideVQEGroupsCondition.getNumSubIptvVqeHDStbs());
		queryDBParameters.put(Constants.DB_PARAM_NUMSUBIPTVVQESDSTBS,
				overrideVQEGroupsCondition.getNumSubIptvVqeSDStbs());
		try {
			groupConidionRepository.save(overrideVQEGroupsCondition);
		} finally {
			LOGGER.logDBQuery(
					"INSERT INTO OVERRIDE_VQE_GROUPS_CONDITION (GROUPID, MACADDRESS, NETWORKBUFFERSIZE,...) VALUES ( ?1, ?2, ...)",
					queryDBParameters, System.currentTimeMillis() - startTime);
		}
	}

}
