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
@JsonPropertyOrder({ "maxAllowedIPTVDevices", "overrideBandwidth", "qoeControlBandwidth", "bandwidthProfile",
		"totalAssignedBandwidth", "freeBandwidth", "retEnable", "rccEnable", "networkBufferSize",
		"natBindingRefreshInterval", "limitsPerContentQuality", "lastUpdateTime" })
public class GetUserDto {
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("maxAllowedIPTVDevices")
	private Long maxAllowedIPTVDevices;
	@JsonProperty("overrideBandwidth")
	private Long overrideBandwidth;
	@JsonProperty("qoeControlBandwidth")
	private Long qoeControlBandwidth;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("bandwidthProfile")
	private String bandwidthProfile;
	@JsonProperty("totalAssignedBandwidth")
	private Long totalAssignedBandwidth;
	@JsonProperty("freeBandwidth")
	private Long freeBandwidth;

	@JsonProperty("retEnable")
	private Boolean retEnable;
	@JsonProperty("rccEnable")
	private Boolean rccEnable;
	@JsonProperty("networkBufferSize")
	private Long networkBufferSize;
	@JsonProperty("natBindingRefreshInterval")
	private Long natBindingRefreshInterval;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("limitsPerContentQuality")
	private List<LimitsPerContentQuality> limitsPerContentQuality = new ArrayList<>();

	@JsonProperty("lastUpdateTime")
	private Long lastUpdateTime;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("maxAllowedIPTVDevices")
	public Long getMaxAllowedIPTVDevices() {
		return maxAllowedIPTVDevices;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("maxAllowedIPTVDevices")
	public void setMaxAllowedIPTVDevices(Long maxAllowedIPTVDevices) {
		this.maxAllowedIPTVDevices = maxAllowedIPTVDevices;
	}

	@JsonProperty("overrideBandwidth")
	public Long getOverrideBandwidth() {
		return overrideBandwidth;
	}

	@JsonProperty("overrideBandwidth")
	public void setOverrideBandwidth(Long overrideBandwidth) {
		this.overrideBandwidth = overrideBandwidth;
	}

	@JsonProperty("qoeControlBandwidth")
	public Long getQoeControlBandwidth() {
		return qoeControlBandwidth;
	}

	@JsonProperty("qoeControlBandwidth")
	public void setQoeControlBandwidth(Long qoeControlBandwidth) {
		this.qoeControlBandwidth = qoeControlBandwidth;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("bandwidthProfile")
	public String getBandwidthProfile() {
		return bandwidthProfile;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("bandwidthProfile")
	public void setBandwidthProfile(String bandwidthProfile) {
		this.bandwidthProfile = bandwidthProfile;
	}

	@JsonProperty("totalAssignedBandwidth")
	public Long getTotalAssignedBandwidth() {
		return totalAssignedBandwidth;
	}

	@JsonProperty("totalAssignedBandwidth")
	public void setTotalAssignedBandwidth(Long totalAssignedBandwidth) {
		this.totalAssignedBandwidth = totalAssignedBandwidth;
	}

	@JsonProperty("freeBandwidth")
	public Long getFreeBandwidth() {
		return freeBandwidth;
	}

	@JsonProperty("freeBandwidth")
	public void setFreeBandwidth(Long freeBandwidth) {
		this.freeBandwidth = freeBandwidth;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("limitsPerContentQuality")
	public List<LimitsPerContentQuality> getLimitsPerContentQuality() {
		return limitsPerContentQuality;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("limitsPerContentQuality")
	public void setLimitsPerContentQuality(List<LimitsPerContentQuality> limitsPerContentQuality) {
		this.limitsPerContentQuality = limitsPerContentQuality;
	}

	/**
	 * @return the retEnable
	 */
	@JsonProperty("retEnable")
	public Boolean getRetEnable() {
		return retEnable;
	}

	/**
	 * @param retEnable
	 *            the retEnable to set
	 */
	@JsonProperty("retEnable")
	public void setRetEnable(Boolean retEnable) {
		this.retEnable = retEnable;
	}

	/**
	 * @return the rccEnable
	 */
	@JsonProperty("rccEnable")
	public Boolean getRccEnable() {
		return rccEnable;
	}

	/**
	 * @param rccEnable
	 *            the rccEnable to set
	 */
	@JsonProperty("rccEnable")
	public void setRccEnable(Boolean rccEnable) {
		this.rccEnable = rccEnable;
	}

	/**
	 * @return the networkBufferSize
	 */
	@JsonProperty("networkBufferSize")
	public Long getNetworkBufferSize() {
		return networkBufferSize;
	}

	/**
	 * @param networkBufferSize
	 *            the networkBufferSize to set
	 */
	@JsonProperty("networkBufferSize")
	public void setNetworkBufferSize(Long networkBufferSize) {
		this.networkBufferSize = networkBufferSize;
	}

	/**
	 * @return the natBindingRefreshInterval
	 */
	@JsonProperty("natBindingRefreshInterval")
	public Long getNatBindingRefreshInterval() {
		return natBindingRefreshInterval;
	}

	/**
	 * @param natBindingRefreshInterval
	 *            the natBindingRefreshInterval to set
	 */
	@JsonProperty("natBindingRefreshInterval")
	public void setNatBindingRefreshInterval(Long natBindingRefreshInterval) {
		this.natBindingRefreshInterval = natBindingRefreshInterval;
	}

	@JsonProperty("lastUpdateTime")
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@JsonProperty("lastUpdateTime")
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

}
