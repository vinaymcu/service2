package com.accenture.avs.device.config.model.configms;

/****************************************************************************
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

import javax.annotation.Generated;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "resultCode",
    "resultDescription",
    "resultObj",
    "executionTime"
})
public class GetVideoTypeProfileResponse {

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
    @JsonProperty("resultDescription")
    private Integer executionTime;
    /**
     * 
     */
    @JsonProperty("resultObj")
    private GetVideoTypesDTO resultObj;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The resultCode
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
     *     The resultCode
     */
    @JsonProperty("resultCode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The resultDescription
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
     *     The resultDescription
     */
    @JsonProperty("resultDescription")
    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }


    /**
     * 
     * (Required)
     * 
     * @return
     *     The executionTime
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
     *     The executionTime
     */
    @JsonProperty("executionTime")
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	/**
	 * @return the resultObj
	 */
    @JsonProperty("resultObj")
	public GetVideoTypesDTO getResultObj() {
		return resultObj;
	}

	/**
	 * @param resultObj the resultObj to set
	 */
    @JsonProperty("resultObj")
	public void setResultObj(GetVideoTypesDTO resultObj) {
		this.resultObj = resultObj;
	}

}
