package com.accenture.avs.device.json.object.devicemanager;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "deviceRegistration" })
public class RegisterDeviceRequest {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("deviceRegistration")
	private DeviceRegistration deviceRegistration;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The deviceRegistration
	 */
	@JsonProperty("deviceRegistration")
	public DeviceRegistration getDeviceRegistration() {
		return deviceRegistration;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param deviceRegistration
	 *            The deviceRegistration
	 */
	@JsonProperty("deviceRegistration")
	public void setDeviceRegistration(DeviceRegistration deviceRegistration) {
		this.deviceRegistration = deviceRegistration;
	}

	public RegisterDeviceRequest withDeviceRegistration(DeviceRegistration deviceRegistration) {
		this.deviceRegistration = deviceRegistration;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(deviceRegistration).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof RegisterDeviceRequest) == false) {
			return false;
		}
		RegisterDeviceRequest rhs = ((RegisterDeviceRequest) other);
		return new EqualsBuilder().append(deviceRegistration, rhs.deviceRegistration).isEquals();
	}

}
