package com.accenture.avs.device.util;

public class PaginationUtils {
	
	public enum GetDeviceAuditsSortByValues {
		DEVICEID("deviceId","deviceId"),
		SERIALNUMBER("serialNumber","serialNumber"),
		MODEL("model","model.modelName"),		
		VENDOR("vendor","model.vendor"),
		DEVICETYPE("deviceType","deviceType"),
		PLATFORM("platform","platform"),
		ASSIGNMENTSTATUS("assignmentStatus","assignmentStatus");
		/** DEFAULT_SORT_BY_DEVICE_ID */
		private static final String DEFAULT_SORT_BY_DEVICE_ID = "deviceId";	
		private String reqParam;
		private String dbParam;
		
		private GetDeviceAuditsSortByValues(String reqParam, String dbParam) {
			this.reqParam = reqParam;
			this.dbParam = dbParam;
		}
		
		/**
		 * This method returns db param name from request parameter name
		 * 
		 * @param reqParam
		 * @return String
		 */
		public static String getSortByValue(String reqParam) {
			String dbParam = DEFAULT_SORT_BY_DEVICE_ID;
			for (GetDeviceAuditsSortByValues enumValue : GetDeviceAuditsSortByValues.values()) {
				if (reqParam.equals(enumValue.reqParam)) {
					dbParam = enumValue.dbParam;
					break;
				}
			}
			return dbParam;
		}
	}
	
	public enum GetDeviceModelsSortByValues {
		
		DEVICEMODEL("deviceModel","modelName"),
		VENDOR("vendor","vendor"),
		PLATFORM("platform","platform"),
		QOECAPABLE("qoeCapable","qoeCapable"),
		STATUS("status","status"),
		DEVICEAUTOREGISTRATION("deviceAutoRegistration","deviceAutoRegistration");
		
		/** DEFAULT_SORT_BY_GETDEVICEMODEL */
		private static final String DEFAULT_SORT_BY_GETDEVICEMODEL = "modelName";
		/** reqParam */
		private String reqParam;
		/** dbParam	 */
		private String dbParam;
		
		private GetDeviceModelsSortByValues(String reqParam, String dbParam) {
			this.reqParam = reqParam;
			this.dbParam = dbParam;
		}
		
		/**
		 * This method returns db param name from request parameter name
		 * 
		 * @param reqParam
		 * @return String
		 */
		public static String getSortByValue(String reqParam) {
			String dbParam = DEFAULT_SORT_BY_GETDEVICEMODEL;
			for (GetDeviceModelsSortByValues enumValue : GetDeviceModelsSortByValues.values()) {
				if (reqParam.equals(enumValue.reqParam)) {
					dbParam = enumValue.dbParam;
					break;
				}
			}
			return dbParam;
		}
	}
	

	/**
	 * SortOrder values 
	 *
	 */
	public enum SortOrderValues{
		ASC,
		DESC;
		
		/**
		 * This method returns value from request parameter name 
		 * @param reqParam 
		 */
		public static String getSortOrderValue(String reqParam) {
			String value = Constants.PaginationParams.DEFAULT_SORT_ORDER;
			for (SortOrderValues enumValue : SortOrderValues.values()) {
				if (reqParam.equals(enumValue.name())) {
					value = enumValue.name();
					break;
				}
			}
			return value;
		}
	}
	
		
}
