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
package com.accenture.avs.device.jms.topic.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.be.jms.client.dto.AvsMessage;
import com.accenture.avs.commons.lib.JsonUtils;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.device.config.ConfigCacheManager;
import com.accenture.avs.device.config.model.BandwidthProfile;
import com.accenture.avs.device.config.model.VTP;
import com.accenture.avs.device.config.model.configms.GetBandwidthDTO;
import com.accenture.avs.device.config.model.configms.VideoTypes;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kumar.rajesh
 *
 */
@Component
public class ConsumerVTPAndBWPListener implements MessageListener {

	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(ConsumerVTPAndBWPListener.class);

	/** configCacheManager */
	@Autowired
	private ConfigCacheManager configCacheManager;
	
	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;
	
	/** Config Name as BWPROFILE */
	private String BWPROFILE = "bwProfiles";

	/** Config Name as VTP (Video Type Profile) */
	private String VIDEO_TYPE_PROFILE = "videoTypes";
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		LOGGER.logMessage("Start: Receive JMS message for VTP/BWP from Topic.");
		AvsMessage avsMessage = null;

		try {
			TextMessage textMessage = (TextMessage) message;
			String mes = textMessage.getText();
			LOGGER.logMessage("Receive JMS message as {} ", mes);

			if (mes != null) {

				avsMessage = JsonUtils.parseJson(AvsMessage.class, mes);

				if (avsMessage != null) {
					Map<String, Object> operationMessage = avsMessage.getOperationMessage();

					if (operationMessage != null) {

						String resource_messages = (String) operationMessage.get("resource");
						LOGGER.logMessage("avsMessage.operation {} ", avsMessage.getOperation());
						LOGGER.logMessage("avsMessage.resource {} ", resource_messages);
						Thread.sleep(1500L);
						if (BWPROFILE.equalsIgnoreCase(resource_messages)) {

							Object bwData = operationMessage.get("patches");
							String jsonValue = mapper.writeValueAsString(bwData);
							LOGGER.logMessage("json data {} ", jsonValue);

							if (jsonValue != null) {
								List<BandwidthProfile> bwpMessList = JsonUtils
										.parseJson(new TypeReference<List<BandwidthProfile>>() {
										}, jsonValue);
								generateBWP(bwpMessList);
							}
						} else if (VIDEO_TYPE_PROFILE.equalsIgnoreCase(resource_messages)) {

							Object vtpData = operationMessage.get("patches");
							String jsonValue = mapper.writeValueAsString(vtpData);
							LOGGER.logMessage("json data {} ", jsonValue);

							if (jsonValue != null) {
								List<VTP> bwpMessList = JsonUtils.parseJson(new TypeReference<List<VTP>>() {
								}, jsonValue);
								generateVTP(bwpMessList);
							}
						}
					}
				}
			}
		} catch (IOException | JMSException | InterruptedException e) {
			LOGGER.logError(e);
		}

		LOGGER.logMessage("Stop: Receive JMS message for VTP/BWP from Topic.");
	}
	
	/**
	 * This method generates video types object based on VTPMessage.
	 * 
	 * @param vtmMess
	 */
	private void generateVTP(List<VTP> vtpList) {
		LOGGER.logMessage("VTP Data size {} ", vtpList.size());
		List<String> changeVTPNameList = new ArrayList<>();
		List<VideoTypes> videoTypesList = configCacheManager.getVideoTypeProfileList();
		for (VTP vtp : vtpList) {
			VideoTypes vts = getVideoTypes(vtp);
			updateVideoTypesList(vts, changeVTPNameList, videoTypesList);
		}
		configCacheManager.reloadVTP();
		if(!changeVTPNameList.isEmpty()) {
			LOGGER.logMessage("Mass calculation starts for VTP definition change for VTPs {}",changeVTPNameList);
			massRecalculationService.collectDataForMass(Constants.Jms.VTP_CHANGE, changeVTPNameList, false); 
		}
	}

	/**
	 * makes VideoTypes object from VTP
	 * 
	 * @param vtp
	 */
	private VideoTypes getVideoTypes(VTP vtp) {
		VideoTypes vts = new VideoTypes();
		vts.setName(vtp.getName());
		vts.setIsActive(vtp.getIsActive());
		vts.setContentQuality(vtp.getContentQuality());
		vts.setBitRate(vtp.getBitRate() == null ? 0 : Integer.parseInt(vtp.getBitRate()));
		vts.setAssociatedBWProfiles(vtp.getAssociatedBWProfiles());
		return vts;
	}

	/**
	 * Generates VTP name list. 
	 * 
	 * @param newVTP
	 * @param changeVTPList
	 * @param videoTypesList
	 */
	private void updateVideoTypesList(VideoTypes newVTP, List<String> changeVTPList, List<VideoTypes> videoTypesList) {
		for (VideoTypes oldVTP : videoTypesList) {
			if (oldVTP.getName().equals(newVTP.getName())) {
				if (!oldVTP.getBitRate().equals(newVTP.getBitRate()) || !oldVTP.getIsActive().equals(newVTP.getIsActive()) 
						|| !oldVTP.getContentQuality().equals(newVTP.getContentQuality())) {
					changeVTPList.add(newVTP.getName());
				}
				break;
			}
		}
	}

	/**
	 * This method generates bandwidthDto object based on
	 * BandwidthProfileMessage.
	 * 
	 * @param bwpMess
	 */
	private void generateBWP(List<BandwidthProfile> bwpMessList) {
		LOGGER.logMessage("BWP Data size {} ", bwpMessList.size());
		List<String> changeBWPNameList = new ArrayList<>();
		List<GetBandwidthDTO> bandwidthProfileList = configCacheManager.getBandwidthProfileList();
		for (BandwidthProfile bwp : bwpMessList) {
			GetBandwidthDTO getBandwidthDTO = getBWPDto(bwp);
			updateBandwidthDTOList(getBandwidthDTO,changeBWPNameList, bandwidthProfileList);
		}
		configCacheManager.reloadBWP();
		if(!changeBWPNameList.isEmpty()) {
			LOGGER.logMessage("Mass calculation starts for Bandwidth Profile update for Bandwidths {}",changeBWPNameList);
			massRecalculationService.collectDataForMass(Constants.Jms.BW_PROFILE_CHANGE, changeBWPNameList, false); 
		}
	}

	/**
	 * Makes GetBandwidthDTO object from BandwidthProfile
	 * 
	 * @param bwp
	 */
	private GetBandwidthDTO getBWPDto(BandwidthProfile bwp) {
		GetBandwidthDTO GetBandwidthDTO = new GetBandwidthDTO();
		GetBandwidthDTO.setName(bwp.getName());
		GetBandwidthDTO.setBandwidth(bwp.getBandwidth());
		GetBandwidthDTO.setVideoTypes(bwp.getVideoTypes());
		return GetBandwidthDTO;
	}

	/**
	 * Generates BWP name list.
	 * 
	 * @param newBandwidthDTO
	 */
	private void updateBandwidthDTOList(GetBandwidthDTO newBandwidthDTO, List<String> changeBWPNameList,
			List<GetBandwidthDTO> bandwidthProfileList) {
		for (GetBandwidthDTO oldBwpDto : bandwidthProfileList) {
			if (oldBwpDto.getName().equals(newBandwidthDTO.getName())) {
				if (!oldBwpDto.getBandwidth().equals(newBandwidthDTO.getBandwidth())) {
					changeBWPNameList.add(newBandwidthDTO.getName());
				}
				break;
			}
		}
	}	
}
