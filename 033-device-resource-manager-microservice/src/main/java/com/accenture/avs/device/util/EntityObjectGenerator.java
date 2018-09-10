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
package com.accenture.avs.device.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.accenture.avs.device.entity.DefaultGroupAttributes;

import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.GroupAttributes;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelAssignedResourcesId;
import com.accenture.avs.device.entity.ModelMaxStreamsAllowedPerQuality;
import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.entity.ModelVtpLinkId;
import com.accenture.avs.device.entity.OverrideVQEGroupsCondition;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.entity.UserLimits;
import com.accenture.avs.device.json.object.devicemanager.CreateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.CreateOverrideVQEGroupsConditionsRequest;
import com.accenture.avs.device.json.object.devicemanager.DeviceDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceRegistration;
import com.accenture.avs.device.json.object.devicemanager.GetVQEGroupsConditionDTO;
import com.accenture.avs.device.json.object.devicemanager.LimitsPerContentQuality;
import com.accenture.avs.device.json.object.devicemanager.MaxStreamsPerQuality;
import com.accenture.avs.device.json.object.devicemanager.ResourceList;
import com.accenture.avs.device.json.object.devicemanager.SetUserRequest;
import com.accenture.avs.device.json.object.devicemanager.UpdateDeviceModelRequest;
import com.accenture.avs.device.json.object.devicemanager.VideoTypeProfile;
import com.accenture.avs.device.json.object.vqe.Extension;
import com.accenture.avs.device.json.object.vqe.Extension2;
import com.accenture.avs.device.json.object.vqe.Extension3;
import com.accenture.avs.device.json.object.vqe.Extension4;
import com.accenture.avs.device.json.object.vqe.Extension5;
import com.accenture.avs.device.json.object.vqe.Extension6;
import com.accenture.avs.device.json.object.vqe.Extension7;
import com.accenture.avs.device.json.object.vqe.GroupAttributesDto;

/**
 * This class generates the entity objects.
 * 
 * @author singh.saurabh
 *
 */
public class EntityObjectGenerator {

	/**
	 * This method creates an entity object for Device
	 * 
	 * @param request
	 * @param deviceEntity
	 * @param deviceModel
	 * @param platform
	 * @param deviceType
	 * @param deviceName
	 * @param lastUpdateUserName
	 * 
	 * @return Device
	 */
	public static Device getDeviceEntity(Object request, Device deviceEntity, Model deviceModel, String platform,
			String deviceType, String deviceName, String lastUpdateUserName) {

		String opeartionType = Constants.OperationType.OPERATION_UPDATE;
		if (DeviceManagerUtil.checkNullObject(deviceEntity)) {
			deviceEntity = new Device();
			deviceEntity.setAssignmentStatus(Constants.AssignmentStatus.UNASSIGNED);
			opeartionType = Constants.OperationType.OPERATION_CREATE;
		}

		DeviceManagerUtil.setPlatformAndDeviceType(deviceEntity, platform, deviceType, opeartionType);

		if (request instanceof DeviceDto) {
			DeviceDto createDeviceDto = (DeviceDto) request;

			if (opeartionType.equals(Constants.OperationType.OPERATION_UPDATE)) {
				deviceEntity.setInternalIpAddress(createDeviceDto.getIpAddress());
				deviceEntity.setExternalIpAddress(createDeviceDto.getExtIPAddress());
			}
			if (opeartionType.equals(Constants.OperationType.OPERATION_CREATE)) {
				deviceEntity.setDrmId(validateAndGetId(createDeviceDto.getDeviceId(), createDeviceDto.getDrmId()));
			} else if (!DeviceManagerUtil.checkNullObject(createDeviceDto.getDrmId())) {
				deviceEntity.setDrmId(createDeviceDto.getDrmId());
			}

			getDeviceEntityForCreateUpdateDevice(deviceEntity, createDeviceDto);
		} else if (request instanceof DeviceRegistration) {
			DeviceRegistration deviceRegistration = (DeviceRegistration) request;

			if (opeartionType.equals(Constants.OperationType.OPERATION_CREATE)) {
				deviceEntity
						.setDrmId(validateAndGetId(deviceRegistration.getDeviceId(), deviceRegistration.getDrmId()));
			} else if (!DeviceManagerUtil.checkNullObject(deviceRegistration.getDrmId())) {
				deviceEntity.setDrmId(deviceRegistration.getDrmId());
			}
			getDeviceEntityForRegisterDevice(deviceEntity, deviceRegistration);
		}

		if (!DeviceManagerUtil.checkNullObject(deviceName)) {
			deviceEntity.setDeviceName(deviceName);
		}

		if (!DeviceManagerUtil.checkNullObject(deviceModel)) {
			deviceEntity.setModel(deviceModel);
		}

		deviceEntity.setSupportedMode(Constants.IMPLICIT_NAT);
		deviceEntity.setConnectionMode(Constants.IMPLICIT_NAT);
		deviceEntity.setLastUpdatedBy(lastUpdateUserName);
		deviceEntity.setLastUpdatedOn(System.currentTimeMillis());
		return deviceEntity;
	}

	/**
	 * Sets Device entity for Create/Update Device Request
	 * 
	 * @param device
	 * @param deviceDto
	 */
	private static void getDeviceEntityForCreateUpdateDevice(Device device, DeviceDto deviceDto) {
		device.setDeviceId(deviceDto.getDeviceId());
		if (!DeviceManagerUtil.checkNullObject(deviceDto.getMaxBandwidthUpdates())) {
			device.setMaxBandwidthUpdate(deviceDto.getMaxBandwidthUpdates().longValue());
		}

		device.setSerialNumber(deviceDto.getSerialNumber());
		device.setSoftwareVersion(deviceDto.getSoftwareVersion());
		device.setUiVersion(deviceDto.getUiVersion());
		if (!DeviceManagerUtil.checkNullObject(deviceDto.getTvQualityInterest())) {
			device.setTvQualityInterest(deviceDto.getTvQualityInterest());
		}
	}

