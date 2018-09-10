
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "retEnable",
    "rccEnable",
    "networkBufferSize",
    "maxRecvBwSD",
    "maxRecvBwHD",
    "maxRecvRccBwSD",
    "maxRecvRccBwHD",
    "groupAttributes"
})
public class CreateVQEGroupsRequest implements Serializable
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("retEnable")
    private Boolean retEnable;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rccEnable")
    private Boolean rccEnable;
    @JsonProperty("networkBufferSize")
    private Long networkBufferSize;
    @JsonProperty("maxRecvBwSD")
    private Long maxRecvBwSD;
    @JsonProperty("maxRecvBwHD")
    private Long maxRecvBwHD;
    @JsonProperty("maxRecvRccBwSD")
    private Long maxRecvRccBwSD;
    @JsonProperty("maxRecvRccBwHD")
    private Long maxRecvRccBwHD;
    /**
     * 
     */
    @JsonProperty("groupAttributes")
    private GroupAttributesDto groupAttributes;
    private final static long serialVersionUID = -11690477490495339L;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The retEnable
     */
    @JsonProperty("retEnable")
    public Boolean getRetEnable() {
        return retEnable;
    }

    /**
     * 
     * (Required)
     * 
     * @param retEnable
     *     The retEnable
     */
    @JsonProperty("retEnable")
    public void setRetEnable(Boolean retEnable) {
        this.retEnable = retEnable;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The rccEnable
     */
    @JsonProperty("rccEnable")
    public Boolean getRccEnable() {
        return rccEnable;
    }

    /**
     * 
     * (Required)
     * 
     * @param rccEnable
     *     The rccEnable
     */
    @JsonProperty("rccEnable")
    public void setRccEnable(Boolean rccEnable) {
        this.rccEnable = rccEnable;
    }

    /**
     * 
     * @return
     *     The networkBufferSize
     */
    @JsonProperty("networkBufferSize")
    public Long getNetworkBufferSize() {
        return networkBufferSize;
    }

    /**
     * 
     * @param networkBufferSize
     *     The networkBufferSize
     */
    @JsonProperty("networkBufferSize")
    public void setNetworkBufferSize(Long networkBufferSize) {
        this.networkBufferSize = networkBufferSize;
    }

    /**
     * 
     * @return
     *     The maxRecvBwSD
     */
    @JsonProperty("maxRecvBwSD")
    public Long getMaxRecvBwSD() {
        return maxRecvBwSD;
    }

    /**
     * 
     * @param maxRecvBwSD
     *     The maxRecvBwSD
     */
    @JsonProperty("maxRecvBwSD")
    public void setMaxRecvBwSD(Long maxRecvBwSD) {
        this.maxRecvBwSD = maxRecvBwSD;
    }

    /**
     * 
     * @return
     *     The maxRecvBwHD
     */
    @JsonProperty("maxRecvBwHD")
    public Long getMaxRecvBwHD() {
        return maxRecvBwHD;
    }

    /**
     * 
     * @param maxRecvBwHD
     *     The maxRecvBwHD
     */
    @JsonProperty("maxRecvBwHD")
    public void setMaxRecvBwHD(Long maxRecvBwHD) {
        this.maxRecvBwHD = maxRecvBwHD;
    }

    /**
     * 
     * @return
     *     The maxRecvRccBwSD
     */
    @JsonProperty("maxRecvRccBwSD")
    public Long getMaxRecvRccBwSD() {
        return maxRecvRccBwSD;
    }

    /**
     * 
     * @param maxRecvRccBwSD
     *     The maxRecvRccBwSD
     */
    @JsonProperty("maxRecvRccBwSD")
    public void setMaxRecvRccBwSD(Long maxRecvRccBwSD) {
        this.maxRecvRccBwSD = maxRecvRccBwSD;
    }

    /**
     * 
     * @return
     *     The maxRecvRccBwHD
     */
    @JsonProperty("maxRecvRccBwHD")
    public Long getMaxRecvRccBwHD() {
        return maxRecvRccBwHD;
    }

    /**
     * 
     * @param maxRecvRccBwHD
     *     The maxRecvRccBwHD
     */
    @JsonProperty("maxRecvRccBwHD")
    public void setMaxRecvRccBwHD(Long maxRecvRccBwHD) {
        this.maxRecvRccBwHD = maxRecvRccBwHD;
    }

    /**
     * 
     * @return
     *     The groupAttributes
     */
    @JsonProperty("groupAttributes")
    public GroupAttributesDto getGroupAttributes() {
        return groupAttributes;
    }

    /**
     * 
     * @param groupAttributes
     *     The groupAttributes
     */
    @JsonProperty("groupAttributes")
    public void setGroupAttributes(GroupAttributesDto groupAttributes) {
        this.groupAttributes = groupAttributes;
    }

}
