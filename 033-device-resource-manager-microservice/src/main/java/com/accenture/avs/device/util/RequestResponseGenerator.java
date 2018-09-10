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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.accenture.avs.be.jms.client.dto.TriggerServerMessage;
import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.DeviceAudit;
import com.accenture.avs.device.entity.DeviceProperty;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.ModelAssignedResources;
import com.accenture.avs.device.entity.ModelMaxStreamsAllowedPerQuality;
import com.accenture.avs.device.entity.ModelVtpLink;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.entity.UserLimits;
import com.accenture.avs.device.json.object.devicemanager.DeviceDetails;
import com.accenture.avs.device.json.object.devicemanager.DeviceDetailsResultObject;
import com.accenture.avs.device.json.object.devicemanager.DeviceDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceLogDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceLogsResultObject;
import com.accenture.avs.device.json.object.devicemanager.DeviceModelListDto;
import com.accenture.avs.device.json.object.devicemanager.DeviceModelResultObj;
import com.accenture.avs.device.json.object.devicemanager.DevicePropertyDto;
import com.accenture.avs.device.json.object.devicemanager.DevicePropertyResultObj;
import com.accenture.avs.device.json.object.devicemanager.GenericResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceAuditLogsResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDeviceModelListResponse;
import com.accenture.avs.device.json.object.devicemanager.GetDevicePropertiesResponse;
import com.accenture.avs.device.json.object.devicemanager.GetOverrideVQEGroupsConditionsResponse;
import com.accenture.avs.device.json.object.devicemanager.GetUpdatedUsers;
import com.accenture.avs.device.json.object.devicemanager.GetUserDto;
import com.accenture.avs.device.json.object.devicemanager.GetUserResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVQEGroupsConditionDTO;
import com.accenture.avs.device.json.object.devicemanager.GetVQEParameterUpdateStatusResponse;
import com.accenture.avs.device.json.object.devicemanager.GetVendorListResponse;
import com.accenture.avs.device.json.object.devicemanager.LimitsPerContentQuality;
import com.accenture.avs.device.json.object.devicemanager.MaxStreamsPerQuality;
import com.accenture.avs.device.json.object.devicemanager.Profile;
import com.accenture.avs.device.json.object.devicemanager.ResourceList;
import com.accenture.avs.device.json.object.devicemanager.UpdatedUserDTO;
import com.accenture.avs.device.json.object.devicemanager.VQEParameterUpdateStatusDto;
import com.accenture.avs.device.json.object.devicemanager.VideoTypeProfile;
import com.accenture.avs.device.json.object.resourcemanager.AssignedDevice;
import com.accenture.avs.device.json.object.resourcemanager.GetProfileReq;
import com.accenture.avs.device.json.object.resourcemanager.Subscriber;

/**
 * This class generates Request and Response objects.
 * 
 * @author singh.saurabh
 *
 */
public class RequestResponseGenerator {

	/**
	 * This method generates a GetDeviceProfiles Request
	 * 
	 * @param sub
	 * @param assignedDevicesList
	 * @return
	 */
	public static GetProfileReq getDeviceProfileRequest(String assignedToUserName, List<Device> assignedDevicesList) {

		// Devices assigned to the Subscriber
		List<AssignedDevice> assignedDevices = getAssignedResourceList(assignedDevicesList);

		Subscriber subscriber = new Subscriber();
		subscriber.setAssignedDevices(assignedDevices);

		GetProfileReq getProfileReq = new GetProfileReq();
		getProfileReq.setSubscriber(subscriber);

		return getProfileReq;
	}

