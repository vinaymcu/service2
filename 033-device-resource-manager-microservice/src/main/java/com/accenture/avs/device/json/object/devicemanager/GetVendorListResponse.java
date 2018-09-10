
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    "executionTime",
    "resultObj"
})
public class GetVendorListResponse implements Serializable
{

    /**
     * resultCode
     * (Required)
     */
    @JsonProperty("resultCode")
    private String resultCode;
    
    /**
     * resultDescription
     * (Required)
     */
    @JsonProperty("resultDescription")
    private String resultDescription;
    
    /**
     * executionTime
     * (Required)
     */
    @JsonProperty("executionTime")
    private Integer executionTime;
    
    @JsonProperty("resultObj")
    private List<String> resultObj = new ArrayList<String>();
    private final static long serialVersionUID = 1559185217937661502L;

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
     * Set ResultCode
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
     * Get ResultDescription
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
     * Set ResultDescription
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
     * Get ExecutionTime
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
     * Set ExecutionTime
     * (Required)
     * 
     * @param executionTime
     *     The executionTime
     */
    @JsonProperty("executionTime")
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * Get ResultObj
     * @return
     *     The resultObj
     */
    @JsonProperty("resultObj")
    public List<String> getResultObj() {
        return resultObj;
    }

    /**
     * Set ResultObj
     * @param resultObj
     *     The resultObj
     */
    @JsonProperty("resultObj")
    public void setResultObj(List<String> resultObj) {
        this.resultObj = resultObj;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
