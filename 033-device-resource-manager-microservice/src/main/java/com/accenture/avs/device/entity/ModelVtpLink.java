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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author surendra.kumar
 *
 */
@Entity
@Table(name = "MODEL_VTP_LINK")
public class ModelVtpLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ModelVtpLinkId id;

	
	/**
	 * 
	 */
	public ModelVtpLink() {
	}
	
	/**
	 * @param id
	 */
	public ModelVtpLink(ModelVtpLinkId id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public ModelVtpLinkId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ModelVtpLinkId id) {
		this.id = id;
	}
	

}
