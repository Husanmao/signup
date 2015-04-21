package com.colin.service;

import java.util.ArrayList;
import java.util.List;

import com.colin.domain.SysManager;
import com.colin.domain.SysManagerDAO;

public class LoginService {
	private static SysManagerDAO smDAO;

	public void setSmDAO(SysManagerDAO smDAO) {
		LoginService.smDAO = smDAO;
	}
	
	public static SysManager checkUser(String name,String password)
	{
		List<SysManager> list = new ArrayList<SysManager>();
		list = smDAO.findByAccountName(name);
		System.out.println("1");
		if(1 <= list.size() && list.get(0).getAccountPassword().equals(password))
		{
			System.out.println("2");
			return list.get(0);
		}
		else 
			return null;
	}
}
