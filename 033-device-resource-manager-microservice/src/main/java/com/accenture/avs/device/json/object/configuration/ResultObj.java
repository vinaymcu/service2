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
package com.accenture.avs.device.json.object.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "configurations" })
public class ResultObj {

	/** configurations */
	@JsonProperty("configurations")
	private List<ApplicationConfiguration> configurations = new ArrayList<>();

	/**
	 * 
	 * @return
	 */
	@JsonProperty("configurations")
	public List<ApplicationConfiguration> getConfigurations() {
		return configurations;
	}

	/**
	 * 
	 * @param configurations
	 */
	@JsonProperty("configurations")
	public void setConfigurations(List<ApplicationConfiguration> configurations) {
		this.configurations = configurations;
	}

}
