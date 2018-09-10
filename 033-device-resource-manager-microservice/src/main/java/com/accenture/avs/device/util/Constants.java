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

/**
 * The interface Constants used for define constant
 * 
 * @author muhammad.yunus
 *
 */
public interface Constants {

	String DB_PARAM_DEVICEID = "deviceId";
	String DB_PARAM_STARTINDEX = "startIndex";
	String DB_PARAM_MAXRESULTS = "maxResults";
	String DB_PARAM_SORT_BY= "sortBy";
	String DB_PARAM_SORT_ORDER= "sortOrder";
	String DB_PARAM_HARDWARENAME = "hardwareName";
	String DB_PARAM_SERVICEID = "serviceId";
	String DB_PARAM_STARTDATE = "startDate";
	String DB_PARAM_ENDDATE = "endDate";
	String DB_PARAM_EQUIPMENTID_LIST = "equipmentIdList";
	String DB_PARAM_ACCOUNTNUMBER = "accountNumber";
	String DB_PARAM_SUBSCRIBERID = "subscriberId";
	String DB_PARAM_LASTUPDATE_DATETIME = "lastUpdateDatetime";
	String DB_PARAM_LASTUPDATE_USER_ID = "lastUpdateUserId";
	String DB_PARAM_NAME = "name";
	String DB_PARAM_EQUIPMENTID = "equipmentId";
	String DB_PARAM_RULEID = "ruleId";
	String DB_PARAM_SOFTWAREVERSION = "softwareVersion";
	
	String DB_PARAM_CRMACCOUNTID = "crmAccountId";
	String DB_PARAM_CRMACCOUNTID_LIST = "crmAccountIdList";
	String DB_PARAM_USERNAME = "userName";
	String DB_PARAM_MAX_ALLOWED_IPTV_DEVICES = "maxAllowedIptvDevices";	
	String DB_PARAM_BANDWIDTH_PROFILE = "bandwithProfile";
	String DB_PARAM_OVERRIDE_BANDWIDTH = "overrideBandwith";	
	String DB_PARAM_QOE_CONTROL_BANDWIDTH = "qoeControlBandwith";
	String DB_PARAM_USER_LIMITS = "userLimits";
	String DB_PARAM_RETENABLE =	"retEnable";
	String DB_PARAM_RCCENABLE =	"rccEnable";
	String DB_PARAM_NETWORK_BUFFER_SIZE =	"networkBufferSize";
	String DB_PARAM_NATBINDING_REFRESH_INTERVAL = "natBindingRefreshInterval";
	
	String DB_PARAM_GROUPID = "groupId";
	String DB_PARAM_MACADDRESS = "macAddress";
	String DB_PARAM_NUMSUBIPTVDEVICES = "numSubIptvDevices";
	String DB_PARAM_NUMSUBIPTVQOEHDSTBS = "numSubIptvQoeHDStbs";
	String DB_PARAM_NUMSUBIPTVQOESDSTBS = "numSubIptvQoeSDStbs";
	String DB_PARAM_NUMSUBIPTVQOESTBS = "numSubIptvQoeStbs";
	String DB_PARAM_NUMSUBIPTVVQEHDSTBS = "numSubIptvVqeHDStbs";
	String DB_PARAM_NUMSUBIPTVVQESDSTBS = "numSubIptvVqeSDStbs";
	
	/** DATA_MINIMIZATION */
	String DATA_MINIMIZATION = "dataMinimization";
	
	String ENABLE_LOG_MASKING = "ENABLE_LOG_MASKING";
	
	/** QUERY_PARAM_STARTDATE */
	String QUERY_PARAM_STARTDATE = "startDate";
	/** QUERY_PARAM_ENDDATE */
	String QUERY_PARAM_ENDDATE = "endDate";

	/** IMPLICIT_NAT */
	final String IMPLICIT_NAT = "Implicit NAT";

