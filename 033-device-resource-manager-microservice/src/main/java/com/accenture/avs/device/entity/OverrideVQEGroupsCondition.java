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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author surendra.kumar
 *
 */
@Entity
@Table(name = "OVERRIDE_VQE_GROUPS_CONDITION")
public class OverrideVQEGroupsCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	
	/** conditionId */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "CONDITIONID", unique = true, nullable = false)
	private Long conditionId;
	
	/** rccEnable */
	@Column(name = "RCC_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean rccEnable;
	
	/** retEnable */
	@Column(name = "RET_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean retEnable;
	
	/** networkBufferSize */
	@Column(name = "NETWORK_BUFFER_SIZE")
	private Long networkBufferSize;
	
	/** qoeControlBandwidth */
	@Column(name = "QOE_CONTROL_BANDWIDTH")
	private Long qoeControlBandwidth;
	
	/** numSubIptvDevices */
	@Column(name = "NUM_SUB_IPTV_DEVICES")
	private Integer numSubIptvDevices;
	
	/** numSubIptvVqeSDStbs */
	@Column(name = "NUM_SUB_IPTV_VQE_SD_STBS")
	private Integer numSubIptvVqeSDStbs;
	
	/** numSubIptvVqeHDStbs */
	@Column(name = "NUM_SUB_IPTV_VQE_HD_STBS")
	private Integer numSubIptvVqeHDStbs;
	
	/** numSubIptvQoeSDStbs */
	@Column(name = "NUM_SUB_IPTV_QOE_SD_STBS")
	private Integer numSubIptvQoeSDStbs;
	
	/** numSubIptvQoeHDStbs */
	@Column(name = "NUM_SUB_IPTV_QOE_HD_STBS")
	private Integer numSubIptvQoeHDStbs;
	
	/** numSubIptvQoeStbs */
	@Column(name = "NUM_SUB_IPTV_QOE_STBS")
	private Integer numSubIptvQoeStbs;
	
	/** macAddress */
	@Column(name = "MAC_ADDRESS")
	private String macAddress;
	
	/** vqeQuality */
	@Column(name = "VQE_QUALITY")
	private String vqeQuality;
	
	/** groupId */
	@Column(name = "GROUPID")
	private Long groupId;

	
	/**
	 * 
	 */
	public OverrideVQEGroupsCondition() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param conditionId
	 * @param rccEnable
	 * @param retEnable
	 * @param networkBufferSize
	 * @param qoeControlBandwidth
	 * @param numSubIptvDevices
	 * @param numSubIptvVqeSDStbs
	 * @param numSubIptvVqeHDStbs
	 * @param numSubIptvQoeSDStbs
	 * @param numSubIptvQoeHDStbs
	 * @param numSubIptvQoeStbs
	 * @param macAddress
	 * @param vqeQuality
	 * @param groupId
	 */
	public OverrideVQEGroupsCondition(Long conditionId, Boolean rccEnable, Boolean retEnable, Long networkBufferSize,
			Long qoeControlBandwidth, Integer numSubIptvDevices, Integer numSubIptvVqeSDStbs,
			Integer numSubIptvVqeHDStbs, Integer numSubIptvQoeSDStbs, Integer numSubIptvQoeHDStbs,
			Integer numSubIptvQoeStbs, String macAddress, String vqeQuality, Long groupId) {
		super();
		this.conditionId = conditionId;
		this.rccEnable = rccEnable;
		this.retEnable = retEnable;
		this.networkBufferSize = networkBufferSize;
		this.qoeControlBandwidth = qoeControlBandwidth;
		this.numSubIptvDevices = numSubIptvDevices;
		this.numSubIptvVqeSDStbs = numSubIptvVqeSDStbs;
		this.numSubIptvVqeHDStbs = numSubIptvVqeHDStbs;
		this.numSubIptvQoeSDStbs = numSubIptvQoeSDStbs;
		this.numSubIptvQoeHDStbs = numSubIptvQoeHDStbs;
		this.numSubIptvQoeStbs = numSubIptvQoeStbs;
		this.macAddress = macAddress;
		this.vqeQuality = vqeQuality;
		this.groupId = groupId;
	}



	/**
	 * @return the conditionId
	 */
	public Long getConditionId() {
		return conditionId;
	}

	/**
	 * @param conditionId the conditionId to set
	 */
	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * @return the rccEnable
	 */
	public Boolean getRccEnable() {
		return rccEnable;
	}

	/**
	 * @param rccEnable the rccEnable to set
	 */
	public void setRccEnable(Boolean rccEnable) {
		this.rccEnable = rccEnable;
	}

	/**
	 * @return the retEnable
	 */
	public Boolean getRetEnable() {
		return retEnable;
	}

	/**
	 * @param retEnable the retEnable to set
	 */
	public void setRetEnable(Boolean retEnable) {
		this.retEnable = retEnable;
	}

	/**
	 * @return the networkBufferSize
	 */
	public Long getNetworkBufferSize() {
		return networkBufferSize;
	}

	/**
	 * @param networkBufferSize the networkBufferSize to set
	 */
	public void setNetworkBufferSize(Long networkBufferSize) {
		this.networkBufferSize = networkBufferSize;
	}

	/**
	 * @return the qoeControlBandwidth
	 */
	public Long getQoeControlBandwidth() {
		return qoeControlBandwidth;
	}

	/**
	 * @param qoeControlBandwidth the qoeControlBandwidth to set
	 */
	public void setQoeControlBandwidth(Long qoeControlBandwidth) {
		this.qoeControlBandwidth = qoeControlBandwidth;
	}

	/**
	 * @return the numSubIptvDevices
	 */
	public Integer getNumSubIptvDevices() {
		return numSubIptvDevices;
	}

	/**
	 * @param numSubIptvDevices the numSubIptvDevices to set
	 */
	public void setNumSubIptvDevices(Integer numSubIptvDevices) {
		this.numSubIptvDevices = numSubIptvDevices;
	}

	/**
	 * @return the numSubIptvVqeSDStbs
	 */
	public Integer getNumSubIptvVqeSDStbs() {
		return numSubIptvVqeSDStbs;
	}

	/**
	 * @param numSubIptvVqeSDStbs the numSubIptvVqeSDStbs to set
	 */
	public void setNumSubIptvVqeSDStbs(Integer numSubIptvVqeSDStbs) {
		this.numSubIptvVqeSDStbs = numSubIptvVqeSDStbs;
	}

	/**
	 * @return the numSubIptvVqeHDStbs
	 */
	public Integer getNumSubIptvVqeHDStbs() {
		return numSubIptvVqeHDStbs;
	}

	/**
	 * @param numSubIptvVqeHDStbs the numSubIptvVqeHDStbs to set
	 */
	public void setNumSubIptvVqeHDStbs(Integer numSubIptvVqeHDStbs) {
		this.numSubIptvVqeHDStbs = numSubIptvVqeHDStbs;
	}

	/**
	 * @return the numSubIptvQoeSDStbs
	 */
	public Integer getNumSubIptvQoeSDStbs() {
		return numSubIptvQoeSDStbs;
	}

	/**
	 * @param numSubIptvQoeSDStbs the numSubIptvQoeSDStbs to set
	 */
	public void setNumSubIptvQoeSDStbs(Integer numSubIptvQoeSDStbs) {
		this.numSubIptvQoeSDStbs = numSubIptvQoeSDStbs;
	}

	/**
	 * @return the numSubIptvQoeHDStbs
	 */
	public Integer getNumSubIptvQoeHDStbs() {
		return numSubIptvQoeHDStbs;
	}

	/**
	 * @param numSubIptvQoeHDStbs the numSubIptvQoeHDStbs to set
	 */
	public void setNumSubIptvQoeHDStbs(Integer numSubIptvQoeHDStbs) {
		this.numSubIptvQoeHDStbs = numSubIptvQoeHDStbs;
	}

	/**
	 * @return the numSubIptvQoeStbs
	 */
	public Integer getNumSubIptvQoeStbs() {
		return numSubIptvQoeStbs;
	}

	/**
	 * @param numSubIptvQoeStbs the numSubIptvQoeStbs to set
	 */
	public void setNumSubIptvQoeStbs(Integer numSubIptvQoeStbs) {
		this.numSubIptvQoeStbs = numSubIptvQoeStbs;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @param macAddress the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * @return the vqeQuality
	 */
	public String getVqeQuality() {
		return vqeQuality;
	}

	/**
	 * @param vqeQuality the vqeQuality to set
	 */
	public void setVqeQuality(String vqeQuality) {
		this.vqeQuality = vqeQuality;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	

	/**
	 * Override hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (conditionId != null ? conditionId.hashCode() : 0);
		return hash;
	}

	/**
	 * Override equals()
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true;
		if (obj == null) {
			isEqual = false;
		} else if (!(obj instanceof OverrideVQEGroupsCondition)) {
			isEqual = false;
		} else {
			final OverrideVQEGroupsCondition other = (OverrideVQEGroupsCondition) obj;
			if (conditionId == null) {
				if (other.conditionId != null) {
					isEqual = false;
				}
			} else if (!conditionId.equals(other.conditionId)) {
				isEqual = false;
			}
		}
		return isEqual;
	}
}
