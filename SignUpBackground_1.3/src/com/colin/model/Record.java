package com.colin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

public class Record {
	/*
	 * 实体类，对应着数据库中的表t_demo
	 */
	@Component("record")
	@Entity
	@Table(name="record")
	public class User{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ID")
		private long ID;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="record_time")
		private String record_time;
		
		@Column(name="weixinID")
		private String weixinID;
		
		@Column(name="real_place")
		private String real_place;
		
		@Column(name="work_place")
		private String work_place;
		
		@Column(name="effectiveness")
		private String effectiveness;
	}
		
}