	/** REQ_PARAM_START_INDEX */
	final String REQ_PARAM_START_INDEX = "startIndex";
	/** REQ_PARAM_MAX_RESULTS */
	final String REQ_PARAM_MAX_RESULTS = "maxResults";
	/** REQ_PARAM_SORT_ORDER */
	String REQ_PARAM_SORT_ORDER = "sortOrder";
	/** REQ_PARAM_SEARCH_BY */
	final String REQ_PARAM_SEARCH_BY = "searchBy";
	/** REQ_PARAM_SEARCH_VALUE */
	final String REQ_PARAM_SEARCH_VALUE = "searchValue";
	/** REQ_PARAM_SEARCH_OPERATION */
	final String REQ_PARAM_SEARCH_OPERATION = "searchOperation";
	/** REQ_PARAM_SORT_BY */
	final String REQ_PARAM_SORT_BY = "sortBy";
	/** REQ_PARAM_PAGE_SIZE */
	final String REQ_PARAM_PAGE_SIZE = "pageSize";
	/** REQ_PARAM_USER_NAME */
	final String REQ_PARAM_USER_NAME = "userName";
	/** REQ_PARAM_LIMIT_DEVICE_FIELDS */
	final String REQ_PARAM_LIMIT_DEVICE_FIELDS = "limitDeviceFields";
	/** REQ_PARAM_SW_VERSIONS */
	final String REQ_PARAM_SW_VERSIONS = "swVersions";
	/** REQ_PARAM_STATUS */
	final String REQ_PARAM_STATUS = "status";
	/** REQ_PARAM_DEVICE_MODEL */
	final String REQ_PARAM_DEVICE_MODEL = "deviceModel";
	/** REQ_PARAM_VENDOR */
	final String REQ_PARAM_VENDOR = "vendor";
	/** PATH_PARAM_CRMACCOUNTIDS */
	final String REQ_PARAM_CRMACCOUNTIDS = "crmAccountIds";

	/** PATH_PARAM_DEVICE_ID */
	final String PATH_PARAM_DEVICE_ID = "deviceId";
	
	/** PATH_PARAM_CRMACCOUNTID */
	String PATH_PARAM_CRMACCOUNTID = "crmAccountId";	
	/** PATH_PARAM_USERNAME */
	String PATH_PARAM_USERNAME = "userName";
	/** PATH_PARAM_DEVICE_MODEL */
	final String PATH_PARAM_DEVICE_MODEL = "deviceModel";
	/** PATH_PARAM_VENDOR */
	final String PATH_PARAM_VENDOR = "vendor";

	/** DEFAULT_LASTUPDATEDUSERNAME */
	final String DEFAULT_LASTUPDATEDUSERNAME = "System";
	/** DEFAULT_LASTINTERFACE */
	final String DEFAULT_LASTINTERFACE = "";	
	/** DEFAULT_BANDWIDTH */
	long DEFAULT_BANDWIDTH = 0;	
	/** DEFAULT_START_INDEX */
	final String DEFAULT_START_INDEX = "0";
	/** DEFAULT_MAX_RESULTS */
	final String DEFAULT_MAX_RESULTS = "50";
	/** DEFAULT_SORT_ORDER */
	String DEFAULT_SORT_ORDER = "ASC";
	/** DEFAULT_SORT_BY_IN_GETDEVICEAUDIT */
	String DEFAULT_SORT_BY_IN_GETDEVICEAUDIT = "deviceId";
	/** DEFAULT_PAGE_SIZE */
	final String DEFAULT_PAGE_SIZE = "100";
	
	/** HWVERSIONS */
	final String HWVERSIONS = "hwVersions";
	
	/** DEFAULT_MASS_CAL_THREADS for Mass Calulation */
	int DEFAULT_MASS_CAL_THREADS = 5;

	/** DEFAULT_MASS_CAL_BATCHSIZE for Mass Calulation */
	int DEFAULT_MASS_CAL_BATCHSIZE = 100;

	/** DEFAULT_MDC_BANDWIDTH_VALUE for MMDDF Server*/
	int DEFAULT_MDC_BANDWIDTH_VALUE = 1000;

	/** MDC_BANDWIDTH_NAME for MMDDF Server */
	String MDC_BANDWIDTH_NAME = "MDCBandwidth";
	
	/** DEFAULT_NETWORK_BUFFER_SIZE */
	final long DEFAULT_NETWORK_BUFFER_SIZE = 0;
	
