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

package com.accenture.avs.device.config.model;

/**
 * @author kumar.rajesh
 *
 */
public class BandwidthAndVQEProperty {

	/** mdcBandwidthName */
	private String mdcBandwidthName;
	
	/** mdcBandwidthValue */
    private Integer mdcBandwidthValue;
    	
    /** use global VQE Bandwidth for VQE */
    private Boolean useGlobalVqeBW;

	/**
	 * @return the mdcBandwidthName
	 */
	public String getMdcBandwidthName() {
		return mdcBandwidthName;
	}

	/**
	 * @param mdcBandwidthName the mdcBandwidthName to set
	 */
	public void setMdcBandwidthName(String mdcBandwidthName) {
		this.mdcBandwidthName = mdcBandwidthName;
	}

	/**
	 * @return the mdcBandwidthValue
	 */
	public Integer getMdcBandwidthValue() {
		return mdcBandwidthValue;
	}

	/**
	 * @param mdcBandwidthValue the mdcBandwidthValue to set
	 */
	public void setMdcBandwidthValue(Integer mdcBandwidthValue) {
		this.mdcBandwidthValue = mdcBandwidthValue;
	}

	/**
	 * @return the useGlobalVqeBW
	 */
	public Boolean getUseGlobalVqeBW() {
		return useGlobalVqeBW;
	}

	/**
	 * @param useGlobalVqeBW the useGlobalVqeBW to set
	 */
	public void setUseGlobalVqeBW(Boolean useGlobalVqeBW) {
		this.useGlobalVqeBW = useGlobalVqeBW;
	}
    
}
