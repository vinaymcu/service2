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
import java.util.Objects;

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
@Table(name = "VQE_GROUP")
public class VQEGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** groupId */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "GROUPID", unique = true, nullable = false)
	private Long groupId;
	
	/** rccEnable */
	@Column(name = "RCC_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean rccEnable;
	
	/** retEnable */
	@Column(name = "RET_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean retEnable;
	
	/** networkBufferSize */
	@Column(name = "NETWORK_BUFFER_SIZE")
	private Long networkBufferSize;
	
	/** maxRecvBwSD */
	@Column(name = "MAX_RECV_BW_SD")
	private Long maxRecvBwSD;
	
	/** maxRecvBwHD */
	@Column(name = "MAX_RECV_BW_HD")
	private Long maxRecvBwHD;
	
	/** maxRecvRccBwSD */
	@Column(name = "MAX_RECV_RCC_BW_SD")
	private Long maxRecvRccBwSD;
	
	/** maxRecvRccBwHD */
	@Column(name = "MAX_RECV_RCC_BW_HD")
	private Long maxRecvRccBwHD;
	
	/**
	 * 
	 */
	public VQEGroup() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param groupId
	 * @param rccEnable
	 * @param retEnable
	 * @param networkBufferSize
	 * @param maxRecvBwSD
	 * @param maxRecvBwHD
	 * @param maxRecvRccBwSD
	 * @param maxRecvRccBwHD
	 */
	public VQEGroup(Long groupId, Boolean rccEnable, Boolean retEnable, Long networkBufferSize, Long maxRecvBwSD,
			Long maxRecvBwHD, Long maxRecvRccBwSD, Long maxRecvRccBwHD) {
		this.groupId = groupId;
		this.rccEnable = rccEnable;
		this.retEnable = retEnable;
		this.networkBufferSize = networkBufferSize;
		this.maxRecvBwSD = maxRecvBwSD;
		this.maxRecvBwHD = maxRecvBwHD;
		this.maxRecvRccBwSD = maxRecvRccBwSD;
		this.maxRecvRccBwHD = maxRecvRccBwHD;
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
		if (Objects.isNull(networkBufferSize)) {
			this.networkBufferSize = 0L;
		} else {
			this.networkBufferSize = networkBufferSize;
		}
	}

	/**
	 * @return the maxRecvBwSD
	 */
	public Long getMaxRecvBwSD() {
		return maxRecvBwSD;
	}

	/**
	 * @param maxRecvBwSD the maxRecvBwSD to set
	 */
	public void setMaxRecvBwSD(Long maxRecvBwSD) {
		if (Objects.isNull(maxRecvBwSD)) {
			this.maxRecvBwSD = 0L;
		} else {
			this.maxRecvBwSD = maxRecvBwSD;
		}
	}

	/**
	 * @return the maxRecvBwHD
	 */
	public Long getMaxRecvBwHD() {
		return maxRecvBwHD;
	}

	/**
	 * @param maxRecvBwHD the maxRecvBwHD to set
	 */
	public void setMaxRecvBwHD(Long maxRecvBwHD) {
		if (Objects.isNull(maxRecvBwHD)) {
			this.maxRecvBwHD = 0L;
		} else {
			this.maxRecvBwHD = maxRecvBwHD;
		}
	}

	/**
	 * @return the maxRecvRccBwSD
	 */
	public Long getMaxRecvRccBwSD() {
		return maxRecvRccBwSD;
	}

	/**
	 * @param maxRecvRccBwSD the maxRecvRccBwSD to set
	 */
	public void setMaxRecvRccBwSD(Long maxRecvRccBwSD) {
		if(Objects.isNull(maxRecvRccBwSD)) {
			this.maxRecvRccBwSD = 0L;
		}else{
		this.maxRecvRccBwSD = maxRecvRccBwSD;
		}
	}

	/**
	 * @return the maxRecvRccBwHD
	 */
	public Long getMaxRecvRccBwHD() {
		return maxRecvRccBwHD;
	}

	/**
	 * @param maxRecvRccBwHD the maxRecvRccBwHD to set
	 */
	public void setMaxRecvRccBwHD(Long maxRecvRccBwHD) {
		if (Objects.isNull(maxRecvRccBwHD)) {
			this.maxRecvRccBwHD = 0L;
		} else {
			this.maxRecvRccBwHD = maxRecvRccBwHD;
		}
	}

	/**
	 * Override hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (groupId != null ? groupId.hashCode() : 0);
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
		} else if (!(obj instanceof VQEGroup)) {
			isEqual = false;
		} else {
			final VQEGroup other = (VQEGroup) obj;
			if (groupId == null) {
				if (other.groupId != null) {
					isEqual = false;
				}
			} else if (!groupId.equals(other.groupId)) {
				isEqual = false;
			}
		}
		return isEqual;
	}

}
