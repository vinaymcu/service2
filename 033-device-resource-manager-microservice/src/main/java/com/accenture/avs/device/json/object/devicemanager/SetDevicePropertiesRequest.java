
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "deviceProperties"
})
public class SetDevicePropertiesRequest implements Serializable
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceProperties")
    private List<DevicePropertyDto> deviceProperties = new ArrayList<DevicePropertyDto>();
    private final static long serialVersionUID = -3404986193081165152L;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The deviceProperties
     */
    @JsonProperty("deviceProperties")
    public List<DevicePropertyDto> getDeviceProperties() {
        return deviceProperties;
    }

    /**
     * 
     * (Required)
     * 
     * @param deviceProperties
     *     The deviceProperties
     */
    @JsonProperty("deviceProperties")
    public void setDeviceProperties(List<DevicePropertyDto> deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

}
