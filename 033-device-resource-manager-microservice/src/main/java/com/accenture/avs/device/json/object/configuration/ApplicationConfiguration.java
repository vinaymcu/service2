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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "creationTime",
    "configuration",
    "author",
    "id",
    "version",
    "status",
    "propertyName",
    "platform",
    "retailerId",
    "deviceType"
})
public class ApplicationConfiguration {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creationTime")
    private Long creationTime;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("configuration")
    private Object configuration;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("author")
    private String author;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private String id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("version")
    private String version;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("propertyName")
    private String propertyName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("platform")
    private String platform;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("retailerId")
    private Object retailerId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceType")
    private String deviceType;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The creationTime
     */
    @JsonProperty("creationTime")
    public Long getCreationTime() {
        return creationTime;
    }

    /**
     * 
     * (Required)
     * 
     * @param creationTime
     *     The creationTime
     */
    @JsonProperty("creationTime")
    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * 
     * 
     * 
     * (Required)
     * 
     * @return
     *     The configuration
     */
    @JsonProperty("configuration")
    public Object getConfiguration() {
        return configuration;
    }

    /**
     * 
     * (Required)
     * 
     * @param configuration
     *     The configuration
     */
    @JsonProperty("configuration")
    public void setConfiguration(Object configuration) {
        this.configuration = configuration;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * (Required)
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * (Required)
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The propertyName
     */
    @JsonProperty("propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 
     * (Required)
     * 
     * @param propertyName
     *     The propertyName
     */
    @JsonProperty("propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The platform
     */
    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    /**
     * 
     * (Required)
     * 
     * @param platform
     *     The platform
     */
    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The retailerId
     */
    @JsonProperty("retailerId")
    public Object getRetailerId() {
        return retailerId;
    }

    /**
     * 
     * (Required)
     * 
     * @param retailerId
     *     The retailerId
     */
    @JsonProperty("retailerId")
    public void setRetailerId(Object retailerId) {
        this.retailerId = retailerId;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The deviceType
     */
    @JsonProperty("deviceType")
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 
     * (Required)
     * 
     * @param deviceType
     *     The deviceType
     */
    @JsonProperty("deviceType")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
		return new HashCodeBuilder().append(creationTime).append(configuration).append(author).append(id)
				.append(version).append(status).append(propertyName).append(platform).append(retailerId)
				.append(deviceType).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof ApplicationConfiguration) == false) {
			isEqual = false;
		} else {
			ApplicationConfiguration rhs = ((ApplicationConfiguration) other);
			isEqual = new EqualsBuilder().append(creationTime, rhs.creationTime)
					.append(configuration, rhs.configuration).append(author, rhs.author).append(id, rhs.id)
					.append(version, rhs.version).append(status, rhs.status).append(propertyName, rhs.propertyName)
					.append(platform, rhs.platform).append(retailerId, rhs.retailerId)
					.append(deviceType, rhs.deviceType).isEquals();
		}
		return isEqual;
	}

}
