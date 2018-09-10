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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contentQuality",
    "maxBitRate"
})
public class MaxBitrate implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("contentQuality")
    private String contentQuality;
    
    @JsonProperty("maxBitRate")
    private String maxBitRate;
    
    @JsonProperty("contentQuality")
    public String getContentQuality() {
        return contentQuality;
    }

    @JsonProperty("contentQuality")
    public void setContentQuality(String contentQuality) {
        this.contentQuality = contentQuality;
    }

    @JsonProperty("maxBitRate")
    public String getMaxBitRate() {
        return maxBitRate;
    }

    @JsonProperty("maxBitRate")
    public void setMaxBitRate(String maxBitRate) {
        this.maxBitRate = maxBitRate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}