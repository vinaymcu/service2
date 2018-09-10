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
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.config.model.VTP;
import com.accenture.avs.device.dto.DeviceProfileDto;
import com.accenture.avs.device.dto.UserDeviceLimitDto;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceMaxBWAllowedPerQuality;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.entity.UserLimits;
import com.accenture.avs.device.enums.DeviceProfiles;
import com.accenture.avs.device.repository.ModelVTPLinkRepository;
import com.accenture.avs.device.service.ResourceDistributionHelper;
import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * This is a helper class for the resource distribution process.
 * 
 * @author singh.saurabh
 *
 */
@Service
public class ResourceDistributionHelperImpl implements ResourceDistributionHelper {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(ResourceDistributionHelperImpl.class);

	/** configurationClient */
	@Autowired
	private ConfigurationClient configurationClient;

	/** modelVtpLinkRepository */
	@Autowired
	private ModelVTPLinkRepository modelVtpLinkRepository;

	/**
	 * This method returns the inclusive device limit for a user.
	 * 
	 * @param assignedUser
	 * @return
	 */
	@Override
	public Map<String, UserDeviceLimitDto> calculatedInclusiveDeviceLimit(User assignedUser) {

		Map<String, UserDeviceLimitDto> userLimitForContentQualityMap = new HashMap<>();

		List<UserLimits> userDeviceLimits = assignedUser.getUserLimits();

		LOGGER.logMessage("Profile Calculation :: User Device limit {}", userDeviceLimits);

		for (UserLimits userLimit : userDeviceLimits) {
			UserDeviceLimitDto userDeviceLimitDto = new UserDeviceLimitDto();
			userDeviceLimitDto.setContentQuality(userLimit.getContentQuality());
			userDeviceLimitDto.setDeviceLimit(userLimit.getMaxDevices());
			userDeviceLimitDto.setTotalAssignedDevice(0L);
			userLimitForContentQualityMap.put(userDeviceLimitDto.getContentQuality(), userDeviceLimitDto);
		}

		// For SD
		UserDeviceLimitDto userDeviceLimitDto = new UserDeviceLimitDto();
		userDeviceLimitDto.setContentQuality(DeviceProfiles.SD.getContentType());
		userDeviceLimitDto.setDeviceLimit(assignedUser.getMaxAllowedIptvDevices());
		userDeviceLimitDto.setTotalAssignedDevice(0L);
		userLimitForContentQualityMap.put(DeviceProfiles.SD.getContentType(), userDeviceLimitDto);

		// For HD
		if (!DeviceManagerUtil.checkNullObject(userLimitForContentQualityMap.get(DeviceProfiles.HD.getContentType()))) {
			Long uhdDeviceLimit = DeviceManagerUtil
					.checkNullObject(userLimitForContentQualityMap.get(DeviceProfiles.UHD.getContentType())) ? 0L
							: userLimitForContentQualityMap.get(DeviceProfiles.UHD.getContentType()).getDeviceLimit();
			UserDeviceLimitDto hdLimitDto = userLimitForContentQualityMap.get(DeviceProfiles.HD.getContentType());
			hdLimitDto.setDeviceLimit(hdLimitDto.getDeviceLimit() + uhdDeviceLimit);
		}

		// For UHD : no inclusive limit required as highest content quality
		LOGGER.logMessage("Profile Calculation :: Inclusive User Device limit {}", userDeviceLimits);

		return userLimitForContentQualityMap;
	}

	/**
	 * This method finds the common VTPs and returns Max bandwidth VTP for each
	 * content type.
	 * 
	 * @param modelVtpNames
	 * @param bwProfileVtpNames
	 * @return
	 */
	@Override
	public Map<String, VTP> findCommonVtpsWithMaxBw(List<String> modelVtpNames, List<String> bwProfileVtpNames) {

		// Get common VTPs
		List<String> commonVtpNames = new ArrayList<>();
		commonVtpNames.addAll(modelVtpNames);
		commonVtpNames.retainAll(bwProfileVtpNames);

		LOGGER.logMessage("Profile Calculation :: Common VideoTypes={}", commonVtpNames);

		// Get VTP details from the memory
		List<VTP> videoTypeList = new ArrayList<>();
		for (String vtpName : commonVtpNames) {
			VTP vtp = configurationClient.findVideoTypeProfile(vtpName);
			if (DeviceManagerUtil.checkNotNullObject(vtp)) {
				videoTypeList.add(vtp);
				continue;
			}
			LOGGER.logMessage("Profile Calculation :: VTP doesn't exists VtpName={}", vtpName);
		}

		// Arrange VTPs according to the content quality
		Map<String, VTP> contentQualityVtpMap = new HashMap<>();

		for (VTP vtp : videoTypeList) {
			VTP maxVTPBitrate = contentQualityVtpMap.get(vtp.getContentQuality());
			if (maxVTPBitrate == null) {
				contentQualityVtpMap.put(vtp.getContentQuality(), vtp);
			} else if (Long.parseLong(vtp.getBitRate()) > Long.parseLong(maxVTPBitrate.getBitRate())) {
				contentQualityVtpMap.put(vtp.getContentQuality(), vtp);
			}
		}

		return contentQualityVtpMap;
	}

