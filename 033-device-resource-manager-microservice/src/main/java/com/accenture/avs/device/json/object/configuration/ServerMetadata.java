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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author singh.saurabh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "metadataValue", "metadataName" })
public class ServerMetadata {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("metadataValue")
	private String metadataValue;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("metadataName")
	private String metadataName;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The metadataValue
	 */
	@JsonProperty("metadataValue")
	public String getMetadataValue() {
		return metadataValue;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param metadataValue
	 *            The metadataValue
	 */
	@JsonProperty("metadataValue")
	public void setMetadataValue(String metadataValue) {
		this.metadataValue = metadataValue;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The metadataName
	 */
	@JsonProperty("metadataName")
	public String getMetadataName() {
		return metadataName;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param metadataName
	 *            The metadataName
	 */
	@JsonProperty("metadataName")
	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
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
		return new HashCodeBuilder().append(metadataValue).append(metadataName).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof ServerMetadata) == false) {
			isEqual = false;
		} else {
			ServerMetadata rhs = ((ServerMetadata) other);
			isEqual = new EqualsBuilder().append(metadataValue, rhs.metadataValue)
					.append(metadataName, rhs.metadataName).isEquals();
		}
		return isEqual;
	}

}
