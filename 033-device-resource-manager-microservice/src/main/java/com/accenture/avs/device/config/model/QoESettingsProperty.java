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
 * @author surendra.kumar
 *
 */
public class QoESettingsProperty {
	
	/**
	 * rccOverride
	 */
    private boolean rccOverride;
    
    /**
     * overrideDefaultQoEBandwidth
     */
    private boolean overrideDefaultQoEBandwidth;

	/**
	 * @return the rccOverride
	 */
	public boolean isRccOverride() {
		return rccOverride;
	}

	/**
	 * @param rccOverride the rccOverride to set
	 */
	public void setRccOverride(boolean rccOverride) {
		this.rccOverride = rccOverride;
	}

	/**
	 * @return the overrideDefaultQoEBandwidth
	 */
	public boolean isOverrideDefaultQoEBandwidth() {
		return overrideDefaultQoEBandwidth;
	}

	/**
	 * @param overrideDefaultQoEBandwidth the overrideDefaultQoEBandwidth to set
	 */
	public void setOverrideDefaultQoEBandwidth(boolean overrideDefaultQoEBandwidth) {
		this.overrideDefaultQoEBandwidth = overrideDefaultQoEBandwidth;
	}
    
    
}
