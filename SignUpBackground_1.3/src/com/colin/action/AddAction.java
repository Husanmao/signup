package com.colin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.colin.service.OperateService;
import com.opensymphony.xwork2.ActionSupport;

public class AddAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String weixinId;
	public String name;
	public String department;
	public String workPlace;
	public String myLng;
	public String myLat; 
	public String getMyLng() {
		return myLng;
	}
	public void setMyLng(String myLng) {
		this.myLng = myLng;
	}
	public String getMyLat() {
		return myLat;
	}
	public void setMyLat(String myLat) {
		this.myLat = myLat;
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
	@Override
	public String execute()
	{
		int  respContent=OperateService.add(weixinId, name, department, workPlace,myLng,myLat);
		if(1 == respContent)
			{
				session.put("addResponse", "添加成功!");
			}
		else if(-1 == respContent)
			{
				session.put("addResponse", "员工已存在!");
			}
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
}
