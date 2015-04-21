package com.colin.test;


import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.colin.service.CompareAddress;
import com.colin.service.HttpRequest;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String respJson = HttpRequest.sendGet("39.983932", "116.308228");
		//获取地址
		try{
			JSONObject jsonObj = JSONObject.fromObject(respJson);
			JSONObject Result = jsonObj.getJSONObject("result");
			String address = Result.getString("address");
			System.out.println("你签到的位置是:"+address);
		}catch(JSONException e){
			System.out.println("json parse error");
			
			e.printStackTrace();
		}*/
		Date date = new Date();
		long longTime = date.getTime();
		/*String respContent;
		String respJson = HttpRequest.sendGet("23.051026", "113.401067");
		System.out.println(respJson);
		//获取地址
		try{
			JSONObject jsonObj = JSONObject.fromObject(respJson);
			JSONObject Result = jsonObj.getJSONObject("result");
			String address = Result.getString("address");
			
			//获取得到了用户签到的地址后，和数据的数据做对比,返回比较结果:有效，无效：重复签到（一天内），签到地点错误，
			//respContent = CompareAddress.addrCompare(fromUserName,Location_x,Location_y,address,creatTime);
			respContent ="签到成功,你签到的位置是:"+address;
			System.out.println(respContent);
		}catch(JSONException e){
			respContent = "json parse error";
			System.out.println(respContent);
			
			e.printStackTrace();
		}*/
		//CompareAddress.timeCompare((long)1429122568, (Timestamp)2015-04-06 02:25:45.0);
		//System.out.println(CompareAddress.addrCompare("colin_husanmao", "23.051026","113.401067","中国广东省广州市番禺区大学城内环东路", longTime));
	}

}