	/**
	 * This method calculates the device maximum capability.
	 * 
	 * @param supportedContentQualities
	 * @return
	 */
	@Override
	public String calculateDeviceMaximumCapability(Set<String> supportedContentQualities) {
		int highestCapabilityPriority = 0;
		for (String contentQuality : supportedContentQualities) {
			highestCapabilityPriority = DeviceProfiles.fromContentQuality(contentQuality)
					.getPriority() > highestCapabilityPriority
							? DeviceProfiles.fromContentQuality(contentQuality).getPriority()
							: highestCapabilityPriority;
		}
		return DeviceProfiles.fromPriority(highestCapabilityPriority).getContentType();
	}

	/**
	 * This methodn sets the total assigned
	 * 
	 * @param assignedUser
	 * @param bwProfileBitrate
	 */
	@Override
	public void calculateFreeAndQoeBandwidth(User assignedUser, Long bwProfileBitrate) {
		// Set Total Free Bandwidth
		if (!DeviceManagerUtil.checkNullObject(assignedUser.getOverrideBandwith())
				&& assignedUser.getOverrideBandwith() > 0) {
			bwProfileBitrate = assignedUser.getOverrideBandwith();
		}
		assignedUser.setFreeBandwidth(bwProfileBitrate);

		// Set Qoe Bandwidth
		if (DeviceManagerUtil.checkNullObject(assignedUser.getQoeControlBandwith())) {
			assignedUser.setQoeControlBandwith(0L);
		}
	}

	/**
	 * This method returns the applicable TVQualityInterest.
	 * 
	 * @param tvQualityInterest
	 * @return
	 */
	@Override
	public String calculateApplicableTvQualityInterest(String tvQualityInterest) {
		// If no TVQualityInterest defined, default = UHD
		return DeviceManagerUtil.checkNullorBlankString(tvQualityInterest) ? DeviceProfiles.UHD.getContentType()
				: tvQualityInterest;
	}

	/**
	 * This method calculates and distributes the VQE bandwidth among the VQE
	 * enabled devices.
	 * 
	 * @param vqeEnabledUserDeviceList
	 * @param assignedUser
	 * @param mdcBandwidth
	 * @param totalBwAssignedToQoeDevices
	 */
	@Override
	public void calculateVqeBandwidth(List<Device> vqeEnabledUserDeviceList, User assignedUser, Long mdcBandwidth,
			Long totalBwAssignedToQoeDevices) {
		Long totalBandwidthForQoe = assignedUser.getFreeBandwidth() + assignedUser.getQoeControlBandwith();
		LOGGER.logMessage("Profile Calculation :: TotalBandwidthForQoeCalculation={}", totalBandwidthForQoe);
		for (Device userDevice : vqeEnabledUserDeviceList) {
			long overheadbandwidth = (userDevice.getDeviceProfileBandwidth() - mdcBandwidth) * totalBandwidthForQoe;
			long qoeBandwidth = overheadbandwidth / totalBwAssignedToQoeDevices;
			LOGGER.logMessage("Profile Calculation :: QoeBandwidth={} for deviceId={}", qoeBandwidth,
					userDevice.getDeviceId());
			userDevice.setQoeBandwidth(qoeBandwidth);
		}
		assignedUser.setFreeBandwidth(0L);
		assignedUser.setTotalAssignedBandwidth(assignedUser.getTotalAssignedBandwidth() + totalBandwidthForQoe);
	}

