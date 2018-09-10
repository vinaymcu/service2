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
public class RemoteConfiguration {

	/** resultCode */
	@JsonProperty("resultCode")
	private String resultCode;
	/** resultDescription */
	@JsonProperty("resultDescription")
	private String resultDescription;
	/** executionTime */
	@JsonProperty("executionTime")
	private Integer executionTime;
	/** resultObj */
	@JsonProperty("resultObj")
	private ResultObj resultObj;

	/**
	 * 
	 * @return
	 */
	@JsonProperty("resultCode")
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * 
	 * @param resultCode
	 */
	@JsonProperty("resultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("resultDescription")
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * 
	 * @param resultDescription
	 */
	@JsonProperty("resultDescription")
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("executionTime")
	public Integer getExecutionTime() {
		return executionTime;
	}

	/**
	 * 
	 * @param executionTime
	 */
	@JsonProperty("executionTime")
	public void setExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("resultObj")
	public ResultObj getResultObj() {
		return resultObj;
	}

	/**
	 * 
	 * @param resultObj
	 */
	@JsonProperty("resultObj")
	public void setResultObj(ResultObj resultObj) {
		this.resultObj = resultObj;
	}

}
