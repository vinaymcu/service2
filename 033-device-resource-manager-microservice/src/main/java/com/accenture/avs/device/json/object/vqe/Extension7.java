
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "pcrRestampEnable",
    "pcrRestampEnableUpdateType"
})
public class Extension7 implements Serializable
{

    @JsonProperty("pcrRestampEnable")
    private Boolean pcrRestampEnable;
    @JsonProperty("pcrRestampEnableUpdateType")
    private String pcrRestampEnableUpdateType;
    private final static long serialVersionUID = 7302485515490488862L;

    /**
     * 
     * @return
     *     The pcrRestampEnable
     */
    @JsonProperty("pcrRestampEnable")
    public Boolean getPcrRestampEnable() {
        return pcrRestampEnable;
    }

    /**
     * 
     * @param pcrRestampEnable
     *     The pcrRestampEnable
     */
    @JsonProperty("pcrRestampEnable")
    public void setPcrRestampEnable(Boolean pcrRestampEnable) {
        this.pcrRestampEnable = pcrRestampEnable;
    }

    /**
     * 
     * @return
     *     The pcrRestampEnableUpdateType
     */
    @JsonProperty("pcrRestampEnableUpdateType")
    public String getPcrRestampEnableUpdateType() {
        return pcrRestampEnableUpdateType;
    }

    /**
     * 
     * @param pcrRestampEnableUpdateType
     *     The pcrRestampEnableUpdateType
     */
    @JsonProperty("pcrRestampEnableUpdateType")
    public void setPcrRestampEnableUpdateType(String pcrRestampEnableUpdateType) {
        this.pcrRestampEnableUpdateType = pcrRestampEnableUpdateType;
    }

}
