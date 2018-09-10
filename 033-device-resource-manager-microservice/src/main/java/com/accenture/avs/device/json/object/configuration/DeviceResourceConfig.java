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

import javax.annotation.Generated;

import com.accenture.avs.be.lib.configuration.client.annotations.ManagedConfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "massCalculation" })
@ManagedConfiguration(type = "microservice", label = "deviceResourceConfig")
public class DeviceResourceConfig {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("massCalculation")
	private MassCalculation massCalculation;
	
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("userLogTableCleanupInterval")
	private Integer userLogTableCleanupInterval;
	
	

	/**
	 * @return the userLogTableCleanupInterval
	 */
	@JsonProperty("userLogTableCleanupInterval")
	public Integer getUserLogTableCleanupInterval() {
		return userLogTableCleanupInterval;
	}

	/**
	 * @param userLogTableCleanupInterval the userLogTableCleanupInterval to set
	 */
	@JsonProperty("userLogTableCleanupInterval")
	public void setUserLogTableCleanupInterval(Integer userLogTableCleanupInterval) {
		this.userLogTableCleanupInterval = userLogTableCleanupInterval;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The massCalculation
	 */
	@JsonProperty("massCalculation")
	public MassCalculation getMassCalculation() {
		return massCalculation;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param massCalculation
	 *            The massCalculation
	 */
	@JsonProperty("massCalculation")
	public void setMassCalculation(MassCalculation massCalculation) {
		this.massCalculation = massCalculation;
	}

	/**
	 * 
	 * @param massCalculation
	 * @return
	 */
	public DeviceResourceConfig withMassCalculation(MassCalculation massCalculation) {
		this.massCalculation = massCalculation;
		return this;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(massCalculation).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof DeviceResourceConfig) == false) {
			isEqual = false;
		} else {
			DeviceResourceConfig rhs = ((DeviceResourceConfig) other);
			isEqual = new EqualsBuilder().append(massCalculation, rhs.massCalculation).isEquals();
		}
		return isEqual;
	}

}
