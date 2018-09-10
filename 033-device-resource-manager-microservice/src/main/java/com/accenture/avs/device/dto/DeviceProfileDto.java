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
public class DeviceProfileDto {

	/** deviceCapability */
	private String deviceCapability;

	/** isDeviceUpgradable */
	private Boolean isDeviceUpgradable;

	/**
	 * @return the deviceCapability
	 */
	public String getDeviceCapability() {
		return deviceCapability;
	}

	/**
	 * @param deviceCapability
	 *            the deviceCapability to set
	 */
	public void setDeviceCapability(String deviceCapability) {
		this.deviceCapability = deviceCapability;
	}

	/**
	 * @return the isDeviceUpgradable
	 */
	public Boolean getIsDeviceUpgradable() {
		return isDeviceUpgradable;
	}

	/**
	 * @param isDeviceUpgradable
	 *            the isDeviceUpgradable to set
	 */
	public void setIsDeviceUpgradable(Boolean isDeviceUpgradable) {
		this.isDeviceUpgradable = isDeviceUpgradable;
	}

}
