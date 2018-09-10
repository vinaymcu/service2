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

import java.util.List;

import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceListResponse;

/**
 * This is a Helper Class for DeviceServiceBeanImpl.
 * 
 * @author Singh.Saurabh
 *
 */
public interface DeviceServiceHelper {

	/**
	 * This method finds the device model in database. If found then returns,
	 * else throws error.
	 * 
	 * @param deviceModel
	 * @param vendor
	 * @param checkIsActive
	 * 
	 * @return
	 */
	Model getDeviceModel(String deviceModel, String vendor, boolean checkIsActive);

	/**
	 * This method returns Device according to provided device id
	 * 
	 * @param deviceId
	 * @param doValidation
	 * 
	 * @return
	 */
	Device getDevice(String deviceId, boolean doValidation);

	/**
	 * Deassociate device from user
	 * @param deviceEntity
	 * @param lastUpdatedUser
	 */
	void deassociateDeviceFromUser(Device deviceEntity, String lastUpdatedUser);
	
	/**
	 * This generates a unique device name for the Subscriber. The default name
	 * will be DEVICE_X where X shall be replaced incrementally in such way a
	 * subscriber shall not have two devices with same name
	 * 
	 * @param assignedToUserName
	 * @return
	 */
	String getUniqueDeviceName(String assignedToUserName);

	/**
	 * 
	 * @param searchBy
	 * @param searchValue
	 * @param searchOperation
	 * @param sortBy
	 * @param sortOrder
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	GetDeviceListResponse getDeviceList(String searchBy, String searchValue, String searchOperation, String sortBy, String sortOrder,
			Integer startIndex, Integer pageSize);

	List<ModelVtpLink> getModelVtpLink(Long modelId);

	List<ModelAssignedResources> getModelAssignedResources(Long modelId);
	
	/**
	 * This method retrieve all distinct vendors from model in database.
	 * 
	 * @return List<String>
	 */
	List<String> getVendorList();
	
}
