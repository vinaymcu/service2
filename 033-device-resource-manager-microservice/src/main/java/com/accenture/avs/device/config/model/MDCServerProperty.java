package com.accenture.avs.device.config.model;

import java.util.ArrayList;
import java.util.List;

public class MDCServerProperty {
    private String name;
    private String address;
    private String type;
    private String locationID;
    private List<MDCServerAttributes> attributeList = new ArrayList<MDCServerAttributes>();
    
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the locationID
	 */
	public String getLocationID() {
		return locationID;
	}
	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	/**
	 * @return the attributeList
	 */
	public List<MDCServerAttributes> getAttributeList() {
		return attributeList;
	}
	/**
	 * @param attributeList the attributeList to set
	 */
	public void setAttributeList(List<MDCServerAttributes> attributeList) {
		this.attributeList = attributeList;
	}
    
}
