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
package com.accenture.avs.device.json.object.configuration;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "batchSize",
    "parallelExecutionThreads"
})
public class MassCalculation {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("batchSize")
    private Integer batchSize;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("parallelExecutionThreads")
    private Integer parallelExecutionThreads;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The batchSize
     */
    @JsonProperty("batchSize")
    public Integer getBatchSize() {
        return batchSize;
    }

    /**
     * 
     * (Required)
     * 
     * @param batchSize
     *     The batchSize
     */
    @JsonProperty("batchSize")
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public MassCalculation withBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The parallelExecutionThreads
     */
    @JsonProperty("parallelExecutionThreads")
    public Integer getParallelExecutionThreads() {
        return parallelExecutionThreads;
    }

    /**
     * 
     * (Required)
     * 
     * @param parallelExecutionThreads
     *     The parallelExecutionThreads
     */
    @JsonProperty("parallelExecutionThreads")
    public void setParallelExecutionThreads(Integer parallelExecutionThreads) {
        this.parallelExecutionThreads = parallelExecutionThreads;
    }

    public MassCalculation withParallelExecutionThreads(Integer parallelExecutionThreads) {
        this.parallelExecutionThreads = parallelExecutionThreads;
        return this;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(batchSize).append(parallelExecutionThreads).toHashCode();
    }

	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = true;
		if (other == this) {
			isEqual = true;
		} else if ((other instanceof MassCalculation) == false) {
			isEqual = false;
		} else {
			MassCalculation rhs = ((MassCalculation) other);
			isEqual = new EqualsBuilder().append(batchSize, rhs.batchSize)
					.append(parallelExecutionThreads, rhs.parallelExecutionThreads).isEquals();
		}
		return isEqual;
	}

}
