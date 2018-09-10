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
package com.accenture.avs.device.json.object.configuration;

import javax.annotation.Generated;

import com.accenture.avs.device.annotation.ManagedApplicationConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "USEGLOBALVQEBW" })
@ManagedApplicationConfiguration(platform = "IPTV", deviceType = "STB", propertyName = "USEGLOBALVQEBWFLAG")
public class GlobalVQEFlag {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("USEGLOBALVQEBW")
	private Boolean uSEGLOBALVQEBW;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The uSEGLOBALVQEBW
	 */
	@JsonProperty("USEGLOBALVQEBW")
	public Boolean getUSEGLOBALVQEBW() {
		return uSEGLOBALVQEBW;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param uSEGLOBALVQEBW
	 *            The USEGLOBALVQEBW
	 */
	@JsonProperty("USEGLOBALVQEBW")
	public void setUSEGLOBALVQEBW(Boolean uSEGLOBALVQEBW) {
		this.uSEGLOBALVQEBW = uSEGLOBALVQEBW;
	}

	/**
	 * 
	 * @param uSEGLOBALVQEBW
	 * @return
	 */
	public GlobalVQEFlag withUSEGLOBALVQEBW(Boolean uSEGLOBALVQEBW) {
		this.uSEGLOBALVQEBW = uSEGLOBALVQEBW;
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
		return new HashCodeBuilder().append(uSEGLOBALVQEBW).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof GlobalVQEFlag) == false) {
			isEqual = false;
		} else {
			GlobalVQEFlag rhs = ((GlobalVQEFlag) other);
			isEqual = new EqualsBuilder().append(uSEGLOBALVQEBW, rhs.uSEGLOBALVQEBW).isEquals();
		}
		return isEqual;
	}

}
