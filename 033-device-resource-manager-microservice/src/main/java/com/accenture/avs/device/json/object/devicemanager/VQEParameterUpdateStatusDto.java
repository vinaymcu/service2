package com.accenture.avs.device.json.object.devicemanager;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "crmAccountId", "status", "errorMessage" })
public class VQEParameterUpdateStatusDto {

	/**
	 * Default Constructor
	 */
	public VQEParameterUpdateStatusDto() {

	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param crmAccountId
	 * @param status
	 * @param errorMessage
	 */
	public VQEParameterUpdateStatusDto(String crmAccountId, String status, String errorMessage) {
		this.crmAccountId = crmAccountId;
		this.status = status;
		this.errorMessage = errorMessage;
	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param crmAccountId
	 * @param errorMessage
	 */
	public VQEParameterUpdateStatusDto(String crmAccountId, String errorMessage) {
		this.crmAccountId = crmAccountId;
		this.errorMessage = errorMessage;
	}
	
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
	@JsonProperty("status")
	private String status;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * (Required)
	 * 
	 * @return The crmAccountId
	 */
	@JsonProperty("crmAccountId")
	public String getCrmAccountId() {
		return crmAccountId;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param crmAccountId
	 *            The crmAccountId
	 */
	@JsonProperty("crmAccountId")
	public void setCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
	}

	public VQEParameterUpdateStatusDto withCrmAccountId(String crmAccountId) {
		this.crmAccountId = crmAccountId;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	public VQEParameterUpdateStatusDto withStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * 
	 * @return The errorMessage
	 */
	@JsonProperty("errorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param errorMessage
	 *            The errorMessage
	 */
	@JsonProperty("errorMessage")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public VQEParameterUpdateStatusDto withErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public VQEParameterUpdateStatusDto withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(crmAccountId).append(status).append(errorMessage)
				.append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof VQEParameterUpdateStatusDto) == false) {
			return false;
		}
		VQEParameterUpdateStatusDto rhs = ((VQEParameterUpdateStatusDto) other);
		return new EqualsBuilder().append(crmAccountId, rhs.crmAccountId).append(status, rhs.status)
				.append(errorMessage, rhs.errorMessage).append(additionalProperties, rhs.additionalProperties)
				.isEquals();
	}

}