	/** DEFAULT_NAT_BINDING_REF_INTERVAL */
	final long DEFAULT_NAT_BINDING_REF_INTERVAL = 30;
	
	/** DEFAULT RET RCC ENABLE */
	final boolean DEFAULT_RETRCC_ENABLE = false;

	/** DEVICE_RESOURCE_MS_JMS_MESSAGE_PRIORITY */
	String DEVICE_RESOURCE_MS_JMS_MESSAGE_PRIORITY = "DEVICE_RESOURCE_MS_JMS_MESSAGE_PRIORITY";
	
	/** DEFAULT_USERLOG_CLEANUP_INTERVAL */
	Integer DEFAULT_USERLOG_CLEANUP_INTERVAL = 1;
	
	/** DEFAULT_RCC_OVERRIDE */
	boolean DEFAULT_RCC_OVERRIDE = true;
	
	/** DEFAULT_OVERRIDE_DEFAULT_QOEBANDWIDTH */
	boolean DEFAULT_OVERRIDE_DEFAULT_QOEBANDWIDTH = true;

	interface LoggingParams {

		String USER_AGENT_HEADER_NAME = "User-Agent";

		String TS = "ts";
		String APP_ID = "appId";
		String THREAD = "thread";
		String SID = "SID";
		String TN = "TN";
		String CLIENT_IP = "clientIp";
		/** USER_ID */
		String USER_ID = "userId";
		String API_TYPE = "apiType";
		String API = "api";
		String PLATFORM = "platform";

		String EVENT_TYPE = "eventType";
		String USER_AGENT = "userAgent";
		String EXECUTION_TIME = "executionTime";
	}
	
	interface PaginationParams {
		/** PARAM_START_INDEX */
		String PARAM_START_INDEX = "startIndex";
		/** PARAM_MAX_RESULTS */
		String PARAM_MAX_RESULTS = "maxResults";
		/** PARAM_SORT_ORDER */
		String PARAM_SORT_ORDER = "sortOrder";
		/** PARAM_SORT_BY */
		String PARAM_SORT_BY = "sortBy";
		/** DEFAULT_START_INDEX */
		String DEFAULT_START_INDEX = "0";
		/** DEFAULT_MAX_RESULTS */
		String DEFAULT_MAX_RESULTS = "50";
		/** DEFAULT_SORT_ORDER */
		String DEFAULT_SORT_ORDER = "ASC";
		/** DEFAULT_SORT_BY_DEVICE_ID */
		String DEFAULT_SORT_BY_DEVICE_ID = "deviceId";
		/** DEFAULT_SORT_BY_DEVICE_MODEL */
		String DEFAULT_SORT_BY_DEVICE_MODEL = "deviceModel";
		
	}

	interface HeaderParams {
		/** X_AVS_USER_ID */
		final String X_AVS_USER_ID = "X-avs-userid";
		/** X_AVS_TRANSACTION_ID */
		final String X_AVS_TRANSACTION_ID = "X-avs-transactionId";
		/** X_AVS_SESSION_ID */
		final String X_AVS_SESSION_ID = "X-avs-sessionId";
		/** X_AVS_PLATFORM */
		final String X_AVS_PLATFORM = "X-avs-platform";
		/** X_AVS_CLIENT_IP */
		final String X_AVS_CLIENT_IP = "X-avs-clientIp";
		/** ACID_TRANSACTION */
		final String ACID_TRANSACTION = "acidTransaction";
		/** LAST_UPDATED_INTERFACE */
		final String LAST_UPDATED_INTERFACE = "lastUpdatedInterface";

	}

	public interface AssignmentStatus {
		String ASSIGNED = "Assigned";
		String UNASSIGNED = "Unassigned";
	}

	public interface Status {
		String SUCCESS_RESULT_CODE = "ACN_200";
		String SUCCESS_RESULT_DESCRIPTION = "OK";
		String FAILURE_RESULT_DESCRIPTION = "KO";
	}

	public interface ApiType {
		final String B2B = "B2B";
		final String UPDATEDEVICE = "UPDATE_DEVICE";
	}

