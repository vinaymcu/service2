
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "maxReceiveBandwidthSd",
    "maxReceiveBandwidthSdUpdateType",
    "maxReceiveBandwidthHd",
    "maxReceiveBandwidthHdUpdateType",
    "minHdStreamBitrate",
    "minHdStreamBitrateUpdateType",
    "maxFastfill",
    "maxFastfillUpdateType",
    "appDelay",
    "appDelayUpdateType",
    "srcIpFilterEnable",
    "srcIpFilterEnableUpdateType",
    "extension"
})
public class Extension implements Serializable
{

    @JsonProperty("maxReceiveBandwidthSd")
    private Long maxReceiveBandwidthSd;
    @JsonProperty("maxReceiveBandwidthSdUpdateType")
    private String maxReceiveBandwidthSdUpdateType;
    @JsonProperty("maxReceiveBandwidthHd")
    private Long maxReceiveBandwidthHd;
    @JsonProperty("maxReceiveBandwidthHdUpdateType")
    private String maxReceiveBandwidthHdUpdateType;
    @JsonProperty("minHdStreamBitrate")
    private Long minHdStreamBitrate;
    @JsonProperty("minHdStreamBitrateUpdateType")
    private String minHdStreamBitrateUpdateType;
    @JsonProperty("maxFastfill")
    private Long maxFastfill;
    @JsonProperty("maxFastfillUpdateType")
    private String maxFastfillUpdateType;
    @JsonProperty("appDelay")
    private Long appDelay;
    @JsonProperty("appDelayUpdateType")
    private String appDelayUpdateType;
    @JsonProperty("srcIpFilterEnable")
    private Boolean srcIpFilterEnable;
    @JsonProperty("srcIpFilterEnableUpdateType")
    private String srcIpFilterEnableUpdateType;
    @JsonProperty("extension")
    private Extension2 extension;
    private final static long serialVersionUID = -3396320127996446001L;

    /**
     * 
     * @return
     *     The maxReceiveBandwidthSd
     */
    @JsonProperty("maxReceiveBandwidthSd")
    public Long getMaxReceiveBandwidthSd() {
        return maxReceiveBandwidthSd;
    }

