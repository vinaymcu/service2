package com.accenture.avs.device.json.object.devicemanager;

import java.util.HashMap;
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
@JsonPropertyOrder({ "userName", "deviceId" })
public class UnAssignDeviceRequest {

	@JsonProperty("userName")
	private String userName;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("deviceId")
	private String deviceId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The userName
	 */
	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 *            The userName
	 */
	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UnAssignDeviceRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The deviceId
	 */
	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param deviceId
	 *            The deviceId
	 */
	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public UnAssignDeviceRequest withDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
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

	public UnAssignDeviceRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userName).append(deviceId).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof UnAssignDeviceRequest) == false) {
			return false;
		}
		UnAssignDeviceRequest rhs = ((UnAssignDeviceRequest) other);
		return new EqualsBuilder().append(userName, rhs.userName).append(deviceId, rhs.deviceId)
				.append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
