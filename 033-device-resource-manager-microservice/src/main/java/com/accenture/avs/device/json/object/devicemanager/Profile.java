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
package com.accenture.avs.device.json.object.devicemanager;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "networkBandwidth", "assignedVQEOverheadBW" })
public class Profile {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("name")
	private String name;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("networkBandwidth")
	private Integer networkBandwidth;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("assignedVQEOverheadBW")
	private Integer assignedVQEOverheadBW;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The networkBandwidth
	 */
	@JsonProperty("networkBandwidth")
	public Integer getNetworkBandwidth() {
		return networkBandwidth;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param networkBandwidth
	 *            The networkBandwidth
	 */
	@JsonProperty("networkBandwidth")
	public void setNetworkBandwidth(Integer networkBandwidth) {
		this.networkBandwidth = networkBandwidth;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The assignedVQEOverheadBW
	 */
	@JsonProperty("assignedVQEOverheadBW")
	public Integer getAssignedVQEOverheadBW() {
		return assignedVQEOverheadBW;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param assignedVQEOverheadBW
	 *            The assignedVQEOverheadBW
	 */
	@JsonProperty("assignedVQEOverheadBW")
	public void setAssignedVQEOverheadBW(Integer assignedVQEOverheadBW) {
		this.assignedVQEOverheadBW = assignedVQEOverheadBW;
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
		return new HashCodeBuilder().append(name).append(networkBandwidth).append(assignedVQEOverheadBW).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof Profile) == false) {
			isEqual = false;
		} else {
			Profile rhs = ((Profile) other);
			isEqual = new EqualsBuilder().append(name, rhs.name).append(networkBandwidth, rhs.networkBandwidth)
					.append(assignedVQEOverheadBW, rhs.assignedVQEOverheadBW).isEquals();
		}
		return isEqual;
	}

}
