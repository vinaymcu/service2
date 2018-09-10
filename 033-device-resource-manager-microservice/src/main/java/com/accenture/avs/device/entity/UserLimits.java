package com.accenture.avs.device.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The persistent class for the userlimits table.
 * 
 */

@Embeddable
public class UserLimits implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_NAME", length = 100)
	private String userName;

	@Column(name = "CONTENT_QUALITY", length = 10)
	private String contentQuality;
	
	@Column(name = "MAX_DEVICES", precision = 9, scale = 0)
	private Long maxDevices;
	
	@Column(name = "MAX_STREAMS", precision = 9, scale = 0)
	private Long maxStreams;

	/**
	 * empty constructor
	 */
	public UserLimits() {

	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the contentQuality
	 */
	public String getContentQuality() {
		return contentQuality;
	}

	/**
	 * @param contentQuality the contentQuality to set
	 */
	public void setContentQuality(String contentQuality) {
		this.contentQuality = contentQuality;
	}

	/**
	 * @return the maxDevices
	 */
	public Long getMaxDevices() {
		return maxDevices;
	}

	/**
	 * @param maxDevices the maxDevices to set
	 */
	public void setMaxDevices(Long maxDevices) {
		this.maxDevices = maxDevices;
	}

	/**
	 * @return the maxStreams
	 */
	public Long getMaxStreams() {
		return maxStreams;
	}

	/**
	 * @param maxStreams the maxStreams to set
	 */
	public void setMaxStreams(Long maxStreams) {
		this.maxStreams = maxStreams;
	}	
}