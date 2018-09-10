/**
 * 
 */
package com.accenture.avs.device.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author surendra.kumar
 *
 */
@Embeddable
public class ModelAssignedResourcesId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** modelId */
	@Column(name = "MODEL_ID")
	private Long modelId;
	
	/** resourceName */
	@Column(name = "RESOURCE_NAME")
	private String resourceName;

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
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	/**
	 * Override hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash +=  modelId.hashCode();
		hash += (resourceName != null ? resourceName.hashCode() : 0);
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
			final ModelAssignedResourcesId other = (ModelAssignedResourcesId) obj;
			if (modelId == null) {
				if (other.modelId != null) {
					isEqual = false;
				}
			} else if (!modelId.equals(other.modelId)) {
				isEqual = false;
			} else if (resourceName == null) {
				if (other.resourceName != null) {
					isEqual = false;
				}
			} else if (!resourceName.equals(other.resourceName)) {
				isEqual = false;
			}
		}
		return isEqual;
	}

}
