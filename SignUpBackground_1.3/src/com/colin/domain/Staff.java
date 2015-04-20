package com.colin.domain;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private String weixinId;
	private String name;
	private String department;
	private String workPlace;
	private String lng;
	private String lat;

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String weixinId, String name, String department,
			String workPlace, String lng, String lat) {
		this.weixinId = weixinId;
		this.name = name;
		this.department = department;
		this.workPlace = workPlace;
		this.lng = lng;
		this.lat = lat;
	}

	// Property accessors

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkPlace() {
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}