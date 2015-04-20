package com.colin.service;

import java.util.ArrayList;
import java.util.List;

import com.colin.domain.Record;
import com.colin.domain.RecordDAO;
import com.colin.domain.Staff;
import com.colin.domain.StaffDAO;

public class GetResult {
	private static StaffDAO sDAO;
	private static RecordDAO rDAO;
	
	public void setrDAO(RecordDAO rDAO){
		GetResult.rDAO = rDAO;
	}
	public void setsDAO(StaffDAO sDAO) {
		GetResult.sDAO = sDAO;
	}
	
	public static List<Staff> getStaff(){
		List<Staff> list = new ArrayList<Staff>();
		list = sDAO.findAll();
		System.out.println("返回所有员工信息成功");
		return list;
	}
	public static Staff getStaff(String weixinId)
	{
		List<Staff> list = sDAO.findByProperty("weixinId", weixinId);
		if(1 == list.size())
		{
			return list.get(0);
		}
		return null;
	}
	public static List<Record> getRecord()
	{
		List<Record> list = new ArrayList<Record>();
		list = rDAO.findAll();
		System.out.println();
		return list;
	}
	
	
}
