
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "maxReceiveBandwidthSdRcc",
    "maxReceiveBandwidthSdRccUpdateType",
    "maxReceiveBandwidthHdRcc",
    "maxReceiveBandwidthHdRccUpdateType",
    "qoeEnable",
    "qoeEnableUpdateType",
    "extension"
})
public class Extension2 implements Serializable
{

    @JsonProperty("maxReceiveBandwidthSdRcc")
    private Long maxReceiveBandwidthSdRcc;
    @JsonProperty("maxReceiveBandwidthSdRccUpdateType")
    private String maxReceiveBandwidthSdRccUpdateType;
    @JsonProperty("maxReceiveBandwidthHdRcc")
    private Long maxReceiveBandwidthHdRcc;
    @JsonProperty("maxReceiveBandwidthHdRccUpdateType")
    private String maxReceiveBandwidthHdRccUpdateType;
    @JsonProperty("qoeEnable")
    private Boolean qoeEnable;
    @JsonProperty("qoeEnableUpdateType")
    private String qoeEnableUpdateType;
    @JsonProperty("extension")
    private Extension3 extension;
    private final static long serialVersionUID = 6553796617503152650L;

    /**
     * 
     * @return
     *     The maxReceiveBandwidthSdRcc
     */
    @JsonProperty("maxReceiveBandwidthSdRcc")
    public Long getMaxReceiveBandwidthSdRcc() {
        return maxReceiveBandwidthSdRcc;
    }

    /**
     * 
     * @param maxReceiveBandwidthSdRcc
     *     The maxReceiveBandwidthSdRcc
     */
    @JsonProperty("maxReceiveBandwidthSdRcc")
    public void setMaxReceiveBandwidthSdRcc(Long maxReceiveBandwidthSdRcc) {
        this.maxReceiveBandwidthSdRcc = maxReceiveBandwidthSdRcc;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthSdRccUpdateType
     */
    @JsonProperty("maxReceiveBandwidthSdRccUpdateType")
    public String getMaxReceiveBandwidthSdRccUpdateType() {
        return maxReceiveBandwidthSdRccUpdateType;
    }

    /**
     * 
     * @param maxReceiveBandwidthSdRccUpdateType
     *     The maxReceiveBandwidthSdRccUpdateType
     */
    @JsonProperty("maxReceiveBandwidthSdRccUpdateType")
    public void setMaxReceiveBandwidthSdRccUpdateType(String maxReceiveBandwidthSdRccUpdateType) {
        this.maxReceiveBandwidthSdRccUpdateType = maxReceiveBandwidthSdRccUpdateType;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthHdRcc
     */
    @JsonProperty("maxReceiveBandwidthHdRcc")
    public Long getMaxReceiveBandwidthHdRcc() {
        return maxReceiveBandwidthHdRcc;
    }

    /**
     * 
     * @param maxReceiveBandwidthHdRcc
     *     The maxReceiveBandwidthHdRcc
     */
    @JsonProperty("maxReceiveBandwidthHdRcc")
    public void setMaxReceiveBandwidthHdRcc(Long maxReceiveBandwidthHdRcc) {
        this.maxReceiveBandwidthHdRcc = maxReceiveBandwidthHdRcc;
    }

    /**
     * 
     * @return
     *     The maxReceiveBandwidthHdRccUpdateType
     */
    @JsonProperty("maxReceiveBandwidthHdRccUpdateType")
    public String getMaxReceiveBandwidthHdRccUpdateType() {
        return maxReceiveBandwidthHdRccUpdateType;
    }

    /**
     * 
     * @param maxReceiveBandwidthHdRccUpdateType
     *     The maxReceiveBandwidthHdRccUpdateType
     */
    @JsonProperty("maxReceiveBandwidthHdRccUpdateType")
    public void setMaxReceiveBandwidthHdRccUpdateType(String maxReceiveBandwidthHdRccUpdateType) {
        this.maxReceiveBandwidthHdRccUpdateType = maxReceiveBandwidthHdRccUpdateType;
    }

    /**
     * 
     * @return
     *     The qoeEnable
     */
    @JsonProperty("qoeEnable")
    public Boolean getQoeEnable() {
        return qoeEnable;
    }

    /**
     * 
     * @param qoeEnable
     *     The qoeEnable
     */
    @JsonProperty("qoeEnable")
    public void setQoeEnable(Boolean qoeEnable) {
        this.qoeEnable = qoeEnable;
    }

    /**
     * 
     * @return
     *     The qoeEnableUpdateType
     */
    @JsonProperty("qoeEnableUpdateType")
    public String getQoeEnableUpdateType() {
        return qoeEnableUpdateType;
    }

    /**
     * 
     * @param qoeEnableUpdateType
     *     The qoeEnableUpdateType
     */
    @JsonProperty("qoeEnableUpdateType")
    public void setQoeEnableUpdateType(String qoeEnableUpdateType) {
        this.qoeEnableUpdateType = qoeEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension3 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension3 extension) {
        this.extension = extension;
    }

}
