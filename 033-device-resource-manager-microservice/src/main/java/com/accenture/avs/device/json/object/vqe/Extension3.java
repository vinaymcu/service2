
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "rccExtraIgmpIp",
    "rccExtraIgmpIpUpdateType",
    "extension"
})
public class Extension3 implements Serializable
{

    @JsonProperty("rccExtraIgmpIp")
    private String rccExtraIgmpIp;
    @JsonProperty("rccExtraIgmpIpUpdateType")
    private String rccExtraIgmpIpUpdateType;
    @JsonProperty("extension")
    private Extension4 extension;
    private final static long serialVersionUID = 2949267250101255987L;

    /**
     * 
     * @return
     *     The rccExtraIgmpIp
     */
    @JsonProperty("rccExtraIgmpIp")
    public String getRccExtraIgmpIp() {
        return rccExtraIgmpIp;
    }

    /**
     * 
     * @param rccExtraIgmpIp
     *     The rccExtraIgmpIp
     */
    @JsonProperty("rccExtraIgmpIp")
    public void setRccExtraIgmpIp(String rccExtraIgmpIp) {
        this.rccExtraIgmpIp = rccExtraIgmpIp;
    }

    /**
     * 
     * @return
     *     The rccExtraIgmpIpUpdateType
     */
    @JsonProperty("rccExtraIgmpIpUpdateType")
    public String getRccExtraIgmpIpUpdateType() {
        return rccExtraIgmpIpUpdateType;
    }

    /**
     * 
     * @param rccExtraIgmpIpUpdateType
     *     The rccExtraIgmpIpUpdateType
     */
    @JsonProperty("rccExtraIgmpIpUpdateType")
    public void setRccExtraIgmpIpUpdateType(String rccExtraIgmpIpUpdateType) {
        this.rccExtraIgmpIpUpdateType = rccExtraIgmpIpUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension4 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension4 extension) {
        this.extension = extension;
    }

}
