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
package com.accenture.avs.device.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.accenture.avs.device.json.object.devicemanager.GenericResponse;

/**
 * @author surendra.kumar
 *
 */
public interface MassRecalculationService {
	/**
	 * Triggers mass resource calculation
	 * @param isMassAtStartup
	 * @return
	 */
	GenericResponse massRecalculation(boolean isMassAtStartup);

	/**
	 * This method starts profile calculation process
	 * @param identifier
	 * @param executorService 
	 */
	void processCalculation(String identifier, ExecutorService executorService);
	
	/**
	 * This method will updated column mass_calculation_counter for mass calculation
	 * @param caller
	 * @param subIdentifier
	 */
	void collectDataForMass(String caller, List<String> subIdentifier, boolean isMassAtStartup);
}
