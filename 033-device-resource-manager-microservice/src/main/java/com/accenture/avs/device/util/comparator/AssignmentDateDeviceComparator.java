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
package com.accenture.avs.device.util.comparator;

import java.util.Comparator;

import com.accenture.avs.device.entity.Device;

/**
 * This class is used to sort the Device List.
 * 
 * @author singh.saurabh
 *
 */
public class AssignmentDateDeviceComparator implements Comparator<Device> {

	/**
	 * This method compares the devices based on their assignement date.
	 * 
	 * @param firstDevice
	 * @param secondDevice
	 */
	@Override
	public int compare(Device firstDevice, Device secondDevice) {
		return firstDevice.getDatetimeOfAssignment().compareTo(secondDevice.getDatetimeOfAssignment());
	}

}
