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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for USER_LOG table.
 * 
 * @author singh.saurabh
 *
 */
@Entity
@Table(name = "USER_LOG")
public class UserLog implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3440129334257753269L;

	/** ID is PK */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;

	/** crmAccountId */
	@Column(name = "CRMACCOUNTID", length = 50, unique = true, nullable = false)
	private String crmAccountId;

	/** qoeControlBandwith */
	@Column(name = "QOE_CONTROL_BANDWIDTH", precision = 9, scale = 0)
	private Long qoeControlBandwith;

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

	/** failedReason */
	@Column(name = "FAILED_REASON")
	private String failedReason;

	/** insertDateTime */
	@Column(name = "INSERT_DATETIME")
	private Long insertDateTime;
	
	@JoinColumn(name = "CRMACCOUNTID", referencedColumnName = "CRMACCOUNTID", insertable=false, updatable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * Default constructor
	 */
	public UserLog() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
		this.qoeControlBandwith = qoeControlBandwith;
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
		this.retEnable = retEnable;
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
		this.rccEnable = rccEnable;
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
		this.networkBufferSize = networkBufferSize;
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
		this.natBindingRefreshInterval = natBindingRefreshInterval;
	}

	/**
	 * @return the failedReason
	 */
	public String getFailedReason() {
		return failedReason;
	}

	/**
	 * @param failedReason
	 *            the failedReason to set
	 */
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	/**
	 * @return the insertDateTime
	 */
	public Long getInsertDateTime() {
		return insertDateTime;
	}

	/**
	 * @param insertDateTime
	 *            the insertDateTime to set
	 */
	public void setInsertDateTime(Long insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
			UserLog other = (UserLog) obj;
			if (id == null) {
				if (other.id != null)
					isEqual = false;
			} else if (!id.equals(other.id))
				isEqual = false;
		}
		return isEqual;
	}
}