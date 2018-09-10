package com.accenture.avs.device.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.avs.device.entity.Device;
import com.accenture.avs.device.entity.Model;
import com.accenture.avs.device.entity.User;
import com.accenture.avs.device.entity.UserLimits;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.exception.ErrorCode;
import com.accenture.avs.device.json.object.devicemanager.DeviceRegistration;
import com.accenture.avs.device.json.object.devicemanager.LimitsPerContentQuality;
import com.accenture.avs.device.json.object.devicemanager.SetUserRequest;
import com.accenture.avs.device.repository.DeviceRepository;
import com.accenture.avs.device.repository.ModelRepository;
import com.accenture.avs.device.repository.UserRepository;
import com.accenture.avs.device.util.validation.JsonRequestValidator;
import com.accenture.avs.device.util.validation.JsonSchema;

/**
 * This class contains methods to validate Device Manager business logic.
 * 
 * @author Singh.Saurabh
 *
 */
@Component
public class DeviceManagerValidator {

	/** jsonRequestValidator */
	@Autowired
	private JsonRequestValidator jsonRequestValidator;

	/** deviceRepository */
	@Autowired
	private DeviceRepository deviceRepository;

	/** modelRepository */
	@Autowired
	private ModelRepository modelRepository;
	
	/** userRepository */
	@Autowired
	private UserRepository userRepository;

	/**
	 * This method validates the uniqueness of the Device Triplet(serial number,
	 * model and vendor). Throws error if not unique.
	 * 
	 * @param serialNumber
	 * @param modelName
	 * @param vendor
	 * @param deviceId
	 * 
	 */
	public void validateDeviceTripletUniqueness(String serialNumber, String modelName, String vendor, String deviceId) {
		long count = deviceRepository.findCountByTripletAndDeviceIdNotLike(serialNumber, modelName, vendor, deviceId);
		if (count > 0) {
			throw new DeviceManagerException(ErrorCode.TRIPLET_MUST_BE_UNIQUE,
					"Triplet serialNumber, model and vendor shall be unique");
		}
	}

	/**
	 * This method validates the uniqueness of the provided DRM ID. Throws error if
	 * not unique.
	 * 
	 * @param drmId
	 * @param deviceId
	 */
	public void validateDrmIdUniqueness(String drmId, String deviceId) {
		long count = deviceRepository.countByDrmIdAndDeviceIdNotLike(drmId, deviceId);
		if (count > 0) {
			throw new DeviceManagerException(ErrorCode.DRM_ID_MUST_BE_UNIQUE, "DRM Id must be unique");
		}
	}

	/**
	 * This method validates the uniqueness of the provided device name for the
	 * given user and excluding same device. Throws error if not unique.
	 * 
	 * @param deviceName
	 * @param assignedToUserName
	 * @param deviceId
	 * 
	 */
	public void validateDeviceNameUniqueness(String deviceName, String assignedToUserName, String deviceId) {
		long count = deviceRepository.countByDeviceNameAndAssignedToUserNameAndDeviceIdNotLike(deviceName,
				assignedToUserName, deviceId);
		if (count > 0) {
			throw new DeviceManagerException(ErrorCode.DUPLICATE_DEVICE_NAME, "Duplicate device name");
		}
	}

	/**
	 * database or not. Throws error based on operation.
	 * 
	 * @param deviceId
	 * 
	 */
	public void validateDeviceExistence(String deviceId) {
		long count = deviceRepository.countByDeviceId(deviceId);
		if (count > 0) {
			throw new DeviceManagerException(ErrorCode.DEVICE_ALREADY_EXISTS, "Device Already Exists");
		}
	}

	/**
	 * This method validates if model status can be updated to inactive if its
	 * assigned to any device
	 * 
	 * @param modelEntity
	 */
	public void validateModelStatusUpdate(Model modelEntity) {
		if (modelEntity.getStatus()) {
			long count = deviceRepository.findCountByModel(modelEntity.getModelName());
			if (count > 0) {
				throw new DeviceManagerException(ErrorCode.DEVICE_MODEL_UPDATE_NOT_ALLOWED,
						"Device model can not be updated to inactive state if assigned to any device");
			}
		}
	}

