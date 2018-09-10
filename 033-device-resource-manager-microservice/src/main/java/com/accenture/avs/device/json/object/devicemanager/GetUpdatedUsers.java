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
@JsonPropertyOrder({ "resultCode", "resultDescription", "executionTime", "resultObj" })
public class GetUpdatedUsers {
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
	private List<UpdatedUserDTO> resultObj = null;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultDescription")
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("resultDescription")
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("executionTime")
	public Integer getExecutionTime() {
		return executionTime;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("executionTime")
	public void setExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
	}

	/**
	 * @return the resultObj
	 */
	public List<UpdatedUserDTO> getResultObj() {
		return resultObj;
	}

	/**
	 * @param resultObj
	 *            the resultObj to set
	 */
	public void setResultObj(List<UpdatedUserDTO> resultObj) {
		this.resultObj = resultObj;
	}

}
