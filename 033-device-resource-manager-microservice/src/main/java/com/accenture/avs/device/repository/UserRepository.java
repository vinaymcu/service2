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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.avs.device.entity.User;

/**
 * The Interface UserRepository.
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	/**
	 * Finds user based on user name
	 *
	 * @param userName
	 * @return user, if found
	 */
	User findByUserName(String userName);
	
	/**
	 * Finds user based on crmAccountId
	 *
	 * @param crmAccountId
	 * @return user, if found
	 */
	/**
	 * Find by userName with userLimits
	 * @param useName
	 * @return User
	 */	
	@Query("SELECT DISTINCT U FROM User U LEFT OUTER JOIN FETCH U.userLimits MRE WHERE U.crmAccountId =:crmAccountId")
	User findByCrmAccountId(@Param("crmAccountId") String crmAccountId);
	
	/**
	 * This method returns subscribers with assigned device
	 * 
	 * @return
	 */
	@Query("SELECT u.crmAccountId FROM User u WHERE EXISTS( select 1 FROM Device d WHERE d.assignedToUserName = u.userName)")
	//@Query(nativeQuery = true, value = "select distinct user_name from user u, devices d where d.assigned_to_username = u.user_name LIMIT :batchSize")
	List<String> findAllSubscribersWithDevice();

	/**
	 * Find by userName with userLimits
	 * @param useName
	 * @return User
	 */	
	@Query("SELECT DISTINCT U FROM User U LEFT OUTER JOIN FETCH U.userLimits MRE WHERE U.userName =:userName")
	User findByUserNameWithUserLimits(@Param("userName") String useName);
	
	/**
	 * This method returns Affective Subscribers For Vtp
	 * @param subIdentifier
	 * @return
	 */
	@Query("SELECT DISTINCT u.crmAccountId FROM Device d, ModelVtpLink mvl, User u WHERE d.model.id = mvl.id.modelId AND d.assignedToUserName = u.userName AND mvl.id.vtpName in (:vtpName)")
	List<String> findAffectiveSubscribersForVtpChange(@Param("vtpName") List<String> subIdentifier);

	/**
	 * This method returns Affective Subscribers For Model
	 * 
	 * @param subIdentifier
	 * @return
	 */
	@Query("SELECT DISTINCT u.crmAccountId FROM Device d, Model m, User u where m.id = d.model.id and d.assignedToUserName = u.userName and m.modelName in (:modelName)")
	List<String> findAffectiveSubscribersForModelChange(@Param("modelName") List<String> subIdentifier);

	/**
	 * This method returns Affective Subscribers For bandwidth
	 * @param subIdentifier
	 * @return
	 */
	@Query("SELECT u.crmAccountId FROM User u WHERE EXISTS( select 1 FROM Device d WHERE d.assignedToUserName = u.userName) AND bandwithProfile in (:bwProfile)")
	List<String> findAffectiveSubscribersForBwChange(@Param("bwProfile") List<String> subIdentifier);
	
	/**
	 * 
	 * @param crmAccountList
	 * @return
	 */
	@Modifying
	@Query("UPDATE User SET massCalculationCounter = massCalculationCounter+1 WHERE crmaccountid in (:crmAccountList) AND massCalculationCounter = 0 or massIdentifier is not null")
	Integer updateMassCalculationCounter(@Param("crmAccountList") List<String> crmAccountList);

	/**
	 * 
	 * @param identifier
	 * @param batchSize
	 * @return
	 */
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query(nativeQuery = true, value = "UPDATE user SET MASS_IDENTIFIER = :identifier WHERE MASS_IDENTIFIER is null AND MASS_CALCULATION_COUNTER > 0 LIMIT :batchSize")
	Integer updateMassIdentifier(@Param("identifier") String identifier, @Param("batchSize") Integer batchSize);
	//Integer updateMassIdentifier(@Param("identifier") String identifier);

	/**
	 * 
	 * @param massIdentifier
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT user_Name FROM User u WHERE MASS_IDENTIFIER = :massIdentifier")
	List<String> getUserByMassIdentifier(@Param("massIdentifier") String massIdentifier);
	
	/**
	 * 
	 * @param massIdentifier
	 * @return
	 */
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query(nativeQuery = true, value = "UPDATE user SET MASS_IDENTIFIER = null WHERE MASS_IDENTIFIER = :massIdentifier")
	Integer clearMassIdentifier(@Param("massIdentifier") String massIdentifier);

	/**
	 * 
	 * @return
	 */
	@Modifying
	@Query("UPDATE User SET massIdentifier = null, massCalculationCounter = 1  WHERE massIdentifier is not null AND massCalculationCounter > 0 ")
	Integer updateDataForStartupMassCalculator();
	
	/**
	 * This method gets the VQE Update Status for provided crmAccountIds.
	 * 
	 * @param crmAccountIdList
	 * @param delimiter
	 * @param value
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CONCAT(CONCAT(vqestatus.crmaccountid, :delimiter),"
			+ " (CASE WHEN vqestatus.loginfo IS NULL THEN :value ELSE vqestatus.loginfo END))"
			+ " FROM ("
			+ " SELECT user.crmaccountid,"
			+ " (SELECT ul.failed_reason FROM user_log ul WHERE ul.crmaccountid = user.crmaccountid ORDER BY ul.insert_datetime DESC LIMIT 1) AS loginfo"
			+ " FROM user LEFT OUTER JOIN user_log log ON user.crmaccountid = log.crmaccountid"
			+ " WHERE user.crmaccountid IN :crmAccountIdList GROUP BY user.crmaccountid) AS vqestatus")
	List<String> getVqeUpdateStatusForUsers(@Param("crmAccountIdList") List<String> crmAccountIdList,
			@Param("delimiter") String delimiter, @Param("value") String value);
	
}
