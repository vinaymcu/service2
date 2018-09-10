package com.accenture.avs.device.json.object.devicemanager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "crmAccountId", "username", "lastUpdateTime" })
public class UpdatedUserDTO {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("crmAccountId")
	private String crmAccountId;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("username")
	private String username;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lastUpdateTime")
	private Long lastUpdateTime;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("crmAccountId")
	public String getCrmAccountId() {
		return crmAccountId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("crmAccountId")
	public void setCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lastUpdateTime")
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lastUpdateTime")
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public UpdatedUserDTO(String crmAccountId, String username, Long lastUpdateTime) {
		super();
		this.crmAccountId = crmAccountId;
		this.username = username;
		this.lastUpdateTime = lastUpdateTime;
	}

	public UpdatedUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