	/**
	 * This method returns GetDeviceModelListResponse corresponding to the
	 * provided Model Entity list.
	 * 
	 * @param modelEntityList
	 * @param resultCode
	 * @param resultDescription
	 * @param executionTime
	 * @return
	 */
	public static GetDeviceModelListResponse getGetDeviceModelListResponse(List<Model> modelEntityList,
			String resultCode, String resultDescription, Long executionTime) {
		List<DeviceModelListDto> deviceModelDtoList = new ArrayList<>();
		for (Model modelEntity : modelEntityList) {
			deviceModelDtoList.add(getDeviceModelListDto(modelEntity));
		}

		DeviceModelResultObj deviceModelResultObj = new DeviceModelResultObj();
		deviceModelResultObj.setDeviceModelList(deviceModelDtoList);
		deviceModelResultObj.setTotalResults(deviceModelDtoList.size());

		GetDeviceModelListResponse getDeviceModelListResponse = new GetDeviceModelListResponse();
		getDeviceModelListResponse.setResultCode(resultCode);
		getDeviceModelListResponse.setResultDescription(resultDescription);
		getDeviceModelListResponse.setExecutionTime(executionTime.intValue());
		getDeviceModelListResponse.setResultObj(deviceModelResultObj);

		return getDeviceModelListResponse;
	}

	/**
	 * This method returns GetDeviceModelListResponse corresponding to the
	 * provided Model Entity list.
	 * 
	 * @param modelEntityList
	 * @param resultCode
	 * @param resultDescription
	 * @param executionTime
	 * @return
	 */
	public static GetDeviceModelListResponse getDeviceModelList(List<DeviceModelListDto> deviceModelDtoList,
			String resultCode, String resultDescription, Long executionTime) {
		DeviceModelResultObj deviceModelResultObj = new DeviceModelResultObj();
		deviceModelResultObj.setDeviceModelList(deviceModelDtoList);
		deviceModelResultObj.setTotalResults(deviceModelDtoList.size());

		GetDeviceModelListResponse getDeviceModelListResponse = new GetDeviceModelListResponse();
		getDeviceModelListResponse.setResultCode(resultCode);
		getDeviceModelListResponse.setResultDescription(resultDescription);
		getDeviceModelListResponse.setExecutionTime(executionTime.intValue());
		getDeviceModelListResponse.setResultObj(deviceModelResultObj);

		return getDeviceModelListResponse;
	}
	
