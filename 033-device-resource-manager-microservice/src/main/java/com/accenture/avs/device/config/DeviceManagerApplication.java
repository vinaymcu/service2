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

import java.util.HashMap;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import com.accenture.avs.commons.lib.LoggerWrapper;
import com.accenture.avs.commons.lib.LoggerWrapper.ErrorType;
import com.accenture.avs.device.config.client.ConfigurationClient;
import com.accenture.avs.device.exception.DeviceManagerException;
import com.accenture.avs.device.jms.queue.listener.VqeUpdateListener;
import com.accenture.avs.device.jms.topic.listener.ConsumerMessageListener;
import com.accenture.avs.device.jms.topic.listener.ConsumerVTPAndBWPListener;
import com.accenture.avs.device.jms.topic.listener.MassRecalculationListner;
import com.accenture.avs.device.scheduler.CleanupServiceScheduler;
import com.accenture.avs.device.service.MassRecalculationService;
import com.accenture.avs.device.util.Constants;

/**
 * The Class DeviceManagerApplication.
 */
@PropertySource("file:${BE_PROPERTIES}/${spring.application.name}/deviceresourcemanager.properties")
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "com.accenture.avs.device.entity" })
@SpringBootApplication(scanBasePackages = { "com.accenture.avs.device" })
@EnableJpaRepositories(basePackages = { "com.accenture.avs.device.repository" })
@ComponentScan(basePackages = { "com.accenture.avs.device" })
@EnableJms
public class DeviceManagerApplication extends SpringBootServletInitializer {
	
	/** log4jConfigLocation */
	@Value("${log4j.config.location}")
	private String log4jConfigLocation;
	
	/** Wire ConfigurationClient bean */
	@Autowired
	private ConfigurationClient configurationClient;
	
	/** Wire ConsumerMessageListener bean */
	@Autowired
	private ConsumerMessageListener consumerMessageListener;
	
	/** Wire ConsumerVTPAndBWPListener bean */
	@Autowired
	private ConsumerVTPAndBWPListener consumerVTPAndBWPListener;
	
	/** massRecalculationService */
	@Autowired
	private MassRecalculationService massRecalculationService;
	
	@Autowired
	private CleanupServiceScheduler cleanupServiceScheduler;
	
	/** configuration_topic_name */
	private String configuration_topic_name;
	
	/** configuration_ms_target_topic_name */
	private String configuration_ms_target_topic_name;
	
	/** Instance of LoggerWrapper for logging purpose */
	private static final LoggerWrapper LOGGER = new LoggerWrapper(DeviceManagerApplication.class);
	
	/** DEVICE_RESOURCE_MANAGER_MS_TOPIC */
	private String DEVICE_RESOURCE_MANAGER_MS_TOPIC;
	
	/** DEVICE_RESOURCE_MANAGER_MS_QUEUE */
	private String DEVICE_RESOURCE_MANAGER_MS_QUEUE;
	
	/*massRecalculationListner*/
	@Autowired
	MassRecalculationListner massRecalculationListner;
	
	/** vqeUpdateListener */
	@Autowired
	private VqeUpdateListener vqeUpdateListener;

	/** configurationProperties */
	@Autowired
	private ConfigurationProperties configurationProperties;
	