	/**
	 * Sets Device entity for Register Device Request
	 * 
	 * @param device
	 * @param deviceRegistration
	 */
	private static void getDeviceEntityForRegisterDevice(Device device, DeviceRegistration deviceRegistration) {
		device.setAssignmentStatus(Constants.AssignmentStatus.ASSIGNED);
		device.setAssignedToUserName(deviceRegistration.getUserName());
		device.setDatetimeOfAssignment(System.currentTimeMillis());
		device.setDeviceId(deviceRegistration.getDeviceId());
		device.setSerialNumber(deviceRegistration.getSerialNumber());
		device.setSoftwareVersion(deviceRegistration.getSoftwareVersion());
		device.setUiVersion(deviceRegistration.getUiVersion());
		device.setInternalIpAddress(deviceRegistration.getIpAddress());
		device.setExternalIpAddress(deviceRegistration.getExtIpAddress());
		if (!DeviceManagerUtil.checkNullObject(deviceRegistration.getTvQualityInterest())) {
			device.setTvQualityInterest(deviceRegistration.getTvQualityInterest());
		}
	}

	/**
	 * This method helps to decide the drmId for the device.
	 * 
	 * @param deviceId
	 * @param drmId
	 * @return
	 */
	private static String validateAndGetId(String deviceId, String drmId) {
		return DeviceManagerUtil.checkNullObject(drmId) ? deviceId : drmId;
	}

