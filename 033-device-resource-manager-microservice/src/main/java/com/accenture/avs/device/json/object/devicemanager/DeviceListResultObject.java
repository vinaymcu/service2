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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonPropertyOrder({ "totalResults", "deviceList" })
public class DeviceListResultObject {

	@JsonProperty("totalResults")
	private Integer totalResults;
	@JsonProperty("deviceList")
	private List<DeviceListDto> deviceList = new ArrayList<DeviceListDto>();
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The totalResults
	 */
	@JsonProperty("totalResults")
	public Integer getTotalResults() {
		return totalResults;
	}

	/**
	 * 
	 * @param totalResults
	 *            The totalResults
	 */
	@JsonProperty("totalResults")
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * 
	 * @return The deviceList
	 */
	@JsonProperty("deviceList")
	public List<DeviceListDto> getDeviceList() {
		return deviceList;
	}

	/**
	 * 
	 * @param deviceList
	 *            The deviceList
	 */
	@JsonProperty("deviceList")
	public void setDeviceList(List<DeviceListDto> deviceList) {
		this.deviceList = deviceList;
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
	 * @return
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(totalResults).append(deviceList).append(additionalProperties).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof DeviceListResultObject) == false) {
			isEqual = false;
		} else {
			DeviceListResultObject rhs = ((DeviceListResultObject) other);
			isEqual = new EqualsBuilder().append(totalResults, rhs.totalResults).append(deviceList, rhs.deviceList)
					.append(additionalProperties, rhs.additionalProperties).isEquals();
		}
		return isEqual;
	}

}
