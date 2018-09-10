/***************************************************************************
 * Copyright (C) Accenture
 *
 * The reproduction, transmission or use of this document or its contents is not permitted without
 * prior express written consent of Accenture. Offenders will be liable for damages. All rights,
 * including but not limited to rights created by patent grant or registration of a utility model or
 * design, are reserved.
 *
 * Accenture reserves the right to modify technical specifications and features.
 *
 * Technical specifications and features are binding only insofar as they are specifically and
 * expressly agreed upon in a written contract.
 *
 **************************************************************************/
package com.accenture.avs.device.json.object.devicemanager;

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "device" })
public class CreateDeviceRequest implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8581030632351873707L;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("device")
	private DeviceDto device;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The device
	 */
	@JsonProperty("device")
	public DeviceDto getDevice() {
		return device;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param device
	 *            The device
	 */
	@JsonProperty("device")
	public void setDevice(DeviceDto device) {
		this.device = device;
	}

	/**
	 * 
	 * @param device
	 * @return
	 */
	public CreateDeviceRequest withDevice(DeviceDto device) {
		this.device = device;
		return this;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(device).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof CreateDeviceRequest) == false) {
			isEqual = false;
		} else {
			CreateDeviceRequest rhs = ((CreateDeviceRequest) other);
			isEqual = new EqualsBuilder().append(device, rhs.device).isEquals();
		}
		return isEqual;
	}

}