	/**
	 * This method validates if max iptv device limit is reached for the user or
	 * not.
	 * 
	 * @param deviceRegistration
	 * @param deviceEntity
	 * @param isNewDevice
	 * 
	 */
	public void validateMaxIptvDeviceLimit(DeviceRegistration deviceRegistration, Device deviceEntity,
			boolean isNewDevice) {
		if (isNewDevice) {
			validateMaxIptvDeviceLimitForNewDevice(deviceRegistration.getPlatform(), deviceRegistration.getUserName());
		} else if (deviceEntity.getAssignmentStatus().equals(Constants.AssignmentStatus.UNASSIGNED)) {
			validateMaxIptvDeviceLimitForUnassignedDevice(deviceRegistration.getPlatform(), deviceEntity.getPlatform(),
					deviceRegistration.getUserName());
		} else {
			validateMaxIptvDeviceLimitForAssignedDevice(deviceRegistration.getPlatform(), deviceEntity.getPlatform(),
					deviceRegistration.getUserName());
		}
	}

	/**
	 * This method validates if max iptv device limit is reached for the user when
	 * new device is registered.
	 * 
	 * @param requestPlatform
	 * @param userName
	 * @param maxDeviceLimit
	 */
	private void validateMaxIptvDeviceLimitForNewDevice(String requestPlatform, String userName) {
		if (DeviceManagerUtil.checkNullObject(requestPlatform) || !requestPlatform.equals(Constants.PlatformType.OTT)) {
			validateMaxIptvDeviceLimit(userName);
		}
	}
	
	/**
	 * This method validates if max iptv device limit is reached for the user when
	 * existing unassigned device is registered.
	 * 
	 * @param requestPlatform
	 * @param entityPlatform
	 * @param userName
	 * @param maxDeviceLimit
	 */
	private void validateMaxIptvDeviceLimitForUnassignedDevice(String requestPlatform, String entityPlatform,
			String userName) {
		if (DeviceManagerUtil.checkNullObject(requestPlatform)) {
			if (entityPlatform.equals(Constants.PlatformType.IPTV)) {
				validateMaxIptvDeviceLimit(userName);
			}
		} else if (!requestPlatform.equals(Constants.PlatformType.OTT)) {
			validateMaxIptvDeviceLimit(userName);
		}
	}
	
	/**
	 * This method validates if max iptv device limit is reached for the user when
	 * already assigned device is registered with updated platform.
	 * 
	 * @param requestPlatform
	 * @param entityPlatform
	 * @param userName
	 * @param maxDeviceLimit
	 */
	private void validateMaxIptvDeviceLimitForAssignedDevice(String requestPlatform, String entityPlatform,
			String userName) {
		if (!DeviceManagerUtil.checkNullObject(requestPlatform) && !requestPlatform.equals(Constants.PlatformType.OTT)
				&& entityPlatform.equals(Constants.PlatformType.OTT)) {

			validateMaxIptvDeviceLimit(userName);
		}
	}

	/**
	 * This method validates if max iptv device limit is reached for the user or
	 * not.
	 * 
	 * @param userName
	 * @param deviceLimit
	 * 
	 */
	private void validateMaxIptvDeviceLimit(String userName) {
		long totalIptvDevices = deviceRepository.countByAssignedToUserNameAndPlatform(userName,
				Constants.PlatformType.IPTV);
		User user = userRepository.findByUserName(userName);
		Long maxAllowedIptvDevices = 0L;

		if(Objects.isNull(user)){
			throw new DeviceManagerException(ErrorCode.USER_DOES_NOT_EXIST,
					"User does not exist");
		}
		
		maxAllowedIptvDevices = user.getMaxAllowedIptvDevices();
		if (totalIptvDevices >= maxAllowedIptvDevices) {
			throw new DeviceManagerException(ErrorCode.MAX_IPTV_DEVICE_LIMIT_REACHED,
					"Max limit for IPTV devices has reached");
		}
	}
	
	/**
	 * This method validates missing parameter while creating user
	 * @param request
	 */
	public void validateMissingParameterInSetUserRequest(SetUserRequest request) {
		if (StringUtils.isBlank(request.getUserName())) {
			throw new DeviceManagerException(ErrorCode.MISSING_PARAMETER, new Object[] {"userName"});
		}
		
		if (StringUtils.isBlank(request.getBandwidthProfile())) {
			throw new DeviceManagerException(ErrorCode.MISSING_PARAMETER, new Object[] {"bandwidthProfile"});
		}
		
		if (Objects.isNull(request.getMaxAllowedIPTVDevices())) {
			throw new DeviceManagerException(ErrorCode.MISSING_PARAMETER, new Object[] {"maxAllowedIPTVDevices"});
		}
	}
	
	/**
	 * This method validates missing parameter while set user
	 * @param paramValue 
	 */
	public void validateEmptyParameterValue(String paramValue) {
		if (Objects.nonNull(paramValue) && StringUtils.isBlank(paramValue)) {				
			throw new DeviceManagerException(ErrorCode.JSON_PARSING_FAILED);
		}
	}
	
