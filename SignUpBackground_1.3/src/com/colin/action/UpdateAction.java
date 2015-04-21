package com.colin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.colin.service.OperateService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateAction extends ActionSupport implements SessionAware{
	public String weixinId;
	public String name;
	public String department;
	public String workPlace;
	public String myLat;
	public String myLng;
	public String getMyLat() {
		return myLat;
	}
	public void setMyLat(String myLat) {
		this.myLat = myLat;
	}
	public String getMyLng() {
		return myLng;
	}
	public void setMyLng(String myLng) {
		this.myLng = myLng;
	}
	private Map<String,Object> session;
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
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
	
	public String execute()
	{
		System.out.println("更新的经纬度:"+this.myLng+","+this.myLat+".位置为:"+this.workPlace);
		OperateService.update(weixinId, name, department, workPlace,myLng,myLat);
		session.put("upResponse","修改成功!");
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
