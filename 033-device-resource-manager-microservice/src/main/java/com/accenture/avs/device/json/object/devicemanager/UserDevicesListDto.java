
package com.accenture.avs.device.json.object.devicemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "devices",
    "userName"
})
public class UserDevicesListDto {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("devices")
    private List<UserDeviceDto> devices = new ArrayList<UserDeviceDto>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userName")
    private String userName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The devices
     */
    @JsonProperty("devices")
    public List<UserDeviceDto> getDevices() {
        return devices;
    }

    /**
     * 
     * (Required)
     * 
     * @param devices
     *     The devices
     */
    @JsonProperty("devices")
    public void setDevices(List<UserDeviceDto> devices) {
        this.devices = devices;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The userName
     */
    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * (Required)
     * 
     * @param userName
     *     The userName
     */
    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(devices).append(userName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserDevicesListDto) == false) {
            return false;
        }
        UserDevicesListDto rhs = ((UserDevicesListDto) other);
        return new EqualsBuilder().append(devices, rhs.devices).append(userName, rhs.userName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
