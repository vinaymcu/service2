package com.accenture.avs.device.config.model.configms;
/****************************************************************************
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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;



/**
 * @author kumar.rajesh
 *
 */
public class VideoTypes implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private Boolean isActive;
    private Boolean isNpvrEnabled;
    
    private NpvrVideoTypeProfile npvrVideoTypeProfile;
    private String maxResolution;
    private String contentQuality;
    private Integer bitRate;
    private String audioCodec;
    private String videoCodec;
    private String container;
    private String transport;
    private Integer frameRate;
    private List<String> associatedBWProfiles;
    private Integer ranking;

    public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    /**
     * Gets the value of the npvrEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Boolean getIsNpvrEnabled() {
		return isNpvrEnabled;
	}

    /**
     * Sets the value of the npvrEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setIsNpvrEnabled(Boolean npvrEnabled) {
		this.isNpvrEnabled = npvrEnabled;
	}
	
    /**
     * Gets the value of the isActive property.
     * 
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(Boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the npvrVideoTypeProfile property.
     * 
     * @return
     *     possible object is
     *     {@link VideoTypeConfigurationRequest.NpvrMediaTypeProfile }
     *     
     */
    public NpvrVideoTypeProfile getNpvrVideoTypeProfile() {
        return npvrVideoTypeProfile;
    }

    /**
     * Sets the value of the npvrMediaTypeProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoTypeConfigurationRequest.NpvrMediaTypeProfile }
     *     
     */
    public void setNpvrVideoTypeProfile(NpvrVideoTypeProfile value) {
        this.npvrVideoTypeProfile = value;
    }
    
    

 
    /**
	 * @return the maxResolution
	 */
	public String getMaxResolution() {
		return maxResolution;
	}

	/**
	 * @param maxResolution the maxResolution to set
	 */
	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
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
	 * @return the bitRate
	 */
	public Integer getBitRate() {
		return bitRate;
	}

	/**
	 * @param bitRate the bitRate to set
	 */
	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}

	/**
	 * @return the audioCodec
	 */
	public String getAudioCodec() {
		return audioCodec;
	}

	/**
	 * @param audioCodec the audioCodec to set
	 */
	public void setAudioCodec(String audioCodec) {
		this.audioCodec = audioCodec;
	}

	/**
	 * @return the videoCodec
	 */
	public String getVideoCodec() {
		return videoCodec;
	}

	/**
	 * @param videoCodec the videoCodec to set
	 */
	public void setVideoCodec(String videoCodec) {
		this.videoCodec = videoCodec;
	}

	/**
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}

	/**
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}

	/**
	 * @return the frameRate
	 */
	public Integer getFrameRate() {
		return frameRate;
	}

	/**
	 * @param frameRate the frameRate to set
	 */
	public void setFrameRate(Integer frameRate) {
		this.frameRate = frameRate;
	}

	/**
	 * @return the associatedBWProfiles
	 */
	public List<String> getAssociatedBWProfiles() {
		return associatedBWProfiles;
	}

	/**
	 * @param associatedBWProfiles the associatedBWProfiles to set
	 */
	public void setAssociatedBWProfiles(List<String> associatedBWProfiles) {
		this.associatedBWProfiles = associatedBWProfiles;
	}




	public static class NpvrVideoTypeProfile implements Serializable{

     
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String npvrRecordingExtension;
     
        private String npvrRecordingProtocol;
     
        private Integer npvrRecordingBitrate;
      
        private Integer npvrRecordingKeepDays;
       
        private String npvrRecordingPath;
       
        private String npvrRecordingSubSystem;
        
        private List<String> npvrRecordingIncludedVideoTypes;

        /**
         * Gets the value of the npvrRecordingExtension property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNpvrRecordingExtension() {
            return npvrRecordingExtension;
        }

        /**
         * Sets the value of the npvrRecordingExtension property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNpvrRecordingExtension(String value) {
            this.npvrRecordingExtension = value;
        }

        /**
         * Gets the value of the npvrRecordingProtocol property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNpvrRecordingProtocol() {
            return npvrRecordingProtocol;
        }

        /**
         * Sets the value of the npvrRecordingProtocol property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNpvrRecordingProtocol(String value) {
            this.npvrRecordingProtocol = value;
        }

        /**
         * Gets the value of the npvrRecordingBitrate property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public Integer getNpvrRecordingBitrate() {
            return npvrRecordingBitrate;
        }

        /**
         * Sets the value of the npvrRecordingBitrate property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNpvrRecordingBitrate(Integer value) {
            this.npvrRecordingBitrate = value;
        }

        /**
         * Gets the value of the npvrRecordingKeepDays property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public Integer getNpvrRecordingKeepDays() {
            return npvrRecordingKeepDays;
        }

        /**
         * Sets the value of the npvrRecordingKeepDays property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNpvrRecordingKeepDays(Integer value) {
            this.npvrRecordingKeepDays = value;
        }

        /**
         * Gets the value of the npvrRecordingPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNpvrRecordingPath() {
            return npvrRecordingPath;
        }

        /**
         * Sets the value of the npvrRecordingPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNpvrRecordingPath(String value) {
            this.npvrRecordingPath = value;
        }

        /**
         * Gets the value of the npvrRecordingSubSystem property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNpvrRecordingSubSystem() {
            return npvrRecordingSubSystem;
        }

        /**
         * Sets the value of the npvrRecordingSubSystem property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNpvrRecordingSubSystem(String value) {
            this.npvrRecordingSubSystem = value;
        }
        
        

        /**
         * Gets the value of the npvrRecordingIncludedVideoTypes property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the npvrRecordingIncludedVideoTypes property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNpvrRecordingIncludedVideoTypes().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNpvrRecordingIncludedVideoTypes() {
            if (npvrRecordingIncludedVideoTypes == null) {
                npvrRecordingIncludedVideoTypes = new ArrayList<String>();
            }
            return this.npvrRecordingIncludedVideoTypes;
        }

		public void setNpvrRecordingIncludedVideoTypes(List<String> npvrRecordingIncludedVideoTypes) {
			this.npvrRecordingIncludedVideoTypes = npvrRecordingIncludedVideoTypes;
		}
    }

}
