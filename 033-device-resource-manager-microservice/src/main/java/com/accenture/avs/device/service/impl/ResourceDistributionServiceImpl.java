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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.config.model.BandwidthAndVQEProperty;
import com.accenture.avs.device.config.model.BandwidthProfile;
import com.accenture.avs.device.config.model.VTP;
import com.accenture.avs.device.dto.UserDeviceLimitDto;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.enums.DeviceProfiles;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.service.ResourceDistributionHelper;
import com.accenture.avs.device.service.ResourceDistributionService;
import com.accenture.avs.device.util.Constants;
import com.accenture.avs.device.util.DeviceManagerUtil;
import com.accenture.avs.device.util.comparator.AssignmentDateDeviceComparator;

/**
 * The service is defined for core profile distribution
 * 
 * @author singh.saurabh
 *
 */
@Service
public class ResourceDistributionServiceImpl implements ResourceDistributionService {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(ResourceDistributionServiceImpl.class);

	/** userRepository */
	@Autowired
	private UserRepository userRepository;

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;

	/** resourceDistributionHelper */
	@Autowired
	private ResourceDistributionHelper resourceDistributionHelper;

	/** configurationClient */
	@Autowired
	private ConfigurationClient configurationClient;

	/**
	 * This method performs following operations:- 1. Calculates the Device Profiles
	 * 2. Update Device Profile in database
	 * 
	 * @param assignedToUserName
	 * @param isMassUpdate
	 * 
	 */
	@Override
	public Boolean getUserDeviceProfile(String assignedToUserName, Boolean isMassUpdate) {

		LOGGER.logMessage("Profile Calculation :: Started for userName={}, isMassUpdate={}", assignedToUserName,
				isMassUpdate);

		// Get User Details
		User assignedUser = userRepository.findByUserName(assignedToUserName);

		// For Mass Updtae
		if (isMassUpdate) {
			if (assignedUser.getMassCalculationCounter() > 0) {
				assignedUser.setMassCalculationCounter(assignedUser.getMassCalculationCounter() - 1);
			}
			assignedUser.setMassIdentifier(null);
		}

		// Get Bw Profile (Memory)
		BandwidthProfile subscriberBwProfile = configurationClient
				.findBandwidthProfile(assignedUser.getBandwithProfile());

		// Validate Bandwidth Profile
		if (DeviceManagerUtil.checkNullObject(subscriberBwProfile)
				|| DeviceManagerUtil.checkNullObject(subscriberBwProfile.getVideoTypes())
				|| subscriberBwProfile.getVideoTypes().isEmpty()) {
			LOGGER.logMessage(
					"Profile Calculation :: Invalid Bandwidth Profile Assigned. Profile Calculation Cancelled");
			return Boolean.FALSE;
		}

		// Get Global Config (Memory)
		BandwidthAndVQEProperty bandwidthAndVQEProperty = configurationClient.getGlobalVqeAndMdcBandwidthFlag();
		Long mdcBandwidth = bandwidthAndVQEProperty.getMdcBandwidthValue().longValue();
		Boolean isUseGlobalQoEBW = bandwidthAndVQEProperty.getUseGlobalVqeBW();

		LOGGER.logMessage("Profile Calculation :: mdcBandwidth={}, isUseGlobalQoEBW={}", mdcBandwidth,
				isUseGlobalQoEBW);

		// Get total subscriber bandwidth
		resourceDistributionHelper.calculateFreeAndQoeBandwidth(assignedUser, subscriberBwProfile.getBandwidth());

		// Get User's IPTV Devices and sorted in ascending order of assignment date.
		List<Device> userDeviceList = deviceRepository.findByAssignedToUserNameAndPlatform(assignedToUserName,
				Constants.PlatformType.IPTV);
		if(userDeviceList.isEmpty()) {
			LOGGER.logMessage("Profile Calculation :: No IPTV devices found");
			return Boolean.FALSE;
		}
		Collections.sort(userDeviceList, new AssignmentDateDeviceComparator());
		LOGGER.logMessage("Profile Calculation :: Total Used Devices for recalculation={}", userDeviceList.size());

		
		// Inclusive Device Limit
		Map<String, UserDeviceLimitDto> userLimitForContentQualityMap = resourceDistributionHelper
				.calculatedInclusiveDeviceLimit(assignedUser);

		// Device Max Bandwidth Per Content Quality Calculation
		Map<Long, Map<String, VTP>> modelMaxVtpPerContentQualityList = resourceDistributionHelper
				.calculateDeviceMaxVtpInterestAndDeviceCapability(userDeviceList, subscriberBwProfile.getVideoTypes());

		// device profile to assign will range from 1 to 3
		int deviceProfileId = 1;
		int totalDeviceCalculationCompleted = 0;
		Long totalBwAssignedToQoeDevices = 0L;
		Long availableBandwidth = assignedUser.getFreeBandwidth();

		// List maintaining VQE enabled devices
		List<Device> vqeEnabledUserDeviceList = new ArrayList<>();
		do {

			LOGGER.logMessage("Profile Calculation :: Profile Cycle for {} profile",
					DeviceProfiles.fromPriority(deviceProfileId));
			LOGGER.logMessage("availableBandwidth :: {}", availableBandwidth);
			
			for (int deviceIdx = 0; deviceIdx < userDeviceList.size(); deviceIdx++) {
				
				Device userDevice = userDeviceList.get(deviceIdx);
				
				try {

					if (userDevice.getDeviceProfileDto().getIsDeviceUpgradable()) {

						// if profile to be assigned is more than max capability or tv quality interest
						if (DeviceProfiles.fromContentQuality(userDevice.getDeviceProfileDto().getDeviceCapability())
								.getPriority() < deviceProfileId
								|| DeviceProfiles.fromContentQuality(userDevice.getTvQualityInterest())
										.getPriority() < deviceProfileId) {

							totalDeviceCalculationCompleted++;							
							// Set Upgradable false
							userDevice.getDeviceProfileDto().setIsDeviceUpgradable(Boolean.FALSE);							

							LOGGER.logMessage("Profile Calculation :: Unable to upgrade deviceId={} further",
									userDevice.getDeviceId());
							continue;
						}

						// Find max VTP for current profile and device model
						VTP deviceProfileVtp = modelMaxVtpPerContentQualityList.get(userDevice.getModel().getId())
								.get(DeviceProfiles.fromPriority(deviceProfileId).getContentType());

						if (DeviceManagerUtil.checkNullObject(deviceProfileVtp)) {
							LOGGER.logMessage(
									"Profile Calculation :: No valid VTP found for [{}] profile for DeviceId={}",
									DeviceProfiles.fromPriority(deviceProfileId).getContentType(),
									userDevice.getDeviceId());
							continue;
						}

						long assingedBW = userDevice.getDeviceProfileBandwidth();
						
						if (availableBandwidth >= (Long.parseLong(deviceProfileVtp.getBitRate()) - assingedBW + mdcBandwidth)) {

							// check device limit
							UserDeviceLimitDto deviceLimitDto = userLimitForContentQualityMap
									.get(DeviceProfiles.fromPriority(deviceProfileId).getContentType());

							if (DeviceManagerUtil.checkNullObject(deviceLimitDto)
									|| deviceLimitDto.getDeviceLimit() <= 0
									|| deviceLimitDto.getDeviceLimit() > deviceLimitDto.getTotalAssignedDevice()) {
								LOGGER.logMessage("Profile Calculation :: DeviceId={}, AssignedProfile={}",
										userDevice.getDeviceId(), deviceProfileVtp.getContentQuality());
								userDevice.setDeviceProfile(deviceProfileVtp.getContentQuality());
								userDevice.setDeviceProfileBandwidth(
										Long.parseLong(deviceProfileVtp.getBitRate())  + mdcBandwidth);
								userDevice.setQoeBandwidth(0L);

								// reduce available bandwidth
								availableBandwidth = availableBandwidth + assingedBW - userDevice.getDeviceProfileBandwidth();
								LOGGER.logMessage("New Profile assinged, availableBandwidth :: " + availableBandwidth);								

								// updated assigned device count
								deviceLimitDto.setTotalAssignedDevice(deviceLimitDto.getTotalAssignedDevice() + 1);								
							}
						}

					}
				} catch (Exception e) {
					// If exception occurs skip the device
					totalDeviceCalculationCompleted++;
					// Set Upgradable false
					userDevice.getDeviceProfileDto().setIsDeviceUpgradable(Boolean.FALSE);
					LOGGER.logMessage("Profile Calculation :: Device Profile Assignment Failed for DeviceId={}",
							userDevice.getDeviceId());
				}

			}

			// Next Device Profile
			deviceProfileId++;

		} while (deviceProfileId <= 3 && totalDeviceCalculationCompleted < userDeviceList.size()
				&& availableBandwidth > 0);
		
		
		totalBwAssignedToQoeDevices = updateMaxBWPerQualityAndfindVqeEnabledDevices(mdcBandwidth, userDeviceList,
																					modelMaxVtpPerContentQualityList, 
																					vqeEnabledUserDeviceList);

		// Set total assigned and free bandwidth
		assignedUser.setTotalAssignedBandwidth(assignedUser.getFreeBandwidth() - availableBandwidth);
		assignedUser.setFreeBandwidth(availableBandwidth);

		// Calculate VQE
		if (assignedUser.getFreeBandwidth() + assignedUser.getQoeControlBandwith() > 0 
				&& !isUseGlobalQoEBW && !vqeEnabledUserDeviceList.isEmpty()) {
			LOGGER.logMessage("Profile Calculation :: VQE bandwidth calculation for userName={}",
					assignedUser.getUserName());
			resourceDistributionHelper.calculateVqeBandwidth(vqeEnabledUserDeviceList, assignedUser, mdcBandwidth,
					totalBwAssignedToQoeDevices);

		}
		
		LOGGER.logMessage("Profile Calculation :: TotalAssignedBandwidth={}, FreeBandwidth={}",
				assignedUser.getFreeBandwidth(), availableBandwidth);

		return Boolean.TRUE;

	}
	
