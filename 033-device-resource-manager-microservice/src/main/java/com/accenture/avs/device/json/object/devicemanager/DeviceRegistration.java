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
@JsonPropertyOrder({ "userName", "serialNumber", "deviceId", "ipAddress", "extIpAddress", "softwareVersion", "osName",
		"osVersion", "model", "vendor", "uiVersion", "deviceName", "drmId", "tvQualityInterest", "deviceType",
		"platform" })
public class DeviceRegistration {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("userName")
	private String userName;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("serialNumber")
	private String serialNumber;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("ipAddress")
	private String ipAddress;
	@JsonProperty("extIpAddress")
	private String extIpAddress;
	@JsonProperty("softwareVersion")
	private String softwareVersion;
	@JsonProperty("osName")
	private String osName;
	@JsonProperty("osVersion")
	private String osVersion;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("model")
	private String model;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("vendor")
	private String vendor;
	@JsonProperty("uiVersion")
	private String uiVersion;
	@JsonProperty("deviceName")
	private String deviceName;
	@JsonProperty("drmId")
	private String drmId;
	@JsonProperty("tvQualityInterest")
	private String tvQualityInterest;
	@JsonProperty("deviceType")
	private String deviceType;
	@JsonProperty("platform")
	private String platform;

	/**
	 * 
	 * (Required)
	 * 
	 * @return The userName
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
	 *            The userName
	 */
	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public DeviceRegistration withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The serialNumber
	 */
	@JsonProperty("serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param serialNumber
	 *            The serialNumber
	 */
	@JsonProperty("serialNumber")
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public DeviceRegistration withSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public DeviceRegistration withDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}

	/**
	 * 
	 * @return The ipAddress
	 */
	@JsonProperty("ipAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 
	 * @param ipAddress
	 *            The ipAddress
	 */
	@JsonProperty("ipAddress")
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public DeviceRegistration withIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}

	/**
	 * 
	 * @return The extIpAddress
	 */
	@JsonProperty("extIpAddress")
	public String getExtIpAddress() {
		return extIpAddress;
	}

	/**
	 * 
	 * @param extIpAddress
	 *            The extIpAddress
	 */
	@JsonProperty("extIpAddress")
	public void setExtIpAddress(String extIpAddress) {
		this.extIpAddress = extIpAddress;
	}

	public DeviceRegistration withExtIpAddress(String extIpAddress) {
		this.extIpAddress = extIpAddress;
		return this;
	}

