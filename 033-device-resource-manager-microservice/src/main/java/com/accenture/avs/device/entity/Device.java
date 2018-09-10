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
package com.accenture.avs.device.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.accenture.avs.device.dto.DeviceProfileDto;
import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * Entity class for DEVICES table
 * 
 * @author Singh.Saurabh
 *
 */
@Entity
@Table(name = "DEVICES")
public class Device implements Serializable {

	private static final long serialVersionUID = 6864957231298093769L;

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", unique = true, nullable = false, precision = 9, scale = 0)
	private Long id;

	/** deviceType */
	@Column(name = "DEVICE_TYPE", length = 20)
	private String deviceType;

	/** platform */
	@Column(name = "PLATFORM", length = 20)
	private String platform;

	/** serialNumber */
	@Column(name = "SERIAL_NUMBER", length = 20)
	private String serialNumber;

	/** internalIpAddress */
	@Column(name = "INTERNAL_IP_ADDRESS", length = 20)
	private String internalIpAddress;

	/** externalIpAddress */
	@Column(name = "EXTERNAL_IP_ADDRESS", length = 20)
	private String externalIpAddress;

	/** deviceId */
	@Column(name = "DEVICEID", unique = true, length = 20)
	private String deviceId;

	/** model */
	@JoinColumn(name = "MODELID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Model model;

	/** uiVersion */
	@Column(name = "UI_VERSION", length = 50)
	private String uiVersion;
	
	/** softwareVersion */
	@Column(name = "SOFTWARE_VERSION", length = 50)
	private String softwareVersion;
	
	/** assignedToUserName */
	@Column(name = "ASSIGNED_TO_USERNAME", length = 100)
	private String assignedToUserName;

	/** assignmentStatus */
	@Column(name = "ASSIGNMENT_STATUS", nullable = false, length = 10)
	private String assignmentStatus;

	/** maxBandwidthUpdate */
	@Column(name = "MAX_BANDWIDTH_UPDATE", precision = 3, scale = 0)
	private Long maxBandwidthUpdate;

	/** tvQualityInterest */
	@Column(name = "TV_QUALITY_INTEREST", length = 20)
	private String tvQualityInterest;

	/** datetimeOfAssignment */
	@Column(name = "DATETIME_OF_ASSIGNMENT", precision = 20, scale = 0)
	private Long datetimeOfAssignment;

	/** deviceName */
	@Column(name = "DEVICE_NAME", length = 30)
	private String deviceName;

	/** drmId */
	@Column(name = "DRMID", nullable = false, length = 30)
	private String drmId;

	/** supportedMode */
	@Column(name = "SUPPORTED_MODES", length = 40)
	private String supportedMode;

	/** connectionMode */
	@Column(name = "CONNECTION_MODE", length = 20)
	private String connectionMode;

	/** deviceProfile */
	@Column(name = "DEVICE_PROFILE", length = 20)
	private String deviceProfile;

	/** deviceProfileBandwidth */
	@Column(name = "DEVICE_PROFILE_BANDWIDTH", precision = 8, scale = 0)
	private Long deviceProfileBandwidth;

	/** qoeBandwidth */
	@Column(name = "QOE_BANDWIDTH", precision = 8, scale = 0)
	private Long qoeBandwidth;

	/** lastUpdatedDatetime */
	@Column(name = "LAST_UPDATED_DATETIME", nullable = false, precision = 20, scale = 0)
	private Long lastUpdatedOn;

	/** last_update_username */
	@Column(name = "LAST_UPDATE_USERNAME", nullable = false, length = 30)
	private String lastUpdatedBy;
	
	/** deviceMaxBWAllowedPerQuality */
	@CollectionTable(name = "DEVICE_MAX_BANDWIDTH_ALLOWED_PER_QUALITY", joinColumns = @JoinColumn(name = "ID"))
	@ElementCollection(targetClass = DeviceMaxBWAllowedPerQuality.class)
	private List<DeviceMaxBWAllowedPerQuality> deviceMaxBWAllowedPerQuality;

	/** deviceProfileDto */
	@Transient
	private DeviceProfileDto deviceProfileDto;
	
	/**
	 * Default Constructor
	 * 
	 */
	public Device() {
	}

	/**
	 * Constructor
	 * 
	 */
	public Device(Long equipmentId) {
		this.id = equipmentId;
	}

	/**
	 * Constructor
	 * 
	 */
	public Device(Long equipmentId, String serialNumber, String assignmentStatus, String deviceName, long lastUpdatedOn,
			String lastUpdatedBy) {
		this.id = equipmentId;
		this.serialNumber = serialNumber;
		this.assignmentStatus = assignmentStatus;
		this.deviceName = deviceName;
		this.lastUpdatedOn = lastUpdatedOn;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Gets Id
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id
	 * 
	 * @param equipmentId
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets serialNumber
	 * 
	 * @return String
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets serialNumber
	 * 
	 * @param serialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		if (!DeviceManagerUtil.checkNullObject(serialNumber)) {
			this.serialNumber = serialNumber;
		}
	}

	/**
	 * Gets internalIpAddress
	 * 
	 * @return String
	 */
	public String getInternalIpAddress() {

		return internalIpAddress;
	}

	/**
	 * Sets internalIpAddress
	 * 
	 * @param internalIpAddress
	 */
	public void setInternalIpAddress(String internalIpAddress) {
		if (!DeviceManagerUtil.checkNullObject(internalIpAddress)) {
			this.internalIpAddress = internalIpAddress;
		}
	}

	/**
	 * Gets externalIpAddress
	 * 
	 * @return String
	 */
	public String getExternalIpAddress() {
		return externalIpAddress;
	}

	/**
	 * Sets externalIpAddress
	 * 
	 * @param externalIpAddress
	 */
	public void setExternalIpAddress(String externalIpAddress) {
		if (!DeviceManagerUtil.checkNullObject(externalIpAddress)) {
			this.externalIpAddress = externalIpAddress;
		}
	}

	/**
	 * Gets deviceId
	 * 
	 * @return String
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Sets deviceId
	 * 
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Gets DrmId
	 * 
	 * @return String
	 */
	public String getDrmId() {
		return drmId;
	}

	/**
	 * Sets DrmId
	 * 
	 * @param drmId
	 */
	public void setDrmId(String drmId) {
		if (!DeviceManagerUtil.checkNullObject(drmId)) {
			this.drmId = drmId;
		}
	}

	/**
	 * Gets model
	 * 
	 * @return model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Sets model
	 * 
	 * @param model
	 */
	public void setModel(Model model) {
		if (!DeviceManagerUtil.checkNullObject(model)) {
			this.model = model;
		}
	}

	/**
	 * Gets softwareVersion
	 * 
	 * @return String
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * Sets softwareVersion
	 * 
	 * @param softwareVersion
	 */
	public void setSoftwareVersion(String softwareVersion) {
		if (!DeviceManagerUtil.checkNullObject(softwareVersion)) {
			this.softwareVersion = softwareVersion;
		}
	}

	/**
	 * Gets uiVersion
	 * 
	 * @return String
	 */
	public String getUiVersion() {
		return uiVersion;
	}

	/**
	 * Sets uiVersion
	 * 
	 * @param uiVersion
	 */
	public void setUiVersion(String uiVersion) {
		if (!DeviceManagerUtil.checkNullObject(uiVersion)) {
			this.uiVersion = uiVersion;
		}
	}

	/**
	 * Gets assignmentStatus
	 * 
	 * @return String
	 */
	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	/**
	 * Sets assignmentStatus
	 * 
	 * @param assignmentStatus
	 */
	public void setAssignmentStatus(String assignmentStatus) {
		if (!DeviceManagerUtil.checkNullObject(assignmentStatus)) {
			this.assignmentStatus = assignmentStatus;
		}
	}

	/**
	 * Gets maxBandwidthUpdate
	 * 
	 * @return Long
	 */
	public Long getMaxBandwidthUpdate() {
		return maxBandwidthUpdate;
	}

	/**
	 * Sets maxBandwidthUpdate
	 * 
	 * @param maxBandwidthUpdate
	 */
	public void setMaxBandwidthUpdate(Long maxBandwidthUpdate) {
		if (!DeviceManagerUtil.checkNullObject(maxBandwidthUpdate)) {
			this.maxBandwidthUpdate = maxBandwidthUpdate;
		}
	}

	/**
	 * Gets tvQualityInterest
	 * 
	 * @return String
	 */
	public String getTvQualityInterest() {
		return tvQualityInterest;
	}

	/**
	 * Sets tvQualityInterest
	 * 
	 * @param tvQualityInterest
	 */
	public void setTvQualityInterest(String tvQualityInterest) {
		this.tvQualityInterest = tvQualityInterest;
	}

	/**
	 * Gets datetimeOfAssignment
	 * 
	 * @return Long
	 */
	public Long getDatetimeOfAssignment() {
		return datetimeOfAssignment;
	}

	/**
	 * Sets datetimeOfAssignment
	 * 
	 * @param datetimeOfAssignment
	 */
	public void setDatetimeOfAssignment(Long datetimeOfAssignment) {
			this.datetimeOfAssignment = datetimeOfAssignment;
	}

	/**
	 * Gets deviceName
	 * 
	 * @return String
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * Sets deviceName
	 * 
	 * @param deviceName
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Gets lastUpdatedOn
	 * 
	 * @return String
	 */
	public Long getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	/**
	 * Sets lastUpdatedOn
	 * 
	 * @param lastUpdatedOn
	 */
	public void setLastUpdatedOn(Long lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	/**
	 * Gets lastUpdatedBy
	 * 
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * Sets lastUpdatedBy
	 * 
	 * @param lastUpdatedBy
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * @return
	 */
	public String getAssignedToUserName() {
		return assignedToUserName;
	}

	/**
	 * @param assignedToUserName
	 */
	public void setAssignedToUserName(String assignedToUserName) {
		this.assignedToUserName = assignedToUserName;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		if (!DeviceManagerUtil.checkNullObject(deviceType)) {
			this.deviceType = deviceType;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * 
	 * @param platform
	 */
	public void setPlatform(String platform) {
		if (!DeviceManagerUtil.checkNullObject(platform)) {
			this.platform = platform;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getDeviceProfile() {
		return deviceProfile;
	}

	/**
	 * 
	 * @param deviceProfile
	 */
	public void setDeviceProfile(String deviceProfile) {
		this.deviceProfile = deviceProfile;
	}

	/**
	 * 
	 * @return
	 */
	public Long getDeviceProfileBandwidth() {
		return deviceProfileBandwidth;
	}

	/**
	 * 
	 * @param deviceProfileBandwidth
	 */
	public void setDeviceProfileBandwidth(Long deviceProfileBandwidth) {
		this.deviceProfileBandwidth = deviceProfileBandwidth;
	}

	/**
	 * 
	 * @return
	 */
	public Long getQoeBandwidth() {
		return qoeBandwidth;
	}

	/**
	 * 
	 * @param qoeBandwidth
	 */
	public void setQoeBandwidth(Long qoeBandwidth) {
		this.qoeBandwidth = qoeBandwidth;
	}
	
	/**
	 * Sets supportedMode
	 * 
	 * @return
	 */
	public String getSupportedMode() {
		return supportedMode;
	}

	/**
	 * Sets supportedMode
	 * 
	 * @param supportedMode
	 */
	public void setSupportedMode(String supportedMode) {
		this.supportedMode = supportedMode;
	}

	/**
	 * Gets connectionMode
	 * 
	 * @return
	 */
	public String getConnectionMode() {
		return connectionMode;
	}

	/**
	 * Sets connectionMode
	 * 
	 * @param connectionMode
	 */
	public void setConnectionMode(String connectionMode) {
		this.connectionMode = connectionMode;
	}
	
	/**
	 * @return the deviceProfileDto
	 */
	public DeviceProfileDto getDeviceProfileDto() {
		return deviceProfileDto;
	}

	/**
	 * @param deviceProfileDto the deviceProfileDto to set
	 */
	public void setDeviceProfileDto(DeviceProfileDto deviceProfileDto) {
		this.deviceProfileDto = deviceProfileDto;
	}
	
	/**
	 * @return the deviceMaxBWAllowedPerQuality
	 */
	public List<DeviceMaxBWAllowedPerQuality> getDeviceMaxBWAllowedPerQuality() {
		return deviceMaxBWAllowedPerQuality;
	}

	/**
	 * @param deviceMaxBWAllowedPerQuality the deviceMaxBWAllowedPerQuality to set
	 */
	public void setDeviceMaxBWAllowedPerQuality(List<DeviceMaxBWAllowedPerQuality> deviceMaxBWAllowedPerQuality) {
		this.deviceMaxBWAllowedPerQuality = deviceMaxBWAllowedPerQuality;
	}

	/**
	 * Override hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * Override equals()
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true;
		if (this == obj) {
			isEqual = true;
		} else if (obj == null) {
			isEqual = false;
		} else if (getClass() != obj.getClass()) {
			isEqual = false;
		} else {
			final Device other = (Device) obj;
			if (id == null) {
				if (other.id != null) {
					isEqual = false;
				}
			} else if (!id.equals(other.id)) {
				isEqual = false;
			} else if (deviceId == null) {
				if (other.deviceId != null) {
					isEqual = false;
				}
			} else if (!deviceId.equals(other.deviceId)) {
				isEqual = false;
			}
		}
		return isEqual;
	}

}
