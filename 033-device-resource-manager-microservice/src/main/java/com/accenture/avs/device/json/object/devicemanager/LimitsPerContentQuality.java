
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "contentQuality",
    "maxDevices",
    "maxStreams"
})
public class LimitsPerContentQuality implements Serializable
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contentQuality")
    private String contentQuality;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("maxDevices")
    private Long maxDevices;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("maxStreams")
    private Long maxStreams;
    private final static long serialVersionUID = 1324969252351244537L;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The contentQuality
     */
    @JsonProperty("contentQuality")
    public String getContentQuality() {
        return contentQuality;
    }

    /**
     * 
     * (Required)
     * 
     * @param contentQuality
     *     The contentQuality
     */
    @JsonProperty("contentQuality")
    public void setContentQuality(String contentQuality) {
        this.contentQuality = contentQuality;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The maxDevices
     */
    @JsonProperty("maxDevices")
    public Long getMaxDevices() {
        return maxDevices;
    }

    /**
     * 
     * (Required)
     * 
     * @param maxDevices
     *     The maxDevices
     */
    @JsonProperty("maxDevices")
    public void setMaxDevices(Long maxDevices) {
        this.maxDevices = maxDevices;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The maxStreams
     */
    @JsonProperty("maxStreams")
    public Long getMaxStreams() {
        return maxStreams;
    }

    /**
     * 
     * (Required)
     * 
     * @param maxStreams
     *     The maxStreams
     */
    @JsonProperty("maxStreams")
    public void setMaxStreams(Long maxStreams) {
        this.maxStreams = maxStreams;
    }

}
