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

import com.accenture.avs.device.json.object.devicemanager.CreateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceModelListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVendorListResponse;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceModelRequest;

/**
 * @author rajesh.karna
 *
 */
public interface DeviceModelService {

	/**
	 * This method validates and creates a Model (i.e. Hardware Version)
	 * 
	 * @param deviceModel
	 * @param userId
	 * @param lastUpdatedInterface
	 * 
	 * @return GenericResponse
	 * 
	 */
	GenericResponse createDeviceModel(CreateDeviceModelRequest deviceModel, String userId, String lastUpdatedInterface);

	/**
	 * This method validates and update a Model (i.e. Hardware Version)
	 * 
	 * @param deviceModel
	 * @param userId
	 * @param lastUpdatedInterface
	 * 
	 * @return GenericResponse
	 * 
	 */
	GenericResponse updateDeviceModel(UpdateDeviceModelRequest deviceModel, String userId, String lastUpdatedInterface);

	/**
	 * This method deletes model (hardware version) from database.
	 * 
	 * @param modelName
	 * @param vendor
	 * 
	 * @return GenericResponse
	 */
	GenericResponse deleteDeviceModel(String modelName, String vendor);

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
	 * 
	 * @return GetDeviceModelListResponse
	 */
	GetDeviceModelListResponse getDeviceModelList(String status, String deviceModel, String deviceVendor, Integer startIndex, Integer maxResults, String sortBy, String sortOrder);
	
	
	/**
	 * This method get vendor list.
	 * 
	 * @return GetVendorListResponse
	 */
	GetVendorListResponse getVendorList();
	

}