	/**
	 * This method returns calculates the tv quality interest and device capability
	 * of each the provided devices. It maintains a list of devices which are valid
	 * for profile calculation and returns a map of max vtps per content quality for
	 * each model.
	 * 
	 * @param userDeviceList
	 * @param bwProfileVtpNames
	 * @return
	 */
	@Override
	public Map<Long, Map<String, VTP>> calculateDeviceMaxVtpInterestAndDeviceCapability(List<Device> userDeviceList,
			List<String> bwProfileVtpNames) {

		LOGGER.logMessage("Profile Calculation :: BandwidthProfile AssociatedVTPs={}", bwProfileVtpNames);

		Map<Long, Map<String, VTP>> modelMaxVtpPerContentQualityList = new HashMap<>();

		int size = userDeviceList.size();
		for (int counter = size - 1; counter >= 0; counter--) {

			if (!modelMaxVtpPerContentQualityList.containsKey(userDeviceList.get(counter).getModel().getId())) {
				// Get VTPs associated with Device Model
				List<String> modelVtpNames = modelVtpLinkRepository
						.findModelVtpNamesByModelId(userDeviceList.get(counter).getModel().getId());
				LOGGER.logMessage("Profile Calculation :: Model={}, Associated VTPs={}",
						userDeviceList.get(counter).getModel().getModelName(), modelVtpNames);
				// Find max bit rate VTP for the current device profile
				Map<String, VTP> maxVtpPerContentTypeMap = findCommonVtpsWithMaxBw(modelVtpNames, bwProfileVtpNames);

				if (maxVtpPerContentTypeMap.isEmpty()) {
					LOGGER.logMessage("Profile Calculation :: No common VTPs found for deviceId={}",
							userDeviceList.get(counter).getDeviceId());
					userDeviceList.remove(counter);
					continue;
				} else {
					// Add devices valid for calculation
					modelMaxVtpPerContentQualityList.put(userDeviceList.get(counter).getModel().getId(),
							maxVtpPerContentTypeMap);
				}
			}

			DeviceProfileDto deviceProfileDto = new DeviceProfileDto();
			// find device maximum capability
			deviceProfileDto.setDeviceCapability(calculateDeviceMaximumCapability(
					modelMaxVtpPerContentQualityList.get(userDeviceList.get(counter).getModel().getId()).keySet()));

			// Set device is upgradable
			deviceProfileDto.setIsDeviceUpgradable(Boolean.TRUE);

			userDeviceList.get(counter).setDeviceProfileDto(deviceProfileDto);

			// If no TVQualityInterest defined, default = UHD
			userDeviceList.get(counter).setTvQualityInterest(
					calculateApplicableTvQualityInterest(userDeviceList.get(counter).getTvQualityInterest()));

			// Initial Profile 'OFF'
			userDeviceList.get(counter).setDeviceProfile(DeviceProfiles.OFF.getContentType());
			userDeviceList.get(counter).setDeviceProfileBandwidth(0L);
			userDeviceList.get(counter).setQoeBandwidth(0L);

			LOGGER.logMessage("Profile Calculation :: DeviceId={}, maxCapability={}, tvQualityInterest={}",
					userDeviceList.get(counter).getDeviceId(),
					userDeviceList.get(counter).getDeviceProfileDto().getDeviceCapability(),
					userDeviceList.get(counter).getTvQualityInterest());

		}
		return modelMaxVtpPerContentQualityList;
	}

	/**
	 * This method sets the DeviceMaxBWAllowedPerQuality for the device upto a max
	 * defined upper limit.
	 * 
	 * @param userDevice
	 * @param upperLimitForMaxQuality
	 * @param maxBandwidthPerQualityMap
	 */
	@Override
	public void setMaxBandwidthPerQualityForDevice(Device userDevice, Map<String, VTP> maxBandwidthPerQualityMap) {
		// Device Max bandwidth allowed per content quality
		List<DeviceMaxBWAllowedPerQuality> deviceMaxBWAllowedPerQualityList = new ArrayList<>();
		for (Entry<String, VTP> entry : maxBandwidthPerQualityMap.entrySet()) {
			DeviceMaxBWAllowedPerQuality deviceMaxBWAllowedPerQuality = new DeviceMaxBWAllowedPerQuality();
			deviceMaxBWAllowedPerQuality.setResolutionTypeName(entry.getKey());
			deviceMaxBWAllowedPerQuality.setMaxBandwidth(Long.parseLong(entry.getValue().getBitRate()));
			deviceMaxBWAllowedPerQualityList.add(deviceMaxBWAllowedPerQuality);
		}
		userDevice.setDeviceMaxBWAllowedPerQuality(deviceMaxBWAllowedPerQualityList);
	}

}
