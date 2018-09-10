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
package com.accenture.avs.device.config.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "isActive",
    "contentQuality",
    "bitRate",
    "associatedBWProfiles"
})
public class VTP implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("name")
    private String name;
    
    @JsonProperty("isActive")
    private Boolean isActive;
    
    @JsonProperty("contentQuality")
    private String contentQuality;
    
    @JsonProperty("associatedBWProfiles")
    private List<String> associatedBWProfiles;
    
    @JsonProperty("bitRate")
    private String bitRate;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("contentQuality")
    public String getContentQuality() {
        return contentQuality;
    }

    @JsonProperty("contentQuality")
    public void setContentQuality(String contentQuality) {
        this.contentQuality = contentQuality;
    }

    @JsonProperty("bitRate")
    public String getBitRate() {
        return bitRate;
    }

    @JsonProperty("bitRate")
    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    @JsonProperty("associatedBWProfiles")
    public List<String> getAssociatedBWProfiles() {
        return associatedBWProfiles;
    }

    @JsonProperty("associatedBWProfiles")
    public void setAssociatedBWProfiles(List<String> associatedBWProfiles) {
        this.associatedBWProfiles = associatedBWProfiles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}