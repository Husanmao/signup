package com.colin.service;

import java.util.ArrayList;
import java.util.List;

import com.colin.domain.Staff;
import com.colin.domain.StaffDAO;

public class OperateService {
	private static StaffDAO sDAO;
	
	
	public void setsDAO(StaffDAO sDAO) {
		OperateService.sDAO = sDAO;
	}
	@SuppressWarnings("unchecked")
	public static int add(String weixinId,String name,String department,String workPlace,String myLng,String myLat)
	{
		List<Staff> list1 = new ArrayList<Staff>();
		list1 = sDAO.findByProperty("weixinId", weixinId);
		if(1 <= list1.size())return -1;
		else 
		{
			Staff staff = new Staff(weixinId,name,department,workPlace,myLng,myLat);
			sDAO.save(staff);
			return 1;
		}	
	}
	public static boolean update(String weixinId,String name,String department,String workPlace,String myLng,String myLat)
	{
		boolean del = delete(weixinId);
		if(del)
		{
			add(weixinId, name, department, workPlace,myLng,myLat);
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public static boolean delete(String weixinId)
	{
		List<Staff> list = new ArrayList<Staff>();
		list = sDAO.findByProperty("weixinId", weixinId);
		Staff staff = list.get(0);
		sDAO.delete(staff);
		return true;
	}
}
