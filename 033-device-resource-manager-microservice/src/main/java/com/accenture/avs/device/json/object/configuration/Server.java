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
@JsonPropertyOrder({ "serverExternalId", "serverAddress", "serverType", "serverMetadata", "serverName",
		"serverPortNumber", "serverId", "serverDescription", "serverLocation" })
public class Server {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverExternalId")
	private String serverExternalId;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverAddress")
	private String serverAddress;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverType")
	private String serverType;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverMetadata")
	private List<ServerMetadata> serverMetadata = new ArrayList<ServerMetadata>();
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverName")
	private String serverName;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverPortNumber")
	private String serverPortNumber;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverId")
	private String serverId;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverDescription")
	private String serverDescription;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serverLocation")
	private String serverLocation;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverExternalId
	 */
	@JsonProperty("serverExternalId")
	public String getServerExternalId() {
		return serverExternalId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverExternalId
	 *            The serverExternalId
	 */
	@JsonProperty("serverExternalId")
	public void setServerExternalId(String serverExternalId) {
		this.serverExternalId = serverExternalId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverAddress
	 */
	@JsonProperty("serverAddress")
	public String getServerAddress() {
		return serverAddress;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverAddress
	 *            The serverAddress
	 */
	@JsonProperty("serverAddress")
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverType
	 */
	@JsonProperty("serverType")
	public String getServerType() {
		return serverType;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverType
	 *            The serverType
	 */
	@JsonProperty("serverType")
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverMetadata
	 */
	@JsonProperty("serverMetadata")
	public List<ServerMetadata> getServerMetadata() {
		return serverMetadata;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverMetadata
	 *            The serverMetadata
	 */
	@JsonProperty("serverMetadata")
	public void setServerMetadata(List<ServerMetadata> serverMetadata) {
		this.serverMetadata = serverMetadata;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverName
	 */
	@JsonProperty("serverName")
	public String getServerName() {
		return serverName;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverName
	 *            The serverName
	 */
	@JsonProperty("serverName")
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverPortNumber
	 */
	@JsonProperty("serverPortNumber")
	public String getServerPortNumber() {
		return serverPortNumber;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverPortNumber
	 *            The serverPortNumber
	 */
	@JsonProperty("serverPortNumber")
	public void setServerPortNumber(String serverPortNumber) {
		this.serverPortNumber = serverPortNumber;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverId
	 */
	@JsonProperty("serverId")
	public String getServerId() {
		return serverId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverId
	 *            The serverId
	 */
	@JsonProperty("serverId")
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverDescription
	 */
	@JsonProperty("serverDescription")
	public String getServerDescription() {
		return serverDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverDescription
	 *            The serverDescription
	 */
	@JsonProperty("serverDescription")
	public void setServerDescription(String serverDescription) {
		this.serverDescription = serverDescription;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serverLocation
	 */
	@JsonProperty("serverLocation")
	public String getServerLocation() {
		return serverLocation;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serverLocation
	 *            The serverLocation
	 */
	@JsonProperty("serverLocation")
	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
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
		return new HashCodeBuilder().append(serverExternalId).append(serverAddress).append(serverType)
				.append(serverMetadata).append(serverName).append(serverPortNumber).append(serverId)
				.append(serverDescription).append(serverLocation).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof Server) == false) {
			isEqual = false;
		} else {
			Server rhs = ((Server) other);
			isEqual = new EqualsBuilder().append(serverExternalId, rhs.serverExternalId)
					.append(serverAddress, rhs.serverAddress).append(serverType, rhs.serverType)
					.append(serverMetadata, rhs.serverMetadata).append(serverName, rhs.serverName)
					.append(serverPortNumber, rhs.serverPortNumber).append(serverId, rhs.serverId)
					.append(serverDescription, rhs.serverDescription).append(serverLocation, rhs.serverLocation)
					.isEquals();
		}
		return isEqual;
	}

}
