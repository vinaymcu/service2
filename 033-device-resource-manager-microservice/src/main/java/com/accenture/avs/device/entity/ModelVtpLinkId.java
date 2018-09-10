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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author surendra.kumar
 *
 */
@Embeddable
public class ModelVtpLinkId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** id */
	@Column(name = "MODEL_ID", unique = true, nullable = false, precision = 9, scale = 0)
	private Long modelId;

	/** tvQuality */
	@Column(name = "VTP_NAME", length = 20)
	private String vtpName;

	/**
	 * @return the modelId
	 */
	public Long getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the vtpName
	 */
	public String getVtpName() {
		return vtpName;
	}

	/**
	 * @param vtpName the vtpName to set
	 */
	public void setVtpName(String vtpName) {
		this.vtpName = vtpName;
	}
	
	/**
	 * Override hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (long) modelId;
		hash += (vtpName != null ? vtpName.hashCode() : 0);
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
			final ModelVtpLinkId other = (ModelVtpLinkId) obj;
			if (modelId == null) {
				if (other.modelId != null) {
					isEqual = false;
				}
			} else if (!modelId.equals(other.modelId)) {
				isEqual = false;
			} else if (vtpName == null) {
				if (other.vtpName != null) {
					isEqual = false;
				}
			} else if (!vtpName.equals(other.vtpName)) {
				isEqual = false;
			}
		}
		return isEqual;
	}

}
