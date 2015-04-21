package com.colin.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.colin.util.ConnManager;

public class CompareAddress {
	private final static double EARTH_RADIUS = 6378.137;
	private static String distanceResp = null;
	private static double rad(double d)
	{
		return d * Math.PI / 180.0;
	}
	public static String addrCompare(String weixinID,String location_x,String location_y,String address,long createTime)
	{	
		
		//将long型时间数据转化成Date和Timestamp类型方便比较

		Date date = new Date(createTime);
		//查看该微信用户是否为公司员工，或关注企业微信号
		
		Connection conn = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String resp = "wrong";
		String sql = "select * from staff where weixinID='"+weixinID+"' limit 1";
		try {
			conn = ConnManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs1 = pstmt.executeQuery();
			/*if(rs1.next());
			{	
				resp = rs1.getString("weixinID")+rs1.getString("name")+rs1.getString("workPlace");
			}
			return resp;*/
			if(rs1.next())
			{
				//存在记录表示该员工存在且关注了企业号
				//比对签到记录
				String addr = rs1.getString("workPlace");//员工工作地点
				String x = rs1.getString("lat");//经纬度
				String y = rs1.getString("lng");
				int t = compareLocation(location_x,location_y,address,x,y,addr);
				if(1 != t) return distanceResp;
				String sql2 = "select * from record where weixinID='"+weixinID+"' and effectiveness=1 order by record_time desc limit 1";
				pstmt = conn.prepareStatement(sql2);
				rs2 = pstmt.executeQuery();
				String sql3 = "insert into record(weixinID,real_place,work_place,effectiveness) values('"+weixinID+"','"+address+"','"+addr+"',1)";
				String sql4 = "insert into record(weixinID,real_place,work_place,effectiveness) values('"+weixinID+"','"+address+"','"+addr+"',0)";
				if(rs2.next())	//之前有签到记录
				{
					Timestamp ts1 = rs2.getTimestamp(2);
					if(1 == timeCompare(createTime,ts1))
						{
							//写入数据库
							pstmt1 = conn.prepareStatement(sql3);
							pstmt1.execute();
							return "您于"+date.toString()+"在"+address+"成功签到!";
						}
					else if(0 == timeCompare(createTime,ts1))
						{	
							pstmt1 = conn.prepareStatement(sql4);
							pstmt1.execute();
							return "您今天已经签到过了!";
						}
					else if(-1 == timeCompare(createTime,ts1))
						{
							pstmt1 = conn.prepareStatement(sql4);
							pstmt1.execute();
							return "非法的签到时间!createTime="+createTime+",ts1="+ts1.getTime();
						}
					else return "错误";
				}
				else 
					{
						//写入数据库
						pstmt1 = conn.prepareStatement(sql3);
						pstmt1.execute();
						return "您于"+date.toString()+"在"+address+"成功签到!";
					}
			}
			else{
				return "你还没有关注企业号，请扫描二维码关注公司企业号或联系管理员";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnManager.close(rs1, rs2,pstmt,conn);
		}
		return "出错误啦!";
		/*"签到情况:"+weixinID+"于"+date.toString()+"在"+address+"处签到。";*/
	}
	
	public static int timeCompare(long time1,Timestamp ts)
	{
		long time2 = ts.getTime()/1000;
		long day1 = time1/(1000*60*60*24);
		long day2 = time2/(1000*60*60*24);
		System.out.println("day1="+day1+",day2="+day2);
		if(day1 > day2) return 1;
		if(day1 == day2) return 0;
		else return -1;
	}
	
	public static int compareLocation(String location_x,String location_y,String address,String x,String y,String addr)
	{
		if(addr.equals(address))
		{
			//计算两个经纬度之间的距离
			double x1 = Double.parseDouble(location_x);
			double y1 = Double.parseDouble(location_y);
			double x2 = Double.parseDouble(x);
			double y2 = Double.parseDouble(y);
			
			double a = rad(x1) - rad(x2);
			double b = rad(y1) - rad(y2);
			
			double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2) + Math.cos(rad(x1)) * Math.cos(rad(x2)) * Math.pow(Math.sin(b/2), 2)));
			s = s * EARTH_RADIUS;
			s = Math.round(s * 10000) / 10;
			//签到地附近300米都可以完成签到功能
			if(s <= 800) 
				{	
				distanceResp = "距离签到地"+s+"米(<800)"+",合格!";
				System.out.println("距离签到地"+s+"米(<800)"+",合格!");
					return 1;
				}
			
			else 
				{
					distanceResp = "距离签到地"+s+"米(>800)"+",不予通过!";
					System.out.println("距离签到地"+s+"米(>800)"+",不予通过!");
					return 2;
				}
		}	
		distanceResp = "不在工作地签到，请移至工作地后在签到!";
		System.out.println("不在工作地签到，请移至工作地后在签到!");
		return 3;
	}
}