	public interface OperationType {
		String OPERATION_CREATE = "Create";
		String OPERATION_UPDATE = "Update";
		String OPERATION_DELETE = "Delete";
	}
	
	public interface DeviceType {
		String STB = "STB";
		String TABLET_ANDROID = "TABLET_ANDROID";
		String SMARTWATCH_ANDROID = "SMARTWATCH_ANDROID";
		String IPHONE_IOS = "IPHONE_IOS";
	}

	public interface PlatformType {
		String IPTV = "IPTV";
		String OTT = "OTT";
	}

	public interface Trigger {
		String T_SUBSCRIBER_INFO = "T_SUBSCRIBER_INFO";
		String T_TRIGGER_INFO = "";
	}

	public interface RequestUrl {
		/** OPERATION_CREATE_UPDATE_DEVICE_URL */
		final String OPERATION_CREATE_UPDATE_DEVICE_URL = "/devices";

		/** OPERATION_REGISTER_DEVICE_URL */
		final String OPERATION_REGISTER_DEVICE_URL = "/devices/registration";

		/** OPERATION_UNASSIGN_DEVICE_URL */
		final String OPERATION_UNASSIGN_DEVICE_URL = "/devices/unassignment";

		/** OPERATION_SET_GET_DEVICE_PROPERTIES_URL */
		final String OPERATION_SET_GET_DEVICE_PROPERTIES_URL = "/devices/{" + PATH_PARAM_DEVICE_ID + "}/properties";

		/** OPERATION_GET_DEVICE_AUDIT_LOGS_URL */
		final String OPERATION_GET_DEVICE_AUDIT_LOGS_URL = "/devices/{" + PATH_PARAM_DEVICE_ID + "}/auditLogs";

		/** OPERATION_GET_USER_DEVICES_URL */
		final String OPERATION_GET_USER_DEVICES_URL = "/users/devices";

		/** OPERATION_REMOVE_DEVICE_URL */
		final String OPERATION_REMOVE_DEVICE_URL = "/devices/{" + PATH_PARAM_DEVICE_ID + "}";

		/** GET_DEVICE_DETAILS_REQUEST_MAPPING */
		final String OPERATION_GET_DEVICE_DETAILS_URL = "/devices/{" + PATH_PARAM_DEVICE_ID + "}";

		/** OPERATION_GET_DEVICE_LIST_URL */
		final String OPERATION_GET_DEVICE_LIST_URL = "/devices";

		/** OPERATION_GET_SW_UPGRADE_COUNT_URL */
		final String OPERATION_GET_SW_UPGRADE_COUNT_URL = "/swUpgradeCount";

		/** OPERATION_CREATE_UPDATE_DEVICE_MODEL_URL */
		final String OPERATION_CREATE_UPDATE_DEVICE_MODEL_URL = "/deviceModels";

		/** OPERATION_DELETE_DEVICE_MODEL_URL */
		final String OPERATION_DELETE_DEVICE_MODEL_URL = "/deviceModels/{" + PATH_PARAM_DEVICE_MODEL + "}/vendor/{"
				+ PATH_PARAM_VENDOR + "}";
		
		/** OPERATION_UPSERT_USER_URL */
		final String OPERATION_UPSERT_USER_URL = "/users/{"+PATH_PARAM_CRMACCOUNTID+"}";
		
		/** OPERATION_DELETE_USER_URL */
		final String OPERATION_DELETE_USER_URL = "/users/{"+PATH_PARAM_USERNAME+"}";		
		
		/** OPERATION_GET_USER_URL */
		final String OPERATION_GET_USER_URL = OPERATION_DELETE_USER_URL;
		
		/** OPERATION_GET_UPDATED_USER_URL */
		String OPERATION_GET_UPDATED_USER_URL = "/users";
		
		/** OPERATION_LIST_MODEL_URL */
		final String OPERATION_LIST_MODEL_URL = "/deviceModels";
		
		/** OPERATION_GET_VENDOR_LIST */
		final String OPERATION_GET_VENDOR_LIST = "/vendors";
		
		/** OPERATION_MASS_RECALCULATION */
		final String OPERATION_MASS_RECALCULATION = "/massRecalculation";
		