		/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DeviceManagerApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.addUrlMappings("/*");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return registration;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * Message source.
	 *
	 * @return the reloadable resource bundle message source
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/message");
		return messageSource;
	}

	/**
	 * Message source accessor.
	 *
	 * @return the message source accessor
	 */
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource(), Locale.ENGLISH);
	}

	/**
	 * The Init method.
	 */
	@PostConstruct
	public void init() {
		LOGGER.logMessage("Initializing Device Manager Application  .. ");
		HashMap<Class, ErrorType> errorMap = new HashMap<>();
		errorMap.put(DeviceManagerException.class, ErrorType.EXPECTED_ERROR);
		LoggerWrapper.initialize(log4jConfigLocation, errorMap);
		
		LOGGER.logMessage("Checking for pending profile calculation ..");
		
		/*Need improvement remove thread */
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					LOGGER.logMessage("Interrupted", e);
				}
			massRecalculationService.massRecalculation(true);
			}
		}).start();
		
		LOGGER.logMessage("Pending profile calculation end");
		
		cleanupServiceScheduler.scheduleCleanup();
		
	}

	/**
	 * @return
	 */
	public ActiveMQConnectionFactory getActiveMQConnectionFactoryForDrm() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
				
		activeMQConnectionFactory
				.setBrokerURL(configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_ENDPOINT));
		activeMQConnectionFactory
				.setUserName(configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_USERNAME));
		activeMQConnectionFactory
				.setPassword(configurationClient.getSystemParameterValue(Constants.Jms.DRM_JMS_PASSWORD));
		DEVICE_RESOURCE_MANAGER_MS_TOPIC = configurationClient.getSystemParameterValue(Constants.Jms.DRM_TOPIC_NAME);
		DEVICE_RESOURCE_MANAGER_MS_QUEUE = configurationClient.getSystemParameterValue(Constants.Jms.DRM_QUEUE_NAME);
		
		LOGGER.logMessage("Registered ActiveMQ : {}, {}", DEVICE_RESOURCE_MANAGER_MS_TOPIC, DEVICE_RESOURCE_MANAGER_MS_QUEUE);
		return activeMQConnectionFactory;
	}

	/**
	 * 
	 * @return
	 */
	public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory
				.setBrokerURL(configurationClient.getSystemParameterValue("CONFIGURATION_MS_TOPIC_ENDPOINT"));
		activeMQConnectionFactory
				.setUserName(configurationClient.getSystemParameterValue("CONFIGURATION_MS_TOPIC_USERNAME"));
		activeMQConnectionFactory
				.setPassword(configurationClient.getSystemParameterValue("CONFIGURATION_MS_TOPIC_PASSWORD"));
		return activeMQConnectionFactory;
	}
	
	/**
	 * This bean set to JMS Listener for Mass Recalculation.
	 * 
	 * @return MessageListenerContainer
	 */
	@Bean
	public MessageListenerContainer massListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		configuration_ms_target_topic_name = configurationClient.getSystemParameterValue("CONFIGURATION_MS_TARGET_TOPIC_NAME");
		LOGGER.logMessage("VQE and MDC-Bandwidth CONFIGURATION_MS_TARGET_TOPIC_NAME as {} ", configuration_ms_target_topic_name);
		container.setConnectionFactory(getActiveMQConnectionFactoryForDrm());
		container.setPubSubDomain(true);
		container.setDestinationName(DEVICE_RESOURCE_MANAGER_MS_TOPIC);
		container.setMessageListener(massRecalculationListner);
		return container;
	}

	/**
	 * This bean set to JMS Listener for VTP and BWP.
	 * 
	 * @return MessageListenerContainer
	 */
	@Bean
	public MessageListenerContainer vtpAndBwpListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		configuration_topic_name = configurationClient.getSystemParameterValue("CONFIGURATION_MS_TARGET_TOPIC_NAME");
		LOGGER.logMessage("VTP and BWP for CONFIGURATION_MS_TARGET_TOPIC_NAME as {} ", configuration_topic_name);
		container.setConnectionFactory(getActiveMQConnectionFactory());
		container.setPubSubDomain(true);
		container.setDestinationName(configuration_topic_name);
		container.setMessageListener(consumerVTPAndBWPListener);
		return container;
	}
	
	/**
	 * This bean set to JMS Listener for VQE, MDCBandwidth etc.
	 * 
	 * @return MessageListenerContainer
	 */
	@Bean
	public MessageListenerContainer listenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(getActiveMQConnectionFactory());
		container.setPubSubDomain(true);
		container.setDestinationName(configuration_ms_target_topic_name);
		container.setMessageListener(consumerMessageListener);
		return container;
	}
	

	@Bean
    public TaskExecutor threadPoolTaskExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
          executor.setCorePoolSize(4);
          executor.setMaxPoolSize(50);
          executor.setThreadNamePrefix("default_task_executor_thread");
          executor.initialize();
          return executor;
       }

	/**
	 * This bean set to JMS Listener for Device Resource Manager.
	 * 
	 * @return MessageListenerContainer
	 */
	@Bean
	public MessageListenerContainer drmListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(getActiveMQConnectionFactoryForDrm());
		container.setPubSubDomain(false);
		container.setDestinationName(DEVICE_RESOURCE_MANAGER_MS_QUEUE);
		container.setMessageListener(vqeUpdateListener);
		return container;
	}

}
