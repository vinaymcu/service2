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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.accenture.avs.device.util.DeviceManagerUtil;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * crmAccountId
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "CRMACCOUNTID", length = 50, unique = true, nullable = false)
	private String crmAccountId;

	/** userName */
	@Column(name = "USER_NAME", length = 100, unique = true, nullable = false)
	private String userName;

	/** maxAllowedIptvDevices */
	@Column(name = "MAX_ALLOWED_IPTV_DEVICES", precision = 9, scale = 0)
	private Long maxAllowedIptvDevices;

	/** bandwidthProfile */
	@Column(name = "BANDWIDTH_PROFILE", length = 20)
	private String bandwithProfile;

	/** overrideBandwith */
	@Column(name = "OVERRIDE_BANDWIDTH", precision = 9, scale = 0)
	private Long overrideBandwith;

	/** qoeControlBandwith */
	@Column(name = "QOE_CONTROL_BANDWIDTH", precision = 9, scale = 0)
	private Long qoeControlBandwith;

	/** totalAssignedBandwidth */
	@Column(name = "TOTAL_ASSIGNED_BANDWIDTH", precision = 9, scale = 0)
	private Long totalAssignedBandwidth;

	/** freeBandwidth */
	@Column(name = "FREE_BANDWIDTH", precision = 9, scale = 0)
	private Long freeBandwidth;

	@ElementCollection
	@CollectionTable(name = "USER_LIMITS", joinColumns = { @JoinColumn(name = "CRMACCOUNTID") })
	private List<UserLimits> userLimits;

	/** massCalculation */
	@Column(name = "MASS_CALCULATION_COUNTER", precision = 3, scale = 0)
	private Long massCalculationCounter = 0L;

	/** massIdentifier */
	@Column(name = "MASS_IDENTIFIER")
	private String massIdentifier;

	/** retEnable */
	@Column(name = "RET_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean retEnable;

	/** rccEnable */
	@Column(name = "RCC_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean rccEnable;

	/** networkBufferSize */
	@Column(name = "NETWORK_BUFFER_SIZE")
	private Long networkBufferSize;

	/** natBindingRefreshInterval */
	@Column(name = "NAT_BINDING_REFRESH_INTERVAL")
	private Long natBindingRefreshInterval;

	/** lastUpdatedDatetime */
	@Column(name = "LAST_UPDATED_DATETIME")
	private Long lastUpdatedDatetime;

	/** userLog */
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<UserLog> userLog;
	
	/**
	 * Default constructor
	 */
	public User() {

	}

	/**
	 * @return the crmAccountId
	 */
	public String getCrmAccountId() {
		return crmAccountId;
	}

	/**
	 * @param crmAccountId
	 *            the crmAccountId to set
	 */
	public void setCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		if (!StringUtils.isBlank(userName)) {
			this.userName = userName;
		}
	}

	/**
	 * @return the maxAllowedIptvDevices
	 */
	public Long getMaxAllowedIptvDevices() {
		return maxAllowedIptvDevices;
	}

	/**
	 * @param maxAllowedIptvDevices
	 *            the maxAllowedIptvDevices to set
	 */
	public void setMaxAllowedIptvDevices(Long maxAllowedIptvDevices) {
		if (!DeviceManagerUtil.checkNullObject(maxAllowedIptvDevices)) {
			this.maxAllowedIptvDevices = maxAllowedIptvDevices;
		}
	}

	/**
	 * @return the bandwithProfile
	 */
	public String getBandwithProfile() {
		return bandwithProfile;
	}

	/**
	 * @param bandwithProfile
	 *            the bandwithProfile to set
	 */
	public void setBandwithProfile(String bandwithProfile) {
		if (!StringUtils.isBlank(bandwithProfile)) {
			this.bandwithProfile = bandwithProfile;
		}
	}

	/**
	 * @return the overrideBandwith
	 */
	public Long getOverrideBandwith() {
		return overrideBandwith;
	}

	/**
	 * @param overrideBandwith
	 *            the overrideBandwith to set
	 */
	public void setOverrideBandwith(Long overrideBandwith) {
		if (!DeviceManagerUtil.checkNullObject(overrideBandwith)) {
			this.overrideBandwith = overrideBandwith;
		}
	}

	/**
	 * @return the qoeControlBandwith
	 */
	public Long getQoeControlBandwith() {
		return qoeControlBandwith;
	}

	/**
	 * @param qoeControlBandwith
	 *            the qoeControlBandwith to set
	 */
	public void setQoeControlBandwith(Long qoeControlBandwith) {
		if (!DeviceManagerUtil.checkNullObject(qoeControlBandwith)) {
			this.qoeControlBandwith = qoeControlBandwith;
		}
	}

	/**
	 * @return the freeBandwidth
	 */
	public Long getFreeBandwidth() {
		return freeBandwidth;
	}

	/**
	 * @param freeBandwidth
	 *            the freeBandwidth to set
	 */
	public void setFreeBandwidth(Long freeBandwidth) {
		if (!DeviceManagerUtil.checkNullObject(freeBandwidth)) {
			this.freeBandwidth = freeBandwidth;
		}
	}

	/**
	 * @return the totalAssignedBandwidth
	 */
	public Long getTotalAssignedBandwidth() {
		return totalAssignedBandwidth;
	}

	/**
	 * @param totalAssignedBandwidth
	 *            the totalAssignedBandwidth to set
	 */
	public void setTotalAssignedBandwidth(Long totalAssignedBandwidth) {
		if (!DeviceManagerUtil.checkNullObject(totalAssignedBandwidth)) {
			this.totalAssignedBandwidth = totalAssignedBandwidth;
		}
	}

	/**
	 * @return the userLimits
	 */
	public List<UserLimits> getUserLimits() {
		return userLimits;
	}

	/**
	 * @param userLimits
	 *            the userLimits to set
	 */
	public void setUserLimits(List<UserLimits> userLimits) {
		if (!DeviceManagerUtil.checkNullObject(userLimits)) {
			this.userLimits = userLimits;
		}
	}

	/**
	 * @return the massCalculationCounter
	 */
	public Long getMassCalculationCounter() {
		return massCalculationCounter;
	}

	/**
	 * @param massCalculationCounter
	 *            the massCalculationCounter to set
	 */
	public void setMassCalculationCounter(Long massCalculationCounter) {
		this.massCalculationCounter = massCalculationCounter;
	}

	/**
	 * @return the massIdentifier
	 */
	public String getMassIdentifier() {
		return massIdentifier;
	}

	/**
	 * @param massIdentifier
	 *            the massIdentifier to set
	 */
	public void setMassIdentifier(String massIdentifier) {
		this.massIdentifier = massIdentifier;
	}

	/**
	 * @return the retEnable
	 */
	public Boolean getRetEnable() {
		return retEnable;
	}

	/**
	 * @param retEnable
	 *            the retEnable to set
	 */
	public void setRetEnable(Boolean retEnable) {
		if (!DeviceManagerUtil.checkNullObject(retEnable)) {
		this.retEnable = retEnable;
		}
	}

	/**
	 * @return the rccEnable
	 */
	public Boolean getRccEnable() {
		return rccEnable;
	}

	/**
	 * @param rccEnable
	 *            the rccEnable to set
	 */
	public void setRccEnable(Boolean rccEnable) {
		if (!DeviceManagerUtil.checkNullObject(rccEnable)) {
		this.rccEnable = rccEnable;
		}
	}

	/**
	 * @return the networkBufferSize
	 */
	public Long getNetworkBufferSize() {
		return networkBufferSize;
	}

	/**
	 * @param networkBufferSize
	 *            the networkBufferSize to set
	 */
	public void setNetworkBufferSize(Long networkBufferSize) {
		if (!DeviceManagerUtil.checkNullObject(networkBufferSize)) {
		this.networkBufferSize = networkBufferSize;}
	}

	/**
	 * @return the natBindingRefreshInterval
	 */
	public Long getNatBindingRefreshInterval() {
		return natBindingRefreshInterval;
	}

	/**
	 * @param natBindingRefreshInterval
	 *            the natBindingRefreshInterval to set
	 */
	public void setNatBindingRefreshInterval(Long natBindingRefreshInterval) {
		if (!DeviceManagerUtil.checkNullObject(natBindingRefreshInterval)) {
		this.natBindingRefreshInterval = natBindingRefreshInterval;
		}
	}

	/**
	 * @return the lastUpdatedDatetime
	 */
	public Long getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	/**
	 * @param lastUpdatedDatetime
	 *            the lastUpdatedDatetime to set
	 */
	public void setLastUpdatedDatetime(Long lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}
	
	/**
	 * @return the userLog
	 */
	public List<UserLog> getUserLog() {
		return userLog;
	}

	/**
	 * @param userLog the userLog to set
	 */
	public void setUserLog(List<UserLog> userLog) {
		this.userLog = userLog;
	}

	/**
	 * Hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crmAccountId == null) ? 0 : crmAccountId.hashCode());
		return result;
	}

	/**
	 * Equals
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
			User other = (User) obj;
			if (crmAccountId == null) {
				if (other.crmAccountId != null)
					isEqual = false;
			} else if (!crmAccountId.equals(other.crmAccountId))
				isEqual = false;
		}
		return isEqual;
	}
}