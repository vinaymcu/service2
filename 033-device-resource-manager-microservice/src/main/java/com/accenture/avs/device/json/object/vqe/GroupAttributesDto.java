
package com.accenture.avs.device.json.object.vqe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "vqecEnable",
    "vqecEnableUpdateType",
    "jitterBuffSize",
    "jitterBuffSizeUpdateType",
    "repairTriggerPointAbs",
    "repairTriggerPointAbsUpdateType",
    "pakpoolSize",
    "pakpoolSizeUpdateType",
    "soRcvbuf",
    "soRcvbufUpdateType",
    "sigMode",
    "sigModeUpdateType",
    "natBindingRefreshInterval",
    "natBindingRefreshIntervalUpdateType",
    "maxPaksize",
    "maxPaksizeUpdateType",
    "libcliTelnetPort",
    "libcliTelnetPortUpdateType",
    "outputPakqLimit",
    "outputPakqLimitUpdateType",
    "updateWindow",
    "updateWindowUpdateType",
    "updateIntervalMax",
    "updateIntervalMaxUpdateType",
    "errorRepairEnable",
    "errorRepairEnableUpdateType",
    "errorRepairPolicerEnable",
    "errorRepairPolicerEnableUpdateType",
    "errorRepairPolicerRate",
    "errorRepairPolicerRateUpdateType",
    "errorRepairPolicerBurst",
    "errorRepairPolicerBurstUpdateType",
    "fecEnable",
    "fecEnableUpdateType",
    "rccEnable",
    "rccEnableUpdateType",
    "rccStartTimeout",
    "rccStartTimeoutUpdateType",
    "reorderDelayAbs",
    "reorderDelayAbsUpdateType",
    "cliIfname",
    "cliIfnameUpdateType",
    "rtcpDscpValue",
    "rtcpDscpValueUpdateType",
    "fastfillEnable",
    "fastfillEnableUpdateType",
    "extension"
})
public class GroupAttributesDto implements Serializable
{