	/**
	 * This method returns DeviceModelListDto corresponding to the provided
	 * Model Entity.
	 * 
	 * @param deviceModelEntity
	 * @return DeviceModelListDto
	 */
	public static DeviceModelListDto getDeviceModelListDto(Model deviceModelEntity) {
		DeviceModelListDto objDeviceModelListDto = new DeviceModelListDto();
		objDeviceModelListDto.setDeviceModel(deviceModelEntity.getModelName());
		objDeviceModelListDto.setVendor(deviceModelEntity.getVendor());
		objDeviceModelListDto.setPlatform(deviceModelEntity.getPlatform());
		objDeviceModelListDto.setOsName(deviceModelEntity.getOsName());
		objDeviceModelListDto.setOsVersion(deviceModelEntity.getOsVersion());
		objDeviceModelListDto.setSwId(deviceModelEntity.getSoftwareVersion());
		objDeviceModelListDto.setQoeCapable(deviceModelEntity.getQoeCapable());
		objDeviceModelListDto.setUiVersion(deviceModelEntity.getUiVersion());
		objDeviceModelListDto.setSdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertLongToInteger(deviceModelEntity.getSdChannelTimeshiftBuffer()));
		objDeviceModelListDto.setHdChannelTimeshiftBuffer(
				DeviceManagerUtil.convertLongToInteger(deviceModelEntity.getHdChannelTimeshiftBuffer()));
		objDeviceModelListDto.setStatus(DeviceManagerUtil.convertBooleanToInt(deviceModelEntity.getStatus()));
		objDeviceModelListDto.setVqeProfile(deviceModelEntity.getVqeProfile());
		objDeviceModelListDto.setDeviceAutoRegistration(deviceModelEntity.getDisableDeviceAutoRegistration());
		objDeviceModelListDto.setLastUpdatedInterface(deviceModelEntity.getLastUpdateInterface());
		objDeviceModelListDto.setLastUpdateUserName(deviceModelEntity.getLastUpdateUsername());
		objDeviceModelListDto
				.setTstvBufferSize(DeviceManagerUtil.getIntegerFromLong(deviceModelEntity.getTstvBufferSize()));
		objDeviceModelListDto.setHdCapable(deviceModelEntity.getHdCapable());
		List<MaxStreamsPerQuality> maxStreamsPerQuality = objDeviceModelListDto.getMaxStreamsPerQuality();
		for (ModelMaxStreamsAllowedPerQuality maxStream : deviceModelEntity.getModelMaxStreamsAllowedPerQuality()) {
			MaxStreamsPerQuality objMaxStreamsPerQuality = new MaxStreamsPerQuality();
			objMaxStreamsPerQuality.setContentQuality(maxStream.getResolutionTypeName());
			objMaxStreamsPerQuality.setStreamLimit(DeviceManagerUtil.convertLongToInteger(maxStream.getMaxStreams()));
			maxStreamsPerQuality.add(objMaxStreamsPerQuality);
		}
		objDeviceModelListDto.setMaxStreamsPerQuality(maxStreamsPerQuality);
		return objDeviceModelListDto;
	}
	
	/**
	 * 
	 * This method returns DeviceModelListDto corresponding to the provided
	 * Model Entity, List<ModelVtpLink> and  List<ModelAssignedResources> .
	 * 
	 * @param deviceModelEntity
	 * @param modelVtpLinks
	 * @param modelAssignedResources
	 * @return
	 */
	public static DeviceModelListDto getDeviceModelDto(Model deviceModelEntity, List<ModelVtpLink> modelVtpLinks, List<ModelAssignedResources> modelAssignedResources) {
		DeviceModelListDto objDeviceModelListDto = getDeviceModelListDto(deviceModelEntity);
		List<VideoTypeProfile> videoTypeProfiles = new ArrayList<>();
		List<ResourceList> resourceLists = new ArrayList<>();
		for (ModelVtpLink modelVtpLink : modelVtpLinks) {
			VideoTypeProfile videoTypeProfile = new VideoTypeProfile();
			videoTypeProfile.setVtpName(modelVtpLink.getId().getVtpName());
			
			videoTypeProfiles.add(videoTypeProfile);
		}
		
		for (ModelAssignedResources assignedResources : modelAssignedResources) {
			ResourceList resourceList = new ResourceList();
			if(StringUtils.isNotBlank(assignedResources.getAmount()))
			resourceList.setAmount(Integer.parseInt(assignedResources.getAmount()));
			resourceList.setName(assignedResources.getId().getResourceName());
			resourceList.setUnit(assignedResources.getUnit());
			resourceLists.add(resourceList);
		}
		objDeviceModelListDto.setVideoTypeProfiles(videoTypeProfiles);
		objDeviceModelListDto.setResourceList(resourceLists);
		return objDeviceModelListDto;
	}

	/**
	 * This method returns assigned resource list
	 * 
	 * @param assignedDevicesList
	 * @return List<AssignedDevice>
	 */
	private static List<AssignedDevice> getAssignedResourceList(List<Device> assignedDevicesList) {
		List<AssignedDevice> assignedDevices = new ArrayList<>();
		for (Device device : assignedDevicesList) {
			AssignedDevice assignedDevice = new AssignedDevice();
			assignedDevice.setEquipmentID(String.valueOf(device.getId()));
			assignedDevice.setHWVersion(device.getModel().getModelName());
			assignedDevice.setTvQualityInterest(device.getTvQualityInterest());
			assignedDevice.setAssigmentDateTime(String.valueOf(device.getDatetimeOfAssignment()));
			assignedDevices.add(assignedDevice);
		}
		return assignedDevices;
	}

	/**
	 * This method generates a TriggerServerMessage Request
	 * 
	 * @param producerDevice
	 * @param targetDevices
	 * @param triggerTypeName
	 * 
	 * @return TriggerServerMessage
	 */
	public static TriggerServerMessage getTriggerServerMessageRequest(String producerDevice, String[] targetDevices,
			String triggerTypeName) {

		TriggerServerMessage triggerServerMessage = new TriggerServerMessage();
		triggerServerMessage.setUserName("");
		if(StringUtils.isNotEmpty(producerDevice)) {
			triggerServerMessage.setProducerDevice(producerDevice);
		}
		triggerServerMessage.setTargetDevices(targetDevices);
		triggerServerMessage.setTriggerType(triggerTypeName);
		triggerServerMessage.setTransactionID(String.valueOf(Instant.now().getEpochSecond()));

	
		return triggerServerMessage;
	}

	/**
	 * This method returns DeviceDetails Object
	 * 
	 * @param deviceEntity
	 * @param executionTime
	 * 
	 * @return
	 */
	public static DeviceDetails getDeviceDetailsResponse(Device deviceEntity, Long executionTime) {
		DeviceDto deviceDto = new DeviceDto();
		deviceDto.setDeviceId(deviceEntity.getDeviceId());
		deviceDto.setDeviceName(deviceEntity.getDeviceName());
		deviceDto.setDeviceType(deviceEntity.getDeviceType());
		deviceDto.setDrmId(deviceEntity.getDrmId());
		if (!DeviceManagerUtil.checkNullObject(deviceEntity.getMaxBandwidthUpdate())) {
			deviceDto.setMaxBandwidthUpdates(deviceEntity.getMaxBandwidthUpdate().intValue());
		}
		if (!DeviceManagerUtil.checkNullObject(deviceEntity.getModel())) {
			deviceDto.setModel(deviceEntity.getModel().getModelName());
			deviceDto.setVendor(deviceEntity.getModel().getVendor());
		}
		deviceDto.setPlatform(deviceEntity.getPlatform());
		deviceDto.setSerialNumber(deviceEntity.getSerialNumber());
		deviceDto.setSoftwareVersion(deviceEntity.getSoftwareVersion());
		deviceDto.setTvQualityInterest(deviceEntity.getTvQualityInterest());
		deviceDto.setUiVersion(deviceEntity.getUiVersion());

		deviceDto.setAssignedProfile(deviceEntity.getDeviceProfile());
		deviceDto.setAssignmentStatus(deviceEntity.getAssignmentStatus());
		deviceDto.setExtIPAddress(deviceEntity.getExternalIpAddress());
		deviceDto.setIpAddress(deviceEntity.getInternalIpAddress());
		deviceDto.setLastUpdateTime(deviceEntity.getLastUpdatedOn().toString());
		deviceDto.setLastUpdateUserName(deviceEntity.getLastUpdatedBy());
		deviceDto.setUserName(deviceEntity.getAssignedToUserName());
		if (Objects.nonNull(deviceEntity.getDeviceProfile()) || Objects.nonNull(deviceEntity.getQoeBandwidth())
				|| Objects.nonNull(deviceEntity.getDeviceProfileBandwidth())) {
			Profile profile = new Profile();
			profile.setName(deviceEntity.getDeviceProfile());
			profile.setAssignedVQEOverheadBW(DeviceManagerUtil.getIntegerFromLong(deviceEntity.getQoeBandwidth()));
			profile.setNetworkBandwidth(DeviceManagerUtil.getIntegerFromLong(deviceEntity.getDeviceProfileBandwidth()));
			deviceDto.setProfile(profile);
		}
		DeviceDetailsResultObject deviceDetailsResultObject = new DeviceDetailsResultObject();
		deviceDetailsResultObject.setDevice(deviceDto);

		DeviceDetails deviceDetails = new DeviceDetails();
		deviceDetails.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		deviceDetails.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		deviceDetails.setExecutionTime(executionTime.intValue());
		deviceDetails.setResultObj(deviceDetailsResultObject);

		return deviceDetails;
	}

	/**
	 * This method generates a Generic Response
	 * 
	 * @param executionTime
	 * @return
	 */
	public static GenericResponse getGenericResponse(String resultCode, String resultDescription, Long executionTime) {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setResultCode(resultCode);
		genericResponse.setResultDescription(resultDescription);
		genericResponse.setExecutionTime(executionTime.intValue());
		return genericResponse;
	}
	
	/**
	 * This method generates a Get User Response
	 * @param userEntity
	 * @param executionTime
	 * @return GetUserResponse
	 */
	public static GetUserResponse getUserResponse(User userEntity, Long executionTime, Long freeBandwidth) {
		GetUserResponse response = new GetUserResponse();
		GetUserDto getUserDto = new GetUserDto();
		getUserDto.setBandwidthProfile(userEntity.getBandwithProfile());
		getUserDto.setMaxAllowedIPTVDevices(userEntity.getMaxAllowedIptvDevices());
		getUserDto.setOverrideBandwidth(userEntity.getOverrideBandwith());
		getUserDto.setQoeControlBandwidth(userEntity.getQoeControlBandwith());
		getUserDto.setTotalAssignedBandwidth(Objects.isNull(userEntity.getTotalAssignedBandwidth()) ? 0 : userEntity.getTotalAssignedBandwidth());
		getUserDto.setFreeBandwidth(Objects.isNull(freeBandwidth) ? 0 : freeBandwidth);
		getUserDto.setRetEnable(userEntity.getRetEnable());
		getUserDto.setRccEnable(userEntity.getRccEnable());
		getUserDto.setNetworkBufferSize(userEntity.getNetworkBufferSize());
		getUserDto.setNatBindingRefreshInterval(userEntity.getNatBindingRefreshInterval());
		getUserDto.setLimitsPerContentQuality(getLimitsPerContentQuality(userEntity.getUserLimits()));
		getUserDto.setLastUpdateTime(userEntity.getLastUpdatedDatetime());
		response.setResultObj(getUserDto);		
		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setExecutionTime(executionTime.intValue());
		return response;
	}
	
	/**
	 * Gets LimitsPerContentQuality
	 * @param userLimitsList
	 * @return List<LimitsPerContentQuality>
	 */
	private static List<LimitsPerContentQuality> getLimitsPerContentQuality(List<UserLimits> userLimitsList){
		List<LimitsPerContentQuality> limitsPerContentQualityList = null;
		if (CollectionUtils.isNotEmpty(userLimitsList)) {
			limitsPerContentQualityList = new ArrayList<>();
			for (UserLimits userLimits : userLimitsList) {
				LimitsPerContentQuality limitsPerContentQuality =  new  LimitsPerContentQuality();
				limitsPerContentQuality.setContentQuality(userLimits.getContentQuality());
				limitsPerContentQuality.setMaxDevices(userLimits.getMaxDevices());
				limitsPerContentQuality.setMaxStreams(userLimits.getMaxStreams());
				limitsPerContentQualityList.add(limitsPerContentQuality);
			}
		}
		return limitsPerContentQualityList;		
	}
	

	/**
	 * This method generates a DeviceProperties Response
	 * 
	 * @param devicePropertyList
	 * @param executionTime
	 * 
	 * @return
	 */
	public static GetDevicePropertiesResponse getDevicePropertiesResponse(List<DeviceProperty> devicePropertyList,
			Long executionTime) {
		GetDevicePropertiesResponse response = new GetDevicePropertiesResponse();
		DevicePropertyResultObj resultObj = new DevicePropertyResultObj();
		List<DevicePropertyDto> devicePropList = resultObj.getDeviceProperties();

		for (DeviceProperty devicePropertyEntity : devicePropertyList) {
			devicePropList.add(generateDevicePropertyDtoObject(devicePropertyEntity));
		}

		if (devicePropList.isEmpty()) {
			response.setResultObj(null);
		} else {
			resultObj.setDeviceProperties(devicePropList);
			response.setResultObj(resultObj);
		}

		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setExecutionTime(executionTime.intValue());
		return response;
	}
	
	/**
	 * @param devicePropertyEntity
	 * @return
	 */
	private static DevicePropertyDto generateDevicePropertyDtoObject(DeviceProperty devicePropertyEntity) {
		DevicePropertyDto devicePropertyDto = new DevicePropertyDto();
		devicePropertyDto.setName(devicePropertyEntity.getId().getPropertyName());
		devicePropertyDto.setValue(devicePropertyEntity.getPropertyValue());
		return devicePropertyDto;
	}

	/**
	 * This method generates a DeviceAuditDTO Response
	 * 
	 * @param GetDeviceAuditLogsResponse
	 * 
	 * @return
	 */
	public static GetDeviceAuditLogsResponse getDeviceAuditLogsResponse(List<DeviceAudit> deviceAuditList) {

		List<DeviceLogDto> devicesLogList = new ArrayList<>();

		for (DeviceAudit deviceAudit : deviceAuditList) {
			DeviceLogDto deviceLog = new DeviceLogDto();
			deviceLog.setDeviceId(deviceAudit.getDeviceId());
			deviceLog.setSerialNumber(deviceAudit.getSerialNumber());
			deviceLog.setIpAddress(deviceAudit.getInternalIpAddress());
			deviceLog.setExtIPAddress(deviceAudit.getExternalIpAddress());
			deviceLog.setDeviceName(deviceAudit.getDeviceName());
			deviceLog.setSoftwareVersion(deviceAudit.getSoftwareVersion());
			if (!DeviceManagerUtil.checkNullObject(deviceAudit.getModel())) {
				deviceLog.setModel(deviceAudit.getModel().getModelName());
				deviceLog.setVendor(deviceAudit.getModel().getVendor());
			}
			deviceLog.setDeviceType(deviceAudit.getDeviceType());
			deviceLog.setPlatform(deviceAudit.getPlatform());
			deviceLog.setUiVersion(deviceAudit.getUiVersion());
			deviceLog.setAssignmentStatus(deviceAudit.getAssignmentStatus());
			deviceLog.setUserName(deviceAudit.getAssignedToUsername());
			if (!DeviceManagerUtil.checkNullObject(deviceAudit.getLastUpdatedDatetime())) {
				deviceLog.setLastUpdateTime(DeviceManagerUtil.getStringFromLong(deviceAudit.getLastUpdatedDatetime()));
			}
			deviceLog.setLastUpdateUserName(deviceAudit.getLastUpdateUsername());
			deviceLog.setMaxBandwidthUpdates(DeviceManagerUtil.getIntegerFromLong(deviceAudit.getMaxBandwidthUpdate()));

			deviceLog.setTvQualityInterest(deviceAudit.getTvQualityInterest());
			deviceLog.setAssignedProfile(deviceAudit.getDeviceProfile());
			deviceLog.setDrmId(deviceAudit.getDrmId());
			deviceLog.setOperation(deviceAudit.getOperation());
			devicesLogList.add(deviceLog);
		}
		DeviceLogsResultObject deviceLogs = new DeviceLogsResultObject();
		deviceLogs.setDeviceLogs(devicesLogList);
		deviceLogs.setTotalResults(devicesLogList.size());

		GetDeviceAuditLogsResponse response = new GetDeviceAuditLogsResponse();
		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setResultObj(deviceLogs);
		return response;
	}
	
	/**
	 * This method returns GetVendorListResponse corresponding to the
	 * provided Vendor list from Model Entity.
	 * 
	 * @param vendorList
	 * @param resultCode
	 * @param resultDescription
	 * @param executionTime
	 * @return GetVendorListResponse
	 */
	public static GetVendorListResponse getVendorListResponse(List<String> vendorList,
			String resultCode, String resultDescription, Long executionTime) {

		GetVendorListResponse getVendorListResponse = new GetVendorListResponse();
		getVendorListResponse.setResultCode(resultCode);
		getVendorListResponse.setResultDescription(resultDescription);
		getVendorListResponse.setExecutionTime(executionTime.intValue());
		
		List<String> resultObjList = new ArrayList<>();
		resultObjList.addAll(vendorList);
		getVendorListResponse.setResultObj(resultObjList);

		return getVendorListResponse;
	}
	
	/**
	 * This method returns GetVQEParameterUpdateStatusResponse corresponding to the
	 * provided resultList.
	 * 
	 * @param resultList
	 * @param queryDelimiter
	 * @param nullReplacerStr
	 * @return
	 */
	public static GetVQEParameterUpdateStatusResponse getVQEParameterUpdateStatusResponse(List<String> resultList,
			String queryDelimiter, String nullReplacerStr) {

		List<VQEParameterUpdateStatusDto> vqeParameterUpdateStatusDtoList = new ArrayList<>();
		for (String resultString : resultList) {
			String[] tokenArray = StringUtils.split(resultString, queryDelimiter);
			VQEParameterUpdateStatusDto vqeParameterUpdateStatusDto = new VQEParameterUpdateStatusDto();
			vqeParameterUpdateStatusDto.setCrmAccountId(tokenArray[0]);
			vqeParameterUpdateStatusDto.setStatus(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
			if (!tokenArray[1].equals(nullReplacerStr)) {
				vqeParameterUpdateStatusDto.setStatus(Constants.Status.FAILURE_RESULT_DESCRIPTION);
				vqeParameterUpdateStatusDto.setErrorMessage(tokenArray[1]);
			}
			vqeParameterUpdateStatusDtoList.add(vqeParameterUpdateStatusDto);
		}

		GetVQEParameterUpdateStatusResponse getVQEParameterUpdateStatusResponse = new GetVQEParameterUpdateStatusResponse();
		getVQEParameterUpdateStatusResponse.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		getVQEParameterUpdateStatusResponse.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		getVQEParameterUpdateStatusResponse.setResultObj(vqeParameterUpdateStatusDtoList);
		return getVQEParameterUpdateStatusResponse;
	}
	
	/**
	 * This method returns GetUpdatedUsers
	 * corresponding to the provided UpdatedUserDTO list.
 	 * @param updatedUserDTOs
	 * @param executionTime
	 * @return
	 */
	public static GetUpdatedUsers getUpdatedUserResponse(List<UpdatedUserDTO> updatedUserDTOs, Long executionTime) {
		GetUpdatedUsers response = new GetUpdatedUsers();
		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setResultObj(updatedUserDTOs);
		response.setExecutionTime(executionTime.intValue());
		return response;
	}

	/**
	 * This method returns GetOverrideVQEGroupsConditionsResponse
	 * corresponding to the provided GetVQEGroupsConditionDTO list.
	 * @param conditionDTOs
	 * @param executionTime
	 * @return
	 */
	public static GetOverrideVQEGroupsConditionsResponse getVQEGroupsConditionsResponse(
			List<GetVQEGroupsConditionDTO> conditionDTOs, Long executionTime) {
		GetOverrideVQEGroupsConditionsResponse response=new GetOverrideVQEGroupsConditionsResponse();
		response.setResultCode(Constants.Status.SUCCESS_RESULT_CODE);
		response.setResultDescription(Constants.Status.SUCCESS_RESULT_DESCRIPTION);
		response.setResultObj(conditionDTOs);
		response.setExecutionTime(executionTime.intValue());
		return response;
	}
	
}
