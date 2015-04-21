package com.colin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

public class Staff {
	/*
	 * 实体类，对应着数据库中的表staff
	 */
	@Component("staff")
	@Entity
	@Table(name="staff")
	public class User{
		@Id
		@Column(name="weixinID")
		private String weixinID;
		
		@Column(name="name")
		private String name;
		
		@Column(name="department")
		private String department;
		
		@Column(name="workPlace")
		private String workPlace;
		
		@Column(name="lng")
		private String lng;
		
		@Column(name="lat")
		private String lat;
		
		public String getWeixinID() {
			return weixinID;
		}
		public void setWeixinID(String weixinID) {
			this.weixinID = weixinID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getWorkPlace() {
			return workPlace;
		}
		public void setWorkPlace(String workPlace) {
			this.workPlace = workPlace;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
	}
}
