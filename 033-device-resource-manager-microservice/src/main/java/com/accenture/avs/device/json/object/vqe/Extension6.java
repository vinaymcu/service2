
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "viewershipEnable",
    "viewershipEnableUpdateType",
    "extension"
})
public class Extension6 implements Serializable
{

    @JsonProperty("viewershipEnable")
    private Boolean viewershipEnable;
    @JsonProperty("viewershipEnableUpdateType")
    private String viewershipEnableUpdateType;
    @JsonProperty("extension")
    private Extension7 extension;
    private final static long serialVersionUID = 8874915660220062155L;

    /**
     * 
     * @return
     *     The viewershipEnable
     */
    @JsonProperty("viewershipEnable")
    public Boolean getViewershipEnable() {
        return viewershipEnable;
    }

    /**
     * 
     * @param viewershipEnable
     *     The viewershipEnable
     */
    @JsonProperty("viewershipEnable")
    public void setViewershipEnable(Boolean viewershipEnable) {
        this.viewershipEnable = viewershipEnable;
    }

    /**
     * 
     * @return
     *     The viewershipEnableUpdateType
     */
    @JsonProperty("viewershipEnableUpdateType")
    public String getViewershipEnableUpdateType() {
        return viewershipEnableUpdateType;
    }

    /**
     * 
     * @param viewershipEnableUpdateType
     *     The viewershipEnableUpdateType
     */
    @JsonProperty("viewershipEnableUpdateType")
    public void setViewershipEnableUpdateType(String viewershipEnableUpdateType) {
        this.viewershipEnableUpdateType = viewershipEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension7 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension7 extension) {
        this.extension = extension;
    }

}
