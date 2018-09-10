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
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.accenture.avs.be.lib.configuration.ConfigurationClient;
import com.accenture.avs.be.lib.configuration.client.annotations.ManagedConfiguration;
import com.accenture.avs.be.lib.configuration.client.bean.managed.DataMinimization;
import com.accenture.avs.be.lib.configuration.client.bean.managed.MaskFields;
import com.accenture.avs.be.lib.configuration.client.bean.managed.SysMessage;
import com.accenture.avs.be.lib.configuration.client.bean.managed.SysMessages;
import com.accenture.avs.be.lib.configuration.client.bean.managed.SysParameter;
import com.accenture.avs.be.lib.configuration.client.bean.managed.SysParameters;
import com.accenture.avs.be.lib.configuration.client.registry.ConfigurationRegistry;
import com.accenture.avs.commons.beans.LogMaskFields;
import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.commons.lib.MaskLoggerConfiguration;
import com.accenture.avs.device.annotation.ManagedApplicationConfiguration;
import com.accenture.avs.device.config.model.BandwidthAndVQEProperty;
import com.accenture.avs.device.config.model.DeviceResourceConfigProperty;
import com.accenture.avs.device.config.model.MDCServerAttributes;
import com.accenture.avs.device.config.model.MDCServerProperty;
import com.accenture.avs.device.config.model.QoESettingsProperty;
import com.accenture.avs.device.json.object.configuration.ApplicationConfiguration;
import com.accenture.avs.device.json.object.configuration.DeviceResourceConfig;
import com.accenture.avs.device.json.object.configuration.GlobalVQEFlag;
import com.accenture.avs.device.json.object.configuration.ModelResource;
import com.accenture.avs.device.json.object.configuration.QoESettings;
import com.accenture.avs.device.json.object.configuration.RemoteConfiguration;
import com.accenture.avs.device.json.object.configuration.Server;
import com.accenture.avs.device.json.object.configuration.ServerMetadata;
import com.accenture.avs.device.json.object.configuration.Servers;
import com.accenture.avs.device.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The manager class for fetching and caching the system messages and system
 * parameters.
 * 
 * @author Singh.Saurabh
 */
@Component
public class ConfigurationManager {

	private static final LoggerWrapper LOGGER = new LoggerWrapper(ConfigurationManager.class);
	
	/** restTemplate */
	@Autowired
	private RestTemplate restTemplate;

	/** configurationProperties */
	@Autowired
	private ConfigurationProperties config;
	
	/** systemMessages collection of SysMessage */
	private List<SysMessage> systemMessages;
	
	/** systemParams collection of SysParameter */
	private List<SysParameter> systemParams;
	
	/** servers properties for MMDDF server */
	private Servers servers;
	
	/** global VQE property for devices */
	private GlobalVQEFlag globalVQEFlag;
	
	/** Mass-calculation property for DeviceResourceConfig */
	private DeviceResourceConfig deviceResourceConfig;
	
	/** global VQE Settings */
	private QoESettings qoESettings;
	
	/** mapper */
	private static final ObjectMapper mapper = new ObjectMapper();	
	
	/** resourcesArray */
	private String[] resourcesArray;
	
	/** DEFAULT_SYSTEM_MESSAGE */
	private static final SystemMessage DEFAULT_SYSTEM_MESSAGE;
	
