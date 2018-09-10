/*******com.accenture.avs.device.entity******************************************
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

package com.accenture.avs.device.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Entity class for DEVICE_MAX_BANDWIDTH_ALLOWED_PER_QUALITY table
 * 
 * @author rajesh.karna
 *
 */
@Embeddable
public class DeviceMaxBWAllowedPerQuality implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** resolutionTypeName */
	@Column(name = "RESOLUTION_TYPE_NAME", length = 10)
	private String resolutionTypeName;

	/** maxBandwidth */
	@Column(name = "MAX_BANDWIDTH", precision = 9, scale = 0)
	private Long maxBandwidth;
		
	/**
	 * Gets resolutionTypeName
	 * 
	 * @return String
	 */
	public String getResolutionTypeName() {
		return resolutionTypeName;
	}

	/**
	 * Sets tvQuality
	 * 
	 * @param tvQuality
	 */
	public void setResolutionTypeName(String resolutionTypeName) {
		this.resolutionTypeName = resolutionTypeName;
	}


	/**
	 * Gets maxBandwidth
	 * 
	 * @return Long
	 */
	public Long getMaxBandwidth() {
		return maxBandwidth;
	}

	/**
	 * Sets maxBandwidth
	 * 
	 * @param maxBandwidth
	 */
	public void setMaxBandwidth(Long maxBandwidth) {
		this.maxBandwidth = maxBandwidth;
	}
}
