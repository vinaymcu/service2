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
package com.accenture.avs.device.dto;

/**
 * 
 * @author singh.saurabh
 *
 */
public class UserDeviceLimitDto {
	
	/** contentQuality */
	private String contentQuality;

	/** deviceLimit */
	private Long deviceLimit;
	
	/** totalAssignedDevice */
	private Long totalAssignedDevice;

	/**
	 * @return the contentQuality
	 */
	public String getContentQuality() {
		return contentQuality;
	}

	/**
	 * @param contentQuality
	 *            the contentQuality to set
	 */
	public void setContentQuality(String contentQuality) {
		this.contentQuality = contentQuality;
	}

	/**
	 * @return the totalAssignedDevice
	 */
	public Long getTotalAssignedDevice() {
		return totalAssignedDevice;
	}

	/**
	 * @param totalAssignedDevice
	 *            the totalAssignedDevice to set
	 */
	public void setTotalAssignedDevice(Long totalAssignedDevice) {
		this.totalAssignedDevice = totalAssignedDevice;
	}

	/**
	 * @return the deviceLimit
	 */
	public Long getDeviceLimit() {
		if(deviceLimit == null) {
			deviceLimit = 0L;
		}
		return deviceLimit;
	}

	/**
	 * @param deviceLimit
	 *            the deviceLimit to set
	 */
	public void setDeviceLimit(Long deviceLimit) {
		this.deviceLimit = deviceLimit;
	}

}