	/**
	 * This method creates an entity object for Device Model for
	 * CreateDeviceModelRequest.
	 * 
	 * @param deviceModel
	 * @param lastUpdateUserName
	 * @param lastUpdatedInterface
	 * @return
	 */
	public static Model getCreateModelEntity(CreateDeviceModelRequest deviceModel, String lastUpdateUserName,
			String lastUpdatedInterface) {
		Model deviceModelEntity = new Model();
		deviceModelEntity.setModelName(deviceModel.getDeviceModel());
		deviceModelEntity.setVendor(deviceModel.getVendor());
		deviceModelEntity.setPlatform(deviceModel.getPlatform());
		deviceModelEntity.setOsName(deviceModel.getOsName());
		deviceModelEntity.setOsVersion(deviceModel.getOsVersion());
		deviceModelEntity.setSoftwareVersion(deviceModel.getSwId());
		deviceModelEntity.setQoeCapable(deviceModel.getQoeCapable());
		deviceModelEntity.setUiVersion(deviceModel.getUiVersion());
		deviceModelEntity.setSdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertIntegerToLong(deviceModel.getSdChannelTimeshiftBuffer()));
		deviceModelEntity.setHdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertIntegerToLong(deviceModel.getHdChannelTimeshiftBuffer()));
		deviceModelEntity.setStatus(DeviceManagerUtil.convertIntToBoolean(deviceModel.getStatus()));
		deviceModelEntity.setVqeProfile(deviceModel.getVqeProfile());
		deviceModelEntity.setDeviceAutoRegistration(deviceModel.getDeviceAutoRegistration());
		deviceModelEntity.setTstvBufferSize(DeviceManagerUtil.convertIntegerToLong(deviceModel.getTstvBufferSize()));
		deviceModelEntity.setHdCapable(deviceModel.getHdCapable());
		deviceModelEntity.setLastUpdatedDatetime(System.currentTimeMillis());
		deviceModelEntity.setLastUpdateUsername(lastUpdateUserName);
		deviceModelEntity.setLastUpdateInterface(lastUpdatedInterface);
		deviceModelEntity.setModelMaxStreamsAllowedPerQuality(
				getModelMaxStreamsAllowedPerQuality(deviceModel.getMaxStreamsPerQuality()));
		return deviceModelEntity;
	}

	public static ModelVtpLink getCreateModelVtpLinkEntity(VideoTypeProfile videoTypeProfile, Long modelId) {
		ModelVtpLink modelVtpLink = new ModelVtpLink();
		ModelVtpLinkId modelVtpLinkId = new ModelVtpLinkId();
		modelVtpLinkId.setModelId(modelId);
		modelVtpLinkId.setVtpName(videoTypeProfile.getVtpName());
		modelVtpLink.setId(modelVtpLinkId);
		return modelVtpLink;
	}

	public static ModelAssignedResources getCreateModelAssignedResourcesEntity(ResourceList resource, Long modelId) {
		ModelAssignedResources modelAssignedResources = new ModelAssignedResources();
		ModelAssignedResourcesId modelAssignedResourcesId = new ModelAssignedResourcesId();
		modelAssignedResourcesId.setModelId(modelId);
		modelAssignedResourcesId.setResourceName(resource.getName());
		modelAssignedResources.setId(modelAssignedResourcesId);
		modelAssignedResources.setAmount(resource.getAmount() + "");
		modelAssignedResources.setUnit(resource.getUnit());
		return modelAssignedResources;
	}

	/**
	 * This method creates an entity object for Device Model for
	 * UpdateDeviceModelRequest.
	 * 
	 * @param deviceModel
	 * @param deviceModelEntity
	 * @param lastUpdateUserName
	 * @param lastUpdatedInterface
	 * @return
	 */
	public static Model getUpdateModelEntity(UpdateDeviceModelRequest deviceModel, Model deviceModelEntity,
			String lastUpdateUserName, String lastUpdatedInterface) {
		deviceModelEntity.setModelName(deviceModel.getDeviceModel());
		deviceModelEntity.setVendor(deviceModel.getVendor());
		deviceModelEntity.setPlatform(deviceModel.getPlatform());
		deviceModelEntity.setOsName(deviceModel.getOsName());
		deviceModelEntity.setOsVersion(deviceModel.getOsVersion());
		deviceModelEntity.setSoftwareVersion(deviceModel.getSwId());
		deviceModelEntity.setQoeCapable(deviceModel.getQoeCapable());
		deviceModelEntity.setUiVersion(deviceModel.getUiVersion());
		deviceModelEntity.setSdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertIntegerToLong(deviceModel.getSdChannelTimeshiftBuffer()));
		deviceModelEntity.setHdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertIntegerToLong(deviceModel.getHdChannelTimeshiftBuffer()));
		deviceModelEntity.setStatus(DeviceManagerUtil.convertIntToBoolean(deviceModel.getStatus()));
		deviceModelEntity.setVqeProfile(deviceModel.getVqeProfile());
		deviceModelEntity.setDeviceAutoRegistration(deviceModel.getDeviceAutoRegistration());
		deviceModelEntity.setTstvBufferSize(DeviceManagerUtil.convertIntegerToLong(deviceModel.getTstvBufferSize()));
		deviceModelEntity.setHdCapable(deviceModel.getHdCapable());
		deviceModelEntity.setLastUpdatedDatetime(System.currentTimeMillis());
		deviceModelEntity.setLastUpdateUsername(lastUpdateUserName);
		deviceModelEntity.setLastUpdateInterface(lastUpdatedInterface);
		deviceModelEntity.setModelMaxStreamsAllowedPerQuality(
				getModelMaxStreamsAllowedPerQuality(deviceModel.getMaxStreamsPerQuality()));
		return deviceModelEntity;
	}

	/**
	 * This method creates a List of ModelMaxStreamsAllowedPerQuality.
	 * 
	 * @param maxStreamsPerQualityList
	 * @return
	 */
	public static List<ModelMaxStreamsAllowedPerQuality> getModelMaxStreamsAllowedPerQuality(
			List<MaxStreamsPerQuality> maxStreamsPerQualityList) {
		List<ModelMaxStreamsAllowedPerQuality> modelMaxStreamsAllowedPerQualityList = new ArrayList<>();
		for (MaxStreamsPerQuality maxStreamsPerQuality : maxStreamsPerQualityList) {
			ModelMaxStreamsAllowedPerQuality modelMaxStreamsAllowedPerQuality = new ModelMaxStreamsAllowedPerQuality();
			modelMaxStreamsAllowedPerQuality.setResolutionTypeName(maxStreamsPerQuality.getContentQuality());
			modelMaxStreamsAllowedPerQuality.setMaxStreams(maxStreamsPerQuality.getStreamLimit().longValue());
			modelMaxStreamsAllowedPerQualityList.add(modelMaxStreamsAllowedPerQuality);
		}
		return modelMaxStreamsAllowedPerQualityList;
	}

	/**
	 * <<<<<<< HEAD Sets user entity for set user Request
	 * 
	 * ======= This method creates an entity object for default group attributes
	 * 
	 * @param groupAttributes
	 * @param defaultGroupAttributesEntity
	 * @return DefaultGroupAttributes
	 */
	public static DefaultGroupAttributes getDefaultGroupAttributesEntity(
			DefaultGroupAttributes defaultGroupAttributesEntity, GroupAttributesDto groupAttributes) {
		defaultGroupAttributesEntity.setVqecEnable(groupAttributes.getVqecEnable());
		defaultGroupAttributesEntity.setVqecEnableUpdatetype(groupAttributes.getVqecEnableUpdateType());
		defaultGroupAttributesEntity.setJitterBuffSize(groupAttributes.getJitterBuffSize());
		defaultGroupAttributesEntity.setJitterBuffSizeUpdatetype(groupAttributes.getJitterBuffSizeUpdateType());
		defaultGroupAttributesEntity.setRepairTriggerPointAbs(groupAttributes.getRepairTriggerPointAbs());
		defaultGroupAttributesEntity
				.setRepairTriggerPointAbsUpdatetype(groupAttributes.getRepairTriggerPointAbsUpdateType());
		defaultGroupAttributesEntity.setPakpoolSize(groupAttributes.getPakpoolSize());
		defaultGroupAttributesEntity.setPakpoolSizeUpdatetype(groupAttributes.getPakpoolSizeUpdateType());
		defaultGroupAttributesEntity.setSoRcvbuf(groupAttributes.getSoRcvbuf());
		defaultGroupAttributesEntity.setSoRcvbufUpdatetype(groupAttributes.getSoRcvbufUpdateType());
		if (Objects.nonNull(groupAttributes.getSigMode())) {
			defaultGroupAttributesEntity.setSigMode(groupAttributes.getSigMode().value());
		}
		defaultGroupAttributesEntity.setSigModeUpdatetype(groupAttributes.getSigModeUpdateType());
		defaultGroupAttributesEntity.setNatBindingRefreshInterval(groupAttributes.getNatBindingRefreshInterval());
		defaultGroupAttributesEntity
				.setNatBindingRefreshIntervalUpdatetype(groupAttributes.getNatBindingRefreshIntervalUpdateType());
		defaultGroupAttributesEntity.setMaxPaksize(groupAttributes.getMaxPaksize());
		defaultGroupAttributesEntity.setMaxPaksizeUpdatetype(groupAttributes.getMaxPaksizeUpdateType());
		defaultGroupAttributesEntity.setLibcliTelnetPort(groupAttributes.getLibcliTelnetPort());
		defaultGroupAttributesEntity.setLibcliTelnetPortUpdatetype(groupAttributes.getLibcliTelnetPortUpdateType());
		defaultGroupAttributesEntity.setOutputPakqLimit(groupAttributes.getOutputPakqLimit());
		defaultGroupAttributesEntity.setOutputPakqLimitUpdatetype(groupAttributes.getOutputPakqLimitUpdateType());
		defaultGroupAttributesEntity.setUpdateWindow(groupAttributes.getUpdateWindow());
		defaultGroupAttributesEntity.setUpdateWindowUpdatetype(groupAttributes.getUpdateWindowUpdateType());
		defaultGroupAttributesEntity.setUpdateIntervalMax(groupAttributes.getUpdateIntervalMax());
		defaultGroupAttributesEntity.setUpdateIntervalMaxUpdatetype(groupAttributes.getUpdateIntervalMaxUpdateType());
		defaultGroupAttributesEntity.setErrorRepairEnable(groupAttributes.getErrorRepairEnable());
		defaultGroupAttributesEntity.setErrorRepairEnableUpdatetype(groupAttributes.getErrorRepairEnableUpdateType());
		defaultGroupAttributesEntity.setErrorRepairPolicerEnable(groupAttributes.getErrorRepairPolicerEnable());
		defaultGroupAttributesEntity
				.setErrorRepairPolicerEnableUpdatetype(groupAttributes.getErrorRepairPolicerEnableUpdateType());
		defaultGroupAttributesEntity.setErrorRepairPolicerRate(groupAttributes.getErrorRepairPolicerRate());
		defaultGroupAttributesEntity
				.setErrorRepairPolicerRateUpdatetype(groupAttributes.getErrorRepairPolicerRateUpdateType());
		defaultGroupAttributesEntity.setErrorRepairPolicerBurst(groupAttributes.getErrorRepairPolicerBurst());
		defaultGroupAttributesEntity
				.setErrorRepairPolicerBurstUpdatetype(groupAttributes.getErrorRepairPolicerBurstUpdateType());
		defaultGroupAttributesEntity.setFecEnable(groupAttributes.getFecEnable());
		defaultGroupAttributesEntity.setFecEnableUpdatetype(groupAttributes.getFecEnableUpdateType());
		defaultGroupAttributesEntity.setRccEnable(groupAttributes.getRccEnable());
		defaultGroupAttributesEntity.setRccEnableUpdatetype(groupAttributes.getRccEnableUpdateType());
		defaultGroupAttributesEntity.setRccStartTimeout(groupAttributes.getRccStartTimeout());
		defaultGroupAttributesEntity.setRccStartTimeoutUpdatetype(groupAttributes.getRccStartTimeoutUpdateType());
		defaultGroupAttributesEntity.setReorderDelayAbs(groupAttributes.getReorderDelayAbs());
		defaultGroupAttributesEntity.setReorderDelayAbsUpdatetype(groupAttributes.getReorderDelayAbsUpdateType());
		defaultGroupAttributesEntity.setCliIfname(groupAttributes.getCliIfname());
		defaultGroupAttributesEntity.setCliIfnameUpdatetype(groupAttributes.getCliIfnameUpdateType());
		defaultGroupAttributesEntity.setRtcpDscpValue(groupAttributes.getRtcpDscpValue());
		defaultGroupAttributesEntity.setRtcpDscpValueUpdatetype(groupAttributes.getRtcpDscpValueUpdateType());
		defaultGroupAttributesEntity.setFastfillEnable(groupAttributes.getFastfillEnable());
		defaultGroupAttributesEntity.setFastfillEnableUpdatetype(groupAttributes.getFastfillEnableUpdateType());
		setExtention(groupAttributes, defaultGroupAttributesEntity);
		return defaultGroupAttributesEntity;
	}

	/**
	 * @param groupAttributes
	 * @param defaultGroupAttributesEntity
	 */
	private static void setExtention(GroupAttributesDto groupAttributes,
			DefaultGroupAttributes defaultGroupAttributesEntity) {
		Extension extension = groupAttributes.getExtension();
		if (Objects.nonNull(extension)) {
			defaultGroupAttributesEntity.setExtensionAppDelay(extension.getAppDelay());
			defaultGroupAttributesEntity.setExtensionAppDelayUpdatetype(extension.getAppDelayUpdateType());
			defaultGroupAttributesEntity.setExtensionMaxFastfill(extension.getMaxFastfill());
			defaultGroupAttributesEntity.setMaxFastfillUpdatetype(extension.getMaxFastfillUpdateType());
			defaultGroupAttributesEntity.setExtensionMaxReceiveBandwidthHd(extension.getMaxReceiveBandwidthHd());
			defaultGroupAttributesEntity
					.setExtensionMaxReceiveBandwidthHdUpdatetype(extension.getMaxReceiveBandwidthHdUpdateType());
			defaultGroupAttributesEntity.setExtensionMaxReceiveBandwidthSD(extension.getMaxReceiveBandwidthSd());
			defaultGroupAttributesEntity
					.setExtensionMaxReceiveBandwidthSDUpdatetype(extension.getMaxReceiveBandwidthSdUpdateType());
			defaultGroupAttributesEntity.setExtensionMinHdStreamBitrate(extension.getMinHdStreamBitrate());
			defaultGroupAttributesEntity
					.setExtensionMinHdStreamBitrateUpdatetype(extension.getMinHdStreamBitrateUpdateType());
			defaultGroupAttributesEntity.setExtensionSrcipFilterEnable(extension.getSrcIpFilterEnable());
			defaultGroupAttributesEntity
					.setExtensionSrcipFilterEnableUpdatetype(extension.getSrcIpFilterEnableUpdateType());
			setExtension2(defaultGroupAttributesEntity, extension);

		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension
	 */
	private static void setExtension2(DefaultGroupAttributes defaultGroupAttributesEntity, Extension extension) {
		Extension2 extension2 = extension.getExtension();
		if (Objects.nonNull(extension2)) {
			defaultGroupAttributesEntity
					.setExtension2MaxReceiveBandwidthHDRcc(extension2.getMaxReceiveBandwidthHdRcc());
			defaultGroupAttributesEntity.setExtension2MaxReceiveBandwidthHDRccUpdatetype(
					extension2.getMaxReceiveBandwidthHdRccUpdateType());
			defaultGroupAttributesEntity
					.setExtension2MaxReceiveBandwidthSDRcc(extension2.getMaxReceiveBandwidthSdRcc());
			defaultGroupAttributesEntity.setExtension2MaxReceiveBandwidthSDRccUpdatetype(
					extension2.getMaxReceiveBandwidthSdRccUpdateType());
			defaultGroupAttributesEntity.setExtension2QoeEnable(extension2.getQoeEnable());
			defaultGroupAttributesEntity.setExtension2QoeEnableUpdatetype(extension2.getQoeEnableUpdateType());
			setExtension3(defaultGroupAttributesEntity, extension2);
		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension2
	 */
	private static void setExtension3(DefaultGroupAttributes defaultGroupAttributesEntity, Extension2 extension2) {
		Extension3 extension3 = extension2.getExtension();
		if (Objects.nonNull(extension3)) {
			defaultGroupAttributesEntity.setExtension3RccExtraIgmpIp(extension3.getRccExtraIgmpIp());
			defaultGroupAttributesEntity
					.setExtension3RccExtraIgmpIpUpdatetype(extension3.getRccExtraIgmpIpUpdateType());
			setExtension4(defaultGroupAttributesEntity, extension3);
		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension3
	 */
	private static void setExtension4(DefaultGroupAttributes defaultGroupAttributesEntity, Extension3 extension3) {
		Extension4 extension4 = extension3.getExtension();
		if (Objects.nonNull(extension4)) {
			defaultGroupAttributesEntity.setExtension4LogLevel(extension4.getLogLevel());
			defaultGroupAttributesEntity.setExtension4LogLevelUpdatetype(extension4.getLogLevelUpdateType());
			setExtension5(defaultGroupAttributesEntity, extension4);
		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension4
	 */
	private static void setExtension5(DefaultGroupAttributes defaultGroupAttributesEntity, Extension4 extension4) {
		Extension5 extension5 = extension4.getExtension();
		if (Objects.nonNull(extension5)) {
			defaultGroupAttributesEntity
					.setExtension5ErrorRepairRepeatRequestEnable(extension5.getErrorRepairRepeatRequestEnable());
			defaultGroupAttributesEntity.setExtension5ErrorRepairRepeatRequestEnableUpdatetype(
					extension5.getErrorRepairRepeatRequestEnableUpdateType());
			defaultGroupAttributesEntity
					.setExtension5ErrorRepairSmartRequestEnable(extension5.getErrorRepairSmartRequestEnable());
			defaultGroupAttributesEntity.setExtension5ErrorRepairSmartRequestEnableUpdatetype(
					extension5.getErrorRepairSmartRequestEnableUpdateType());
			defaultGroupAttributesEntity
					.setExtension5RepairMinRoundTripTimeAbs(extension5.getRepairMinRoundTripTimeAbs());
			defaultGroupAttributesEntity.setExtension5RepairMinRoundTripTimeAbsUpdatetype(
					extension5.getRepairMinRoundTripTimeAbsUpdateType());
			setExtension6(defaultGroupAttributesEntity, extension5);

		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension5
	 */
	private static void setExtension6(DefaultGroupAttributes defaultGroupAttributesEntity, Extension5 extension5) {
		Extension6 extension6 = extension5.getExtension();
		if (Objects.nonNull(extension6)) {
			defaultGroupAttributesEntity.setExtension6ViewershipEnable(extension6.getViewershipEnable());
			defaultGroupAttributesEntity
					.setExtension6ViewershipEnableUpdatetype(extension6.getViewershipEnableUpdateType());
			setExtension7(defaultGroupAttributesEntity, extension6);
		}
	}

	/**
	 * @param defaultGroupAttributesEntity
	 * @param extension6
	 */
	private static void setExtension7(DefaultGroupAttributes defaultGroupAttributesEntity, Extension6 extension6) {
		Extension7 extension7 = extension6.getExtension();
		if (Objects.nonNull(extension7)) {
			defaultGroupAttributesEntity.setExtension7PcrRestampEnable(extension7.getPcrRestampEnable());
			defaultGroupAttributesEntity
					.setExtension7PcrRestampEnableUpdatetype(extension7.getPcrRestampEnableUpdateType());
		}
	}

	/**
	 * Sets user entity for set user Request >>>>>>>
	 * 7d530d19e56d5b2bb441824607187a83a3bcd4b3
	 * 
	 * @param crmAccountId
	 * @param setUserRequest
	 * @param userEntity
	 * @param isCreate
	 * @return UserTemp
	 */
	public static User getUserEntityForSetUser(String crmAccountId, SetUserRequest setUserRequest, User userEntity,
			boolean isCreate) {
		User user = userEntity;
		if (isCreate) {
			user = new User();
			user.setOverrideBandwith(Constants.DEFAULT_BANDWIDTH); // Default
																	// Zero,
																	// in-case
																	// of fresh
																	// user
			user.setQoeControlBandwith(Constants.DEFAULT_BANDWIDTH); // Default
																		// Zero,
																		// in-case
																		// of
																		// fresh
																		// user

			user.setRetEnable(Constants.DEFAULT_RETRCC_ENABLE); // Default false
			user.setRccEnable(Constants.DEFAULT_RETRCC_ENABLE); // Default false
			user.setNetworkBufferSize(Constants.DEFAULT_NETWORK_BUFFER_SIZE); // Default
																				// 0
			user.setNatBindingRefreshInterval(Constants.DEFAULT_NAT_BINDING_REF_INTERVAL); // Default
																							// 30

		}
		user.setCrmAccountId(crmAccountId);
		user.setBandwithProfile(setUserRequest.getBandwidthProfile());
		user.setOverrideBandwith(setUserRequest.getOverrideBandwidth());
		user.setQoeControlBandwith(setUserRequest.getQoeControlBandwidth());
		user.setUserName(setUserRequest.getUserName());
		user.setMaxAllowedIptvDevices(setUserRequest.getMaxAllowedIPTVDevices());
		user.setUserLimits(getUserLimits(setUserRequest, isCreate ? null : userEntity, isCreate));
		user.setRetEnable(setUserRequest.getRetEnable());
		user.setRccEnable(setUserRequest.getRccEnable());
		user.setNetworkBufferSize(setUserRequest.getNetworkBufferSize());
		user.setNatBindingRefreshInterval(setUserRequest.getNatBindingRefreshInterval());
		user.setLastUpdatedDatetime(System.currentTimeMillis());

		return user;
	}

	/**
	 * This method creates a List of UserLimits.
	 * 
	 * @param setUserRequest
	 * @param user
	 * @param isCreate
	 * @return List<UserLimits>
	 */
	private static List<UserLimits> getUserLimits(SetUserRequest setUserRequest, User user, boolean isCreate) {
		List<UserLimits> serLimitList = null;
		if (!CollectionUtils.isEmpty(setUserRequest.getLimitsPerContentQuality())) {
			serLimitList = new ArrayList<>();
			String userName = StringUtils.isBlank(setUserRequest.getUserName()) ? user.getUserName()
					: setUserRequest.getUserName();
			for (LimitsPerContentQuality limitsPerContentQuality : setUserRequest.getLimitsPerContentQuality()) {
				String contentQuality = limitsPerContentQuality.getContentQuality();
				if (StringUtils.isBlank(contentQuality)) {
					continue;
				}
				UserLimits userLimit = new UserLimits();
				userLimit.setUserName(userName);
				userLimit.setContentQuality(contentQuality);
				userLimit.setMaxDevices(limitsPerContentQuality.getMaxDevices());
				userLimit.setMaxStreams(limitsPerContentQuality.getMaxStreams());
				serLimitList.add(userLimit);
			}
		} else if (!isCreate && StringUtils.isNotBlank(setUserRequest.getUserName())
				&& !CollectionUtils.isEmpty(user.getUserLimits())) {
			serLimitList = new ArrayList<>();
			for (UserLimits userLimit : user.getUserLimits()) {
				userLimit.setUserName(setUserRequest.getUserName());
				serLimitList.add(userLimit);
			}
		}
		return serLimitList;
	}
	
	/**
	 * @param groupAttributesEntity
	 * @param veqRequest
	 * @return
	 */
	public static GroupAttributes buildGroupAttributeObject(GroupAttributesDto groupAttributesRequest) {
		GroupAttributes groupAttributesEntity = new GroupAttributes();
		groupAttributesEntity.setVqecEnable(groupAttributesRequest.getVqecEnable());
		groupAttributesEntity.setVqecEnableUpdatetype(groupAttributesRequest.getVqecEnableUpdateType());
		groupAttributesEntity.setJitterBuffSize(groupAttributesRequest.getJitterBuffSize());
		groupAttributesEntity.setJitterBuffSizeUpdatetype(groupAttributesRequest.getJitterBuffSizeUpdateType()); 
		groupAttributesEntity.setRepairTriggerPointAbs(groupAttributesRequest.getRepairTriggerPointAbs()); 
		groupAttributesEntity.setRepairTriggerPointAbsUpdatetype(groupAttributesRequest.getRepairTriggerPointAbsUpdateType());
		groupAttributesEntity.setPakpoolSize(groupAttributesRequest.getPakpoolSize()); 
		groupAttributesEntity.setPakpoolSizeUpdatetype(groupAttributesRequest.getPakpoolSizeUpdateType());
		groupAttributesEntity.setSoRcvbuf(groupAttributesRequest.getSoRcvbuf());
		groupAttributesEntity.setSoRcvbufUpdatetype(groupAttributesRequest.getSoRcvbufUpdateType());
		if (groupAttributesRequest.getSigMode() != null) {
			groupAttributesEntity.setSigMode(groupAttributesRequest.getSigMode().value()); 
		}																						
		groupAttributesEntity.setSigModeUpdatetype(groupAttributesRequest.getSigModeUpdateType());
		groupAttributesEntity.setNatBindingRefreshInterval(groupAttributesRequest.getNatBindingRefreshInterval()); 
		groupAttributesEntity.setNatBindingRefreshIntervalUpdatetype(groupAttributesRequest.getNatBindingRefreshIntervalUpdateType());
		groupAttributesEntity.setMaxPaksize(groupAttributesRequest.getMaxPaksize()); 
		groupAttributesEntity.setMaxPaksizeUpdatetype(groupAttributesRequest.getMaxPaksizeUpdateType());
		groupAttributesEntity.setLibcliTelnetPort(groupAttributesRequest.getLibcliTelnetPort()); 
		groupAttributesEntity.setLibcliTelnetPortUpdatetype(groupAttributesRequest.getLibcliTelnetPortUpdateType());
		groupAttributesEntity.setOutputPakqLimit(groupAttributesRequest.getOutputPakqLimit()); 
		groupAttributesEntity.setOutputPakqLimitUpdatetype(groupAttributesRequest.getOutputPakqLimitUpdateType());
		groupAttributesEntity.setUpdateWindow(groupAttributesRequest.getUpdateWindow()); 
		groupAttributesEntity.setUpdateWindowUpdatetype(groupAttributesRequest.getUpdateWindowUpdateType());
		groupAttributesEntity.setUpdateIntervalMax(groupAttributesRequest.getUpdateIntervalMax()); 
		groupAttributesEntity.setUpdateIntervalMaxUpdatetype(groupAttributesRequest.getUpdateIntervalMaxUpdateType());
		groupAttributesEntity.setErrorRepairEnable(groupAttributesRequest.getErrorRepairEnable());
		groupAttributesEntity.setErrorRepairEnableUpdatetype(groupAttributesRequest.getErrorRepairEnableUpdateType());
		groupAttributesEntity.setErrorRepairPolicerEnable(groupAttributesRequest.getErrorRepairPolicerEnable());
		groupAttributesEntity.setErrorRepairPolicerEnableUpdatetype(groupAttributesRequest.getErrorRepairPolicerEnableUpdateType());
		groupAttributesEntity.setErrorRepairPolicerRate(groupAttributesRequest.getErrorRepairPolicerRate()); 
		groupAttributesEntity.setErrorRepairPolicerRateUpdatetype(groupAttributesRequest.getErrorRepairPolicerRateUpdateType());
		groupAttributesEntity.setErrorRepairPolicerBurst(groupAttributesRequest.getErrorRepairPolicerBurst()); 
		groupAttributesEntity.setErrorRepairPolicerBurstUpdatetype(groupAttributesRequest.getErrorRepairPolicerBurstUpdateType());
		groupAttributesEntity.setFecEnable(groupAttributesRequest.getFecEnable());
		groupAttributesEntity.setFecEnableUpdatetype(groupAttributesRequest.getFecEnableUpdateType());
		groupAttributesEntity.setRccEnable(groupAttributesRequest.getRccEnable());
		groupAttributesEntity.setRccEnableUpdatetype(groupAttributesRequest.getRccEnableUpdateType());
		groupAttributesEntity.setRccStartTimeout(groupAttributesRequest.getRccStartTimeout()); 
		groupAttributesEntity.setRccStartTimeoutUpdatetype(groupAttributesRequest.getRccStartTimeoutUpdateType());
		groupAttributesEntity.setReorderDelayAbs(groupAttributesRequest.getReorderDelayAbs()); 
		groupAttributesEntity.setReorderDelayAbsUpdatetype(groupAttributesRequest.getReorderDelayAbsUpdateType());
		groupAttributesEntity.setCliIfname(groupAttributesRequest.getCliIfname());
		groupAttributesEntity.setCliIfnameUpdatetype(groupAttributesRequest.getCliIfnameUpdateType());
		groupAttributesEntity.setRtcpDscpValue(groupAttributesRequest.getRtcpDscpValue());
		groupAttributesEntity.setRtcpDscpValueUpdatetype(groupAttributesRequest.getRtcpDscpValueUpdateType());
		groupAttributesEntity.setFastfillEnable(groupAttributesRequest.getFastfillEnable());
		groupAttributesEntity.setFastfillEnableUpdatetype(groupAttributesRequest.getFastfillEnableUpdateType());
		
		//set extension data from request.
		buildExtensionObject(groupAttributesEntity, groupAttributesRequest);
		return groupAttributesEntity;
	}

	/**
	 * @param veqRequest
	 * @param groupAttributesEntity
	 * @return
	 */
	private static void buildExtensionObject(GroupAttributes groupAttributesEntity, GroupAttributesDto groupAttributesRequest) {
		Extension extension = groupAttributesRequest.getExtension();
		if (Objects.nonNull(extension)) {
			groupAttributesEntity.setExtensionMaxReceiveBandwidthSD(extension.getMaxReceiveBandwidthSd()); 
			groupAttributesEntity.setExtensionMaxReceiveBandwidthSDUpdatetype(extension.getMaxReceiveBandwidthSdUpdateType());
			groupAttributesEntity.setExtensionMaxReceiveBandwidthHd(extension.getMaxReceiveBandwidthHd()); 
			groupAttributesEntity.setExtensionMaxReceiveBandwidthHdUpdatetype(extension.getMaxReceiveBandwidthHdUpdateType());
			groupAttributesEntity.setExtensionMinHdStreamBitrate(extension.getMinHdStreamBitrate()); 
			groupAttributesEntity.setExtensionMinHdStreamBitrateUpdatetype(extension.getMinHdStreamBitrateUpdateType());
			groupAttributesEntity.setExtensionMaxFastfill(extension.getMaxFastfill()); 
			groupAttributesEntity.setMaxFastfillUpdatetype(extension.getMaxFastfillUpdateType());
			groupAttributesEntity.setExtensionAppDelay(extension.getAppDelay()); 
			groupAttributesEntity.setExtensionAppDelayUpdatetype(extension.getAppDelayUpdateType());
			groupAttributesEntity.setExtensionSrcipFilterEnable(extension.getSrcIpFilterEnable());
			groupAttributesEntity.setExtensionSrcipFilterEnableUpdatetype(extension.getSrcIpFilterEnableUpdateType());
			buildExtension2Object(groupAttributesEntity, extension);
		}
		
	}

	/**
	 * @param groupAttributesEntity
	 * @param extension
	 * @return
	 */
	private static void buildExtension2Object(GroupAttributes groupAttributesEntity, Extension extension) {
		Extension2 extension2 = extension.getExtension();
		if (Objects.nonNull(extension2)) {
			groupAttributesEntity.setExtension2MaxReceiveBandwidthSDRcc(extension2.getMaxReceiveBandwidthSdRcc());
			groupAttributesEntity.setExtension2MaxReceiveBandwidthSDRccUpdatetype(extension2.getMaxReceiveBandwidthSdRccUpdateType());
			groupAttributesEntity.setExtension2MaxReceiveBandwidthHDRcc(extension2.getMaxReceiveBandwidthHdRcc());
			groupAttributesEntity.setExtension2MaxReceiveBandwidthHDRccUpdatetype(extension2.getMaxReceiveBandwidthHdRccUpdateType());
			groupAttributesEntity.setExtension2QoeEnable(extension2.getQoeEnable());
			groupAttributesEntity.setExtension2QoeEnableUpdatetype(extension2.getQoeEnableUpdateType());
			buildExtension3Object(groupAttributesEntity, extension2);
		}
	}

	/**
	 * @param groupAttributesEntity
	 * @param extension2
	 * @return
	 */
	private static void buildExtension3Object(GroupAttributes groupAttributesEntity, Extension2 extension2) {
		Extension3 extension3 = extension2.getExtension();
		if (Objects.nonNull(extension3)) {
			groupAttributesEntity.setExtension3RccExtraIgmpIp(extension3.getRccExtraIgmpIp());
			groupAttributesEntity.setExtension3RccExtraIgmpIpUpdatetype(extension3.getRccExtraIgmpIpUpdateType());
			buildExtension4Object(groupAttributesEntity, extension3);
		}

	}

	/**
	 * @param groupAttributesEntity
	 * @param extension3
	 * @return
	 */
	private static void buildExtension4Object(GroupAttributes groupAttributesEntity, Extension3 extension3) {
		Extension4 extension4 = extension3.getExtension();
		if (Objects.nonNull(extension4)) {
			groupAttributesEntity.setExtension4LogLevel(extension4.getLogLevel());
			groupAttributesEntity.setExtension4LogLevelUpdatetype(extension4.getLogLevelUpdateType());
			buildExtension5Object(groupAttributesEntity, extension4);
		}
	}

	/**
	 * @param groupAttributesEntity
	 * @param extension4
	 * @return
	 */
	private static void buildExtension5Object(GroupAttributes groupAttributesEntity, Extension4 extension4) {
		Extension5 extension5 = extension4.getExtension();
		if (Objects.nonNull(extension5)) {
			groupAttributesEntity.setExtension5ErrorRepairSmartRequestEnable(extension5.getErrorRepairSmartRequestEnable());
			groupAttributesEntity.setExtension5ErrorRepairSmartRequestEnableUpdatetype(extension5.getErrorRepairRepeatRequestEnableUpdateType());
			groupAttributesEntity.setExtension5ErrorRepairRepeatRequestEnable(extension5.getErrorRepairRepeatRequestEnable());
			groupAttributesEntity.setExtension5ErrorRepairRepeatRequestEnableUpdatetype(extension5.getErrorRepairRepeatRequestEnableUpdateType());
			groupAttributesEntity.setExtension5RepairMinRoundTripTimeAbs(extension5.getRepairMinRoundTripTimeAbs());
			groupAttributesEntity.setExtension5RepairMinRoundTripTimeAbsUpdatetype(extension5.getRepairMinRoundTripTimeAbsUpdateType());
			buildExtension6Object(groupAttributesEntity, extension5);
		}
	}

	/**
	 * @param groupAttributesEntity
	 * @param extension5
	 * @return
	 */
	private static void buildExtension6Object(GroupAttributes groupAttributesEntity, Extension5 extension5) {
		Extension6 extension6 = extension5.getExtension();
		if (Objects.nonNull(extension6)) {
			groupAttributesEntity.setExtension6ViewershipEnable(extension6.getViewershipEnable());
			groupAttributesEntity.setExtension6ViewershipEnableUpdatetype(extension6.getViewershipEnableUpdateType());
			buildExtension7Object(groupAttributesEntity, extension6);
		}
	}

	/**
	 * @param groupAttributesEntity
	 * @param extension6
	 */
	private static void buildExtension7Object(GroupAttributes groupAttributesEntity, Extension6 extension6) {
		Extension7 extension7 = extension6.getExtension();
		if (Objects.nonNull(extension7)) {
			groupAttributesEntity.setExtension7PcrRestampEnable(extension7.getPcrRestampEnable());
			groupAttributesEntity.setExtension7PcrRestampEnableUpdatetype(extension7.getPcrRestampEnableUpdateType());
		}
	}
	
	/**
	 * This method create vqe group condition entity object from request object
	 * 
	 * @param createOverrideVQEGroupsConditionsRequest
	 * @return
	 */
	public static OverrideVQEGroupsCondition getVQEGroupConditionEntity(
			CreateOverrideVQEGroupsConditionsRequest createOverrideVQEGroupsConditionsRequest) {
		OverrideVQEGroupsCondition groupsCondition = new OverrideVQEGroupsCondition();
		groupsCondition.setRccEnable(createOverrideVQEGroupsConditionsRequest.getRccEnable());
		groupsCondition.setRetEnable(createOverrideVQEGroupsConditionsRequest.getRetEnable());
		groupsCondition.setGroupId(createOverrideVQEGroupsConditionsRequest.getGroupId().longValue());
		groupsCondition.setMacAddress(createOverrideVQEGroupsConditionsRequest.getMacAddress());
		if (createOverrideVQEGroupsConditionsRequest.getNetworkBufferSize() != null)
			groupsCondition
					.setNetworkBufferSize(createOverrideVQEGroupsConditionsRequest.getNetworkBufferSize().longValue());
		groupsCondition.setNumSubIptvDevices(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVDevices());
		groupsCondition.setNumSubIptvQoeHDStbs(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVQoeHDSTBs());
		groupsCondition.setNumSubIptvQoeSDStbs(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVQoeSDSTBs());
		groupsCondition.setNumSubIptvQoeStbs(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVQoeSTBs());
		groupsCondition.setNumSubIptvVqeHDStbs(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVVqeHDSTBs());
		groupsCondition.setNumSubIptvVqeSDStbs(createOverrideVQEGroupsConditionsRequest.getNumSubIPTVVqeSDSTBs());
		if (createOverrideVQEGroupsConditionsRequest.getQoeControlBandwidth() != null)
			groupsCondition.setQoeControlBandwidth(
					createOverrideVQEGroupsConditionsRequest.getQoeControlBandwidth().longValue());
		groupsCondition.setVqeQuality(createOverrideVQEGroupsConditionsRequest.getVqeQuality());
		return groupsCondition;

	}

	/**
	 * This method creates list of vqe group condition dtos using list of entity
	 * objects
	 * 
	 * @param conditionDTOs
	 * @param groupsConditionEntities
	 * @return
	 */
	public static List<GetVQEGroupsConditionDTO> getVQEGroupConsitionDTOFromEntity(
			List<GetVQEGroupsConditionDTO> conditionDTOs, List<OverrideVQEGroupsCondition> groupsConditionEntities) {
		for (OverrideVQEGroupsCondition groupsConditionEntity : groupsConditionEntities) {
			GetVQEGroupsConditionDTO conditionDTO = new GetVQEGroupsConditionDTO();
			conditionDTO.setConditionId(groupsConditionEntity.getConditionId().intValue());
			conditionDTO.setGroupId(groupsConditionEntity.getGroupId().intValue());
			conditionDTO.setMacAddress(groupsConditionEntity.getMacAddress());
			if (groupsConditionEntity.getNetworkBufferSize() != null)
				conditionDTO.setNetworkBufferSize(groupsConditionEntity.getNetworkBufferSize().intValue());
			conditionDTO.setNumSubIPTVDevices(groupsConditionEntity.getNumSubIptvDevices());
			conditionDTO.setNumSubIPTVQoeHDSTBs(groupsConditionEntity.getNumSubIptvQoeHDStbs());
			conditionDTO.setNumSubIPTVQoeSDSTBs(groupsConditionEntity.getNumSubIptvQoeSDStbs());
			conditionDTO.setNumSubIPTVQoeSTBs(groupsConditionEntity.getNumSubIptvQoeStbs());
			conditionDTO.setNumSubIPTVVqeHDSTBs(groupsConditionEntity.getNumSubIptvVqeHDStbs());
			conditionDTO.setNumSubIPTVVqeSDSTBs(groupsConditionEntity.getNumSubIptvVqeSDStbs());
			if (groupsConditionEntity.getQoeControlBandwidth() != null)
				conditionDTO.setQoeControlBandwidth(groupsConditionEntity.getQoeControlBandwidth().intValue());
			conditionDTO.setRccEnable(groupsConditionEntity.getRccEnable());
			conditionDTO.setRetEnable(groupsConditionEntity.getRetEnable());
			conditionDTO.setVqeQuality(groupsConditionEntity.getVqeQuality());
			conditionDTOs.add(conditionDTO);
		}
		return conditionDTOs;

	}
}
