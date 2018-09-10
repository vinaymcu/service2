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

package com.accenture.avs.device.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.avs.device.entity.DeviceProperty;
import com.accenture.avs.device.entity.DevicePropertyId;

/**
 * A repository for DeviceProperty
 * 
 * @author Singh.Saurabh
 *
 */
@Repository
public interface DevicePropertyRepository extends JpaRepository<DeviceProperty, DevicePropertyId> {

	/**
	 * This method finds all device properties for the provided id
	 * 
	 * @param Id
	 * 
	 * @return List<DeviceProperty>
	 */
	@Query("SELECT sp FROM DeviceProperty sp where sp.id.id = :id")
	List<DeviceProperty> findById(@Param("id") Long id);

	/**
	 * This method deletes all device properties for the provided id
	 * 
	 * @param Id
	 * 
	 * @return int
	 */
	@Modifying
	@Query("DELETE DeviceProperty sp where sp.id.id = :id")
	int deleteById(@Param("id") Long id);

	/**
	 * This method deletes all device properties for the provided id and name
	 * 
	 * @param Id
	 * @param name
	 * 
	 * @return int
	 */
	@Modifying
	@Query("DELETE FROM DeviceProperty dp WHERE dp.id.id = :id AND dp.id.propertyName IN :propertyNameList")
	void deleteByIdAndName(@Param("id") Long id, @Param("propertyNameList") List<String> propertyNameList);

}