		/** OPERATION_GET_VQE_UPDATE_STATUS */
		final String OPERATION_GET_VQE_UPDATE_STATUS = "/users/vqeParameters/status";
		
		/** OPERATION_CREATE_VQE_GROUPS_DEFAULT_ATTRIBUTES */
		String OPERATION_CREATE_VQE_GROUPS_DEFAULT_ATTRIBUTES = "/vqeGroups/defaultAttributes";
		
		 /** OPERATION_CREATE_VQE_GROUP_URL */
		 String OPERATION_CREATE_VQE_GROUP_URL = "/vqeGroups";

		final String OPERATION_OVERRIDE_VQEGROUPS_CONDITIONS = "/vqeGroups/overridingConditions";
	}
	
	public interface Jms {
		
		/** DRM_JMS_ENDPOINT */
		final String DRM_JMS_ENDPOINT =  "DEVICE_RESOURCE_MS_JMS_ENDPOINT";
		/** DRM_JMS_USERNAME */
		final String DRM_JMS_USERNAME = "DEVICE_RESOURCE_MS_JMS_USERNAME";
		/** DRM_JMS_PASSWORD */
		final String DRM_JMS_PASSWORD = "DEVICE_RESOURCE_MS_JMS_PASSWORD";
		
		/** DRM_TOPIC_ENABLED */
		final String DRM_JMS_ENABLED = "DEVICE_RESOURCE_MS_JMS_ENABLED";
		/** DRM_TOPIC_NAME */
		final String DRM_TOPIC_NAME = "DEVICE_RESOURCE_MS_TOPIC_NAME";
		
		/** DRM_QUEUE_NAME */
		final String DRM_QUEUE_NAME = "DEVICE_RESOURCE_MS_QUEUE_NAME";
		
		/**Mass calculation reason */
		final String VTP_CHANGE = "VtpChange";
		final String HW_MODEL_CHANGE = "HwModelChange";
		final String BW_PROFILE_CHANGE = "BwProfileChange";
		final String MMDDF_CHANGE = "Mmddf_change";
		final String VQE_CHANGE = "VqeChange";
		final String TIRGGER = "Trigger";
		final String DRM_OPERATION = "triggerMassCalculation";
		
		/** KEY_VQE_UPDATE */
		final String KEY_VQE_UPDATE = "VQEParams";
		
		/** GROUP_MS_QUEUE_INTERACTION_ENABLED */
		String GROUP_MS_QUEUE_INTERACTION_ENABLED = "GROUP_MS_QUEUE_INTERACTION_ENABLED";

		/** GROUP_MS_QUEUE_PASSWORD */
		String GROUP_MS_QUEUE_PASSWORD = "GROUP_MS_QUEUE_PASSWORD";

		/** GROUP_MS_QUEUE_USERNAME */
		String GROUP_MS_QUEUE_USERNAME = "GROUP_MS_QUEUE_USERNAME";

		/** GROUP_MS_QUEUE_ENDPOINT */
		String GROUP_MS_QUEUE_ENDPOINT = "GROUP_MS_QUEUE_ENDPOINT";

		/** GROUP_MS_QUEUE_NAME */
		String GROUP_MS_QUEUE_NAME = "GROUP_MS_QUEUE_NAME";
		
	}
	
	public interface PushMessage {
		String PUSH_MESSAGE_MS_JMS_ENABLED = "PUSH_MESSAGE_MS_JMS_ENABLED";
		String PUSH_MESSAGE_MS_JMS_ENDPOINT = "PUSH_MESSAGE_MS_JMS_ENDPOINT";
		String PUSH_MESSAGE_MS_JMS_USERNAME = "PUSH_MESSAGE_MS_JMS_USERNAME";
		String PUSH_MESSAGE_MS_JMS_PASSWORD = "PUSH_MESSAGE_MS_JMS_PASSWORD";
		String PUSH_MESSAGE_MS_UNICAST_TRIGGER_QUEUE_NAME = "PUSH_MESSAGE_MS_UNICAST_TRIGGER_QUEUE_NAME";
	}

}
