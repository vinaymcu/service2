/****************************************************************************
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
package com.accenture.avs.device.config.client.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.ConfigurationProperties;
import com.accenture.avs.device.config.DeviceManagerApplication;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.config.model.configms.GetBandwidthDTO;
import com.accenture.avs.device.config.model.configms.GetBandwidthResponse;

/**
 * ResourceManagerClient
 * 
 * @author kumar.rajesh
 *
 */
@Component
public class BandwidthProfileRestClient {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceManagerApplication.class);
	
	/** configurationClient */
	@Autowired
	private ConfigurationClient configurationClient;
	
	/** restTemplate */
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Refresh system messages
	 * 
	 */
	public List<GetBandwidthDTO> getbandwidthProfileList() {
		List<GetBandwidthDTO> resultBandwidthProfileObj = new ArrayList<>() ;
		try {
			String finalUrl = configurationClient.getSystemParameterValue("GETBWPROFILE_CONFIGURATION_URL");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl);
			ResponseEntity<GetBandwidthResponse> response = restTemplate.getForEntity(builder.toUriString(),
					GetBandwidthResponse.class);
			GetBandwidthResponse bandwidthProfileResponse = response.getBody();
			resultBandwidthProfileObj = bandwidthProfileResponse.getResultObj();
		} catch (RestClientException exception) {
			LOGGER.logMessage("Error: fetching Bandwidth Profiles information.");
		}
		return resultBandwidthProfileObj;
	}

}
