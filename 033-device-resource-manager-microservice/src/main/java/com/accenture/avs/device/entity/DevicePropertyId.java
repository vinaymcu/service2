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
 * Composite Primary Key for DEVICE_PROPERTIES table
 * 
 * @author singh.saurabh
 *
 */
@Embeddable
public class DevicePropertyId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7692432779859588029L;

	/** id */
	@Column(name = "ID", nullable = false, precision = 9, scale = 0)
	private Long id;

	/** propertyName */
	@Column(name = "PROPERTY_NAME", nullable = false, length = 20)
	private String propertyName;

	/**
	 * Constructor
	 * 
	 */
	public DevicePropertyId() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param propertyName
	 */
	public DevicePropertyId(Long id, String propertyName) {
		this.id = id;
		this.propertyName = propertyName;
	}

	/**
	 * Gets id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets propertyName
	 * 
	 * @return propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Sets propertyName
	 * 
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (long) id;
		hash += (propertyName != null ? propertyName.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		boolean isEqual = true;
		if (!(object instanceof DevicePropertyId)) {
			isEqual = false;
		} else {
			DevicePropertyId other = (DevicePropertyId) object;
			if (this.id == null || !this.id.equals(other.id)) {
				isEqual = false;
			} else if ((this.propertyName == null && other.propertyName != null)
					|| (this.propertyName != null && !this.propertyName.equals(other.propertyName))) {
				isEqual = false;
			}
		}
		return isEqual;
	}

}