    @JsonProperty("vqecEnable")
    private Boolean vqecEnable;
    @JsonProperty("vqecEnableUpdateType")
    private String vqecEnableUpdateType;
    @JsonProperty("jitterBuffSize")
    private Long jitterBuffSize;
    @JsonProperty("jitterBuffSizeUpdateType")
    private String jitterBuffSizeUpdateType;
    @JsonProperty("repairTriggerPointAbs")
    private Long repairTriggerPointAbs;
    @JsonProperty("repairTriggerPointAbsUpdateType")
    private String repairTriggerPointAbsUpdateType;
    @JsonProperty("pakpoolSize")
    private Long pakpoolSize;
    @JsonProperty("pakpoolSizeUpdateType")
    private String pakpoolSizeUpdateType;
    @JsonProperty("soRcvbuf")
    private Long soRcvbuf;
    @JsonProperty("soRcvbufUpdateType")
    private String soRcvbufUpdateType;
    @JsonProperty("sigMode")
    private GroupAttributesDto.SigMode sigMode;
    @JsonProperty("sigModeUpdateType")
    private String sigModeUpdateType;
    @JsonProperty("natBindingRefreshInterval")
    private Long natBindingRefreshInterval;
    @JsonProperty("natBindingRefreshIntervalUpdateType")
    private String natBindingRefreshIntervalUpdateType;
    @JsonProperty("maxPaksize")
    private Long maxPaksize;
    @JsonProperty("maxPaksizeUpdateType")
    private String maxPaksizeUpdateType;
    @JsonProperty("libcliTelnetPort")
    private Long libcliTelnetPort;
    @JsonProperty("libcliTelnetPortUpdateType")
    private String libcliTelnetPortUpdateType;
    @JsonProperty("outputPakqLimit")
    private Long outputPakqLimit;
    @JsonProperty("outputPakqLimitUpdateType")
    private String outputPakqLimitUpdateType;
    @JsonProperty("updateWindow")
    private Long updateWindow;
    @JsonProperty("updateWindowUpdateType")
    private String updateWindowUpdateType;
    @JsonProperty("updateIntervalMax")
    private Long updateIntervalMax;
    @JsonProperty("updateIntervalMaxUpdateType")
    private String updateIntervalMaxUpdateType;
    @JsonProperty("errorRepairEnable")
    private Boolean errorRepairEnable;
    @JsonProperty("errorRepairEnableUpdateType")
    private String errorRepairEnableUpdateType;
    @JsonProperty("errorRepairPolicerEnable")
    private Boolean errorRepairPolicerEnable;
    @JsonProperty("errorRepairPolicerEnableUpdateType")
    private String errorRepairPolicerEnableUpdateType;
    @JsonProperty("errorRepairPolicerRate")
    private Long errorRepairPolicerRate;
    @JsonProperty("errorRepairPolicerRateUpdateType")
    private String errorRepairPolicerRateUpdateType;
    @JsonProperty("errorRepairPolicerBurst")
    private Long errorRepairPolicerBurst;
    @JsonProperty("errorRepairPolicerBurstUpdateType")
    private String errorRepairPolicerBurstUpdateType;
    @JsonProperty("fecEnable")
    private Boolean fecEnable;
    @JsonProperty("fecEnableUpdateType")
    private String fecEnableUpdateType;
    @JsonProperty("rccEnable")
    private Boolean rccEnable;
    @JsonProperty("rccEnableUpdateType")
    private String rccEnableUpdateType;
    @JsonProperty("rccStartTimeout")
    private Long rccStartTimeout;
    @JsonProperty("rccStartTimeoutUpdateType")
    private String rccStartTimeoutUpdateType;
    @JsonProperty("reorderDelayAbs")
    private Long reorderDelayAbs;
    @JsonProperty("reorderDelayAbsUpdateType")
    private String reorderDelayAbsUpdateType;
    @JsonProperty("cliIfname")
    private String cliIfname;
    @JsonProperty("cliIfnameUpdateType")
    private String cliIfnameUpdateType;
    @JsonProperty("rtcpDscpValue")
    private Long rtcpDscpValue;
    @JsonProperty("rtcpDscpValueUpdateType")
    private String rtcpDscpValueUpdateType;
    @JsonProperty("fastfillEnable")
    private Boolean fastfillEnable;
    @JsonProperty("fastfillEnableUpdateType")
    private String fastfillEnableUpdateType;
    @JsonProperty("extension")
    private Extension extension;
    private final static long serialVersionUID = -7496570375344625205L;

    /**
     * 
     * @return
     *     The vqecEnable
     */
    @JsonProperty("vqecEnable")
    public Boolean getVqecEnable() {
        return vqecEnable;
    }

    /**
     * 
     * @param vqecEnable
     *     The vqecEnable
     */
    @JsonProperty("vqecEnable")
    public void setVqecEnable(Boolean vqecEnable) {
        this.vqecEnable = vqecEnable;
    }

    /**
     * 
     * @return
     *     The vqecEnableUpdateType
     */
    @JsonProperty("vqecEnableUpdateType")
    public String getVqecEnableUpdateType() {
        return vqecEnableUpdateType;
    }