	/**
	 * This method check if any changes in user object then return true otherwise false
	 * @param request
	 * @param userEntity
	 */
	public boolean checkProfileCalculationRequired(SetUserRequest request, User userEntity) {
		boolean isValueChanged = false;
		
		if (StringUtils.isNotBlank(request.getBandwidthProfile()) && !request.getBandwidthProfile().equals(userEntity.getBandwithProfile())) {
			isValueChanged = true;
		} else if (Objects.nonNull(request.getOverrideBandwidth()) && !request.getOverrideBandwidth().equals(userEntity.getOverrideBandwith())) {
			isValueChanged = true;
		} else if (Objects.nonNull(request.getQoeControlBandwidth()) && !request.getQoeControlBandwidth().equals(userEntity.getQoeControlBandwith())) {
			isValueChanged = true;
		} else if (Objects.nonNull(request.getMaxAllowedIPTVDevices()) && !request.getMaxAllowedIPTVDevices().equals(userEntity.getMaxAllowedIptvDevices())) {
			isValueChanged = true;
		} 
		
		if (!isValueChanged) {
			isValueChanged = checkChangesInMaxDevicePerQuality(request, userEntity);
		}
		
		return isValueChanged;
	}

	/**
	 * Check changes in MaxDevicePerQuality
	 * @param request
	 * @param userEntity 
	 * @return true/false
	 */
	private boolean checkChangesInMaxDevicePerQuality(SetUserRequest request, User userEntity) {
		boolean isValueChanged = false;
		if (CollectionUtils.isNotEmpty(request.getLimitsPerContentQuality()) && CollectionUtils.isEmpty(userEntity.getUserLimits())) {
			return true;
		}
		Map<String, Long> maxDevicesPerQualityHolder = new HashMap<>();
		if (CollectionUtils.isNotEmpty(userEntity.getUserLimits())) { 
			for (UserLimits userLimit : userEntity.getUserLimits()) {
				maxDevicesPerQualityHolder.put(new StringBuilder(userEntity.getCrmAccountId()).append(userLimit.getContentQuality()).toString(), userLimit.getMaxDevices());
			}		
			if (CollectionUtils.isNotEmpty(request.getLimitsPerContentQuality())) {
				for (LimitsPerContentQuality requestedUserLimit : request.getLimitsPerContentQuality()) {
					String requestMaxDevicePerQuality = new StringBuilder(userEntity.getCrmAccountId()).append(requestedUserLimit.getContentQuality()).toString();
					Long maxDevice = maxDevicesPerQualityHolder.get(requestMaxDevicePerQuality);
					if (!requestedUserLimit.getMaxDevices().equals(maxDevice)) {
						isValueChanged = true;
						break;
					}					
				}
			}
		}
		return isValueChanged;
	}
	
	
	/**
	 * This method validates model-name is unique. Throws error if found.
	 * 
	 * @param modelName
	 * @param vendor
	 */
	public void validateModelUniqueness(String modelName, String vendor) {
		if (StringUtils.isEmpty(modelName) || StringUtils.isEmpty(vendor)) {
			throw new DeviceManagerException(ErrorCode.INVALID_MODEL, "Param Missing: modelName/vendor");
		}
		long count = modelRepository.countByModelNameAndVendor(modelName, vendor);
		if (count > 0) {
			throw new DeviceManagerException(ErrorCode.DUPLICATE_DEVICE_MODEL);
		}
	}
	

	/**
	 * This method calls validator to validate request parameters
	 * 
	 * @param request
	 * @param jsonFileName
	 */
	public <T> void validateRequestParameters(T request, String jsonFileName) {
		String requestValidationResult = jsonRequestValidator.validate(request, jsonFileName,
				JsonSchema.getSchemaFolderPath());
		if (!requestValidationResult.isEmpty()) {
			throw new DeviceManagerException(ErrorCode.JSON_PARSING_FAILED, requestValidationResult);
		}
	}

	/**
	 * This method validates max device according to assigned device to specific
	 * subscriber.
	 * 
	 * @param userName
	 * @param maxAllowed
	 * @exception DeviceManagerException
	 * 
	 */
	public void validateMaxDeviceBasedOnAssignedDevice(String userName, Long maxAllowed) {
		long assignedDevice = deviceRepository.countByAssignedDevices(userName);
		if (assignedDevice > maxAllowed) {
			throw new DeviceManagerException(ErrorCode.MAX_IPTV_DEVICE_NOT_LESS_ASSINGED_DEVICE);
		}
	}
}
