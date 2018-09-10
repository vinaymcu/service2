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
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "configName",
    "microservice",
    "data"
})
public class MaxBitrateMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("configName")
    private String configName;
    
    @JsonProperty("microservice")
    private String microservice;
    
    @JsonProperty("data")
    private List<MaxBitrate> data = new ArrayList<MaxBitrate>();

    @JsonProperty("configName")
    public String getConfigName() {
        return configName;
    }

    @JsonProperty("configName")
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    @JsonProperty("microservice")
    public String getMicroservice() {
        return microservice;
    }

    @JsonProperty("microservice")
    public void setMicroservice(String microService) {
        this.microservice = microService;
    }

    @JsonProperty("data")
    public List<MaxBitrate> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<MaxBitrate> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}