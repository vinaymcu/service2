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
import com.accenture.avs.device.config.model.configms.GetVideoTypeProfileResponse;
import com.accenture.avs.device.config.model.configms.GetVideoTypesDTO;
import com.accenture.avs.device.config.model.configms.VideoTypes;

/**
 * ResourceManagerClient
 * 
 * @author kumar.rajesh
 *
 */
@Component
public class VideoTypeProfileRestClient {

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
	public List<VideoTypes> getVideoTypeProfileList() {
		List<VideoTypes> resultVideoTypeObj = new ArrayList<>();
		try {
			String finalUrl = configurationClient.getSystemParameterValue("GETVIDEOTYPE_CONFIGURATION_URL");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl).queryParam("isActive", "true");
			ResponseEntity<GetVideoTypeProfileResponse> response = restTemplate.getForEntity(builder.toUriString(),
					GetVideoTypeProfileResponse.class);
			GetVideoTypeProfileResponse videoTypeProfileResponse = response.getBody();
			GetVideoTypesDTO vtpDto = videoTypeProfileResponse.getResultObj();
			for(VideoTypes vt : vtpDto.getVideoTypes()) {
				resultVideoTypeObj.add(vt);
			}
		} catch (RestClientException exception) {
			LOGGER.logMessage("Error: fetching Video Type Profiles information.");
		}
		return resultVideoTypeObj;
	}
	
}
