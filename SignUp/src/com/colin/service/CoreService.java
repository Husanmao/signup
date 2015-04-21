package com.colin.service;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.colin.message.req.TextMessage;
import com.colin.util.MessageUtil;


public class CoreService {
	public static String processRequest(String msg)
	{
		String respMessage = null;
		
		//default text message to be returned
		String respContent = "unknown message type";
		
		try{
			//analyze the data using parseXml
			Map<String,String> requestMap = MessageUtil.parseXml(msg);
			
			System.out.println("Event=" + requestMap.get("Event"));
			
			//id from the user
			String fromUserName = requestMap.get("FromUserName");
			//weixin id of the user
			String toUserName = requestMap.get("ToUserName");
			//message type
			String msgType = requestMap.get("MsgType");
			
			//create time 
			long creatTime = Long.valueOf(requestMap.get("CreateTime"));
			//response to text
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			//text
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
			{
				respContent = "You are sending text message!";
			}
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
			{
				String eventType = requestMap.get("Event");
				
				if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION))
				{
					//deal with the location
					//respContent = "You are sending location message";
					//获取到用户的地理位置信息后进入到核心业务层
					
					//获取到用户的地理位置信息：经度，纬度和精确度
					String Location_x = requestMap.get("Latitude");
					String Location_y = requestMap.get("Longitude");
					String respJson = HttpRequest.sendGet(Location_x, Location_y);
					
					//获取地址
					try{
						JSONObject jsonObj = JSONObject.fromObject(respJson);
						JSONObject Result = jsonObj.getJSONObject("result");
						String address = Result.getString("address");
						
						//获取得到了用户签到的地址后，和数据的数据做对比,返回比较结果:有效，无效：重复签到（一天内），签到地点错误，
						respContent = CompareAddress.addrCompare(fromUserName,Location_x,Location_y,address,creatTime);
						respContent += "签到地经度:"+Location_y+",纬度:"+Location_x;
						//respContent ="签到成功,你签到的位置是:"+address;
					}catch(JSONException e){
						respContent = "json parse error";
						System.out.println("json parse error");
						
						e.printStackTrace();
					}
				}
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
				{
					respContent = "Thank you for concern!";
				}
			}
			textMessage.setContent((respContent));
			respMessage = MessageUtil.messageToXml(textMessage);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			respMessage="wrong information";
		}

		return respMessage;
	}
	
	public static String checkUser()
	{
		return null;
	}

}
