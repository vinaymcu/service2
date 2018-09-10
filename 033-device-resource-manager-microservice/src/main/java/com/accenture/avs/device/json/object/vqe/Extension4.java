
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "logLevel",
    "logLevelUpdateType",
    "extension"
})
public class Extension4 implements Serializable
{

    @JsonProperty("logLevel")
    private Integer logLevel;
    @JsonProperty("logLevelUpdateType")
    private String logLevelUpdateType;
    @JsonProperty("extension")
    private Extension5 extension;
    private final static long serialVersionUID = 1719302892112704178L;

    /**
     * 
     * @return
     *     The logLevel
     */
    @JsonProperty("logLevel")
    public Integer getLogLevel() {
        return logLevel;
    }

    /**
     * 
     * @param logLevel
     *     The logLevel
     */
    @JsonProperty("logLevel")
    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * 
     * @return
     *     The logLevelUpdateType
     */
    @JsonProperty("logLevelUpdateType")
    public String getLogLevelUpdateType() {
        return logLevelUpdateType;
    }

    /**
     * 
     * @param logLevelUpdateType
     *     The logLevelUpdateType
     */
    @JsonProperty("logLevelUpdateType")
    public void setLogLevelUpdateType(String logLevelUpdateType) {
        this.logLevelUpdateType = logLevelUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension5 getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension5 extension) {
        this.extension = extension;
    }

}
