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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.accenture.avs.be.lib.configuration.client.annotations.ManagedConfiguration;
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
@JsonPropertyOrder({ "servers" })
@ManagedConfiguration(type = "microservice", label = "servers")
public class Servers {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("servers")
	private List<Server> servers = new ArrayList<Server>();

	/**
	 * 
	 * (Required)
	 * 
	 * @return The servers
	 */
	@JsonProperty("servers")
	public List<Server> getServers() {
		return servers;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param servers
	 *            The servers
	 */
	@JsonProperty("servers")
	public void setServers(List<Server> servers) {
		this.servers = servers;
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
		return new HashCodeBuilder().append(servers).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof Servers) == false) {
			isEqual = false;
		} else {
			Servers rhs = ((Servers) other);
			isEqual = new EqualsBuilder().append(servers, rhs.servers).isEquals();
		}
		return isEqual;
	}

}
