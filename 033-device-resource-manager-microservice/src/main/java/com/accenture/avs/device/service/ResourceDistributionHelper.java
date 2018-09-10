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
import java.util.Map;
import java.util.Set;

import com.accenture.avs.device.config.model.VTP;
import com.accenture.avs.device.dto.UserDeviceLimitDto;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.User;

/**
 * This is a helper class for the resource distribution process.
 * 
 * @author singh.saurabh
 *
 */
public interface ResourceDistributionHelper {

	/**
	 * This method returns the inclusive device limit for a user.
	 * 
	 * @param assignedUser
	 * @return
	 */
	Map<String, UserDeviceLimitDto> calculatedInclusiveDeviceLimit(User assignedUser);

	/**
	 * This method finds the common VTPs and returns Max bandwidth VTP for each
	 * content type.
	 * 
	 * @param modelVtpNames
	 * @param bwProfileVtpNames
	 * @return
	 */
	Map<String, VTP> findCommonVtpsWithMaxBw(List<String> modelVtpNames, List<String> bwProfileVtpNames);

	/**
	 * This method calculates the device maximum capability.
	 * 
	 * @param supportedContentQualities
	 * @return
	 */
	String calculateDeviceMaximumCapability(Set<String> supportedContentQualities);

	/**
	 * This methodn sets the total assigned
	 * 
	 * @param assignedUser
	 * @param bwProfileBitrate
	 */
	void calculateFreeAndQoeBandwidth(User assignedUser, Long bwProfileBitrate);

	/**
	 * This method returns the applicable TVQualityInterest.
	 * 
	 * @param tvQualityInterest
	 * @return
	 */
	String calculateApplicableTvQualityInterest(String tvQualityInterest);

	/**
	 * This method calculates and distributes the VQE bandwidth among the VQE
	 * enabled devices.
	 * 
	 * @param vqeEnabledUserDeviceList
	 * @param assignedUser
	 * @param mdcBandwidth
	 * @param totalBwAssignedToQoeDevices
	 */
	void calculateVqeBandwidth(List<Device> vqeEnabledUserDeviceList, User assignedUser, Long mdcBandwidth,
			Long totalBwAssignedToQoeDevices);

	/**
	 * This method returns calculates the tv quality interest and device capability
	 * of each the provided devices. It maintains a list of devices which are valid
	 * for profile calculation and returns a map of max vtps per content quality for
	 * each model.
	 * 
	 * @param userDeviceList
	 * @param userDevicesApplicableForCalculation
	 * @param bwProfileVtpNames
	 * @return
	 */
	Map<Long, Map<String, VTP>> calculateDeviceMaxVtpInterestAndDeviceCapability(List<Device> userDeviceList,
			List<String> bwProfileVtpNames);

	/**
	 * This method sets the DeviceMaxBWAllowedPerQuality for the device upto a max
	 * defined upper limit.
	 * 
	 * @param userDevice
	 * @param maxBandwidthPerQualityMap
	 */
	void setMaxBandwidthPerQualityForDevice(Device userDevice, Map<String, VTP> maxBandwidthPerQualityMap);
}
