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
@JsonPropertyOrder({ "resultCode", "resultDescription", "executionTime", "resultObj" })
public class GetDeviceListResponse {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultCode")
	private String resultCode;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultDescription")
	private String resultDescription;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("executionTime")
	private Integer executionTime;
	@JsonProperty("resultObj")
	private DeviceListResultObject resultObj;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The resultCode
	 */
	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param resultCode
	 *            The resultCode
	 */
	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The resultDescription
	 */
	@JsonProperty("resultDescription")
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param resultDescription
	 *            The resultDescription
	 */
	@JsonProperty("resultDescription")
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The executionTime
	 */
	@JsonProperty("executionTime")
	public Integer getExecutionTime() {
		return executionTime;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param executionTime
	 *            The executionTime
	 */
	@JsonProperty("executionTime")
	public void setExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
	}

	/**
	 * 
	 * @return The resultObj
	 */
	@JsonProperty("resultObj")
	public DeviceListResultObject getResultObj() {
		return resultObj;
	}

	/**
	 * 
	 * @param resultObj
	 *            The resultObj
	 */
	@JsonProperty("resultObj")
	public void setResultObj(DeviceListResultObject resultObj) {
		this.resultObj = resultObj;
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
		return new HashCodeBuilder().append(resultCode).append(resultDescription).append(executionTime)
				.append(resultObj).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof GetDeviceListResponse) == false) {
			isEqual = false;
		} else {
			GetDeviceListResponse rhs = ((GetDeviceListResponse) other);
			isEqual = new EqualsBuilder().append(resultCode, rhs.resultCode)
					.append(resultDescription, rhs.resultDescription).append(executionTime, rhs.executionTime)
					.append(resultObj, rhs.resultObj).isEquals();
		}
		return isEqual;
	}

}