	/**
	 * 
	 * @return The softwareVersion
	 */
	@JsonProperty("softwareVersion")
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * 
	 * @param softwareVersion
	 *            The softwareVersion
	 */
	@JsonProperty("softwareVersion")
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public DeviceRegistration withSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
		return this;
	}

	/**
	 * 
	 * @return The osName
	 */
	@JsonProperty("osName")
	public String getOsName() {
		return osName;
	}

	/**
	 * 
	 * @param osName
	 *            The osName
	 */
	@JsonProperty("osName")
	public void setOsName(String osName) {
		this.osName = osName;
	}

	public DeviceRegistration withOsName(String osName) {
		this.osName = osName;
		return this;
	}

	/**
	 * 
	 * @return The osVersion
	 */
	@JsonProperty("osVersion")
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * 
	 * @param osVersion
	 *            The osVersion
	 */
	@JsonProperty("osVersion")
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public DeviceRegistration withOsVersion(String osVersion) {
		this.osVersion = osVersion;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The model
	 */
	@JsonProperty("model")
	public String getModel() {
		return model;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param model
	 *            The model
	 */
	@JsonProperty("model")
	public void setModel(String model) {
		this.model = model;
	}

	public DeviceRegistration withModel(String model) {
		this.model = model;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The vendor
	 */
	@JsonProperty("vendor")
	public String getVendor() {
		return vendor;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param vendor
	 *            The vendor
	 */
	@JsonProperty("vendor")
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public DeviceRegistration withVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}

	/**
	 * 
	 * @return The uiVersion
	 */
	@JsonProperty("uiVersion")
	public String getUiVersion() {
		return uiVersion;
	}

	/**
	 * 
	 * @param uiVersion
	 *            The uiVersion
	 */
	@JsonProperty("uiVersion")
	public void setUiVersion(String uiVersion) {
		this.uiVersion = uiVersion;
	}

	public DeviceRegistration withUiVersion(String uiVersion) {
		this.uiVersion = uiVersion;
		return this;
	}

	/**
	 * 
	 * @return The deviceName
	 */
	@JsonProperty("deviceName")
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * 
	 * @param deviceName
	 *            The deviceName
	 */
	@JsonProperty("deviceName")
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceRegistration withDeviceName(String deviceName) {
		this.deviceName = deviceName;
		return this;
	}

	/**
	 * 
	 * @return The drmId
	 */
	@JsonProperty("drmId")
	public String getDrmId() {
		return drmId;
	}

	/**
	 * 
	 * @param drmId
	 *            The drmId
	 */
	@JsonProperty("drmId")
	public void setDrmId(String drmId) {
		this.drmId = drmId;
	}

	public DeviceRegistration withDrmId(String drmId) {
		this.drmId = drmId;
		return this;
	}

	/**
	 * 
	 * @return The tvQualityInterest
	 */
	@JsonProperty("tvQualityInterest")
	public String getTvQualityInterest() {
		return tvQualityInterest;
	}

	/**
	 * 
	 * @param tvQualityInterest
	 *            The tvQualityInterest
	 */
	@JsonProperty("tvQualityInterest")
	public void setTvQualityInterest(String tvQualityInterest) {
		this.tvQualityInterest = tvQualityInterest;
	}

	public DeviceRegistration withTvQualityInterest(String tvQualityInterest) {
		this.tvQualityInterest = tvQualityInterest;
		return this;
	}

	/**
	 * 
	 * @return The deviceType
	 */
	@JsonProperty("deviceType")
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 
	 * @param deviceType
	 *            The deviceType
	 */
	@JsonProperty("deviceType")
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceRegistration withDeviceType(String deviceType) {
		this.deviceType = deviceType;
		return this;
	}

	/**
	 * 
	 * @return The platform
	 */
	@JsonProperty("platform")
	public String getPlatform() {
		return platform;
	}

	/**
	 * 
	 * @param platform
	 *            The platform
	 */
	@JsonProperty("platform")
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * 
	 * @param platform
	 * @return
	 */
	public DeviceRegistration withPlatform(String platform) {
		this.platform = platform;
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
		return new HashCodeBuilder().append(userName).append(serialNumber).append(deviceId).append(ipAddress)
				.append(extIpAddress).append(softwareVersion).append(osName).append(osVersion).append(model)
				.append(vendor).append(uiVersion).append(deviceName).append(drmId).append(tvQualityInterest)
				.append(deviceType).append(platform).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof DeviceRegistration) == false) {
			isEqual = false;
		} else {
			DeviceRegistration rhs = ((DeviceRegistration) other);
			isEqual = new EqualsBuilder().append(userName, rhs.userName).append(serialNumber, rhs.serialNumber)
					.append(deviceId, rhs.deviceId).append(ipAddress, rhs.ipAddress)
					.append(extIpAddress, rhs.extIpAddress).append(softwareVersion, rhs.softwareVersion)
					.append(osName, rhs.osName).append(osVersion, rhs.osVersion).append(model, rhs.model)
					.append(vendor, rhs.vendor).append(uiVersion, rhs.uiVersion).append(deviceName, rhs.deviceName)
					.append(drmId, rhs.drmId).append(tvQualityInterest, rhs.tvQualityInterest)
					.append(deviceType, rhs.deviceType).append(platform, rhs.platform).isEquals();
		}
		return isEqual;
	}

}
