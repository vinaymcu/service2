
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "resultCode",
    "resultDescription",
    "executionTime"
})
public class SetDevicePropertyResponse implements Serializable
{

    /**
     * result code for the response
     * (Required)
     * 
     */
    @JsonProperty("resultCode")
    @JsonPropertyDescription("")
    private String resultCode;
    /**
     * Description corresponding to the result code
     * (Required)
     * 
     */
    @JsonProperty("resultDescription")
    @JsonPropertyDescription("")
    private String resultDescription;
    /**
     * GM Time in Milliseconds
     * (Required)
     * 
     */
    @JsonProperty("executionTime")
    @JsonPropertyDescription("")
    private Integer executionTime;
    private final static long serialVersionUID = -8294174541427424721L;

    /**
     * result code for the response
     * (Required)
     * 
     * @return
     *     The resultCode
     */
    @JsonProperty("resultCode")
    public String getResultCode() {
        return resultCode;
    }

    /**
     * result code for the response
     * (Required)
     * 
     * @param resultCode
     *     The resultCode
     */
    @JsonProperty("resultCode")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Description corresponding to the result code
     * (Required)
     * 
     * @return
     *     The resultDescription
     */
    @JsonProperty("resultDescription")
    public String getResultDescription() {
        return resultDescription;
    }

    /**
     * Description corresponding to the result code
     * (Required)
     * 
     * @param resultDescription
     *     The resultDescription
     */
    @JsonProperty("resultDescription")
    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    /**
     * GM Time in Milliseconds
     * (Required)
     * 
     * @return
     *     The executionTime
     */
    @JsonProperty("executionTime")
    public Integer getExecutionTime() {
        return executionTime;
    }

    /**
     * GM Time in Milliseconds
     * (Required)
     * 
     * @param executionTime
     *     The executionTime
     */
    @JsonProperty("executionTime")
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

}
