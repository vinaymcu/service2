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

import com.accenture.avs.device.annotation.ManagedApplicationConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "conflictResolutionTimeout",
    "applications"
})
@ManagedApplicationConfiguration(platform = "IPTV", deviceType="STB", propertyName="managedDeviceApplications")
public class ManagedDeviceApplication {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("conflictResolutionTimeout")
    private String conflictResolutionTimeout;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("applications")
    private List<Application> applications = new ArrayList<Application>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The conflictResolutionTimeout
     */
    @JsonProperty("conflictResolutionTimeout")
    public String getConflictResolutionTimeout() {
        return conflictResolutionTimeout;
    }

    /**
     * 
     * (Required)
     * 
     * @param conflictResolutionTimeout
     *     The conflictResolutionTimeout
     */
    @JsonProperty("conflictResolutionTimeout")
    public void setConflictResolutionTimeout(String conflictResolutionTimeout) {
        this.conflictResolutionTimeout = conflictResolutionTimeout;
    }

    public ManagedDeviceApplication withConflictResolutionTimeout(String conflictResolutionTimeout) {
        this.conflictResolutionTimeout = conflictResolutionTimeout;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The applications
     */
    @JsonProperty("applications")
    public List<Application> getApplications() {
        return applications;
    }

    /**
     * 
     * (Required)
     * 
     * @param applications
     *     The applications
     */
    @JsonProperty("applications")
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public ManagedDeviceApplication withApplications(List<Application> applications) {
        this.applications = applications;
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
		return new HashCodeBuilder().append(conflictResolutionTimeout).append(applications).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof ManagedDeviceApplication) == false) {
			isEqual = false;
		} else {
			ManagedDeviceApplication rhs = ((ManagedDeviceApplication) other);
			return new EqualsBuilder().append(conflictResolutionTimeout, rhs.conflictResolutionTimeout)
					.append(applications, rhs.applications).isEquals();
		}
		return isEqual;
	}

}
