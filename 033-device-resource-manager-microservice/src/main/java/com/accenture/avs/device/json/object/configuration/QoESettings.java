
package com.accenture.avs.device.json.object.configuration;

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
    "rccOverride",
    "overrideDefaultQoEBandwidth"
})
@ManagedConfiguration(type="microservice", label="qoeSettings")
public class QoESettings {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rccOverride")
    private boolean rccOverride;
    
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("overrideDefaultQoEBandwidth")
    private boolean overrideDefaultQoEBandwidth;
    

    
    
    /**
	 * @return the overrideDefaultQoEBandwidth
	 */
    @JsonProperty("overrideDefaultQoEBandwidth")
	public boolean isOverrideDefaultQoEBandwidth() {
		return overrideDefaultQoEBandwidth;
	}

	/**
	 * @param overrideDefaultQoEBandwidth the overrideDefaultQoEBandwidth to set
	 */
    @JsonProperty("overrideDefaultQoEBandwidth")
	public void setOverrideDefaultQoEBandwidth(boolean overrideDefaultQoEBandwidth) {
		this.overrideDefaultQoEBandwidth = overrideDefaultQoEBandwidth;
	}

	/**
     * 
     * (Required)
     * 
     * @return
     *     The rccOverride
     */
    @JsonProperty("rccOverride")
    public boolean getRccOverride() {
        return rccOverride;
    }

    /**
     * 
     * (Required)
     * 
     * @param rccOverride
     *     The rccOverride
     */
    @JsonProperty("rccOverride")
    public void setRccOverride(boolean rccOverride) {
        this.rccOverride = rccOverride;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(rccOverride).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QoESettings) == false) {
            return false;
        }
        QoESettings rhs = ((QoESettings) other);
        return new EqualsBuilder().append(rccOverride, rhs.rccOverride).isEquals();
    }

}