	static {
		ConfigurationRegistry.registerClass(ModelResource.class);
		ConfigurationRegistry.registerClass(DeviceResourceConfig.class);
		ConfigurationRegistry.registerClass(Servers.class);
		ConfigurationRegistry.registerClass(QoESettings.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		DEFAULT_SYSTEM_MESSAGE = new SystemMessage();
		DEFAULT_SYSTEM_MESSAGE.setMessageText("GENERIC ERROR");
		DEFAULT_SYSTEM_MESSAGE.setMessageCode("ACN_300");
		DEFAULT_SYSTEM_MESSAGE.setRestStatus(500);
	}
	
	/**
	 * Find systems parameters by parameter name
	 * 
	 * @param paramName
	 * 
	 * @return SystemsParameter
	 * 
	 * @throws IllegalArgumentException
	 *             if system parameter not found by given name
	 */
	public SystemsParameter findSystemsParameter(String paramName) {				
		if(CollectionUtils.isEmpty(systemParams)) {
			refreshSystemMessagesAndParameters();
		}
		
		SystemsParameter systemsParameter = null;
		for (SysParameter systemParam : systemParams) {
			if (systemParam.getParamName().equals(paramName)) {
				systemsParameter = new SystemsParameter();
				systemsParameter.setParamName(systemParam.getParamName());
				systemsParameter.setParamType(systemParam.getParamType());
				systemsParameter.setParamValue(systemParam.getParamValue());
				break;
			}
		}
		return systemsParameter;
	}

	/**
	 * Find system message by language and code
	 * 
	 * @param language
	 * @param code
	 * 
	 * @return SystemMessage
	 * 
	 * @throws IllegalArgumentException
	 *             if system message not found by given language and code
	 */
	public SystemMessage findSystemMessage(String language, String message_key) {
		if(CollectionUtils.isEmpty(systemMessages)) {
			refreshSystemMessagesAndParameters();
		}
		
		SystemMessage message = DEFAULT_SYSTEM_MESSAGE;
		for (SysMessage systemMessageDto : systemMessages) {
			
			if (systemMessageDto.getMessageKey().equals(message_key)) {
				message = new SystemMessage();
				message.setMessageCode(systemMessageDto.getMessageCode());
				message.setMessageText(systemMessageDto.getMessageText());
				message.setType(systemMessageDto.getType());
				message.setRestStatus(Integer.parseInt(systemMessageDto.getRestStatus()));
				break;
			}
		}
		return message;
	}

	/**
	 * Refresh system messages
	 * 
	 */	
	@Scheduled(fixedDelayString = "${system.params.refresh.interval:300000}")
	private void refreshSystemMessagesAndParameters() {
		try {

			Map<String, ?> generalConfiguration = null;

			generalConfiguration = com.accenture.avs.be.lib.configuration.ConfigurationClient
					.getGeneralConfiguration(resourcesArray);
			SysParameters sysParameters = (SysParameters) generalConfiguration.get(config.getSystemParamResourceName());
			SysMessages sysMessages = (SysMessages) generalConfiguration.get(config.getSystemMsgResourceName());

			if (sysParameters != null) {
				systemParams = sysParameters.getConfigurations();
			}

			if (sysMessages != null) {
				systemMessages = sysMessages.getConfigurations();
			}						

		} catch (Exception ex) {
			LOGGER.logMessage("Unable to refresh", ex);
		}					
	}


	/**
	 * Load server properties for MMDDF properties. 
	 */
	public void findMMDDFResourceConfig() {
		servers = findMicroserviceResource(Servers.class);
	}
	
	/**
	 * Load Global VQE flag property. 
	 */
	public void findGlobalVQEFlagCongig() {
		globalVQEFlag = findApplicationResource(GlobalVQEFlag.class);
	}
	
	/**
	 * Load device resource properties for Mass calculation. 
	 */
	public void findMassCalculationResourceCongig() {
		deviceResourceConfig = findMicroserviceResource(DeviceResourceConfig.class);
	}
	
	/**
	 * Load device resource properties for Mass calculation. 
	 */
	public void findQoESettingsConfig() {
		qoESettings = findMicroserviceResource(QoESettings.class);
	}
	
	/**
	 * Gets useVQE and MDC server Bandwidth.
	 * 
	 * @return BandwidthAndVQEProperty
	 */
	public BandwidthAndVQEProperty getGlobalVQEBandwidthConfig() {
		BandwidthAndVQEProperty bwAndVQE = new BandwidthAndVQEProperty();
		bwAndVQE.setMdcBandwidthName(Constants.MDC_BANDWIDTH_NAME);
		bwAndVQE.setMdcBandwidthValue(Constants.DEFAULT_MDC_BANDWIDTH_VALUE);
		bwAndVQE.setUseGlobalVqeBW(globalVQEFlag != null ? globalVQEFlag.getUSEGLOBALVQEBW() : false);
		if (getMMDDFResources().getAttributeList() != null) {
			for (MDCServerAttributes serverAttrib : getMMDDFResources().getAttributeList()) {
				if (Constants.MDC_BANDWIDTH_NAME.equalsIgnoreCase(serverAttrib.getName())) {
					bwAndVQE.setMdcBandwidthName(serverAttrib.getName());
					bwAndVQE.setMdcBandwidthValue(Integer.parseInt(serverAttrib.getValue()));
				}
			}
		}
		return bwAndVQE;
	}
	
	/**
	 * Fetch MMDDF Server Resources from Servers object.
	 */
	public MDCServerProperty getMMDDFResources() {
		MDCServerProperty mdcServer = new MDCServerProperty();
		if (servers != null) {
			for (Server serverObj : servers.getServers()) {
				if ("MMDDF".equalsIgnoreCase(serverObj.getServerType())) {
					mdcServer.setName(serverObj.getServerName());
					mdcServer.setAddress(serverObj.getServerAddress());
					mdcServer.setType(serverObj.getServerType());
					mdcServer.setLocationID(serverObj.getServerLocation());
					for (ServerMetadata serverMetadata : serverObj.getServerMetadata()) {
						MDCServerAttributes serverAttrib = new MDCServerAttributes();
						serverAttrib.setName(serverMetadata.getMetadataName());
						serverAttrib.setValue(serverMetadata.getMetadataValue());
						mdcServer.getAttributeList().add(serverAttrib);
					}
				}
			}
		}
		return mdcServer;
	}
	
	/**
	 * Gets Mass calculation properties as batchSize and Parallel Thread.
	 * 
	 * @return MassCalculationProperty
	 */
	public DeviceResourceConfigProperty getDrmResources() {
		DeviceResourceConfigProperty drmConfigProperty = new DeviceResourceConfigProperty();
		if (deviceResourceConfig != null) {
			drmConfigProperty.setBatchSize(deviceResourceConfig.getMassCalculation().getBatchSize());
			drmConfigProperty.setParallelExecutionThreads(
					deviceResourceConfig.getMassCalculation().getParallelExecutionThreads());
			drmConfigProperty.setUserLogTableCleanupInterval(deviceResourceConfig.getUserLogTableCleanupInterval());
		}else{
			drmConfigProperty.setBatchSize(Constants.DEFAULT_MASS_CAL_BATCHSIZE);
			drmConfigProperty.setParallelExecutionThreads(Constants.DEFAULT_MASS_CAL_THREADS);
			drmConfigProperty.setUserLogTableCleanupInterval(Constants.DEFAULT_USERLOG_CLEANUP_INTERVAL);
		}
		return drmConfigProperty;
	}

	
	/**
	 * @return the servers
	 */
	public Servers getServers() {
		return servers;
	}

	/**
	 * @return the globalVQEFlag
	 */
	public GlobalVQEFlag getGlobalVQEFlag() {
		return globalVQEFlag;
	}

	/**
	 * @return the deviceResourceConfig
	 */
	public DeviceResourceConfig getDeviceResourceConfig() {
		return deviceResourceConfig;
	}

	/**
	 * Refresh Mask field at configured interval.
	 */
	@Scheduled(fixedDelayString = "${system.params.refresh.interval:300000}")
	private void refreshMaskFields() {
		loadRefreshMaskLoggerConfiguration();
	}

	/**
	 * Load masked field
	 * 
	 */
	public void loadRefreshMaskLoggerConfiguration() {		
		try {
			SystemsParameter enableLogMaksing = findSystemsParameter(Constants.ENABLE_LOG_MASKING);
			LogMaskFields logMaskFieldDTO = null;
			if ("Y".equals(enableLogMaksing.getParamValue())) {
				DataMinimization dataMinimization = getMaskedFieldConfiguration();
				List<LogMaskFields> logMaskFields = null;
				if (dataMinimization != null) {
					logMaskFields = new ArrayList<>();
					for (MaskFields maskFields : dataMinimization.getDataMinimization()) {
						logMaskFieldDTO = new LogMaskFields(maskFields.getField(), maskFields.getCharMask());
						logMaskFields.add(logMaskFieldDTO);
					}
				}
				if (!CollectionUtils.isEmpty(logMaskFields)) {
					MaskLoggerConfiguration.getInstance(logMaskFields, true);
				}
			} else {
				MaskLoggerConfiguration.clearFields();
			}
		} catch (Exception exception) {
			LOGGER.logError(exception);
		}
	}
	
	/**
	 * Method to get mask field configuration
	 * 
	 * @return DataMinimization
	 */
	private DataMinimization getMaskedFieldConfiguration() {
		DataMinimization dataMinimization = null;
		try {
			LOGGER.logMessage("Invoking configuration-ms via configuration-client for dataMinimization resources.");
			String[] string = { Constants.DATA_MINIMIZATION };
			Map<String, DataMinimization> configuration = (Map<String, DataMinimization>) ConfigurationClient
					.getMicroserviceConfiguration(string);
			dataMinimization = configuration.get(Constants.DATA_MINIMIZATION);
		} catch (Exception exception) {
			LOGGER.logError(exception);
		}
		
		return dataMinimization;
	}
	
	/**
	 * Initializing the System message, System parameters, Device Resource, Global VQE,  once application
	 * start-up is finished.
	 * 
	 */
	@PostConstruct
	public void init() {
		resourcesArray = new String[] {config.getSystemParamResourceName(), config.getSystemMsgResourceName()};
		refreshSystemMessagesAndParameters();
		findMMDDFResourceConfig();
		findMassCalculationResourceCongig();
		findGlobalVQEFlagCongig();
		findQoESettingsConfig();
		refreshMaskFields();
	}
	
	/**
	 * 
	 * @param configClass
	 * @return
	 */
	public <T> T findMicroserviceResource(Class<T> configClass) {
		ManagedConfiguration managedAnnotation = configClass.getAnnotation(ManagedConfiguration.class);
		LOGGER.logMessage("Retrieving configuration for {} ", managedAnnotation.label());
		String label = managedAnnotation.label();
		Map<String, ?> configurationMap = null;
		T jsonResponse = null;
		
		try {
			configurationMap = ConfigurationClient.getMicroserviceConfiguration(new String[] {label});						
			jsonResponse = (T) configurationMap.get(label);
			
		} catch(Exception ex) {
			LOGGER.logError(ex, "error while retrieve microservice configuration");
		}
		
		return jsonResponse;		
	}

	/**
	 * 
	 * @param configClass
	 * @return
	 */
	public <T> T findApplicationResource(Class<T> configClass) {
		T jsonResponse = null;
		ManagedApplicationConfiguration managedAnnotation = configClass
				.getAnnotation(ManagedApplicationConfiguration.class);
		StringBuilder configURL = new StringBuilder(System.getenv("CONFIGURATION_MICROSERVICE_BASE_URL")).append("/application");
		
		try {
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(configURL.toString())
					.queryParam("platform", managedAnnotation.platform())
					.queryParam("deviceType", managedAnnotation.deviceType())
					.queryParam("propertyName", managedAnnotation.propertyName())
					.queryParam("merge", false)
			        .queryParam("status", "ACTIVE");
			ResponseEntity<RemoteConfiguration> response;
			String finalUrl = builder.toUriString();
			
			LOGGER.logMessage("finalUrl : {}" , finalUrl);
			
			response = restTemplate.getForEntity(finalUrl, RemoteConfiguration.class);
			RemoteConfiguration remoteConfiguration = response.getBody();
			
			LOGGER.logMessage("remoteConfiguration : {}" , remoteConfiguration);
			
			if(remoteConfiguration != null) {
				List<ApplicationConfiguration> configList = remoteConfiguration.getResultObj().getConfigurations();
				LOGGER.logMessage("configList : {}" , configList);
											
				if(!CollectionUtils.isEmpty(configList)) {
					
					for(ApplicationConfiguration appConfig : configList) {
					
						if(managedAnnotation.propertyName().equals(appConfig.getPropertyName())) {
							String jsonValue = mapper.writeValueAsString(appConfig.getConfiguration());
							
							try {
								jsonResponse = mapper.readValue(jsonValue, configClass);
								break;
							} catch (Exception ex) {
								LOGGER.logError(ex, "error while retrieve application configuration");
							}
						}
					}										
				} else {
					LOGGER.logMessage("Unable to find ApplicationConfiguration");
				}
			} else {
				LOGGER.logMessage("Unable to find remoteConfiguration");
			}						
			
		} catch(Exception ex) {
			LOGGER.logError(ex, "Unable to retrive application configuration " + managedAnnotation.propertyName() + " from configuration ms");
			
		}
		
		return jsonResponse;
	}

	/**
	 * @return the qoESettings
	 */
	public QoESettings getQoESettings() {
		return qoESettings;
	}

	/**
	 * @param qoESettings the qoESettings to set
	 */
	public void setQoESettings(QoESettings qoESettings) {
		this.qoESettings = qoESettings;
	}

	public QoESettingsProperty getQoESettingsResources() {
		QoESettingsProperty qoESettingsProperty = new QoESettingsProperty();
		if (qoESettings != null) {
			qoESettingsProperty.setRccOverride(qoESettings.getRccOverride());
			qoESettingsProperty.setOverrideDefaultQoEBandwidth(qoESettings.isOverrideDefaultQoEBandwidth());
		}else{
			qoESettingsProperty.setRccOverride(Constants.DEFAULT_RCC_OVERRIDE);
			qoESettingsProperty.setOverrideDefaultQoEBandwidth(Constants.DEFAULT_OVERRIDE_DEFAULT_QOEBANDWIDTH);
		}
		return qoESettingsProperty;
	}
	
	
	
//	private void sampleCalling() {
//		DeviceResourceConfig deviceResourceConfig = findMicroserviceResource(DeviceResourceConfig.class);
//		ModelResource modelResource = findMicroserviceResource(ModelResource.class);
//		Servers servers = findMicroserviceResource(Servers.class);		
//		GlobalVQEFlag globalVQEFlag = findApplicationResource(GlobalVQEFlag.class);
//		ManagedDeviceApplication managedDeviceApplication = findApplicationResource(ManagedDeviceApplication.class);
//	}		
}
