package com.colin.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.colin.domain.Record;
import com.colin.domain.Staff;
import com.colin.domain.SysManager;
import com.colin.service.GetResult;
import com.colin.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	private Map<String,Object> session;
	private List<Staff> list1;
	private List<Record> list2;
	
	public List<Staff> getList1()
	{
		return list1;
	}
	
	public List<Record> getList2()
	{
		return list2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute()
	{
		String name=(String)session.get("username");
		String pwd=(String)session.get("password");
		System.out.println("name="+username+","+"password="+password);
		if((name!=null)&&(pwd!=null))
		{
			this.username=name;
			this.password=pwd;
		}
		//return "succeed";
		SysManager sm = LoginService.checkUser(username,password);
		if(null != sm)
		{
			//将管理员的登录信息放置到session当中，方便以后使用
			session.put("username",sm.getAccountName());
			session.put("password",sm.getAccountPassword());
			
			//将员工信息和签到信息放置到list当中,方便导向的jsp页面展示数据
			list1 = GetResult.getStaff();
			list2 = GetResult.getRecord();
			
			return "succeed";
		}
		else{
			session.put("wrongInfo", "用户名或密码错误");
			return "failed";
		}
		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
}
