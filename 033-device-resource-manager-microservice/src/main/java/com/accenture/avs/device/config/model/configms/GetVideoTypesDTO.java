package com.accenture.avs.device.config.model.configms;
/****************************************************************************
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author kumar.rajesh
 *
 */
public class GetVideoTypesDTO {

	private List<VideoTypes> videoTypes = new ArrayList<>();

	public List<VideoTypes> getVideoTypes() {
		return videoTypes;
	}

	public void setVideoTypes(List<VideoTypes> videoTypes) {
		this.videoTypes = videoTypes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