	/**
	 * Update Max BW allowed per content quality
	 * @param mdcBandwidth
	 * @param userDeviceList
	 * @param modelMaxVtpPerContentQualityList
	 * @param vqeEnabledUserDeviceList
	 * @return
	 */
	private long updateMaxBWPerQualityAndfindVqeEnabledDevices(long mdcBandwidth, 
			List<Device> userDeviceList,
			Map<Long, Map<String, VTP>> modelMaxVtpPerContentQualityList, 
			List<Device> vqeEnabledUserDeviceList) {
		long totalBwAssignedToQoeDevices = 0;

		// qoe calculation and setMaxBWQuality for each device
		for (int deviceIdx = 0; deviceIdx < userDeviceList.size(); deviceIdx++) {
			Device userDevice = userDeviceList.get(deviceIdx);

			Map<String, VTP> maxBandwidthPerQualityMap = modelMaxVtpPerContentQualityList
					.get(userDevice.getModel().getId());

			if (!DeviceManagerUtil.checkNullObject(maxBandwidthPerQualityMap)) {
				resourceDistributionHelper.setMaxBandwidthPerQualityForDevice(userDevice, maxBandwidthPerQualityMap);

				if (userDevice.getDeviceProfileBandwidth() > 0 && userDevice.getModel().getQoeCapable()) { // Device
																											// have
																											// valid
																											// profile

					totalBwAssignedToQoeDevices = totalBwAssignedToQoeDevices + userDevice.getDeviceProfileBandwidth()
							- mdcBandwidth;
					vqeEnabledUserDeviceList.add(userDevice);
				}
			}
		}

		return totalBwAssignedToQoeDevices;
	}
}
