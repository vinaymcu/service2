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
package com.accenture.avs.device.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author surendra.kumar
 *
 */
@Entity
@Table(name = "GROUP_ATTRIBUTES")
public class GroupAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	/** groupId */
	@Column(name = "GROUPID")
	private Long groupId;
	
	/** vqecEnable */
	@Column(name = "VQEC_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean vqecEnable;
	
	/** vqecEnableUpdatetype */
	@Column(name = "VQEC_ENABLE_UPDATETYPE")
	private String vqecEnableUpdatetype;
	
	/** jitterBuffSize */
	@Column(name = "JITTER_BUFF_SIZE")
	private Long jitterBuffSize;
	
	/** jitterBuffSizeUpdatetype */
	@Column(name = "JITTER_BUFF_SIZE_UPDATETYPE")
	private String jitterBuffSizeUpdatetype;
	
	/** repairTriggerPointAbs */
	@Column(name = "REPAIR_TRIGGER_POINT_ABS")
	private Long repairTriggerPointAbs;
	
	/** repairTriggerPointAbsUpdatetype */
	@Column(name = "REPAIR_TRIGGER_POINT_ABS_UPDATETYPE")
	private String repairTriggerPointAbsUpdatetype;
	
	/** pakpoolSize */
	@Column(name = "PAKPOOL_SIZE")
	private Long pakpoolSize;
	
	/** pakpoolSizeUpdatetype */
	@Column(name = "PAKPOOL_SIZE_UPDATETYPE")
	private String pakpoolSizeUpdatetype;
	
	/** soRcvbuf */
	@Column(name = "SO_RCVBUF")
	private Long soRcvbuf;
	
	/** soRcvbufUpdatetype */
	@Column(name = "SO_RCVBUF_UPDATETYPE")
	private String soRcvbufUpdatetype;
	
	/** sigMode */
	@Column(name = "SIG_MODE")
	private String sigMode;
	
	/** sigModeUpdatetype */
	@Column(name = "SIG_MODE_UPDATETYPE")
	private String sigModeUpdatetype;
	
	/** natBindingRefreshInterval */
	@Column(name = "NAT_BINDING_REFRESH_INTERVAL")
	private Long natBindingRefreshInterval;
	
	/** natBindingRefreshIntervalUpdatetype */
	@Column(name = "NAT_BINDING_REFRESH_INTERVAL_UPDATETYPE")
	private String natBindingRefreshIntervalUpdatetype;
	
	/** maxPaksize */
	@Column(name = "MAX_PAKSIZE")
	private Long maxPaksize;
	
	/** rccEnable */
	@Column(name = "MAX_PAKSIZE_UPDATETYPE")
	private String maxPaksizeUpdatetype;
	
	/** libcliTelnetPort */
	@Column(name = "LIBCLI_TELNET_PORT")
	private Long libcliTelnetPort;
	
	/** libcliTelnetPortUpdatetype */
	@Column(name = "LIBCLI_TELNET_PORT_UPDATETYPE")
	private String libcliTelnetPortUpdatetype;
	
	/** outputPakqLimit */
	@Column(name = "OUTPUT_PAKQ_LIMIT")
	private Long outputPakqLimit;
	
	/** outputPakqLimitUpdatetype */
	@Column(name = "OUTPUT_PAKQ_LIMIT_UPDATETYPE")
	private String outputPakqLimitUpdatetype;
	
	/** updateWindow */
	@Column(name = "UPDATE_WINDOW")
	private Long updateWindow;
	
	/** updateWindowUpdatetype */
	@Column(name = "UPDATE_WINDOW_UPDATETYPE")
	private String updateWindowUpdatetype;
	
	/** updateIntervalMax */
	@Column(name = "UPDATE_INTERVAL_MAX")
	private Long updateIntervalMax;
	
	/** rccEnable */
	@Column(name = "UPDATE_INTERVAL_MAX_UPDATETYPE")
	private String updateIntervalMaxUpdatetype;
	
	/** errorRepairEnable */
	@Column(name = "ERROR_REPAIR_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean errorRepairEnable;
	
	/** errorRepairEnableUpdatetype */
	@Column(name = "ERROR_REPAIR_ENABLE_UPDATETYPE")
	private String errorRepairEnableUpdatetype;
	
	/** errorRepairPolicerEnable */
	@Column(name = "ERROR_REPAIR_POLICER_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean errorRepairPolicerEnable;
	
	/** errorRepairPolicerEnableUpdatetype */
	@Column(name = "ERROR_REPAIR_POLICER_ENABLE_UPDATETYPE")
	private String errorRepairPolicerEnableUpdatetype;
	
	/** errorRepairPolicerRate */
	@Column(name = "ERROR_REPAIR_POLICER_RATE")
	private Long errorRepairPolicerRate;
	
	/** errorRepairPolicerRateUpdatetype */
	@Column(name = "ERROR_REPAIR_POLICER_RATE_UPDATETYPE")
	private String errorRepairPolicerRateUpdatetype;
	
	/** errorRepairPolicerBurst */
	@Column(name = "ERROR_REPAIR_POLICER_BURST")
	private Long errorRepairPolicerBurst;
	
	/** errorRepairPolicerBurstUpdatetype */
	@Column(name = "ERROR_REPAIR_POLICER_BURST_UPDATETYPE")
	private String errorRepairPolicerBurstUpdatetype;
	
	/** fecEnable */
	@Column(name = "FEC_ENABLE", columnDefinition = "TINYINT", length = 1 )
	private Boolean fecEnable;
	
	/** fecEnableUpdatetype */
	@Column(name = "FEC_ENABLE_UPDATETYPE")
	private String fecEnableUpdatetype;
	
	/** rccEnable */
	@Column(name = "RCC_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean rccEnable;
	
	/** rccEnableUpdatetype */
	@Column(name = "RCC_ENABLE_UPDATETYPE")
	private String rccEnableUpdatetype;
	
	/** rccStartTimeout */
	@Column(name = "RCC_START_TIMEOUT")
	private Long rccStartTimeout;
	
	/** rccStartTimeoutUpdatetype */
	@Column(name = "RCC_START_TIMEOUT_UPDATETYPE")
	private String rccStartTimeoutUpdatetype;
	
	/** reorderDelayAbs */
	@Column(name = "REORDER_DELAY_ABS")
	private Long reorderDelayAbs;
	
	/** reorderDelayAbsUpdatetype */
	@Column(name = "REORDER_DELAY_ABS_UPDATETYPE")
	private String reorderDelayAbsUpdatetype;
	
	/** cliIfname */
	@Column(name = "CLI_IFNAME")
	private String cliIfname;
	
	/** cliIfnameUpdatetype */
	@Column(name = "CLI_IFNAME_UPDATETYPE")
	private String cliIfnameUpdatetype;
	
	/** rtcpDscpValue */
	@Column(name = "RTCP_DSCP_VALUE")
	private Long rtcpDscpValue;
	
	/** rtcpDscpValueUpdatetype */
	@Column(name = "RTCP_DSCP_VALUE_UPDATETYPE")
	private String rtcpDscpValueUpdatetype;
	
	/** fastfillEnable */
	@Column(name = "FASTFILL_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean fastfillEnable;
	
	/** fastfillEnableUpdatetype */
	@Column(name = "FASTFILL_ENABLE_UPDATETYPE")
	private String fastfillEnableUpdatetype;
	
	/** extensionMaxReceiveBandwidthSD */
	@Column(name = "EXTENSION_MAX_RECEIVE_BANDWIDTH_SD")
	private Long extensionMaxReceiveBandwidthSD;
	
	/** extensionMaxReceiveBandwidthSDUpdatetype */
	@Column(name = "EXTENSION_MAX_RECEIVE_BANDWIDTH_SD_UPDATETYPE")
	private String extensionMaxReceiveBandwidthSDUpdatetype;
	
	/** extensionMaxReceiveBandwidthHd */
	@Column(name = "EXTENSION_MAX_RECEIVE_BANDWIDTH_HD")
	private Long extensionMaxReceiveBandwidthHd;
	
	/** extensionMaxReceiveBandwidthHdUpdatetype */
	@Column(name = "EXTENSION_MAX_RECEIVE_BANDWIDTH_HD_UPDATETYPE")
	private String extensionMaxReceiveBandwidthHdUpdatetype;
	
	/** extensionMinHdStreamBitrate */
	@Column(name = "EXTENSION_MIN_HD_STREAM_BITRATE")
	private Long extensionMinHdStreamBitrate;
	
	/** extensionMinHdStreamBitrateUpdatetype */
	@Column(name = "EXTENSION_MIN_HD_STREAM_BITRATE_UPDATETYPE")
	private String extensionMinHdStreamBitrateUpdatetype;
	
	/** extensionMaxFastfill */
	@Column(name = "EXTENSION_MAX_FASTFILL")
	private Long extensionMaxFastfill;
	
	/** maxFastfillUpdatetype */
	@Column(name = "MAX_FASTFILL_UPDATETYPE")
	private String maxFastfillUpdatetype;
	
	/** extensionAppDelay */
	@Column(name = "EXTENSION_APP_DELAY")
	private Long extensionAppDelay;
	
	/** extensionAppDelayUpdatetype */
	@Column(name = "EXTENSION_APP_DELAY_UPDATETYPE")
	private String extensionAppDelayUpdatetype;
	
	/** extensionSrcipFilterEnable */
	@Column(name = "EXTENSION_SRCIP_FILTER_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean extensionSrcipFilterEnable;
	
	/** extensionSrcipFilterEnableUpdatetype */
	@Column(name = "EXTENSION_SRCIP_FILTER_ENABLE_UPDATETYPE")
	private String extensionSrcipFilterEnableUpdatetype;
	
	/** extension2MaxReceiveBandwidthSDRcc */
	@Column(name = "EXTENSION2_MAX_RECEIVE_BANDWIDTH_SD_RCC")
	private Long extension2MaxReceiveBandwidthSDRcc;
	
	/** extension2MaxReceiveBandwidthSDRccUpdatetype */
	@Column(name = "EXTENSION2_MAX_RECEIVE_BANDWIDTH_SD_RCC_UPDATETYPE")
	private String extension2MaxReceiveBandwidthSDRccUpdatetype;
	
	/** extension2MaxReceiveBandwidthHDRcc */
	@Column(name = "EXTENSION2_MAX_RECEIVE_BANDWIDTH_HD_RCC")
	private Long extension2MaxReceiveBandwidthHDRcc;
	
	/** extension2MaxReceiveBandwidthHDRccUpdatetype */
	@Column(name = "EXTENSION2_MAX_RECEIVE_BANDWIDTH_HD_RCC_UPDATETYPE")
	private String extension2MaxReceiveBandwidthHDRccUpdatetype;
	
	/** extension2QoeEnable */
	@Column(name = "EXTENSION2_QOE_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean extension2QoeEnable;
	
	/** extension2QoeEnableUpdatetype */
	@Column(name = "EXTENSION2_QOE_ENABLE_UPDATETYPE")
	private String extension2QoeEnableUpdatetype;
	
	/** extension3RccExtraIgmpIp */
	@Column(name = "EXTENSION3_RCC_EXTRA_IGMP_IP")
	private String extension3RccExtraIgmpIp;
	
	/** extension3RccExtraIgmpIpUpdatetype */
	@Column(name = "EXTENSION3_RCC_EXTRA_IGMP_IP_UPDATETYPE")
	private String extension3RccExtraIgmpIpUpdatetype;
	
	/** extension4LogLevel */
	@Column(name = "EXTENSION4_LOG_LEVEL")
	private Integer extension4LogLevel;
	
	/** extension4LogLevelUpdatetype */
	@Column(name = "EXTENSION4_LOG_LEVEL_UPDATETYPE")
	private String extension4LogLevelUpdatetype;
	
	/** extension5ErrorRepairSmartRequestEnable */
	@Column(name = "EXTENSION5_ERROR_REPAIR_SMART_REQUEST_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean extension5ErrorRepairSmartRequestEnable;
	
	/** extension5ErrorRepairSmartRequestEnableUpdatetype */
	@Column(name = "EXTENSION5_ERROR_REPAIR_SMART_REQUEST_ENABLE_UPDATETYPE")
	private String extension5ErrorRepairSmartRequestEnableUpdatetype;
	
	/** extension5ErrorRepairRepeatRequestEnable */
	@Column(name = "EXTENSION5_ERROR_REPAIR_REPEAT_REQUEST_ENABLE" , columnDefinition = "TINYINT", length = 1)
	private Boolean extension5ErrorRepairRepeatRequestEnable;
	
	/** extension5ErrorRepairRepeatRequestEnableUpdatetype */
	@Column(name = "EXTENSION5_ERROR_REPAIR_REPEAT_REQUEST_ENABLE_UPDATETYPE")
	private String extension5ErrorRepairRepeatRequestEnableUpdatetype;
	
	/** extension5RepairMinRoundTripTimeAbs */
	@Column(name = "EXTENSION5_REPAIR_MIN_ROUND_TRIP_TIME_ABS")
	private Long extension5RepairMinRoundTripTimeAbs;
	
	/** extension5RepairMinRoundTripTimeAbsUpdatetype */
	@Column(name = "EXTENSION5_REPAIR_MIN_ROUND_TRIP_TIME_ABS_UPDATETYPE")
	private String extension5RepairMinRoundTripTimeAbsUpdatetype;
	
	/** extension6ViewershipEnable */
	@Column(name = "EXTENSION6_VIEWERSHIP_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean extension6ViewershipEnable;
	
	/** extension6ViewershipEnableUpdatetype */
	@Column(name = "EXTENSION6_VIEWERSHIP_ENABLE_UPDATETYPE")
	private String extension6ViewershipEnableUpdatetype;
	
	/** extension7PcrRestampEnable */
	@Column(name = "EXTENSION7_PCR_RESTAMP_ENABLE", columnDefinition = "TINYINT", length = 1)
	private Boolean extension7PcrRestampEnable;
	
	/** extension7PcrRestampEnableUpdatetype */
	@Column(name = "EXTENSION7_PCR_RESTAMP_ENABLE_UPDATETYPE")
	private String extension7PcrRestampEnableUpdatetype;
	
	
	/**
	 * 
	 */
	public GroupAttributes() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param groupId
	 * @param vqecEnable
	 * @param vqecEnableUpdatetype
	 * @param jitterBuffSize
	 * @param jitterBuffSizeUpdatetype
	 * @param repairTriggerPointAbs
	 * @param repairTriggerPointAbsUpdatetype
	 * @param pakpoolSize
	 * @param pakpoolSizeUpdatetype
	 * @param soRcvbuf
	 * @param soRcvbufUpdatetype
	 * @param sigMode
	 * @param sigModeUpdatetype
	 * @param natBindingRefreshInterval
	 * @param natBindingRefreshIntervalUpdatetype
	 * @param maxPaksize
	 * @param maxPaksizeUpdatetype
	 * @param libcliTelnetPort
	 * @param libcliTelnetPortUpdatetype
	 * @param outputPakqLimit
	 * @param outputPakqLimitUpdatetype
	 * @param updateWindow
	 * @param updateWindowUpdatetype
	 * @param updateIntervalMax
	 * @param updateIntervalMaxUpdatetype
	 * @param errorRepairEnable
	 * @param errorRepairEnableUpdatetype
	 * @param errorRepairPolicerEnable
	 * @param errorRepairPolicerEnableUpdatetype
	 * @param errorRepairPolicerRate
	 * @param errorRepairPolicerRateUpdatetype
	 * @param errorRepairPolicerBurst
	 * @param errorRepairPolicerBurstUpdatetype
	 * @param fecEnable
	 * @param fecEnableUpdatetype
	 * @param rccEnable
	 * @param rccEnableUpdatetype
	 * @param rccStartTimeout
	 * @param rccStartTimeoutUpdatetype
	 * @param reorderDelayAbs
	 * @param reorderDelayAbsUpdatetype
	 * @param cliIfname
	 * @param cliIfnameUpdatetype
	 * @param rtcpDscpValue
	 * @param rtcpDscpValueUpdatetype
	 * @param fastfillEnable
	 * @param fastfillEnableUpdatetype
	 * @param extensionMaxReceiveBandwidthSD
	 * @param extensionMaxReceiveBandwidthSDUpdatetype
	 * @param extensionMaxReceiveBandwidthHd
	 * @param extensionMaxReceiveBandwidthHdUpdatetype
	 * @param extensionMinHdStreamBitrate
	 * @param extensionMinHdStreamBitrateUpdatetype
	 * @param extensionMaxFastfill
	 * @param maxFastfillUpdatetype
	 * @param extensionAppDelay
	 * @param extensionAppDelayUpdatetype
	 * @param extensionSrcipFilterEnable
	 * @param extensionSrcipFilterEnableUpdatetype
	 * @param extension2MaxReceiveBandwidthSDRcc
	 * @param extension2MaxReceiveBandwidthSDRccUpdatetype
	 * @param extension2MaxReceiveBandwidthHDRcc
	 * @param extension2MaxReceiveBandwidthHDRccUpdatetype
	 * @param extension2QoeEnable
	 * @param extension2QoeEnableUpdatetype
	 * @param extension3RccExtraIgmpIp
	 * @param extension3RccExtraIgmpIpUpdatetype
	 * @param extension4LogLevel
	 * @param extension4LogLevelUpdatetype
	 * @param extension5ErrorRepairSmartRequestEnable
	 * @param extension5ErrorRepairSmartRequestEnableUpdatetype
	 * @param extension5ErrorRepairRepeatRequestEnable
	 * @param extension5ErrorRepairRepeatRequestEnableUpdatetype
	 * @param extension5RepairMinRoundTripTimeAbs
	 * @param extension5RepairMinRoundTripTimeAbsUpdatetype
	 * @param extension6ViewershipEnable
	 * @param extension6ViewershipEnableUpdatetype
	 * @param extension7PcrRestampEnable
	 * @param extension7PcrRestampEnableUpdatetype
	 */
	public GroupAttributes(Long id, Long groupId, Boolean vqecEnable, String vqecEnableUpdatetype, Long jitterBuffSize,
			String jitterBuffSizeUpdatetype, Long repairTriggerPointAbs, String repairTriggerPointAbsUpdatetype,
			Long pakpoolSize, String pakpoolSizeUpdatetype, Long soRcvbuf, String soRcvbufUpdatetype, String sigMode,
			String sigModeUpdatetype, Long natBindingRefreshInterval, String natBindingRefreshIntervalUpdatetype,
			Long maxPaksize, String maxPaksizeUpdatetype, Long libcliTelnetPort, String libcliTelnetPortUpdatetype,
			Long outputPakqLimit, String outputPakqLimitUpdatetype, Long updateWindow, String updateWindowUpdatetype,
			Long updateIntervalMax, String updateIntervalMaxUpdatetype, Boolean errorRepairEnable,
			String errorRepairEnableUpdatetype, Boolean errorRepairPolicerEnable,
			String errorRepairPolicerEnableUpdatetype, Long errorRepairPolicerRate,
			String errorRepairPolicerRateUpdatetype, Long errorRepairPolicerBurst,
			String errorRepairPolicerBurstUpdatetype, Boolean fecEnable, String fecEnableUpdatetype, Boolean rccEnable,
			String rccEnableUpdatetype, Long rccStartTimeout, String rccStartTimeoutUpdatetype, Long reorderDelayAbs,
			String reorderDelayAbsUpdatetype, String cliIfname, String cliIfnameUpdatetype, Long rtcpDscpValue,
			String rtcpDscpValueUpdatetype, Boolean fastfillEnable, String fastfillEnableUpdatetype,
			Long extensionMaxReceiveBandwidthSD, String extensionMaxReceiveBandwidthSDUpdatetype,
			Long extensionMaxReceiveBandwidthHd, String extensionMaxReceiveBandwidthHdUpdatetype,
			Long extensionMinHdStreamBitrate, String extensionMinHdStreamBitrateUpdatetype, Long extensionMaxFastfill,
			String maxFastfillUpdatetype, Long extensionAppDelay, String extensionAppDelayUpdatetype,
			Boolean extensionSrcipFilterEnable, String extensionSrcipFilterEnableUpdatetype,
			Long extension2MaxReceiveBandwidthSDRcc, String extension2MaxReceiveBandwidthSDRccUpdatetype,
			Long extension2MaxReceiveBandwidthHDRcc, String extension2MaxReceiveBandwidthHDRccUpdatetype,
			Boolean extension2QoeEnable, String extension2QoeEnableUpdatetype, String extension3RccExtraIgmpIp,
			String extension3RccExtraIgmpIpUpdatetype, Integer extension4LogLevel, String extension4LogLevelUpdatetype,
			Boolean extension5ErrorRepairSmartRequestEnable, String extension5ErrorRepairSmartRequestEnableUpdatetype,
			Boolean extension5ErrorRepairRepeatRequestEnable, String extension5ErrorRepairRepeatRequestEnableUpdatetype,
			Long extension5RepairMinRoundTripTimeAbs, String extension5RepairMinRoundTripTimeAbsUpdatetype,
			Boolean extension6ViewershipEnable, String extension6ViewershipEnableUpdatetype,
			Boolean extension7PcrRestampEnable, String extension7PcrRestampEnableUpdatetype) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.vqecEnable = vqecEnable;
		this.vqecEnableUpdatetype = vqecEnableUpdatetype;
		this.jitterBuffSize = jitterBuffSize;
		this.jitterBuffSizeUpdatetype = jitterBuffSizeUpdatetype;
		this.repairTriggerPointAbs = repairTriggerPointAbs;
		this.repairTriggerPointAbsUpdatetype = repairTriggerPointAbsUpdatetype;
		this.pakpoolSize = pakpoolSize;
		this.pakpoolSizeUpdatetype = pakpoolSizeUpdatetype;
		this.soRcvbuf = soRcvbuf;
		this.soRcvbufUpdatetype = soRcvbufUpdatetype;
		this.sigMode = sigMode;
		this.sigModeUpdatetype = sigModeUpdatetype;
		this.natBindingRefreshInterval = natBindingRefreshInterval;
		this.natBindingRefreshIntervalUpdatetype = natBindingRefreshIntervalUpdatetype;
		this.maxPaksize = maxPaksize;
		this.maxPaksizeUpdatetype = maxPaksizeUpdatetype;
		this.libcliTelnetPort = libcliTelnetPort;
		this.libcliTelnetPortUpdatetype = libcliTelnetPortUpdatetype;
		this.outputPakqLimit = outputPakqLimit;
		this.outputPakqLimitUpdatetype = outputPakqLimitUpdatetype;
		this.updateWindow = updateWindow;
		this.updateWindowUpdatetype = updateWindowUpdatetype;
		this.updateIntervalMax = updateIntervalMax;
		this.updateIntervalMaxUpdatetype = updateIntervalMaxUpdatetype;
		this.errorRepairEnable = errorRepairEnable;
		this.errorRepairEnableUpdatetype = errorRepairEnableUpdatetype;
		this.errorRepairPolicerEnable = errorRepairPolicerEnable;
		this.errorRepairPolicerEnableUpdatetype = errorRepairPolicerEnableUpdatetype;
		this.errorRepairPolicerRate = errorRepairPolicerRate;
		this.errorRepairPolicerRateUpdatetype = errorRepairPolicerRateUpdatetype;
		this.errorRepairPolicerBurst = errorRepairPolicerBurst;
		this.errorRepairPolicerBurstUpdatetype = errorRepairPolicerBurstUpdatetype;
		this.fecEnable = fecEnable;
		this.fecEnableUpdatetype = fecEnableUpdatetype;
		this.rccEnable = rccEnable;
		this.rccEnableUpdatetype = rccEnableUpdatetype;
		this.rccStartTimeout = rccStartTimeout;
		this.rccStartTimeoutUpdatetype = rccStartTimeoutUpdatetype;
		this.reorderDelayAbs = reorderDelayAbs;
		this.reorderDelayAbsUpdatetype = reorderDelayAbsUpdatetype;
		this.cliIfname = cliIfname;
		this.cliIfnameUpdatetype = cliIfnameUpdatetype;
		this.rtcpDscpValue = rtcpDscpValue;
		this.rtcpDscpValueUpdatetype = rtcpDscpValueUpdatetype;
		this.fastfillEnable = fastfillEnable;
		this.fastfillEnableUpdatetype = fastfillEnableUpdatetype;
		this.extensionMaxReceiveBandwidthSD = extensionMaxReceiveBandwidthSD;
		this.extensionMaxReceiveBandwidthSDUpdatetype = extensionMaxReceiveBandwidthSDUpdatetype;
		this.extensionMaxReceiveBandwidthHd = extensionMaxReceiveBandwidthHd;
		this.extensionMaxReceiveBandwidthHdUpdatetype = extensionMaxReceiveBandwidthHdUpdatetype;
		this.extensionMinHdStreamBitrate = extensionMinHdStreamBitrate;
		this.extensionMinHdStreamBitrateUpdatetype = extensionMinHdStreamBitrateUpdatetype;
		this.extensionMaxFastfill = extensionMaxFastfill;
		this.maxFastfillUpdatetype = maxFastfillUpdatetype;
		this.extensionAppDelay = extensionAppDelay;
		this.extensionAppDelayUpdatetype = extensionAppDelayUpdatetype;
		this.extensionSrcipFilterEnable = extensionSrcipFilterEnable;
		this.extensionSrcipFilterEnableUpdatetype = extensionSrcipFilterEnableUpdatetype;
		this.extension2MaxReceiveBandwidthSDRcc = extension2MaxReceiveBandwidthSDRcc;
		this.extension2MaxReceiveBandwidthSDRccUpdatetype = extension2MaxReceiveBandwidthSDRccUpdatetype;
		this.extension2MaxReceiveBandwidthHDRcc = extension2MaxReceiveBandwidthHDRcc;
		this.extension2MaxReceiveBandwidthHDRccUpdatetype = extension2MaxReceiveBandwidthHDRccUpdatetype;
		this.extension2QoeEnable = extension2QoeEnable;
		this.extension2QoeEnableUpdatetype = extension2QoeEnableUpdatetype;
		this.extension3RccExtraIgmpIp = extension3RccExtraIgmpIp;
		this.extension3RccExtraIgmpIpUpdatetype = extension3RccExtraIgmpIpUpdatetype;
		this.extension4LogLevel = extension4LogLevel;
		this.extension4LogLevelUpdatetype = extension4LogLevelUpdatetype;
		this.extension5ErrorRepairSmartRequestEnable = extension5ErrorRepairSmartRequestEnable;
		this.extension5ErrorRepairSmartRequestEnableUpdatetype = extension5ErrorRepairSmartRequestEnableUpdatetype;
		this.extension5ErrorRepairRepeatRequestEnable = extension5ErrorRepairRepeatRequestEnable;
		this.extension5ErrorRepairRepeatRequestEnableUpdatetype = extension5ErrorRepairRepeatRequestEnableUpdatetype;
		this.extension5RepairMinRoundTripTimeAbs = extension5RepairMinRoundTripTimeAbs;
		this.extension5RepairMinRoundTripTimeAbsUpdatetype = extension5RepairMinRoundTripTimeAbsUpdatetype;
		this.extension6ViewershipEnable = extension6ViewershipEnable;
		this.extension6ViewershipEnableUpdatetype = extension6ViewershipEnableUpdatetype;
		this.extension7PcrRestampEnable = extension7PcrRestampEnable;
		this.extension7PcrRestampEnableUpdatetype = extension7PcrRestampEnableUpdatetype;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the vqecEnable
	 */
	public Boolean getVqecEnable() {
		return vqecEnable;
	}

	/**
	 * @param vqecEnable the vqecEnable to set
	 */
	public void setVqecEnable(Boolean vqecEnable) {
		this.vqecEnable = vqecEnable;
	}

	/**
	 * @return the vqecEnableUpdatetype
	 */
	public String getVqecEnableUpdatetype() {
		return vqecEnableUpdatetype;
	}

	/**
	 * @param vqecEnableUpdatetype the vqecEnableUpdatetype to set
	 */
	public void setVqecEnableUpdatetype(String vqecEnableUpdatetype) {
		this.vqecEnableUpdatetype = vqecEnableUpdatetype;
	}

	/**
	 * @return the jitterBuffSize
	 */
	public Long getJitterBuffSize() {
		return jitterBuffSize;
	}

	/**
	 * @param jitterBuffSize the jitterBuffSize to set
	 */
	public void setJitterBuffSize(Long jitterBuffSize) {
		this.jitterBuffSize = jitterBuffSize;
	}

	/**
	 * @return the jitterBuffSizeUpdatetype
	 */
	public String getJitterBuffSizeUpdatetype() {
		return jitterBuffSizeUpdatetype;
	}

	/**
	 * @param jitterBuffSizeUpdatetype the jitterBuffSizeUpdatetype to set
	 */
	public void setJitterBuffSizeUpdatetype(String jitterBuffSizeUpdatetype) {
		this.jitterBuffSizeUpdatetype = jitterBuffSizeUpdatetype;
	}

	/**
	 * @return the repairTriggerPointAbs
	 */
	public Long getRepairTriggerPointAbs() {
		return repairTriggerPointAbs;
	}

	/**
	 * @param repairTriggerPointAbs the repairTriggerPointAbs to set
	 */
	public void setRepairTriggerPointAbs(Long repairTriggerPointAbs) {
		this.repairTriggerPointAbs = repairTriggerPointAbs;
	}

	/**
	 * @return the repairTriggerPointAbsUpdatetype
	 */
	public String getRepairTriggerPointAbsUpdatetype() {
		return repairTriggerPointAbsUpdatetype;
	}

	/**
	 * @param repairTriggerPointAbsUpdatetype the repairTriggerPointAbsUpdatetype to set
	 */
	public void setRepairTriggerPointAbsUpdatetype(String repairTriggerPointAbsUpdatetype) {
		this.repairTriggerPointAbsUpdatetype = repairTriggerPointAbsUpdatetype;
	}

	/**
	 * @return the pakpoolSize
	 */
	public Long getPakpoolSize() {
		return pakpoolSize;
	}

	/**
	 * @param pakpoolSize the pakpoolSize to set
	 */
	public void setPakpoolSize(Long pakpoolSize) {
		this.pakpoolSize = pakpoolSize;
	}

	/**
	 * @return the pakpoolSizeUpdatetype
	 */
	public String getPakpoolSizeUpdatetype() {
		return pakpoolSizeUpdatetype;
	}

	/**
	 * @param pakpoolSizeUpdatetype the pakpoolSizeUpdatetype to set
	 */
	public void setPakpoolSizeUpdatetype(String pakpoolSizeUpdatetype) {
		this.pakpoolSizeUpdatetype = pakpoolSizeUpdatetype;
	}

	/**
	 * @return the soRcvbuf
	 */
	public Long getSoRcvbuf() {
		return soRcvbuf;
	}

	/**
	 * @param soRcvbuf the soRcvbuf to set
	 */
	public void setSoRcvbuf(Long soRcvbuf) {
		this.soRcvbuf = soRcvbuf;
	}

	/**
	 * @return the soRcvbufUpdatetype
	 */
	public String getSoRcvbufUpdatetype() {
		return soRcvbufUpdatetype;
	}

	/**
	 * @param soRcvbufUpdatetype the soRcvbufUpdatetype to set
	 */
	public void setSoRcvbufUpdatetype(String soRcvbufUpdatetype) {
		this.soRcvbufUpdatetype = soRcvbufUpdatetype;
	}

	/**
	 * @return the sigMode
	 */
	public String getSigMode() {
		return sigMode;
	}

	/**
	 * @param sigMode the sigMode to set
	 */
	public void setSigMode(String sigMode) {
		this.sigMode = sigMode;
	}

	/**
	 * @return the sigModeUpdatetype
	 */
	public String getSigModeUpdatetype() {
		return sigModeUpdatetype;
	}

	/**
	 * @param sigModeUpdatetype the sigModeUpdatetype to set
	 */
	public void setSigModeUpdatetype(String sigModeUpdatetype) {
		this.sigModeUpdatetype = sigModeUpdatetype;
	}

	/**
	 * @return the natBindingRefreshInterval
	 */
	public Long getNatBindingRefreshInterval() {
		return natBindingRefreshInterval;
	}

	/**
	 * @param natBindingRefreshInterval the natBindingRefreshInterval to set
	 */
	public void setNatBindingRefreshInterval(Long natBindingRefreshInterval) {
		this.natBindingRefreshInterval = natBindingRefreshInterval;
	}

	/**
	 * @return the natBindingRefreshIntervalUpdatetype
	 */
	public String getNatBindingRefreshIntervalUpdatetype() {
		return natBindingRefreshIntervalUpdatetype;
	}

	/**
	 * @param natBindingRefreshIntervalUpdatetype the natBindingRefreshIntervalUpdatetype to set
	 */
	public void setNatBindingRefreshIntervalUpdatetype(String natBindingRefreshIntervalUpdatetype) {
		this.natBindingRefreshIntervalUpdatetype = natBindingRefreshIntervalUpdatetype;
	}

	/**
	 * @return the maxPaksize
	 */
	public Long getMaxPaksize() {
		return maxPaksize;
	}

	/**
	 * @param maxPaksize the maxPaksize to set
	 */
	public void setMaxPaksize(Long maxPaksize) {
		this.maxPaksize = maxPaksize;
	}

	/**
	 * @return the maxPaksizeUpdatetype
	 */
	public String getMaxPaksizeUpdatetype() {
		return maxPaksizeUpdatetype;
	}

	/**
	 * @param maxPaksizeUpdatetype the maxPaksizeUpdatetype to set
	 */
	public void setMaxPaksizeUpdatetype(String maxPaksizeUpdatetype) {
		this.maxPaksizeUpdatetype = maxPaksizeUpdatetype;
	}

	/**
	 * @return the libcliTelnetPort
	 */
	public Long getLibcliTelnetPort() {
		return libcliTelnetPort;
	}

	/**
	 * @param libcliTelnetPort the libcliTelnetPort to set
	 */
	public void setLibcliTelnetPort(Long libcliTelnetPort) {
		this.libcliTelnetPort = libcliTelnetPort;
	}

	/**
	 * @return the libcliTelnetPortUpdatetype
	 */
	public String getLibcliTelnetPortUpdatetype() {
		return libcliTelnetPortUpdatetype;
	}

	/**
	 * @param libcliTelnetPortUpdatetype the libcliTelnetPortUpdatetype to set
	 */
	public void setLibcliTelnetPortUpdatetype(String libcliTelnetPortUpdatetype) {
		this.libcliTelnetPortUpdatetype = libcliTelnetPortUpdatetype;
	}

	/**
	 * @return the outputPakqLimit
	 */
	public Long getOutputPakqLimit() {
		return outputPakqLimit;
	}

	/**
	 * @param outputPakqLimit the outputPakqLimit to set
	 */
	public void setOutputPakqLimit(Long outputPakqLimit) {
		this.outputPakqLimit = outputPakqLimit;
	}

	/**
	 * @return the outputPakqLimitUpdatetype
	 */
	public String getOutputPakqLimitUpdatetype() {
		return outputPakqLimitUpdatetype;
	}

	/**
	 * @param outputPakqLimitUpdatetype the outputPakqLimitUpdatetype to set
	 */
	public void setOutputPakqLimitUpdatetype(String outputPakqLimitUpdatetype) {
		this.outputPakqLimitUpdatetype = outputPakqLimitUpdatetype;
	}

	/**
	 * @return the updateWindow
	 */
	public Long getUpdateWindow() {
		return updateWindow;
	}

	/**
	 * @param updateWindow the updateWindow to set
	 */
	public void setUpdateWindow(Long updateWindow) {
		this.updateWindow = updateWindow;
	}

	/**
	 * @return the updateWindowUpdatetype
	 */
	public String getUpdateWindowUpdatetype() {
		return updateWindowUpdatetype;
	}

	/**
	 * @param updateWindowUpdatetype the updateWindowUpdatetype to set
	 */
	public void setUpdateWindowUpdatetype(String updateWindowUpdatetype) {
		this.updateWindowUpdatetype = updateWindowUpdatetype;
	}

	/**
	 * @return the updateIntervalMax
	 */
	public Long getUpdateIntervalMax() {
		return updateIntervalMax;
	}

	/**
	 * @param updateIntervalMax the updateIntervalMax to set
	 */
	public void setUpdateIntervalMax(Long updateIntervalMax) {
		this.updateIntervalMax = updateIntervalMax;
	}

	/**
	 * @return the updateIntervalMaxUpdatetype
	 */
	public String getUpdateIntervalMaxUpdatetype() {
		return updateIntervalMaxUpdatetype;
	}

	/**
	 * @param updateIntervalMaxUpdatetype the updateIntervalMaxUpdatetype to set
	 */
	public void setUpdateIntervalMaxUpdatetype(String updateIntervalMaxUpdatetype) {
		this.updateIntervalMaxUpdatetype = updateIntervalMaxUpdatetype;
	}

	/**
	 * @return the errorRepairEnable
	 */
	public Boolean getErrorRepairEnable() {
		return errorRepairEnable;
	}

	/**
	 * @param errorRepairEnable the errorRepairEnable to set
	 */
	public void setErrorRepairEnable(Boolean errorRepairEnable) {
		this.errorRepairEnable = errorRepairEnable;
	}

	/**
	 * @return the errorRepairEnableUpdatetype
	 */
	public String getErrorRepairEnableUpdatetype() {
		return errorRepairEnableUpdatetype;
	}

	/**
	 * @param errorRepairEnableUpdatetype the errorRepairEnableUpdatetype to set
	 */
	public void setErrorRepairEnableUpdatetype(String errorRepairEnableUpdatetype) {
		this.errorRepairEnableUpdatetype = errorRepairEnableUpdatetype;
	}

	/**
	 * @return the errorRepairPolicerEnable
	 */
	public Boolean getErrorRepairPolicerEnable() {
		return errorRepairPolicerEnable;
	}

	/**
	 * @param errorRepairPolicerEnable the errorRepairPolicerEnable to set
	 */
	public void setErrorRepairPolicerEnable(Boolean errorRepairPolicerEnable) {
		this.errorRepairPolicerEnable = errorRepairPolicerEnable;
	}

	/**
	 * @return the errorRepairPolicerEnableUpdatetype
	 */
	public String getErrorRepairPolicerEnableUpdatetype() {
		return errorRepairPolicerEnableUpdatetype;
	}

	/**
	 * @param errorRepairPolicerEnableUpdatetype the errorRepairPolicerEnableUpdatetype to set
	 */
	public void setErrorRepairPolicerEnableUpdatetype(String errorRepairPolicerEnableUpdatetype) {
		this.errorRepairPolicerEnableUpdatetype = errorRepairPolicerEnableUpdatetype;
	}

	/**
	 * @return the errorRepairPolicerRate
	 */
	public Long getErrorRepairPolicerRate() {
		return errorRepairPolicerRate;
	}

	/**
	 * @param errorRepairPolicerRate the errorRepairPolicerRate to set
	 */
	public void setErrorRepairPolicerRate(Long errorRepairPolicerRate) {
		this.errorRepairPolicerRate = errorRepairPolicerRate;
	}

	/**
	 * @return the errorRepairPolicerRateUpdatetype
	 */
	public String getErrorRepairPolicerRateUpdatetype() {
		return errorRepairPolicerRateUpdatetype;
	}

	/**
	 * @param errorRepairPolicerRateUpdatetype the errorRepairPolicerRateUpdatetype to set
	 */
	public void setErrorRepairPolicerRateUpdatetype(String errorRepairPolicerRateUpdatetype) {
		this.errorRepairPolicerRateUpdatetype = errorRepairPolicerRateUpdatetype;
	}

	/**
	 * @return the errorRepairPolicerBurst
	 */
	public Long getErrorRepairPolicerBurst() {
		return errorRepairPolicerBurst;
	}

	/**
	 * @param errorRepairPolicerBurst the errorRepairPolicerBurst to set
	 */
	public void setErrorRepairPolicerBurst(Long errorRepairPolicerBurst) {
		this.errorRepairPolicerBurst = errorRepairPolicerBurst;
	}

	/**
	 * @return the errorRepairPolicerBurstUpdatetype
	 */
	public String getErrorRepairPolicerBurstUpdatetype() {
		return errorRepairPolicerBurstUpdatetype;
	}

	/**
	 * @param errorRepairPolicerBurstUpdatetype the errorRepairPolicerBurstUpdatetype to set
	 */
	public void setErrorRepairPolicerBurstUpdatetype(String errorRepairPolicerBurstUpdatetype) {
		this.errorRepairPolicerBurstUpdatetype = errorRepairPolicerBurstUpdatetype;
	}

	/**
	 * @return the fecEnable
	 */
	public Boolean getFecEnable() {
		return fecEnable;
	}

	/**
	 * @param fecEnable the fecEnable to set
	 */
	public void setFecEnable(Boolean fecEnable) {
		this.fecEnable = fecEnable;
	}

	/**
	 * @return the fecEnableUpdatetype
	 */
	public String getFecEnableUpdatetype() {
		return fecEnableUpdatetype;
	}

	/**
	 * @param fecEnableUpdatetype the fecEnableUpdatetype to set
	 */
	public void setFecEnableUpdatetype(String fecEnableUpdatetype) {
		this.fecEnableUpdatetype = fecEnableUpdatetype;
	}

	/**
	 * @return the rccEnable
	 */
	public Boolean getRccEnable() {
		return rccEnable;
	}

	/**
	 * @param rccEnable the rccEnable to set
	 */
	public void setRccEnable(Boolean rccEnable) {
		this.rccEnable = rccEnable;
	}

	/**
	 * @return the rccEnableUpdatetype
	 */
	public String getRccEnableUpdatetype() {
		return rccEnableUpdatetype;
	}

	/**
	 * @param rccEnableUpdatetype the rccEnableUpdatetype to set
	 */
	public void setRccEnableUpdatetype(String rccEnableUpdatetype) {
		this.rccEnableUpdatetype = rccEnableUpdatetype;
	}

	/**
	 * @return the rccStartTimeout
	 */
	public Long getRccStartTimeout() {
		return rccStartTimeout;
	}

	/**
	 * @param rccStartTimeout the rccStartTimeout to set
	 */
	public void setRccStartTimeout(Long rccStartTimeout) {
		this.rccStartTimeout = rccStartTimeout;
	}

	/**
	 * @return the rccStartTimeoutUpdatetype
	 */
	public String getRccStartTimeoutUpdatetype() {
		return rccStartTimeoutUpdatetype;
	}

	/**
	 * @param rccStartTimeoutUpdatetype the rccStartTimeoutUpdatetype to set
	 */
	public void setRccStartTimeoutUpdatetype(String rccStartTimeoutUpdatetype) {
		this.rccStartTimeoutUpdatetype = rccStartTimeoutUpdatetype;
	}

	/**
	 * @return the reorderDelayAbs
	 */
	public Long getReorderDelayAbs() {
		return reorderDelayAbs;
	}

	/**
	 * @param reorderDelayAbs the reorderDelayAbs to set
	 */
	public void setReorderDelayAbs(Long reorderDelayAbs) {
		this.reorderDelayAbs = reorderDelayAbs;
	}

	/**
	 * @return the reorderDelayAbsUpdatetype
	 */
	public String getReorderDelayAbsUpdatetype() {
		return reorderDelayAbsUpdatetype;
	}

	/**
	 * @param reorderDelayAbsUpdatetype the reorderDelayAbsUpdatetype to set
	 */
	public void setReorderDelayAbsUpdatetype(String reorderDelayAbsUpdatetype) {
		this.reorderDelayAbsUpdatetype = reorderDelayAbsUpdatetype;
	}

	/**
	 * @return the cliIfname
	 */
	public String getCliIfname() {
		return cliIfname;
	}

	/**
	 * @param cliIfname the cliIfname to set
	 */
	public void setCliIfname(String cliIfname) {
		this.cliIfname = cliIfname;
	}

	/**
	 * @return the cliIfnameUpdatetype
	 */
	public String getCliIfnameUpdatetype() {
		return cliIfnameUpdatetype;
	}

	/**
	 * @param cliIfnameUpdatetype the cliIfnameUpdatetype to set
	 */
	public void setCliIfnameUpdatetype(String cliIfnameUpdatetype) {
		this.cliIfnameUpdatetype = cliIfnameUpdatetype;
	}

	/**
	 * @return the rtcpDscpValue
	 */
	public Long getRtcpDscpValue() {
		return rtcpDscpValue;
	}

	/**
	 * @param rtcpDscpValue the rtcpDscpValue to set
	 */
	public void setRtcpDscpValue(Long rtcpDscpValue) {
		this.rtcpDscpValue = rtcpDscpValue;
	}

	/**
	 * @return the rtcpDscpValueUpdatetype
	 */
	public String getRtcpDscpValueUpdatetype() {
		return rtcpDscpValueUpdatetype;
	}

	/**
	 * @param rtcpDscpValueUpdatetype the rtcpDscpValueUpdatetype to set
	 */
	public void setRtcpDscpValueUpdatetype(String rtcpDscpValueUpdatetype) {
		this.rtcpDscpValueUpdatetype = rtcpDscpValueUpdatetype;
	}

	/**
	 * @return the fastfillEnable
	 */
	public Boolean getFastfillEnable() {
		return fastfillEnable;
	}

	/**
	 * @param fastfillEnable the fastfillEnable to set
	 */
	public void setFastfillEnable(Boolean fastfillEnable) {
		this.fastfillEnable = fastfillEnable;
	}

	/**
	 * @return the fastfillEnableUpdatetype
	 */
	public String getFastfillEnableUpdatetype() {
		return fastfillEnableUpdatetype;
	}

	/**
	 * @param fastfillEnableUpdatetype the fastfillEnableUpdatetype to set
	 */
	public void setFastfillEnableUpdatetype(String fastfillEnableUpdatetype) {
		this.fastfillEnableUpdatetype = fastfillEnableUpdatetype;
	}

	/**
	 * @return the extensionMaxReceiveBandwidthSD
	 */
	public Long getExtensionMaxReceiveBandwidthSD() {
		return extensionMaxReceiveBandwidthSD;
	}

	/**
	 * @param extensionMaxReceiveBandwidthSD the extensionMaxReceiveBandwidthSD to set
	 */
	public void setExtensionMaxReceiveBandwidthSD(Long extensionMaxReceiveBandwidthSD) {
		this.extensionMaxReceiveBandwidthSD = extensionMaxReceiveBandwidthSD;
	}

	/**
	 * @return the extensionMaxReceiveBandwidthSDUpdatetype
	 */
	public String getExtensionMaxReceiveBandwidthSDUpdatetype() {
		return extensionMaxReceiveBandwidthSDUpdatetype;
	}

	/**
	 * @param extensionMaxReceiveBandwidthSDUpdatetype the extensionMaxReceiveBandwidthSDUpdatetype to set
	 */
	public void setExtensionMaxReceiveBandwidthSDUpdatetype(String extensionMaxReceiveBandwidthSDUpdatetype) {
		this.extensionMaxReceiveBandwidthSDUpdatetype = extensionMaxReceiveBandwidthSDUpdatetype;
	}

	/**
	 * @return the extensionMaxReceiveBandwidthHd
	 */
	public Long getExtensionMaxReceiveBandwidthHd() {
		return extensionMaxReceiveBandwidthHd;
	}

	/**
	 * @param extensionMaxReceiveBandwidthHd the extensionMaxReceiveBandwidthHd to set
	 */
	public void setExtensionMaxReceiveBandwidthHd(Long extensionMaxReceiveBandwidthHd) {
		this.extensionMaxReceiveBandwidthHd = extensionMaxReceiveBandwidthHd;
	}

	/**
	 * @return the extensionMaxReceiveBandwidthHdUpdatetype
	 */
	public String getExtensionMaxReceiveBandwidthHdUpdatetype() {
		return extensionMaxReceiveBandwidthHdUpdatetype;
	}

	/**
	 * @param extensionMaxReceiveBandwidthHdUpdatetype the extensionMaxReceiveBandwidthHdUpdatetype to set
	 */
	public void setExtensionMaxReceiveBandwidthHdUpdatetype(String extensionMaxReceiveBandwidthHdUpdatetype) {
		this.extensionMaxReceiveBandwidthHdUpdatetype = extensionMaxReceiveBandwidthHdUpdatetype;
	}

	/**
	 * @return the extensionMinHdStreamBitrate
	 */
	public Long getExtensionMinHdStreamBitrate() {
		return extensionMinHdStreamBitrate;
	}

	/**
	 * @param extensionMinHdStreamBitrate the extensionMinHdStreamBitrate to set
	 */
	public void setExtensionMinHdStreamBitrate(Long extensionMinHdStreamBitrate) {
		this.extensionMinHdStreamBitrate = extensionMinHdStreamBitrate;
	}

	/**
	 * @return the extensionMinHdStreamBitrateUpdatetype
	 */
	public String getExtensionMinHdStreamBitrateUpdatetype() {
		return extensionMinHdStreamBitrateUpdatetype;
	}

	/**
	 * @param extensionMinHdStreamBitrateUpdatetype the extensionMinHdStreamBitrateUpdatetype to set
	 */
	public void setExtensionMinHdStreamBitrateUpdatetype(String extensionMinHdStreamBitrateUpdatetype) {
		this.extensionMinHdStreamBitrateUpdatetype = extensionMinHdStreamBitrateUpdatetype;
	}

	/**
	 * @return the extensionMaxFastfill
	 */
	public Long getExtensionMaxFastfill() {
		return extensionMaxFastfill;
	}

	/**
	 * @param extensionMaxFastfill the extensionMaxFastfill to set
	 */
	public void setExtensionMaxFastfill(Long extensionMaxFastfill) {
		this.extensionMaxFastfill = extensionMaxFastfill;
	}

	/**
	 * @return the maxFastfillUpdatetype
	 */
	public String getMaxFastfillUpdatetype() {
		return maxFastfillUpdatetype;
	}

	/**
	 * @param maxFastfillUpdatetype the maxFastfillUpdatetype to set
	 */
	public void setMaxFastfillUpdatetype(String maxFastfillUpdatetype) {
		this.maxFastfillUpdatetype = maxFastfillUpdatetype;
	}

	/**
	 * @return the extensionAppDelay
	 */
	public Long getExtensionAppDelay() {
		return extensionAppDelay;
	}

	/**
	 * @param extensionAppDelay the extensionAppDelay to set
	 */
	public void setExtensionAppDelay(Long extensionAppDelay) {
		this.extensionAppDelay = extensionAppDelay;
	}

	/**
	 * @return the extensionAppDelayUpdatetype
	 */
	public String getExtensionAppDelayUpdatetype() {
		return extensionAppDelayUpdatetype;
	}

	/**
	 * @param extensionAppDelayUpdatetype the extensionAppDelayUpdatetype to set
	 */
	public void setExtensionAppDelayUpdatetype(String extensionAppDelayUpdatetype) {
		this.extensionAppDelayUpdatetype = extensionAppDelayUpdatetype;
	}

	/**
	 * @return the extensionSrcipFilterEnable
	 */
	public Boolean getExtensionSrcipFilterEnable() {
		return extensionSrcipFilterEnable;
	}

	/**
	 * @param extensionSrcipFilterEnable the extensionSrcipFilterEnable to set
	 */
	public void setExtensionSrcipFilterEnable(Boolean extensionSrcipFilterEnable) {
		this.extensionSrcipFilterEnable = extensionSrcipFilterEnable;
	}

	/**
	 * @return the extensionSrcipFilterEnableUpdatetype
	 */
	public String getExtensionSrcipFilterEnableUpdatetype() {
		return extensionSrcipFilterEnableUpdatetype;
	}

	/**
	 * @param extensionSrcipFilterEnableUpdatetype the extensionSrcipFilterEnableUpdatetype to set
	 */
	public void setExtensionSrcipFilterEnableUpdatetype(String extensionSrcipFilterEnableUpdatetype) {
		this.extensionSrcipFilterEnableUpdatetype = extensionSrcipFilterEnableUpdatetype;
	}

	/**
	 * @return the extension2MaxReceiveBandwidthSDRcc
	 */
	public Long getExtension2MaxReceiveBandwidthSDRcc() {
		return extension2MaxReceiveBandwidthSDRcc;
	}

	/**
	 * @param extension2MaxReceiveBandwidthSDRcc the extension2MaxReceiveBandwidthSDRcc to set
	 */
	public void setExtension2MaxReceiveBandwidthSDRcc(Long extension2MaxReceiveBandwidthSDRcc) {
		this.extension2MaxReceiveBandwidthSDRcc = extension2MaxReceiveBandwidthSDRcc;
	}

	/**
	 * @return the extension2MaxReceiveBandwidthSDRccUpdatetype
	 */
	public String getExtension2MaxReceiveBandwidthSDRccUpdatetype() {
		return extension2MaxReceiveBandwidthSDRccUpdatetype;
	}

	/**
	 * @param extension2MaxReceiveBandwidthSDRccUpdatetype the extension2MaxReceiveBandwidthSDRccUpdatetype to set
	 */
	public void setExtension2MaxReceiveBandwidthSDRccUpdatetype(String extension2MaxReceiveBandwidthSDRccUpdatetype) {
		this.extension2MaxReceiveBandwidthSDRccUpdatetype = extension2MaxReceiveBandwidthSDRccUpdatetype;
	}

	/**
	 * @return the extension2MaxReceiveBandwidthHDRcc
	 */
	public Long getExtension2MaxReceiveBandwidthHDRcc() {
		return extension2MaxReceiveBandwidthHDRcc;
	}

	/**
	 * @param extension2MaxReceiveBandwidthHDRcc the extension2MaxReceiveBandwidthHDRcc to set
	 */
	public void setExtension2MaxReceiveBandwidthHDRcc(Long extension2MaxReceiveBandwidthHDRcc) {
		this.extension2MaxReceiveBandwidthHDRcc = extension2MaxReceiveBandwidthHDRcc;
	}

	/**
	 * @return the extension2MaxReceiveBandwidthHDRccUpdatetype
	 */
	public String getExtension2MaxReceiveBandwidthHDRccUpdatetype() {
		return extension2MaxReceiveBandwidthHDRccUpdatetype;
	}

	/**
	 * @param extension2MaxReceiveBandwidthHDRccUpdatetype the extension2MaxReceiveBandwidthHDRccUpdatetype to set
	 */
	public void setExtension2MaxReceiveBandwidthHDRccUpdatetype(String extension2MaxReceiveBandwidthHDRccUpdatetype) {
		this.extension2MaxReceiveBandwidthHDRccUpdatetype = extension2MaxReceiveBandwidthHDRccUpdatetype;
	}

	/**
	 * @return the extension2QoeEnable
	 */
	public Boolean getExtension2QoeEnable() {
		return extension2QoeEnable;
	}

	/**
	 * @param extension2QoeEnable the extension2QoeEnable to set
	 */
	public void setExtension2QoeEnable(Boolean extension2QoeEnable) {
		this.extension2QoeEnable = extension2QoeEnable;
	}

	/**
	 * @return the extension2QoeEnableUpdatetype
	 */
	public String getExtension2QoeEnableUpdatetype() {
		return extension2QoeEnableUpdatetype;
	}

	/**
	 * @param extension2QoeEnableUpdatetype the extension2QoeEnableUpdatetype to set
	 */
	public void setExtension2QoeEnableUpdatetype(String extension2QoeEnableUpdatetype) {
		this.extension2QoeEnableUpdatetype = extension2QoeEnableUpdatetype;
	}

	/**
	 * @return the extension3RccExtraIgmpIp
	 */
	public String getExtension3RccExtraIgmpIp() {
		return extension3RccExtraIgmpIp;
	}

	/**
	 * @param extension3RccExtraIgmpIp the extension3RccExtraIgmpIp to set
	 */
	public void setExtension3RccExtraIgmpIp(String extension3RccExtraIgmpIp) {
		this.extension3RccExtraIgmpIp = extension3RccExtraIgmpIp;
	}

	/**
	 * @return the extension3RccExtraIgmpIpUpdatetype
	 */
	public String getExtension3RccExtraIgmpIpUpdatetype() {
		return extension3RccExtraIgmpIpUpdatetype;
	}

	/**
	 * @param extension3RccExtraIgmpIpUpdatetype the extension3RccExtraIgmpIpUpdatetype to set
	 */
	public void setExtension3RccExtraIgmpIpUpdatetype(String extension3RccExtraIgmpIpUpdatetype) {
		this.extension3RccExtraIgmpIpUpdatetype = extension3RccExtraIgmpIpUpdatetype;
	}

	/**
	 * @return the extension4LogLevel
	 */
	public Integer getExtension4LogLevel() {
		return extension4LogLevel;
	}

	/**
	 * @param extension4LogLevel the extension4LogLevel to set
	 */
	public void setExtension4LogLevel(Integer extension4LogLevel) {
		this.extension4LogLevel = extension4LogLevel;
	}

	/**
	 * @return the extension4LogLevelUpdatetype
	 */
	public String getExtension4LogLevelUpdatetype() {
		return extension4LogLevelUpdatetype;
	}

	/**
	 * @param extension4LogLevelUpdatetype the extension4LogLevelUpdatetype to set
	 */
	public void setExtension4LogLevelUpdatetype(String extension4LogLevelUpdatetype) {
		this.extension4LogLevelUpdatetype = extension4LogLevelUpdatetype;
	}

	/**
	 * @return the extension5ErrorRepairSmartRequestEnable
	 */
	public Boolean getExtension5ErrorRepairSmartRequestEnable() {
		return extension5ErrorRepairSmartRequestEnable;
	}

	/**
	 * @param extension5ErrorRepairSmartRequestEnable the extension5ErrorRepairSmartRequestEnable to set
	 */
	public void setExtension5ErrorRepairSmartRequestEnable(Boolean extension5ErrorRepairSmartRequestEnable) {
		this.extension5ErrorRepairSmartRequestEnable = extension5ErrorRepairSmartRequestEnable;
	}

	/**
	 * @return the extension5ErrorRepairSmartRequestEnableUpdatetype
	 */
	public String getExtension5ErrorRepairSmartRequestEnableUpdatetype() {
		return extension5ErrorRepairSmartRequestEnableUpdatetype;
	}

	/**
	 * @param extension5ErrorRepairSmartRequestEnableUpdatetype the extension5ErrorRepairSmartRequestEnableUpdatetype to set
	 */
	public void setExtension5ErrorRepairSmartRequestEnableUpdatetype(
			String extension5ErrorRepairSmartRequestEnableUpdatetype) {
		this.extension5ErrorRepairSmartRequestEnableUpdatetype = extension5ErrorRepairSmartRequestEnableUpdatetype;
	}

	/**
	 * @return the extension5ErrorRepairRepeatRequestEnable
	 */
	public Boolean getExtension5ErrorRepairRepeatRequestEnable() {
		return extension5ErrorRepairRepeatRequestEnable;
	}

	/**
	 * @param extension5ErrorRepairRepeatRequestEnable the extension5ErrorRepairRepeatRequestEnable to set
	 */
	public void setExtension5ErrorRepairRepeatRequestEnable(Boolean extension5ErrorRepairRepeatRequestEnable) {
		this.extension5ErrorRepairRepeatRequestEnable = extension5ErrorRepairRepeatRequestEnable;
	}

	/**
	 * @return the extension5ErrorRepairRepeatRequestEnableUpdatetype
	 */
	public String getExtension5ErrorRepairRepeatRequestEnableUpdatetype() {
		return extension5ErrorRepairRepeatRequestEnableUpdatetype;
	}

	/**
	 * @param extension5ErrorRepairRepeatRequestEnableUpdatetype the extension5ErrorRepairRepeatRequestEnableUpdatetype to set
	 */
	public void setExtension5ErrorRepairRepeatRequestEnableUpdatetype(
			String extension5ErrorRepairRepeatRequestEnableUpdatetype) {
		this.extension5ErrorRepairRepeatRequestEnableUpdatetype = extension5ErrorRepairRepeatRequestEnableUpdatetype;
	}

	/**
	 * @return the extension5RepairMinRoundTripTimeAbs
	 */
	public Long getExtension5RepairMinRoundTripTimeAbs() {
		return extension5RepairMinRoundTripTimeAbs;
	}

	/**
	 * @param extension5RepairMinRoundTripTimeAbs the extension5RepairMinRoundTripTimeAbs to set
	 */
	public void setExtension5RepairMinRoundTripTimeAbs(Long extension5RepairMinRoundTripTimeAbs) {
		this.extension5RepairMinRoundTripTimeAbs = extension5RepairMinRoundTripTimeAbs;
	}

	/**
	 * @return the extension5RepairMinRoundTripTimeAbsUpdatetype
	 */
	public String getExtension5RepairMinRoundTripTimeAbsUpdatetype() {
		return extension5RepairMinRoundTripTimeAbsUpdatetype;
	}

	/**
	 * @param extension5RepairMinRoundTripTimeAbsUpdatetype the extension5RepairMinRoundTripTimeAbsUpdatetype to set
	 */
	public void setExtension5RepairMinRoundTripTimeAbsUpdatetype(String extension5RepairMinRoundTripTimeAbsUpdatetype) {
		this.extension5RepairMinRoundTripTimeAbsUpdatetype = extension5RepairMinRoundTripTimeAbsUpdatetype;
	}

	/**
	 * @return the extension6ViewershipEnable
	 */
	public Boolean getExtension6ViewershipEnable() {
		return extension6ViewershipEnable;
	}

	/**
	 * @param extension6ViewershipEnable the extension6ViewershipEnable to set
	 */
	public void setExtension6ViewershipEnable(Boolean extension6ViewershipEnable) {
		this.extension6ViewershipEnable = extension6ViewershipEnable;
	}

	/**
	 * @return the extension6ViewershipEnableUpdatetype
	 */
	public String getExtension6ViewershipEnableUpdatetype() {
		return extension6ViewershipEnableUpdatetype;
	}

	/**
	 * @param extension6ViewershipEnableUpdatetype the extension6ViewershipEnableUpdatetype to set
	 */
	public void setExtension6ViewershipEnableUpdatetype(String extension6ViewershipEnableUpdatetype) {
		this.extension6ViewershipEnableUpdatetype = extension6ViewershipEnableUpdatetype;
	}

	/**
	 * @return the extension7PcrRestampEnable
	 */
	public Boolean getExtension7PcrRestampEnable() {
		return extension7PcrRestampEnable;
	}

	/**
	 * @param extension7PcrRestampEnable the extension7PcrRestampEnable to set
	 */
	public void setExtension7PcrRestampEnable(Boolean extension7PcrRestampEnable) {
		this.extension7PcrRestampEnable = extension7PcrRestampEnable;
	}

	/**
	 * @return the extension7PcrRestampEnableUpdatetype
	 */
	public String getExtension7PcrRestampEnableUpdatetype() {
		return extension7PcrRestampEnableUpdatetype;
	}

	/**
	 * @param extension7PcrRestampEnableUpdatetype the extension7PcrRestampEnableUpdatetype to set
	 */
	public void setExtension7PcrRestampEnableUpdatetype(String extension7PcrRestampEnableUpdatetype) {
		this.extension7PcrRestampEnableUpdatetype = extension7PcrRestampEnableUpdatetype;
	}

	

}
