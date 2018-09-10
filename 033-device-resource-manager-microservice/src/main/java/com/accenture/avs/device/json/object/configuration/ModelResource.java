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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.accenture.avs.be.lib.configuration.client.annotations.ManagedConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "modelResources"
})
@ManagedConfiguration(type="microservice", label="modelResources")

public class ModelResource {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("modelResources")
    private List<String> modelResources = new ArrayList<String>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The modelResources
     */
    @JsonProperty("modelResources")
    public List<String> getModelResources() {
        return modelResources;
    }

    /**
     * 
     * (Required)
     * 
     * @param modelResources
     *     The modelResources
     */
    @JsonProperty("modelResources")
    public void setModelResources(List<String> modelResources) {
        this.modelResources = modelResources;
    }

    public ModelResource withModelResources(List<String> modelResources) {
        this.modelResources = modelResources;
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
        return new HashCodeBuilder().append(modelResources).toHashCode();
    }

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof ModelResource) == false) {
			isEqual = false;
		} else {
			ModelResource rhs = ((ModelResource) other);
			isEqual = new EqualsBuilder().append(modelResources, rhs.modelResources).isEquals();
		}
		return isEqual;
	}

}
