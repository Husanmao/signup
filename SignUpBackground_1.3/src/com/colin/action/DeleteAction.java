package com.colin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.colin.service.OperateService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String weixinId;
	private Map<String,Object> session;
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public String execute()
	{
		OperateService.delete(weixinId);
		session.put("delResponse", "删除成功!");
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
