
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "errorRepairSmartRequestEnable",
    "errorRepairSmartRequestEnableUpdateType",
    "errorRepairRepeatRequestEnable",
    "errorRepairRepeatRequestEnableUpdateType",
    "repairMinRoundTripTimeAbs",
    "repairMinRoundTripTimeAbsUpdateType",
    "extension"
})
public class Extension5 implements Serializable
{

    @JsonProperty("errorRepairSmartRequestEnable")
    private Boolean errorRepairSmartRequestEnable;
    @JsonProperty("errorRepairSmartRequestEnableUpdateType")
    private String errorRepairSmartRequestEnableUpdateType;
    @JsonProperty("errorRepairRepeatRequestEnable")
    private Boolean errorRepairRepeatRequestEnable;
    @JsonProperty("errorRepairRepeatRequestEnableUpdateType")
    private String errorRepairRepeatRequestEnableUpdateType;
    @JsonProperty("repairMinRoundTripTimeAbs")
    private Long repairMinRoundTripTimeAbs;
    @JsonProperty("repairMinRoundTripTimeAbsUpdateType")
    private String repairMinRoundTripTimeAbsUpdateType;
    @JsonProperty("extension")
    private Extension6 extension;
    private final static long serialVersionUID = -3193124032726591865L;

    /**
     * 
     * @return
     *     The errorRepairSmartRequestEnable
     */
    @JsonProperty("errorRepairSmartRequestEnable")
    public Boolean getErrorRepairSmartRequestEnable() {
        return errorRepairSmartRequestEnable;
    }

    /**
     * 
     * @param errorRepairSmartRequestEnable
     *     The errorRepairSmartRequestEnable
     */
    @JsonProperty("errorRepairSmartRequestEnable")
    public void setErrorRepairSmartRequestEnable(Boolean errorRepairSmartRequestEnable) {
        this.errorRepairSmartRequestEnable = errorRepairSmartRequestEnable;
    }

    /**
     * 
     * @return
     *     The errorRepairSmartRequestEnableUpdateType
     */
    @JsonProperty("errorRepairSmartRequestEnableUpdateType")
    public String getErrorRepairSmartRequestEnableUpdateType() {
        return errorRepairSmartRequestEnableUpdateType;
    }

    /**
     * 
     * @param errorRepairSmartRequestEnableUpdateType
     *     The errorRepairSmartRequestEnableUpdateType
     */
    @JsonProperty("errorRepairSmartRequestEnableUpdateType")
    public void setErrorRepairSmartRequestEnableUpdateType(String errorRepairSmartRequestEnableUpdateType) {
        this.errorRepairSmartRequestEnableUpdateType = errorRepairSmartRequestEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The errorRepairRepeatRequestEnable
     */
    @JsonProperty("errorRepairRepeatRequestEnable")
    public Boolean getErrorRepairRepeatRequestEnable() {
        return errorRepairRepeatRequestEnable;
    }

    /**
     * 
     * @param errorRepairRepeatRequestEnable
     *     The errorRepairRepeatRequestEnable
     */
    @JsonProperty("errorRepairRepeatRequestEnable")
    public void setErrorRepairRepeatRequestEnable(Boolean errorRepairRepeatRequestEnable) {
        this.errorRepairRepeatRequestEnable = errorRepairRepeatRequestEnable;
    }

    /**
     * 
     * @return
     *     The errorRepairRepeatRequestEnableUpdateType
     */
    @JsonProperty("errorRepairRepeatRequestEnableUpdateType")
    public String getErrorRepairRepeatRequestEnableUpdateType() {
        return errorRepairRepeatRequestEnableUpdateType;
    }

    /**
     * 
     * @param errorRepairRepeatRequestEnableUpdateType
     *     The errorRepairRepeatRequestEnableUpdateType
     */
    @JsonProperty("errorRepairRepeatRequestEnableUpdateType")
    public void setErrorRepairRepeatRequestEnableUpdateType(String errorRepairRepeatRequestEnableUpdateType) {
        this.errorRepairRepeatRequestEnableUpdateType = errorRepairRepeatRequestEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The repairMinRoundTripTimeAbs
     */
    @JsonProperty("repairMinRoundTripTimeAbs")
    public Long getRepairMinRoundTripTimeAbs() {
        return repairMinRoundTripTimeAbs;
    }

    /**
     * 
     * @param repairMinRoundTripTimeAbs
     *     The repairMinRoundTripTimeAbs
     */
    @JsonProperty("repairMinRoundTripTimeAbs")
    public void setRepairMinRoundTripTimeAbs(Long repairMinRoundTripTimeAbs) {
        this.repairMinRoundTripTimeAbs = repairMinRoundTripTimeAbs;
    }

    /**
     * 
     * @return
     *     The repairMinRoundTripTimeAbsUpdateType
     */
    @JsonProperty("repairMinRoundTripTimeAbsUpdateType")
    public String getRepairMinRoundTripTimeAbsUpdateType() {
        return repairMinRoundTripTimeAbsUpdateType;
    }

    /**
     * 
     * @param repairMinRoundTripTimeAbsUpdateType
     *     The repairMinRoundTripTimeAbsUpdateType
     */
    @JsonProperty("repairMinRoundTripTimeAbsUpdateType")
    public void setRepairMinRoundTripTimeAbsUpdateType(String repairMinRoundTripTimeAbsUpdateType) {
        this.repairMinRoundTripTimeAbsUpdateType = repairMinRoundTripTimeAbsUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension6 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension6 extension) {
        this.extension = extension;
    }

}
