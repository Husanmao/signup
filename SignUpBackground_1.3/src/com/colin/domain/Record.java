package com.colin.domain;

import java.sql.Timestamp;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp recordTime;
	private String weixinId;
	private String realPlace;
	private String workPlace;
	private Short effectiveness;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** full constructor */
	public Record(Timestamp recordTime, String weixinId, String realPlace,
			String workPlace, Short effectiveness) {
		this.recordTime = recordTime;
		this.weixinId = weixinId;
		this.realPlace = realPlace;
		this.workPlace = workPlace;
		this.effectiveness = effectiveness;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getRealPlace() {
		return this.realPlace;
	}

	public void setRealPlace(String realPlace) {
		this.realPlace = realPlace;
	}

	public String getWorkPlace() {
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Short getEffectiveness() {
		return this.effectiveness;
	}

	public void setEffectiveness(Short effectiveness) {
		this.effectiveness = effectiveness;
	}

}