    /**
     * 
     * @param vqecEnableUpdateType
     *     The vqecEnableUpdateType
     */
    @JsonProperty("vqecEnableUpdateType")
    public void setVqecEnableUpdateType(String vqecEnableUpdateType) {
        this.vqecEnableUpdateType = vqecEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The jitterBuffSize
     */
    @JsonProperty("jitterBuffSize")
    public Long getJitterBuffSize() {
        return jitterBuffSize;
    }

    /**
     * 
     * @param jitterBuffSize
     *     The jitterBuffSize
     */
    @JsonProperty("jitterBuffSize")
    public void setJitterBuffSize(Long jitterBuffSize) {
        this.jitterBuffSize = jitterBuffSize;
    }

    /**
     * 
     * @return
     *     The jitterBuffSizeUpdateType
     */
    @JsonProperty("jitterBuffSizeUpdateType")
    public String getJitterBuffSizeUpdateType() {
        return jitterBuffSizeUpdateType;
    }

    /**
     * 
     * @param jitterBuffSizeUpdateType
     *     The jitterBuffSizeUpdateType
     */
    @JsonProperty("jitterBuffSizeUpdateType")
    public void setJitterBuffSizeUpdateType(String jitterBuffSizeUpdateType) {
        this.jitterBuffSizeUpdateType = jitterBuffSizeUpdateType;
    }

    /**
     * 
     * @return
     *     The repairTriggerPointAbs
     */
    @JsonProperty("repairTriggerPointAbs")
    public Long getRepairTriggerPointAbs() {
        return repairTriggerPointAbs;
    }

    /**
     * 
     * @param repairTriggerPointAbs
     *     The repairTriggerPointAbs
     */
    @JsonProperty("repairTriggerPointAbs")
    public void setRepairTriggerPointAbs(Long repairTriggerPointAbs) {
        this.repairTriggerPointAbs = repairTriggerPointAbs;
    }

    /**
     * 
     * @return
     *     The repairTriggerPointAbsUpdateType
     */
    @JsonProperty("repairTriggerPointAbsUpdateType")
    public String getRepairTriggerPointAbsUpdateType() {
        return repairTriggerPointAbsUpdateType;
    }

    /**
     * 
     * @param repairTriggerPointAbsUpdateType
     *     The repairTriggerPointAbsUpdateType
     */
    @JsonProperty("repairTriggerPointAbsUpdateType")
    public void setRepairTriggerPointAbsUpdateType(String repairTriggerPointAbsUpdateType) {
        this.repairTriggerPointAbsUpdateType = repairTriggerPointAbsUpdateType;
    }

    /**
     * 
     * @return
     *     The pakpoolSize
     */
    @JsonProperty("pakpoolSize")
    public Long getPakpoolSize() {
        return pakpoolSize;
    }

    /**
     * 
     * @param pakpoolSize
     *     The pakpoolSize
     */
    @JsonProperty("pakpoolSize")
    public void setPakpoolSize(Long pakpoolSize) {
        this.pakpoolSize = pakpoolSize;
    }

    /**
     * 
     * @return
     *     The pakpoolSizeUpdateType
     */
    @JsonProperty("pakpoolSizeUpdateType")
    public String getPakpoolSizeUpdateType() {
        return pakpoolSizeUpdateType;
    }

    /**
     * 
     * @param pakpoolSizeUpdateType
     *     The pakpoolSizeUpdateType
     */
    @JsonProperty("pakpoolSizeUpdateType")
    public void setPakpoolSizeUpdateType(String pakpoolSizeUpdateType) {
        this.pakpoolSizeUpdateType = pakpoolSizeUpdateType;
    }

    /**
     * 
     * @return
     *     The soRcvbuf
     */
    @JsonProperty("soRcvbuf")
    public Long getSoRcvbuf() {
        return soRcvbuf;
    }

    /**
     * 
     * @param soRcvbuf
     *     The soRcvbuf
     */
    @JsonProperty("soRcvbuf")
    public void setSoRcvbuf(Long soRcvbuf) {
        this.soRcvbuf = soRcvbuf;
    }

    /**
     * 
     * @return
     *     The soRcvbufUpdateType
     */
    @JsonProperty("soRcvbufUpdateType")
    public String getSoRcvbufUpdateType() {
        return soRcvbufUpdateType;
    }

    /**
     * 
     * @param soRcvbufUpdateType
     *     The soRcvbufUpdateType
     */
    @JsonProperty("soRcvbufUpdateType")
    public void setSoRcvbufUpdateType(String soRcvbufUpdateType) {
        this.soRcvbufUpdateType = soRcvbufUpdateType;
    }

    /**
     * 
     * @return
     *     The sigMode
     */
    @JsonProperty("sigMode")
    public GroupAttributesDto.SigMode getSigMode() {
        return sigMode;
    }

    /**
     * 
     * @param sigMode
     *     The sigMode
     */
    @JsonProperty("sigMode")
    public void setSigMode(GroupAttributesDto.SigMode sigMode) {
        this.sigMode = sigMode;
    }

    /**
     * 
     * @return
     *     The sigModeUpdateType
     */
    @JsonProperty("sigModeUpdateType")
    public String getSigModeUpdateType() {
        return sigModeUpdateType;
    }

    /**
     * 
     * @param sigModeUpdateType
     *     The sigModeUpdateType
     */
    @JsonProperty("sigModeUpdateType")
    public void setSigModeUpdateType(String sigModeUpdateType) {
        this.sigModeUpdateType = sigModeUpdateType;
    }

    /**
     * 
     * @return
     *     The natBindingRefreshInterval
     */
    @JsonProperty("natBindingRefreshInterval")
    public Long getNatBindingRefreshInterval() {
        return natBindingRefreshInterval;
    }

    /**
     * 
     * @param natBindingRefreshInterval
     *     The natBindingRefreshInterval
     */
    @JsonProperty("natBindingRefreshInterval")
    public void setNatBindingRefreshInterval(Long natBindingRefreshInterval) {
        this.natBindingRefreshInterval = natBindingRefreshInterval;
    }

    /**
     * 
     * @return
     *     The natBindingRefreshIntervalUpdateType
     */
    @JsonProperty("natBindingRefreshIntervalUpdateType")
    public String getNatBindingRefreshIntervalUpdateType() {
        return natBindingRefreshIntervalUpdateType;
    }

    /**
     * 
     * @param natBindingRefreshIntervalUpdateType
     *     The natBindingRefreshIntervalUpdateType
     */
    @JsonProperty("natBindingRefreshIntervalUpdateType")
    public void setNatBindingRefreshIntervalUpdateType(String natBindingRefreshIntervalUpdateType) {
        this.natBindingRefreshIntervalUpdateType = natBindingRefreshIntervalUpdateType;
    }

    /**
     * 
     * @return
     *     The maxPaksize
     */
    @JsonProperty("maxPaksize")
    public Long getMaxPaksize() {
        return maxPaksize;
    }

    /**
     * 
     * @param maxPaksize
     *     The maxPaksize
     */
    @JsonProperty("maxPaksize")
    public void setMaxPaksize(Long maxPaksize) {
        this.maxPaksize = maxPaksize;
    }

    /**
     * 
     * @return
     *     The maxPaksizeUpdateType
     */
    @JsonProperty("maxPaksizeUpdateType")
    public String getMaxPaksizeUpdateType() {
        return maxPaksizeUpdateType;
    }

    /**
     * 
     * @param maxPaksizeUpdateType
     *     The maxPaksizeUpdateType
     */
    @JsonProperty("maxPaksizeUpdateType")
    public void setMaxPaksizeUpdateType(String maxPaksizeUpdateType) {
        this.maxPaksizeUpdateType = maxPaksizeUpdateType;
    }

    /**
     * 
     * @return
     *     The libcliTelnetPort
     */
    @JsonProperty("libcliTelnetPort")
    public Long getLibcliTelnetPort() {
        return libcliTelnetPort;
    }

    /**
     * 
     * @param libcliTelnetPort
     *     The libcliTelnetPort
     */
    @JsonProperty("libcliTelnetPort")
    public void setLibcliTelnetPort(Long libcliTelnetPort) {
        this.libcliTelnetPort = libcliTelnetPort;
    }

    /**
     * 
     * @return
     *     The libcliTelnetPortUpdateType
     */
    @JsonProperty("libcliTelnetPortUpdateType")
    public String getLibcliTelnetPortUpdateType() {
        return libcliTelnetPortUpdateType;
    }

    /**
     * 
     * @param libcliTelnetPortUpdateType
     *     The libcliTelnetPortUpdateType
     */
    @JsonProperty("libcliTelnetPortUpdateType")
    public void setLibcliTelnetPortUpdateType(String libcliTelnetPortUpdateType) {
        this.libcliTelnetPortUpdateType = libcliTelnetPortUpdateType;
    }

    /**
     * 
     * @return
     *     The outputPakqLimit
     */
    @JsonProperty("outputPakqLimit")
    public Long getOutputPakqLimit() {
        return outputPakqLimit;
    }

    /**
     * 
     * @param outputPakqLimit
     *     The outputPakqLimit
     */
    @JsonProperty("outputPakqLimit")
    public void setOutputPakqLimit(Long outputPakqLimit) {
        this.outputPakqLimit = outputPakqLimit;
    }

    /**
     * 
     * @return
     *     The outputPakqLimitUpdateType
     */
    @JsonProperty("outputPakqLimitUpdateType")
    public String getOutputPakqLimitUpdateType() {
        return outputPakqLimitUpdateType;
    }

    /**
     * 
     * @param outputPakqLimitUpdateType
     *     The outputPakqLimitUpdateType
     */
    @JsonProperty("outputPakqLimitUpdateType")
    public void setOutputPakqLimitUpdateType(String outputPakqLimitUpdateType) {
        this.outputPakqLimitUpdateType = outputPakqLimitUpdateType;
    }

    /**
     * 
     * @return
     *     The updateWindow
     */
    @JsonProperty("updateWindow")
    public Long getUpdateWindow() {
        return updateWindow;
    }

    /**
     * 
     * @param updateWindow
     *     The updateWindow
     */
    @JsonProperty("updateWindow")
    public void setUpdateWindow(Long updateWindow) {
        this.updateWindow = updateWindow;
    }

    /**
     * 
     * @return
     *     The updateWindowUpdateType
     */
    @JsonProperty("updateWindowUpdateType")
    public String getUpdateWindowUpdateType() {
        return updateWindowUpdateType;
    }

    /**
     * 
     * @param updateWindowUpdateType
     *     The updateWindowUpdateType
     */
    @JsonProperty("updateWindowUpdateType")
    public void setUpdateWindowUpdateType(String updateWindowUpdateType) {
        this.updateWindowUpdateType = updateWindowUpdateType;
    }

    /**
     * 
     * @return
     *     The updateIntervalMax
     */
    @JsonProperty("updateIntervalMax")
    public Long getUpdateIntervalMax() {
        return updateIntervalMax;
    }

    /**
     * 
     * @param updateIntervalMax
     *     The updateIntervalMax
     */
    @JsonProperty("updateIntervalMax")
    public void setUpdateIntervalMax(Long updateIntervalMax) {
        this.updateIntervalMax = updateIntervalMax;
    }

    /**
     * 
     * @return
     *     The updateIntervalMaxUpdateType
     */
    @JsonProperty("updateIntervalMaxUpdateType")
    public String getUpdateIntervalMaxUpdateType() {
        return updateIntervalMaxUpdateType;
    }

    /**
     * 
     * @param updateIntervalMaxUpdateType
     *     The updateIntervalMaxUpdateType
     */
    @JsonProperty("updateIntervalMaxUpdateType")
    public void setUpdateIntervalMaxUpdateType(String updateIntervalMaxUpdateType) {
        this.updateIntervalMaxUpdateType = updateIntervalMaxUpdateType;
    }

    /**
     * 
     * @return
     *     The errorRepairEnable
     */
    @JsonProperty("errorRepairEnable")
    public Boolean getErrorRepairEnable() {
        return errorRepairEnable;
    }

    /**
     * 
     * @param errorRepairEnable
     *     The errorRepairEnable
     */
    @JsonProperty("errorRepairEnable")
    public void setErrorRepairEnable(Boolean errorRepairEnable) {
        this.errorRepairEnable = errorRepairEnable;
    }

    /**
     * 
     * @return
     *     The errorRepairEnableUpdateType
     */
    @JsonProperty("errorRepairEnableUpdateType")
    public String getErrorRepairEnableUpdateType() {
        return errorRepairEnableUpdateType;
    }

    /**
     * 
     * @param errorRepairEnableUpdateType
     *     The errorRepairEnableUpdateType
     */
    @JsonProperty("errorRepairEnableUpdateType")
    public void setErrorRepairEnableUpdateType(String errorRepairEnableUpdateType) {
        this.errorRepairEnableUpdateType = errorRepairEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerEnable
     */
    @JsonProperty("errorRepairPolicerEnable")
    public Boolean getErrorRepairPolicerEnable() {
        return errorRepairPolicerEnable;
    }

    /**
     * 
     * @param errorRepairPolicerEnable
     *     The errorRepairPolicerEnable
     */
    @JsonProperty("errorRepairPolicerEnable")
    public void setErrorRepairPolicerEnable(Boolean errorRepairPolicerEnable) {
        this.errorRepairPolicerEnable = errorRepairPolicerEnable;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerEnableUpdateType
     */
    @JsonProperty("errorRepairPolicerEnableUpdateType")
    public String getErrorRepairPolicerEnableUpdateType() {
        return errorRepairPolicerEnableUpdateType;
    }

    /**
     * 
     * @param errorRepairPolicerEnableUpdateType
     *     The errorRepairPolicerEnableUpdateType
     */
    @JsonProperty("errorRepairPolicerEnableUpdateType")
    public void setErrorRepairPolicerEnableUpdateType(String errorRepairPolicerEnableUpdateType) {
        this.errorRepairPolicerEnableUpdateType = errorRepairPolicerEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerRate
     */
    @JsonProperty("errorRepairPolicerRate")
    public Long getErrorRepairPolicerRate() {
        return errorRepairPolicerRate;
    }

    /**
     * 
     * @param errorRepairPolicerRate
     *     The errorRepairPolicerRate
     */
    @JsonProperty("errorRepairPolicerRate")
    public void setErrorRepairPolicerRate(Long errorRepairPolicerRate) {
        this.errorRepairPolicerRate = errorRepairPolicerRate;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerRateUpdateType
     */
    @JsonProperty("errorRepairPolicerRateUpdateType")
    public String getErrorRepairPolicerRateUpdateType() {
        return errorRepairPolicerRateUpdateType;
    }

    /**
     * 
     * @param errorRepairPolicerRateUpdateType
     *     The errorRepairPolicerRateUpdateType
     */
    @JsonProperty("errorRepairPolicerRateUpdateType")
    public void setErrorRepairPolicerRateUpdateType(String errorRepairPolicerRateUpdateType) {
        this.errorRepairPolicerRateUpdateType = errorRepairPolicerRateUpdateType;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerBurst
     */
    @JsonProperty("errorRepairPolicerBurst")
    public Long getErrorRepairPolicerBurst() {
        return errorRepairPolicerBurst;
    }

    /**
     * 
     * @param errorRepairPolicerBurst
     *     The errorRepairPolicerBurst
     */
    @JsonProperty("errorRepairPolicerBurst")
    public void setErrorRepairPolicerBurst(Long errorRepairPolicerBurst) {
        this.errorRepairPolicerBurst = errorRepairPolicerBurst;
    }

    /**
     * 
     * @return
     *     The errorRepairPolicerBurstUpdateType
     */
    @JsonProperty("errorRepairPolicerBurstUpdateType")
    public String getErrorRepairPolicerBurstUpdateType() {
        return errorRepairPolicerBurstUpdateType;
    }

    /**
     * 
     * @param errorRepairPolicerBurstUpdateType
     *     The errorRepairPolicerBurstUpdateType
     */
    @JsonProperty("errorRepairPolicerBurstUpdateType")
    public void setErrorRepairPolicerBurstUpdateType(String errorRepairPolicerBurstUpdateType) {
        this.errorRepairPolicerBurstUpdateType = errorRepairPolicerBurstUpdateType;
    }

    /**
     * 
     * @return
     *     The fecEnable
     */
    @JsonProperty("fecEnable")
    public Boolean getFecEnable() {
        return fecEnable;
    }

    /**
     * 
     * @param fecEnable
     *     The fecEnable
     */
    @JsonProperty("fecEnable")
    public void setFecEnable(Boolean fecEnable) {
        this.fecEnable = fecEnable;
    }

    /**
     * 
     * @return
     *     The fecEnableUpdateType
     */
    @JsonProperty("fecEnableUpdateType")
    public String getFecEnableUpdateType() {
        return fecEnableUpdateType;
    }

    /**
     * 
     * @param fecEnableUpdateType
     *     The fecEnableUpdateType
     */
    @JsonProperty("fecEnableUpdateType")
    public void setFecEnableUpdateType(String fecEnableUpdateType) {
        this.fecEnableUpdateType = fecEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The rccEnable
     */
    @JsonProperty("rccEnable")
    public Boolean getRccEnable() {
        return rccEnable;
    }

    /**
     * 
     * @param rccEnable
     *     The rccEnable
     */
    @JsonProperty("rccEnable")
    public void setRccEnable(Boolean rccEnable) {
        this.rccEnable = rccEnable;
    }

    /**
     * 
     * @return
     *     The rccEnableUpdateType
     */
    @JsonProperty("rccEnableUpdateType")
    public String getRccEnableUpdateType() {
        return rccEnableUpdateType;
    }

    /**
     * 
     * @param rccEnableUpdateType
     *     The rccEnableUpdateType
     */
    @JsonProperty("rccEnableUpdateType")
    public void setRccEnableUpdateType(String rccEnableUpdateType) {
        this.rccEnableUpdateType = rccEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The rccStartTimeout
     */
    @JsonProperty("rccStartTimeout")
    public Long getRccStartTimeout() {
        return rccStartTimeout;
    }

    /**
     * 
     * @param rccStartTimeout
     *     The rccStartTimeout
     */
    @JsonProperty("rccStartTimeout")
    public void setRccStartTimeout(Long rccStartTimeout) {
        this.rccStartTimeout = rccStartTimeout;
    }

    /**
     * 
     * @return
     *     The rccStartTimeoutUpdateType
     */
    @JsonProperty("rccStartTimeoutUpdateType")
    public String getRccStartTimeoutUpdateType() {
        return rccStartTimeoutUpdateType;
    }

    /**
     * 
     * @param rccStartTimeoutUpdateType
     *     The rccStartTimeoutUpdateType
     */
    @JsonProperty("rccStartTimeoutUpdateType")
    public void setRccStartTimeoutUpdateType(String rccStartTimeoutUpdateType) {
        this.rccStartTimeoutUpdateType = rccStartTimeoutUpdateType;
    }

    /**
     * 
     * @return
     *     The reorderDelayAbs
     */
    @JsonProperty("reorderDelayAbs")
    public Long getReorderDelayAbs() {
        return reorderDelayAbs;
    }

    /**
     * 
     * @param reorderDelayAbs
     *     The reorderDelayAbs
     */
    @JsonProperty("reorderDelayAbs")
    public void setReorderDelayAbs(Long reorderDelayAbs) {
        this.reorderDelayAbs = reorderDelayAbs;
    }

    /**
     * 
     * @return
     *     The reorderDelayAbsUpdateType
     */
    @JsonProperty("reorderDelayAbsUpdateType")
    public String getReorderDelayAbsUpdateType() {
        return reorderDelayAbsUpdateType;
    }

    /**
     * 
     * @param reorderDelayAbsUpdateType
     *     The reorderDelayAbsUpdateType
     */
    @JsonProperty("reorderDelayAbsUpdateType")
    public void setReorderDelayAbsUpdateType(String reorderDelayAbsUpdateType) {
        this.reorderDelayAbsUpdateType = reorderDelayAbsUpdateType;
    }

    /**
     * 
     * @return
     *     The cliIfname
     */
    @JsonProperty("cliIfname")
    public String getCliIfname() {
        return cliIfname;
    }

    /**
     * 
     * @param cliIfname
     *     The cliIfname
     */
    @JsonProperty("cliIfname")
    public void setCliIfname(String cliIfname) {
        this.cliIfname = cliIfname;
    }

    /**
     * 
     * @return
     *     The cliIfnameUpdateType
     */
    @JsonProperty("cliIfnameUpdateType")
    public String getCliIfnameUpdateType() {
        return cliIfnameUpdateType;
    }

    /**
     * 
     * @param cliIfnameUpdateType
     *     The cliIfnameUpdateType
     */
    @JsonProperty("cliIfnameUpdateType")
    public void setCliIfnameUpdateType(String cliIfnameUpdateType) {
        this.cliIfnameUpdateType = cliIfnameUpdateType;
    }

    /**
     * 
     * @return
     *     The rtcpDscpValue
     */
    @JsonProperty("rtcpDscpValue")
    public Long getRtcpDscpValue() {
        return rtcpDscpValue;
    }

    /**
     * 
     * @param rtcpDscpValue
     *     The rtcpDscpValue
     */
    @JsonProperty("rtcpDscpValue")
    public void setRtcpDscpValue(Long rtcpDscpValue) {
        this.rtcpDscpValue = rtcpDscpValue;
    }

    /**
     * 
     * @return
     *     The rtcpDscpValueUpdateType
     */
    @JsonProperty("rtcpDscpValueUpdateType")
    public String getRtcpDscpValueUpdateType() {
        return rtcpDscpValueUpdateType;
    }

    /**
     * 
     * @param rtcpDscpValueUpdateType
     *     The rtcpDscpValueUpdateType
     */
    @JsonProperty("rtcpDscpValueUpdateType")
    public void setRtcpDscpValueUpdateType(String rtcpDscpValueUpdateType) {
        this.rtcpDscpValueUpdateType = rtcpDscpValueUpdateType;
    }

    /**
     * 
     * @return
     *     The fastfillEnable
     */
    @JsonProperty("fastfillEnable")
    public Boolean getFastfillEnable() {
        return fastfillEnable;
    }

    /**
     * 
     * @param fastfillEnable
     *     The fastfillEnable
     */
    @JsonProperty("fastfillEnable")
    public void setFastfillEnable(Boolean fastfillEnable) {
        this.fastfillEnable = fastfillEnable;
    }

    /**
     * 
     * @return
     *     The fastfillEnableUpdateType
     */
    @JsonProperty("fastfillEnableUpdateType")
    public String getFastfillEnableUpdateType() {
        return fastfillEnableUpdateType;
    }

    /**
     * 
     * @param fastfillEnableUpdateType
     *     The fastfillEnableUpdateType
     */
    @JsonProperty("fastfillEnableUpdateType")
    public void setFastfillEnableUpdateType(String fastfillEnableUpdateType) {
        this.fastfillEnableUpdateType = fastfillEnableUpdateType;
    }

    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public Extension getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    @Generated("org.jsonschema2pojo")
    public enum SigMode {

        NAT("nat"),
        NAT_("NAT"),
        STD("std"),
        STD_("STD");
        private final String value;
        private final static Map<String, GroupAttributesDto.SigMode> CONSTANTS = new HashMap<String, GroupAttributesDto.SigMode>();

        static {
            for (GroupAttributesDto.SigMode c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private SigMode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static GroupAttributesDto.SigMode fromValue(String value) {
            GroupAttributesDto.SigMode constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
