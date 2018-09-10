
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "vtpName"
})
public class VideoTypeProfile implements Serializable
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("vtpName")
    private String vtpName;
    private final static long serialVersionUID = 5356062877742960298L;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The vtpName
     */
    @JsonProperty("vtpName")
    public String getVtpName() {
        return vtpName;
    }

    /**
     * 
     * (Required)
     * 
     * @param vtpName
     *     The vtpName
     */
    @JsonProperty("vtpName")
    public void setVtpName(String vtpName) {
        this.vtpName = vtpName;
    }

}
