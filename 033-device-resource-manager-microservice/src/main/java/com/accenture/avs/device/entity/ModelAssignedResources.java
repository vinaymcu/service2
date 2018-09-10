/**
 * 
 */
package com.accenture.avs.device.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author surendra.kumar
 *
 */
@Entity
@Table(name = "MODEL_ASSIGNED_RESOURCES")
public class ModelAssignedResources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModelAssignedResourcesId id;
	
	/** amount */
	@Column(name = "AMOUNT")
	private String amount;
	
	/** unit */
	@Column(name = "UNIT")
	private String unit;

	/**
	 * 
	 */
	public ModelAssignedResources() {
	}

	/**
	 * @param id
	 */
	public ModelAssignedResources(ModelAssignedResourcesId id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public ModelAssignedResourcesId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ModelAssignedResourcesId id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
