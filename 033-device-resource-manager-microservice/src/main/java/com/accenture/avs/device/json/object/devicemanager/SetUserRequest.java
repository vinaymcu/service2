
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "userName",
    "maxAllowedIPTVDevices",
    "bandwidthProfile",
    "overrideBandwidth",
    "qoeControlBandwidth",
    "retEnable",
    "rccEnable",
    "networkBufferSize",
    "natBindingRefreshInterval",
    "limitsPerContentQuality"
})
public class SetUserRequest implements Serializable
{

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("maxAllowedIPTVDevices")
    private Long maxAllowedIPTVDevices;
    @JsonProperty("bandwidthProfile")
    private String bandwidthProfile;
    @JsonProperty("overrideBandwidth")
    private Long overrideBandwidth;
    @JsonProperty("qoeControlBandwidth")
    private Long qoeControlBandwidth;
    @JsonProperty("retEnable")
    private String retEnable;
    @JsonProperty("rccEnable")
    private String rccEnable;
    @JsonProperty("networkBufferSize")
    private Long networkBufferSize;
    @JsonProperty("natBindingRefreshInterval")
    private Long natBindingRefreshInterval;
    
    
    @JsonProperty("limitsPerContentQuality")
    private List<LimitsPerContentQuality> limitsPerContentQuality = new ArrayList<LimitsPerContentQuality>();
    private final static long serialVersionUID = -1706896354451153977L;

    /**
     * 
     * @return
     *     The userName
     */
    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The maxAllowedIPTVDevices
     */
    @JsonProperty("maxAllowedIPTVDevices")
    public Long getMaxAllowedIPTVDevices() {
        return maxAllowedIPTVDevices;
    }

    /**
     * 
     * @param maxAllowedIPTVDevices
     *     The maxAllowedIPTVDevices
     */
    @JsonProperty("maxAllowedIPTVDevices")
    public void setMaxAllowedIPTVDevices(Long maxAllowedIPTVDevices) {
        this.maxAllowedIPTVDevices = maxAllowedIPTVDevices;
    }

    /**
     * 
     * @return
     *     The bandwidthProfile
     */
    @JsonProperty("bandwidthProfile")
    public String getBandwidthProfile() {
        return bandwidthProfile;
    }

    /**
     * 
     * @param bandwidthProfile
     *     The bandwidthProfile
     */
    @JsonProperty("bandwidthProfile")
    public void setBandwidthProfile(String bandwidthProfile) {
        this.bandwidthProfile = bandwidthProfile;
    }

    /**
     * 
     * @return
     *     The overrideBandwidth
     */
    @JsonProperty("overrideBandwidth")
    public Long getOverrideBandwidth() {
        return overrideBandwidth;
    }

    /**
     * 
     * @param overrideBandwidth
     *     The overrideBandwidth
     */
    @JsonProperty("overrideBandwidth")
    public void setOverrideBandwidth(Long overrideBandwidth) {
        this.overrideBandwidth = overrideBandwidth;
    }

    /**
     * 
     * @return
     *     The qoeControlBandwidth
     */
    @JsonProperty("qoeControlBandwidth")
    public Long getQoeControlBandwidth() {
        return qoeControlBandwidth;
    }

    /**
     * 
     * @param qoeControlBandwidth
     *     The qoeControlBandwidth
     */
    @JsonProperty("qoeControlBandwidth")
    public void setQoeControlBandwidth(Long qoeControlBandwidth) {
        this.qoeControlBandwidth = qoeControlBandwidth;
    }

    /**
     * 
     * @return
     *     The limitsPerContentQuality
     */
    @JsonProperty("limitsPerContentQuality")
    public List<LimitsPerContentQuality> getLimitsPerContentQuality() {
        return limitsPerContentQuality;
    }

    /**
     * 
     * @param limitsPerContentQuality
     *     The limitsPerContentQuality
     */
    @JsonProperty("limitsPerContentQuality")
    public void setLimitsPerContentQuality(List<LimitsPerContentQuality> limitsPerContentQuality) {
        this.limitsPerContentQuality = limitsPerContentQuality;
    }
    
	/**
	 * @return the retEnable
	 */
	@JsonProperty("retEnable")
	public Boolean getRetEnable() {
		return converToBoolean(this.retEnable);
	}

	/**
	 * @param retEnable
	 *            the retEnable to set
	 */
	@JsonProperty("retEnable")
	public void setRetEnable(String retEnable) {
		if (retEnable != null) {
			if (converToBoolean(retEnable) == null) {
				throw new DeviceManagerException(ErrorCode.JSON_PARSING_FAILED);
			}
			this.retEnable = retEnable;
		}
	}
	
	/**
	 * @return the rccEnable
	 */
	@JsonProperty("rccEnable")
	public Boolean getRccEnable() {
		return converToBoolean(this.rccEnable);
	}

	/**
	 * @param rccEnable
	 *            the rccEnable to set
	 */
	@JsonProperty("rccEnable")
	public void setRccEnable(String rccEnable) {
		if (rccEnable != null) {
			if (converToBoolean(rccEnable) == null) {
				throw new DeviceManagerException(ErrorCode.JSON_PARSING_FAILED);
			}
			this.rccEnable = rccEnable;
		}
	}
	
	/**
	 * This method converts the String to appropriate Boolean value.
	 * 
	 * @param stringValue
	 * @return
	 */
	private Boolean converToBoolean(String stringValue) {
		Boolean booleanValue = null;
		if (stringValue != null && !stringValue.isEmpty()) {
			switch (stringValue) {
			case "true":
				booleanValue = Boolean.TRUE;
				break;
			case "false":
				booleanValue = Boolean.FALSE;
				break;
			case "1":
				booleanValue = Boolean.TRUE;
				break;
			case "0":
				booleanValue = Boolean.FALSE;
				break;
			}
		}
		return booleanValue;
	}

    /**
     * @return the networkBufferSize
     */
    @JsonProperty("networkBufferSize")
    public Long getNetworkBufferSize() {
        return networkBufferSize;
    }

    /**
     * @param networkBufferSize the networkBufferSize to set
     */
    @JsonProperty("networkBufferSize")
    public void setNetworkBufferSize(Long networkBufferSize) {
        this.networkBufferSize = networkBufferSize;
    }

    /**
     * @return the natBindingRefreshInterval
     */
    @JsonProperty("natBindingRefreshInterval")
    public Long getNatBindingRefreshInterval() {
        return natBindingRefreshInterval;
    }

    /**
     * @param natBindingRefreshInterval the natBindingRefreshInterval to set
     */
    @JsonProperty("natBindingRefreshInterval")
    public void setNatBindingRefreshInterval(Long natBindingRefreshInterval) {
        this.natBindingRefreshInterval = natBindingRefreshInterval;
    }

}
