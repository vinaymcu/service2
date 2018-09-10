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

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "resultCode", "resultDescription", "resultObj", "executionTime" })
public class GenericResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8757662420350871959L;

	/**
	 * It will be valorized in case of generic Error, that is in case the MS will
	 * not able to execute the requested interface, for example in case of the
	 * DataBase is down(ACN_300) or in case all entities in the request (or the
	 * single entity in case the request is not for list of entities) are
	 * successfully executed (ACN_200). In the other case it will be not present.
	 * (Required)
	 * 
	 */
	@JsonProperty("resultCode")
	@JsonPropertyDescription("")
	private String resultCode;
	/**
	 * Description Error. Example: 300-GENERIC ERROR. In the other case it will be
	 * not present. (Required)
	 * 
	 */
	@JsonProperty("resultDescription")
	@JsonPropertyDescription("")
	private String resultDescription;

	/**
	 * GM Time in Milliseconds (Required)
	 * 
	 */
	@JsonProperty("executionTime")
	@JsonPropertyDescription("")
	private Integer executionTime;

	/**
	 * It will be valorized in case of generic Error, that is in case the MS will
	 * not able to execute the requested interface, for example in case of the
	 * DataBase is down(ACN_300) or in case all entities in the request (or the
	 * single entity in case the request is not for list of entities) are
	 * successfully executed (ACN_200). In the other case it will be not present.
	 * (Required)
	 * 
	 * @return The resultCode
	 */
	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * It will be valorized in case of generic Error, that is in case the MS will
	 * not able to execute the requested interface, for example in case of the
	 * DataBase is down(ACN_300) or in case all entities in the request (or the
	 * single entity in case the request is not for list of entities) are
	 * successfully executed (ACN_200). In the other case it will be not present.
	 * (Required)
	 * 
	 * @param resultCode
	 *            The resultCode
	 */
	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public GenericResponse withResultCode(String resultCode) {
		this.resultCode = resultCode;
		return this;
	}

	/**
	 * Description Error. Example: 300-GENERIC ERROR. In the other case it will be
	 * not present. (Required)
	 * 
	 * @return The resultDescription
	 */
	@JsonProperty("resultDescription")
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * Description Error. Example: 300-GENERIC ERROR. In the other case it will be
	 * not present. (Required)
	 * 
	 * @param resultDescription
	 *            The resultDescription
	 */
	@JsonProperty("resultDescription")
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public GenericResponse withResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
		return this;
	}

	/**
	 * GM Time in Milliseconds (Required)
	 * 
	 * @return The executionTime
	 */
	@JsonProperty("executionTime")
	public Integer getExecutionTime() {
		return executionTime;
	}

	/**
	 * GM Time in Milliseconds (Required)
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
	 * @param executionTime
	 * @return
	 */
	public GenericResponse withExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
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
		return new HashCodeBuilder().append(resultCode).append(resultDescription).append(executionTime).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof GenericResponse) == false) {
			isEqual = false;
		} else {
			GenericResponse rhs = ((GenericResponse) other);
			isEqual = new EqualsBuilder().append(resultCode, rhs.resultCode)
					.append(resultDescription, rhs.resultDescription).append(executionTime, rhs.executionTime)
					.isEquals();
		}
		return isEqual;
	}

}
