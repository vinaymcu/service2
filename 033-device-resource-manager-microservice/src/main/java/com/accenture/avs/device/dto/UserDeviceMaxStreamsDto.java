package com.accenture.avs.device.dto;

/**
 * 
 * @author singh.saurabh
 *
 */
public class UserDeviceMaxStreamsDto {

	/** contentQuality */
	private String contentQuality;

	/** allowedStreamsPerDevice */
	private Long allowedStreamsPerDevice;

	/** totalAssignedStreams */
	private Long totalAssignedStreams;

	/** totalAssignedQoeStreams */
	private Long totalAssignedQoeStreams;

	/** totalAssignedBandwidth */
	private Long totalAssignedBandwidth;

	/**
	 * @return the contentQuality
	 */
	public String getContentQuality() {
		return contentQuality;
	}

	/**
	 * @param contentQuality
	 *            the contentQuality to set
	 */
	public void setContentQuality(String contentQuality) {
		this.contentQuality = contentQuality;
	}

	/**
	 * @return the allowedStreamsPerDevice
	 */
	public Long getAllowedStreamsPerDevice() {
		return allowedStreamsPerDevice;
	}

	/**
	 * @param allowedStreamsPerDevice
	 *            the allowedStreamsPerDevice to set
	 */
	public void setAllowedStreamsPerDevice(Long allowedStreamsPerDevice) {
		this.allowedStreamsPerDevice = allowedStreamsPerDevice;
	}

	/**
	 * @return the totalAssignedStreams
	 */
	public Long getTotalAssignedStreams() {
		return totalAssignedStreams;
	}

	/**
	 * @param totalAssignedStreams
	 *            the totalAssignedStreams to set
	 */
	public void setTotalAssignedStreams(Long totalAssignedStreams) {
		this.totalAssignedStreams = totalAssignedStreams;
	}

	/**
	 * @return the totalAssignedQoeStreams
	 */
	public Long getTotalAssignedQoeStreams() {
		return totalAssignedQoeStreams;
	}

	/**
	 * @param totalAssignedQoeStreams
	 *            the totalAssignedQoeStreams to set
	 */
	public void setTotalAssignedQoeStreams(Long totalAssignedQoeStreams) {
		this.totalAssignedQoeStreams = totalAssignedQoeStreams;
	}

	/**
	 * @return the totalAssignedBandwidth
	 */
	public Long getTotalAssignedBandwidth() {
		return totalAssignedBandwidth;
	}

	/**
	 * @param totalAssignedBandwidth
	 *            the totalAssignedBandwidth to set
	 */
	public void setTotalAssignedBandwidth(Long totalAssignedBandwidth) {
		this.totalAssignedBandwidth = totalAssignedBandwidth;
	}

}
