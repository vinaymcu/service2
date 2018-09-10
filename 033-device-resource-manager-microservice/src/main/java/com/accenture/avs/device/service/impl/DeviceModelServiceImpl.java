/***********************************************************************
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
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.jms.topic.sender.DeviceResourceJMSProducer;
import com.accenture.avs.device.json.object.devicemanager.CreateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.DeviceModelListDto;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceModelListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVendorListResponse;
import com.accenture.avs.device.json.object.devicemanager.ResourceList;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.VideoTypeProfile;
import com.accenture.avs.device.repository.CustomModelRepository;
import com.accenture.avs.device.repository.ModelAssignedResourcesRepository;
import com.accenture.avs.device.repository.ModelRepository;
import com.accenture.avs.device.repository.ModelVTPLinkRepository;
import com.accenture.avs.device.service.DeviceModelService;
import com.accenture.avs.device.service.DeviceServiceHelper;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.DeviceManagerValidator;
import com.accenture.avs.device.util.EntityObjectGenerator;
import com.accenture.avs.device.util.RequestResponseGenerator;

/**
 * @author rajesh.karna
 *
 */
@Service
public class DeviceModelServiceImpl implements DeviceModelService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceModelServiceImpl.class);

	/** modelRepository */
	@Autowired
	private ModelRepository modelRepository;
	
	/** customModelRepository */
	@Autowired
	private CustomModelRepository customModelRepository;

	/** modelVTPLinkRepository */
	@Autowired
	private ModelVTPLinkRepository modelVTPLinkRepository;

	/** modelAssignedResourcesRepository */
	@Autowired
	private ModelAssignedResourcesRepository modelAssignedResourcesRepository;

	/** deviceManagerValidator */
	@Autowired
	private DeviceManagerValidator deviceManagerValidator;

	/** deviceServiceHelper */
	@Autowired
	private DeviceServiceHelper deviceServiceHelper;

	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;

	/** deviceResourceJMSProducer */
	@Autowired
	DeviceResourceJMSProducer deviceResourceJMSProducer;
	
	/**
	 * This method validates and creates a new Model (i.e. Hardware Version)
	 * 
	 * @param deviceModel
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public GenericResponse createDeviceModel(CreateDeviceModelRequest deviceModel, String lastUpdateUserName,
			String lastUpdatedInterface) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("CreateDeviceModel for model={}", deviceModel.getDeviceModel());

		// Validate Device Model
		deviceManagerValidator.validateModelUniqueness(deviceModel.getDeviceModel(), deviceModel.getVendor());

		// Save Model and MaxStreamsAllowedPerQuality
		Model deviceModelEntity = modelRepository.save(
				EntityObjectGenerator.getCreateModelEntity(deviceModel, lastUpdateUserName, lastUpdatedInterface));
		if (deviceModelEntity != null) {
			for (VideoTypeProfile videoTypeProfile : deviceModel.getVideoTypeProfiles()) {
				modelVTPLinkRepository.save(
						EntityObjectGenerator.getCreateModelVtpLinkEntity(videoTypeProfile, deviceModelEntity.getId()));
			}

			for (ResourceList resourceList : deviceModel.getResourceList()) {
				modelAssignedResourcesRepository.save(EntityObjectGenerator
						.getCreateModelAssignedResourcesEntity(resourceList, deviceModelEntity.getId()));
			}
			// Trigger Document Generator
			if (deviceModelEntity.getStatus()) {
				LOGGER.logMessage("Triggered create notification for {}", Constants.HWVERSIONS);
				deviceResourceJMSProducer.generateTriggerToGLDocument(Constants.OperationType.OPERATION_CREATE, Constants.HWVERSIONS);
			}
		}
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method validates and update a Model (i.e. Hardware Version)
	 * 
	 * @param deviceModel
	 * @param lastUpdateUserName
	 * @param lastUpdatedInterface
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public GenericResponse updateDeviceModel(UpdateDeviceModelRequest deviceModel, String lastUpdateUserName,
			String lastUpdatedInterface) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("UpdateDeviceModel for model={}", deviceModel.getDeviceModel());

		// Validate Model Existence
		Model oldModelEntity = deviceServiceHelper.getDeviceModel(deviceModel.getDeviceModel(), deviceModel.getVendor(),
				Boolean.FALSE);
		boolean isMassCalculationRequired = checkChangesForMassCalc(deviceModel, oldModelEntity);
		boolean oldModelStatus = oldModelEntity.getStatus();

		// Validate Model Status Update
		if (!DeviceManagerUtil.checkNullObject(deviceModel.getStatus()) && deviceModel.getStatus() == 0) {
			deviceManagerValidator.validateModelStatusUpdate(oldModelEntity);
		}

		oldModelEntity = EntityObjectGenerator.getUpdateModelEntity(deviceModel, oldModelEntity, lastUpdateUserName,
				lastUpdatedInterface);

		// Update Device Model
		modelRepository.save(oldModelEntity);

		List<ModelVtpLink> toDeleteVtps = new ArrayList<>();
		List<ModelVtpLink> existingVtps = modelVTPLinkRepository.findModelVtpLinkByModelId(oldModelEntity.getId());
		if (CollectionUtils.isEmpty(existingVtps)) {
			for (VideoTypeProfile videoTypeProfile : deviceModel.getVideoTypeProfiles()) {
				modelVTPLinkRepository.save(
						EntityObjectGenerator.getCreateModelVtpLinkEntity(videoTypeProfile, oldModelEntity.getId()));
			}
		} else if (CollectionUtils.isNotEmpty(deviceModel.getVideoTypeProfiles())) {

			for (ModelVtpLink modelVtp : existingVtps) {
				boolean flag = false;
				for (VideoTypeProfile vtp : deviceModel.getVideoTypeProfiles()) {
					if (vtp.getVtpName().equals(modelVtp.getId().getVtpName())) {
						flag = true;
						break;
					}

				}
				if (!flag) {
					toDeleteVtps.add(modelVtp);
				}
			}

			modelVTPLinkRepository.delete(toDeleteVtps);
			for (VideoTypeProfile videoTypeProfile : deviceModel.getVideoTypeProfiles()) {
				modelVTPLinkRepository.save(
						EntityObjectGenerator.getCreateModelVtpLinkEntity(videoTypeProfile, oldModelEntity.getId()));
			}
		}

		List<ModelAssignedResources> toDeleteResource = new ArrayList<>();
		List<ModelAssignedResources> assignedResources = modelAssignedResourcesRepository
				.findModelAssignedResourcesByModelId(oldModelEntity.getId());
		if (CollectionUtils.isEmpty(assignedResources)) {
			for (ResourceList resourceList : deviceModel.getResourceList()) {
				modelAssignedResourcesRepository.save(EntityObjectGenerator
						.getCreateModelAssignedResourcesEntity(resourceList, oldModelEntity.getId()));
			}
		} else if (CollectionUtils.isNotEmpty(deviceModel.getResourceList())) {

			for (ModelAssignedResources resources : assignedResources) {
				boolean flag = false;
				for (ResourceList resourceList : deviceModel.getResourceList()) {
					if (resourceList.getName().equals(resources.getId().getResourceName())) {
						flag = true;
						break;
					}

				}
				if (!flag) {
					toDeleteResource.add(resources);
				}
			}

			modelAssignedResourcesRepository.delete(toDeleteResource);
			for (ResourceList list : deviceModel.getResourceList()) {
				modelAssignedResourcesRepository.save(
						EntityObjectGenerator.getCreateModelAssignedResourcesEntity(list, oldModelEntity.getId()));
			}
		}

		List<String> modelNameList = new ArrayList<>();
		modelNameList.add(deviceModel.getDeviceModel());

		if (isMassCalculationRequired) {
			massRecalculationService.collectDataForMass(Constants.Jms.HW_MODEL_CHANGE, modelNameList,
					false);
		}

		// Trigger Document Generator
		if (!DeviceManagerUtil.checkNullObject(deviceModel.getStatus())) {
			
			boolean retValue = !(oldModelStatus == (deviceModel.getStatus() == 1));
						
			if(retValue || oldModelStatus) {			
				LOGGER.logMessage("Triggered update notification for {}", Constants.HWVERSIONS);
				deviceResourceJMSProducer.generateTriggerToGLDocument(Constants.OperationType.OPERATION_UPDATE, Constants.HWVERSIONS);
			}
		}else {
			if(oldModelStatus) {			
				LOGGER.logMessage("Triggered update notification for {}", Constants.HWVERSIONS);
				deviceResourceJMSProducer.generateTriggerToGLDocument(Constants.OperationType.OPERATION_UPDATE, Constants.HWVERSIONS);
			}
		}

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	private boolean checkChangesForMassCalc(UpdateDeviceModelRequest deviceModel, Model oldModelEntity) {
		
		Boolean newValQoeCapable = deviceModel.getQoeCapable();
		
		if (!DeviceManagerUtil.checkNullObject(newValQoeCapable)) {
			if (!(newValQoeCapable.equals(oldModelEntity.getQoeCapable()))) {
				return true;
			 }
		}		 
		 
		List<ModelVtpLink> modelVtpLinks = deviceServiceHelper.getModelVtpLink(oldModelEntity.getId());
		if (deviceModel.getVideoTypeProfiles().size() > 0) {
			if (CollectionUtils.isEmpty(modelVtpLinks)
					|| modelVtpLinks.size() != deviceModel.getVideoTypeProfiles().size()) {
				return true;
			} else {
				boolean match = false;
				for (ModelVtpLink modelVtpLink : modelVtpLinks) {
					for (VideoTypeProfile videoTypeProfile : deviceModel.getVideoTypeProfiles()) {
						if (modelVtpLink.getId().getVtpName().equals(videoTypeProfile.getVtpName())) {
							match = true;
							break;
						}
					}
					if (!match) {
						return true;
					}
					match = false;
				}
			}
		}
		return false;
	}

	/**
	 * This method deletes model (hardware version) from database.
	 * 
	 * @param modelName
	 * @param vendor
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public GenericResponse deleteDeviceModel(String modelName, String vendor) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("DeleteDeviceModel for modelName={}, vendor={}", modelName, vendor);

		// Device Model Validation
		Model deviceModelEntity = deviceServiceHelper.getDeviceModel(modelName, vendor, Boolean.FALSE);

		// Device Model Status Validation
		if (deviceModelEntity.getStatus()) {
			throw new DeviceManagerException(ErrorCode.DEVICE_MODEL_ACTIVE, "Device model is active");
		}

		// Delete Device Model
		modelRepository.delete(deviceModelEntity.getId());				
		
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getGenericResponse(Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method get the model (hardware version) list.
	 * 
	 * @param status
	 * @param deviceModel
	 * @param deviceVendor
	 * @param startIndex
	 * @param maxResults
	 * @param sortBy
	 * @param sortOrder
	 * @return List<Model>
	 */
	@Override
	public GetDeviceModelListResponse getDeviceModelList(String status, String deviceModel, String deviceVendor, 
			Integer startIndex, Integer maxResults, String sortBy, String sortOrder) {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("GetDeviceModelList for  model={}, vendor={}, status={},"
				+ " startIndex={}, maxResults={}, sortBy={}, sortOrder={}", deviceModel, deviceVendor, status, startIndex, maxResults, sortBy,sortOrder);

		List<Model> modelEntityList = new ArrayList<>();
		if (StringUtils.isNotBlank(deviceModel) && StringUtils.isNotBlank(deviceVendor)) {
			LOGGER.logMessage("GetDeviceModelList :: Get By Model Couple");
			Model deviceModelEntity = deviceServiceHelper.getDeviceModel(deviceModel, deviceVendor, Boolean.FALSE);

			modelEntityList.add(deviceModelEntity);

		} else if (StringUtils.isNotBlank(status) && ("1".equals(status) || "0".equals(status))) {
			LOGGER.logMessage("GetDeviceModelList :: Get By Status");
			modelEntityList = customModelRepository.getDeviceModelListByStatus(DeviceManagerUtil.convertStringToBoolean(status), startIndex, maxResults, sortBy, sortOrder);
			
		} else {
			LOGGER.logMessage("GetDeviceModelList :: Get All Device Models");
			modelEntityList = customModelRepository.getAllDeviceModels(startIndex, maxResults, sortBy, sortOrder);
		}

		List<DeviceModelListDto> deviceModelDtoList = new ArrayList<>();
		for (Model modelEntity : modelEntityList) {
			List<ModelVtpLink> modelVtpLinks = deviceServiceHelper.getModelVtpLink(modelEntity.getId());
			List<ModelAssignedResources> assignedResources = deviceServiceHelper
					.getModelAssignedResources(modelEntity.getId());
			DeviceModelListDto deviceModelListDto = RequestResponseGenerator.getDeviceModelDto(modelEntity,
					modelVtpLinks, assignedResources);
			deviceModelDtoList.add(deviceModelListDto);
		}
		LOGGER.logMessage("GetDeviceModelList Successful:  model={}, vendor={}, status={}", deviceModel, deviceVendor,
				status);
		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getDeviceModelList(deviceModelDtoList, Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

	/**
	 * This method retrieve all vendors from database and generate response.
	 * 
	 * @return List<String>
	 */
	@Override
	public GetVendorListResponse getVendorList() {
		long startTime = System.currentTimeMillis();
		LOGGER.logMessage("GetVendorList, retrieve all distinct vendors from model");

		List<String> vendorList = deviceServiceHelper.getVendorList();

		LOGGER.logMessage("GetVendorList Successful: retrieved all distinct vendors from Entity");

		Long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.logMethodEnd(executionTime);
		return RequestResponseGenerator.getVendorListResponse(vendorList, Constants.Status.SUCCESS_RESULT_CODE,
				Constants.Status.SUCCESS_RESULT_DESCRIPTION, executionTime);
	}

}
