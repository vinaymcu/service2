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
package com.accenture.avs.device.enums;

/**
 * Enum for the Device Profiles
 * 
 * @author singh.saurabh
 *
 */
public enum DeviceProfiles {

	/** OFF */
	OFF("OFF", 0),
	/** SD */
	SD("SD", 1),
	/** HD */
	HD("HD", 2),
	/** UHD */
	UHD("UHD", 3);

	/** contentType */
	private String contentType;

	/** priority */
	private Integer priority;

	/**
	 * Instantiates a new DeviceProfiles type.
	 * 
	 * @param contentType
	 * @param priority
	 */
	DeviceProfiles(String contentType, Integer priority) {
		this.contentType = contentType;
		this.priority = priority;
	}

	/**
	 * Gets contentType
	 * 
	 * @return
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * Gets priority
	 * 
	 * @return
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * This method returns DeviceProfiles type, if found
	 * 
	 * @param priority
	 * @return
	 */
	public static DeviceProfiles fromPriority(Integer priority) {
		DeviceProfiles profileToReturn = null;
		for (DeviceProfiles deviceProfile : DeviceProfiles.values()) {
			if (priority.equals(deviceProfile.priority)) {
				profileToReturn = deviceProfile;
			}
		}
		return profileToReturn;
	}

	/**
	 * This method returns DeviceProfiles type, if found
	 * 
	 * @param contentType
	 * @return
	 */
	public static DeviceProfiles fromContentQuality(String contentType) {
		DeviceProfiles profileToReturn = null;
		for (DeviceProfiles deviceProfile : DeviceProfiles.values()) {
			if (contentType.equalsIgnoreCase(deviceProfile.contentType)) {
				profileToReturn = deviceProfile;
			}
		}
		return profileToReturn;
	}
}