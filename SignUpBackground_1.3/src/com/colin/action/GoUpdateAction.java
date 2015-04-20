package com.colin.action;

import com.colin.domain.Staff;
import com.colin.service.GetResult;
import com.opensymphony.xwork2.ActionSupport;

public class GoUpdateAction extends ActionSupport {
	public String weixinId;
	public  String name;
	public String department;
	public String workPlace;

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	
	public String execute()
	{
		Staff staff = GetResult.getStaff(weixinId);
		this.name = staff.getName();
		this.department = staff.getDepartment();
		this.workPlace = staff.getWorkPlace();
		return SUCCESS;
	}
}
