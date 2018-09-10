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
package com.accenture.avs.device.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
	"bandwidth",
	"videoTypes"
})
public class BandwidthProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("bandwidth")
	private Long bandwidth;

	@JsonProperty("videoTypes")
	private List<String> videoTypes = new ArrayList<String>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("bandwidth")
	public Long getBandwidth() {
		return bandwidth;
	}

	@JsonProperty("bandwidth")
	public void setBandwidth(Long bandwidth) {
		this.bandwidth = bandwidth;
	}

	@JsonProperty("videoTypes")
	public List<String> getVideoTypes() {
		return videoTypes;
	}

	@JsonProperty("videoTypes")
	public void setVideoTypes(List<String> videoTypes) {
		this.videoTypes = videoTypes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}