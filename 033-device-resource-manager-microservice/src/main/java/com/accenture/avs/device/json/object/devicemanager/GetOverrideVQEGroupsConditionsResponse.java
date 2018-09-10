package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"resultCode",
"resultDescription",
"executionTime",
"resultObj"
})
public class GetOverrideVQEGroupsConditionsResponse implements Serializable
{

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
private List<GetVQEGroupsConditionDTO> resultObj = null;

private final static long serialVersionUID = -3651882786639660909L;

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

@JsonProperty("resultObj")
public List<GetVQEGroupsConditionDTO> getResultObj() {
return resultObj;
}

@JsonProperty("resultObj")
public void setResultObj(List<GetVQEGroupsConditionDTO> resultObj) {
this.resultObj = resultObj;
}

}


