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
 * Entity class for MODEL_MAX_STREAMS_ALLOWED_PER_QUALITY table
 * 
 * @author rajesh.kumar
 *
 */
@Embeddable
public class ModelMaxStreamsAllowedPerQuality implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Column(name = "RESOLUTION_TYPE_NAME", length = 10)
	private String resolutionTypeName;

	/** maxStreams */
	@Column(name = "MAX_STREAMS", precision = 10, scale = 0)
	private Long maxStreams;

	public String getResolutionTypeName() {
		return resolutionTypeName;
	}

	public void setResolutionTypeName(String resolutionTypeName) {
		this.resolutionTypeName = resolutionTypeName;
	}

	public Long getMaxStreams() {
		return maxStreams;
	}

	public void setMaxStreams(Long maxStreams) {
		this.maxStreams = maxStreams;
	}	
}
