package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"conditionId",
"retEnable",
"rccEnable",
"networkBufferSize",
"qoeControlBandwidth",
"numSubIPTVDevices",
"numSubIPTVVqeSDSTBs",
"numSubIPTVVqeHDSTBs",
"numSubIPTVQoeSDSTBs",
"numSubIPTVQoeHDSTBs",
"numSubIPTVQoeSTBs",
"macAddress",
"vqeQuality",
"groupId"
})
public class GetVQEGroupsConditionDTO implements Serializable
{

@JsonProperty("conditionId")
private Integer conditionId;
@JsonProperty("retEnable")
private Boolean retEnable;
@JsonProperty("rccEnable")
private Boolean rccEnable;
@JsonProperty("networkBufferSize")
private Integer networkBufferSize;
@JsonProperty("qoeControlBandwidth")
private Integer qoeControlBandwidth;
@JsonProperty("numSubIPTVDevices")
private Integer numSubIPTVDevices;
@JsonProperty("numSubIPTVVqeSDSTBs")
private Integer numSubIPTVVqeSDSTBs;
@JsonProperty("numSubIPTVVqeHDSTBs")
private Integer numSubIPTVVqeHDSTBs;
@JsonProperty("numSubIPTVQoeSDSTBs")
private Integer numSubIPTVQoeSDSTBs;
@JsonProperty("numSubIPTVQoeHDSTBs")
private Integer numSubIPTVQoeHDSTBs;
@JsonProperty("numSubIPTVQoeSTBs")
private Integer numSubIPTVQoeSTBs;
@JsonProperty("macAddress")
private String macAddress;
@JsonProperty("vqeQuality")
private String vqeQuality;
/**
* 
* (Required)
* 
*/
@JsonProperty("groupId")
private Integer groupId;

private final static long serialVersionUID = 2945459673970997960L;

@JsonProperty("conditionId")
public Integer getConditionId() {
return conditionId;
}

@JsonProperty("conditionId")
public void setConditionId(Integer conditionId) {
this.conditionId = conditionId;
}

@JsonProperty("retEnable")
public Boolean getRetEnable() {
return retEnable;
}

@JsonProperty("retEnable")
public void setRetEnable(Boolean retEnable) {
this.retEnable = retEnable;
}

@JsonProperty("rccEnable")
public Boolean getRccEnable() {
return rccEnable;
}

@JsonProperty("rccEnable")
public void setRccEnable(Boolean rccEnable) {
this.rccEnable = rccEnable;
}

@JsonProperty("networkBufferSize")
public Integer getNetworkBufferSize() {
return networkBufferSize;
}

@JsonProperty("networkBufferSize")
public void setNetworkBufferSize(Integer networkBufferSize) {
this.networkBufferSize = networkBufferSize;
}

@JsonProperty("qoeControlBandwidth")
public Integer getQoeControlBandwidth() {
return qoeControlBandwidth;
}

@JsonProperty("qoeControlBandwidth")
public void setQoeControlBandwidth(Integer qoeControlBandwidth) {
this.qoeControlBandwidth = qoeControlBandwidth;
}

@JsonProperty("numSubIPTVDevices")
public Integer getNumSubIPTVDevices() {
return numSubIPTVDevices;
}

@JsonProperty("numSubIPTVDevices")
public void setNumSubIPTVDevices(Integer numSubIPTVDevices) {
this.numSubIPTVDevices = numSubIPTVDevices;
}

@JsonProperty("numSubIPTVVqeSDSTBs")
public Integer getNumSubIPTVVqeSDSTBs() {
return numSubIPTVVqeSDSTBs;
}

@JsonProperty("numSubIPTVVqeSDSTBs")
public void setNumSubIPTVVqeSDSTBs(Integer numSubIPTVVqeSDSTBs) {
this.numSubIPTVVqeSDSTBs = numSubIPTVVqeSDSTBs;
}

@JsonProperty("numSubIPTVVqeHDSTBs")
public Integer getNumSubIPTVVqeHDSTBs() {
return numSubIPTVVqeHDSTBs;
}

@JsonProperty("numSubIPTVVqeHDSTBs")
public void setNumSubIPTVVqeHDSTBs(Integer numSubIPTVVqeHDSTBs) {
this.numSubIPTVVqeHDSTBs = numSubIPTVVqeHDSTBs;
}

@JsonProperty("numSubIPTVQoeSDSTBs")
public Integer getNumSubIPTVQoeSDSTBs() {
return numSubIPTVQoeSDSTBs;
}

@JsonProperty("numSubIPTVQoeSDSTBs")
public void setNumSubIPTVQoeSDSTBs(Integer numSubIPTVQoeSDSTBs) {
this.numSubIPTVQoeSDSTBs = numSubIPTVQoeSDSTBs;
}

@JsonProperty("numSubIPTVQoeHDSTBs")
public Integer getNumSubIPTVQoeHDSTBs() {
return numSubIPTVQoeHDSTBs;
}

@JsonProperty("numSubIPTVQoeHDSTBs")
public void setNumSubIPTVQoeHDSTBs(Integer numSubIPTVQoeHDSTBs) {
this.numSubIPTVQoeHDSTBs = numSubIPTVQoeHDSTBs;
}

@JsonProperty("numSubIPTVQoeSTBs")
public Integer getNumSubIPTVQoeSTBs() {
return numSubIPTVQoeSTBs;
}

@JsonProperty("numSubIPTVQoeSTBs")
public void setNumSubIPTVQoeSTBs(Integer numSubIPTVQoeSTBs) {
this.numSubIPTVQoeSTBs = numSubIPTVQoeSTBs;
}

@JsonProperty("macAddress")
public String getMacAddress() {
return macAddress;
}

@JsonProperty("macAddress")
public void setMacAddress(String macAddress) {
this.macAddress = macAddress;
}

@JsonProperty("vqeQuality")
public String getVqeQuality() {
return vqeQuality;
}

@JsonProperty("vqeQuality")
public void setVqeQuality(String vqeQuality) {
this.vqeQuality = vqeQuality;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("groupId")
public Integer getGroupId() {
return groupId;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("groupId")
public void setGroupId(Integer groupId) {
this.groupId = groupId;
}

}