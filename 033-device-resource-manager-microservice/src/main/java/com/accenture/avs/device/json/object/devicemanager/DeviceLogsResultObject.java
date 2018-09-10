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
@JsonPropertyOrder({ "totalResults", "deviceLogs" })
public class DeviceLogsResultObject {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("totalResults")
	private Integer totalResults;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("deviceLogs")
	private List<DeviceLogDto> deviceLogDto = new ArrayList<DeviceLogDto>();
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * (Required)
	 * 
	 * @return The totalResults
	 */
	@JsonProperty("totalResults")
	public Integer getTotalResults() {
		return totalResults;
	}

	/**
	 * 
	 * (Required)
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
	 * (Required)
	 * 
	 * @return The deviceLogs
	 */
	@JsonProperty("deviceLogs")
	public List<DeviceLogDto> getDeviceLogs() {
		return deviceLogDto;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param deviceLogs
	 *            The deviceLogs
	 */
	@JsonProperty("deviceLogs")
	public void setDeviceLogs(List<DeviceLogDto> deviceLogs) {
		this.deviceLogDto = deviceLogs;
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
		return new HashCodeBuilder().append(totalResults).append(deviceLogDto).append(additionalProperties)
				.toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof DeviceLogsResultObject) == false) {
			isEqual = false;
		} else {
			DeviceLogsResultObject rhs = ((DeviceLogsResultObject) other);
			isEqual = new EqualsBuilder().append(totalResults, rhs.totalResults).append(deviceLogDto, rhs.deviceLogDto)
					.append(additionalProperties, rhs.additionalProperties).isEquals();
		}
		return isEqual;
	}

}
