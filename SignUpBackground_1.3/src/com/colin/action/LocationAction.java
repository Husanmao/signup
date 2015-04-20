package com.colin.action;

import com.opensymphony.xwork2.ActionSupport;

public class LocationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String myLng;
	public String myLat;
	public String myAddr;
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
	public String getMyAddr() {
		return myAddr;
	}
	public void setMyAddr(String myAddr) {
		this.myAddr = myAddr;
	}
	
	public String execute()
	{
		System.out.println("经纬度:"+this.myLng+","+this.myLat+";位置:"+this.myAddr);
		return SUCCESS;
	}
}