    /**
     * 
     * @param maxReceiveBandwidthSd
     *     The maxReceiveBandwidthSd
     */
    @JsonProperty("maxReceiveBandwidthSd")
    public void setMaxReceiveBandwidthSd(Long maxReceiveBandwidthSd) {
        this.maxReceiveBandwidthSd = maxReceiveBandwidthSd;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthSdUpdateType
     */
    @JsonProperty("maxReceiveBandwidthSdUpdateType")
    public String getMaxReceiveBandwidthSdUpdateType() {
        return maxReceiveBandwidthSdUpdateType;
    }

    /**
     * 
     * @param maxReceiveBandwidthSdUpdateType
     *     The maxReceiveBandwidthSdUpdateType
     */
    @JsonProperty("maxReceiveBandwidthSdUpdateType")
    public void setMaxReceiveBandwidthSdUpdateType(String maxReceiveBandwidthSdUpdateType) {
        this.maxReceiveBandwidthSdUpdateType = maxReceiveBandwidthSdUpdateType;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthHd
     */
    @JsonProperty("maxReceiveBandwidthHd")
    public Long getMaxReceiveBandwidthHd() {
        return maxReceiveBandwidthHd;
    }

    /**
     * 
     * @param maxReceiveBandwidthHd
     *     The maxReceiveBandwidthHd
     */
    @JsonProperty("maxReceiveBandwidthHd")
    public void setMaxReceiveBandwidthHd(Long maxReceiveBandwidthHd) {
        this.maxReceiveBandwidthHd = maxReceiveBandwidthHd;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthHdUpdateType
     */
    @JsonProperty("maxReceiveBandwidthHdUpdateType")
    public String getMaxReceiveBandwidthHdUpdateType() {
        return maxReceiveBandwidthHdUpdateType;
    }

    /**
     * 
     * @param maxReceiveBandwidthHdUpdateType
     *     The maxReceiveBandwidthHdUpdateType
     */
    @JsonProperty("maxReceiveBandwidthHdUpdateType")
    public void setMaxReceiveBandwidthHdUpdateType(String maxReceiveBandwidthHdUpdateType) {
        this.maxReceiveBandwidthHdUpdateType = maxReceiveBandwidthHdUpdateType;
    }

    /**
     * 
     * @return
     *     The minHdStreamBitrate
     */
    @JsonProperty("minHdStreamBitrate")
    public Long getMinHdStreamBitrate() {
        return minHdStreamBitrate;
    }

    /**
     * 
     * @param minHdStreamBitrate
     *     The minHdStreamBitrate
     */
    @JsonProperty("minHdStreamBitrate")
    public void setMinHdStreamBitrate(Long minHdStreamBitrate) {
        this.minHdStreamBitrate = minHdStreamBitrate;
    }

    /**
     * 
     * @return
     *     The minHdStreamBitrateUpdateType
     */
    @JsonProperty("minHdStreamBitrateUpdateType")
    public String getMinHdStreamBitrateUpdateType() {
        return minHdStreamBitrateUpdateType;
    }

    /**
     * 
     * @param minHdStreamBitrateUpdateType
     *     The minHdStreamBitrateUpdateType
     */
    @JsonProperty("minHdStreamBitrateUpdateType")
    public void setMinHdStreamBitrateUpdateType(String minHdStreamBitrateUpdateType) {
        this.minHdStreamBitrateUpdateType = minHdStreamBitrateUpdateType;
    }

    /**
     * 
     * @return
     *     The maxFastfill
     */
    @JsonProperty("maxFastfill")
    public Long getMaxFastfill() {
        return maxFastfill;
    }

    /**
     * 
     * @param maxFastfill
     *     The maxFastfill
     */
    @JsonProperty("maxFastfill")
    public void setMaxFastfill(Long maxFastfill) {
        this.maxFastfill = maxFastfill;
    }

    /**
     * 
     * @return
     *     The maxFastfillUpdateType
     */
    @JsonProperty("maxFastfillUpdateType")
    public String getMaxFastfillUpdateType() {
        return maxFastfillUpdateType;
    }

    /**
     * 
     * @param maxFastfillUpdateType
     *     The maxFastfillUpdateType
     */
    @JsonProperty("maxFastfillUpdateType")
    public void setMaxFastfillUpdateType(String maxFastfillUpdateType) {
        this.maxFastfillUpdateType = maxFastfillUpdateType;
    }

    /**
     * 
     * @return
     *     The appDelay
     */
    @JsonProperty("appDelay")
    public Long getAppDelay() {
        return appDelay;
    }

    /**
     * 
     * @param appDelay
     *     The appDelay
     */
    @JsonProperty("appDelay")
    public void setAppDelay(Long appDelay) {
        this.appDelay = appDelay;
    }

    /**
     * 
     * @return
     *     The appDelayUpdateType
     */
    @JsonProperty("appDelayUpdateType")
    public String getAppDelayUpdateType() {
        return appDelayUpdateType;
    }

    /**
     * 
     * @param appDelayUpdateType
     *     The appDelayUpdateType
     */
    @JsonProperty("appDelayUpdateType")
    public void setAppDelayUpdateType(String appDelayUpdateType) {
        this.appDelayUpdateType = appDelayUpdateType;
    }

    /**
     * 
     * @return
     *     The srcIpFilterEnable
     */
    @JsonProperty("srcIpFilterEnable")
    public Boolean getSrcIpFilterEnable() {
        return srcIpFilterEnable;
    }

    /**
     * 
     * @param srcIpFilterEnable
     *     The srcIpFilterEnable
     */
    @JsonProperty("srcIpFilterEnable")
    public void setSrcIpFilterEnable(Boolean srcIpFilterEnable) {
        this.srcIpFilterEnable = srcIpFilterEnable;
    }

    /**
     * 
     * @return
     *     The srcIpFilterEnableUpdateType
     */
    @JsonProperty("srcIpFilterEnableUpdateType")
    public String getSrcIpFilterEnableUpdateType() {
        return srcIpFilterEnableUpdateType;
    }

    /**
     * 
     * @param srcIpFilterEnableUpdateType
     *     The srcIpFilterEnableUpdateType
     */
    @JsonProperty("srcIpFilterEnableUpdateType")
    public void setSrcIpFilterEnableUpdateType(String srcIpFilterEnableUpdateType) {
        this.srcIpFilterEnableUpdateType = srcIpFilterEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension2 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension2 extension) {
        this.extension = extension;
    }

}
