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

package com.accenture.avs.device.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.client.rest.BandwidthProfileRestClient;
import com.accenture.avs.device.config.client.rest.VideoTypeProfileRestClient;
import com.accenture.avs.device.config.model.BandwidthProfile;
import com.accenture.avs.device.config.model.VTP;
import com.accenture.avs.device.config.model.configms.GetBandwidthDTO;
import com.accenture.avs.device.config.model.configms.VideoTypes;

/**
 * The Configuration client class for interacting with ConfigurationManager to
 * read system message and parameters.
 * 
 * @author kumar.rajesh
 *
 */
@Component
public class ConfigCacheManager {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(ConfigCacheManager.class);

	/** bandwidthProfileRestClient */
	@Autowired
	private BandwidthProfileRestClient bandwidthProfileRestClient;

	/** videoTypeProfileRestClient */
	@Autowired
	private VideoTypeProfileRestClient videoTypeProfileRestClient;

	/** videoTypeProfileList */
	private List<VideoTypes> videoTypeProfileList = new ArrayList<>();

	/** bandwidthProfileList */
	private List<GetBandwidthDTO> bandwidthProfileList = new ArrayList<>();

	
	/**
	 * @return the videoTypeProfileList
	 */
	public List<VideoTypes> getVideoTypeProfileList() {
		return videoTypeProfileList;
	}

	/**
	 * @param videoTypeProfileList the videoTypeProfileList to set
	 */
	public void setVideoTypeProfileList(List<VideoTypes> videoTypeProfileList) {
		this.videoTypeProfileList = videoTypeProfileList;
	}

	/**
	 * @return the bandwidthProfileList
	 */
	public List<GetBandwidthDTO> getBandwidthProfileList() {
		return bandwidthProfileList;
	}

	/**
	 * @param bandwidthProfileList the bandwidthProfileList to set
	 */
	public void setBandwidthProfileList(List<GetBandwidthDTO> bandwidthProfileList) {
		this.bandwidthProfileList = bandwidthProfileList;
	}

	/**
	 * Find video type profile cache if not found then reload VTP from
	 * configuration MS.
	 * 
	 * @param videoTypeName
	 * @return VTP
	 */
	public VTP findVideoTypeProfile(String videoTypeName) {
		LOGGER.logMessage("Find video type profile from cache memory for {} ", videoTypeName);
		VTP vtpOjbect = getVideoTypeProfile(videoTypeName);
		if (vtpOjbect == null) {
			reloadVTP();
			vtpOjbect = getVideoTypeProfile(videoTypeName);
		}
		return vtpOjbect;
	}

	/**
	 * Find video type profile cache if not found then reload BWP from
	 * configuration MS.
	 * 
	 * @param bwName
	 * @return BandwidthProfile
	 */
	public BandwidthProfile findBandwidthProfile(String bwName) {
		LOGGER.logMessage("Find Band width profile from cache memory for {} ", bwName);
		BandwidthProfile bwOjbect = getBandwidthProfile(bwName);
		if (bwOjbect == null) {
			reloadBWP();
			bwOjbect = getBandwidthProfile(bwName);
		}
		return bwOjbect;
	}

	/**
	 * Get Video Type Profile object based on videoType name.
	 * 
	 * @param videoTypeName
	 * @return VTP
	 */
	private VTP getVideoTypeProfile(String videoTypeName) {
		VTP vtpOjbect = null;
		for (VideoTypes videoType : videoTypeProfileList) {
			if (videoType.getName().equals(videoTypeName)) {
				vtpOjbect = new VTP();
				vtpOjbect.setName(videoType.getName());
				vtpOjbect.setIsActive(videoType.getIsActive());
				vtpOjbect.setContentQuality(videoType.getContentQuality());
				vtpOjbect.setBitRate(videoType.getBitRate().toString());
				vtpOjbect.setAssociatedBWProfiles(videoType.getAssociatedBWProfiles());
			}
		}
		return vtpOjbect;
	}

	/**
	 * Get bandwidth profile object based on bandwidth name
	 * 
	 * @param bwName
	 * @return BandwidthProfile
	 */
	private BandwidthProfile getBandwidthProfile(String bwName) {
		BandwidthProfile bwOjbect = null;
		for (GetBandwidthDTO bandWidth : bandwidthProfileList) {
			if (bandWidth.getName().equals(bwName)) {
				bwOjbect = new BandwidthProfile();
				bwOjbect.setName(bandWidth.getName());
				bwOjbect.setBandwidth(bandWidth.getBandwidth());
				bwOjbect.setVideoTypes(bandWidth.getVideoTypes());
			}
		}
		return bwOjbect;
	}

	/**
	 * Load Video Type profile into memory
	 */
	public void reloadVTP() {
		videoTypeProfileList = videoTypeProfileRestClient.getVideoTypeProfileList();
	}

	/**
	 * Load Band width profile into memory
	 */
	public void reloadBWP() {
		bandwidthProfileList = bandwidthProfileRestClient.getbandwidthProfileList();
	}

	/**
	 * Initializing the Bandwidth profile and VideoType profile once application
	 * start-up is finished.
	 * 
	 */
	@PostConstruct
	public void init() {
		reloadBWP();
		reloadVTP();
	}
}
