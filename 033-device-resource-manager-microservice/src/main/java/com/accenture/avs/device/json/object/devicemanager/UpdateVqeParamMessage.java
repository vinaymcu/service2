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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Message DTO for VQE Status Update.
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "crmAccountId", "qoeControlBandwidth", "retEnable", "rccEnable", "networkBufferSize",
		"natBindingRefreshInterval" })
public class UpdateVqeParamMessage {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("crmAccountId")
	private String crmAccountId;
	@JsonProperty("qoeControlBandwidth")
	private Integer qoeControlBandwidth;
	@JsonProperty("retEnable")
	private Boolean retEnable;
	@JsonProperty("rccEnable")
	private Boolean rccEnable;
	@JsonProperty("networkBufferSize")
	private Integer networkBufferSize;
	@JsonProperty("natBindingRefreshInterval")
	private Integer natBindingRefreshInterval;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The crmAccountId
	 */
	@JsonProperty("crmAccountId")
	public String getCrmAccountId() {
		return crmAccountId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param crmAccountId
	 *            The crmAccountId
	 */
	@JsonProperty("crmAccountId")
	public void setCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
	}

	public UpdateVqeParamMessage withCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
		return this;
	}

	/**
	 * 
	 * @return The qoeControlBandwidth
	 */
	@JsonProperty("qoeControlBandwidth")
	public Integer getQoeControlBandwidth() {
		return qoeControlBandwidth;
	}

	/**
	 * 
	 * @param qoeControlBandwidth
	 *            The qoeControlBandwidth
	 */
	@JsonProperty("qoeControlBandwidth")
	public void setQoeControlBandwidth(Integer qoeControlBandwidth) {
		this.qoeControlBandwidth = qoeControlBandwidth;
	}

	public UpdateVqeParamMessage withQoeControlBandwidth(Integer qoeControlBandwidth) {
		this.qoeControlBandwidth = qoeControlBandwidth;
		return this;
	}

	/**
	 * 
	 * @return The retEnable
	 */
	@JsonProperty("retEnable")
	public Boolean getRetEnable() {
		return retEnable;
	}

	/**
	 * 
	 * @param retEnable
	 *            The retEnable
	 */
	@JsonProperty("retEnable")
	public void setRetEnable(Boolean retEnable) {
		this.retEnable = retEnable;
	}

	public UpdateVqeParamMessage withRetEnable(Boolean retEnable) {
		this.retEnable = retEnable;
		return this;
	}

	/**
	 * 
	 * @return The rccEnable
	 */
	@JsonProperty("rccEnable")
	public Boolean getRccEnable() {
		return rccEnable;
	}

	/**
	 * 
	 * @param rccEnable
	 *            The rccEnable
	 */
	@JsonProperty("rccEnable")
	public void setRccEnable(Boolean rccEnable) {
		this.rccEnable = rccEnable;
	}

	public UpdateVqeParamMessage withRccEnable(Boolean rccEnable) {
		this.rccEnable = rccEnable;
		return this;
	}

	/**
	 * 
	 * @return The networkBufferSize
	 */
	@JsonProperty("networkBufferSize")
	public Integer getNetworkBufferSize() {
		return networkBufferSize;
	}

	/**
	 * 
	 * @param networkBufferSize
	 *            The networkBufferSize
	 */
	@JsonProperty("networkBufferSize")
	public void setNetworkBufferSize(Integer networkBufferSize) {
		this.networkBufferSize = networkBufferSize;
	}

	public UpdateVqeParamMessage withNetworkBufferSize(Integer networkBufferSize) {
		this.networkBufferSize = networkBufferSize;
		return this;
	}

	/**
	 * 
	 * @return The natBindingRefreshInterval
	 */
	@JsonProperty("natBindingRefreshInterval")
	public Integer getNatBindingRefreshInterval() {
		return natBindingRefreshInterval;
	}

	/**
	 * 
	 * @param natBindingRefreshInterval
	 *            The natBindingRefreshInterval
	 */
	@JsonProperty("natBindingRefreshInterval")
	public void setNatBindingRefreshInterval(Integer natBindingRefreshInterval) {
		this.natBindingRefreshInterval = natBindingRefreshInterval;
	}

	public UpdateVqeParamMessage withNatBindingRefreshInterval(Integer natBindingRefreshInterval) {
		this.natBindingRefreshInterval = natBindingRefreshInterval;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(crmAccountId).append(qoeControlBandwidth).append(retEnable)
				.append(rccEnable).append(networkBufferSize).append(natBindingRefreshInterval).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof UpdateVqeParamMessage) == false) {
			return false;
		}
		UpdateVqeParamMessage rhs = ((UpdateVqeParamMessage) other);
		return new EqualsBuilder().append(crmAccountId, rhs.crmAccountId)
				.append(qoeControlBandwidth, rhs.qoeControlBandwidth).append(retEnable, rhs.retEnable)
				.append(rccEnable, rhs.rccEnable).append(networkBufferSize, rhs.networkBufferSize)
				.append(natBindingRefreshInterval, rhs.natBindingRefreshInterval).isEquals();
	}

}
