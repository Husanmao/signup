package com.colin.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.colin.message.req.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息处理工具类
 * @author 琪
 * @date 2014-09-10
 */

public class MessageUtil {
  //请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT="text";
	
	//请求消息类型：事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT="event";
	//事件类型：订阅
	public static final String EVENT_TYPE_SUBSCRIBE="subscribe";
	//事件类型：取消订阅
	public static final String EVENT_TYPE_UNSUSCRIBE="unsubscribe";
	//事件类型：CLICK
	public static final String EVENT_TYPE_CLICK="CLICK";
	
	//响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT="text";
	// 响应消息类型：图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	//响应消息类型：客服
	public static final String RESP_MESSAGE_TYPE_CUSTOMER = "transfer_customer_service";
	
	//事件类型LOCATION（上报地理位置）
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	/**
	 * 解析微信发来的消息请求（XML）
	 * 
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
	throws Exception{
		//将解析结果存储在HashMap中
		Map<String, String> map=new HashMap<String, String>();
		
		//从request中取得输入流
		InputStream inputStream=request.getInputStream();
		//读取输入流
		SAXReader reader=new SAXReader();
		Document document=reader.read(inputStream);
		//得到XML跟元素
		Element root=document.getRootElement();
		//得到根元素所有子节点
		List<Element> elementList=root.elements();
		
		//遍历所有子节点
		for (Element e:elementList)
			map.put(e.getName(),e.getText());
		
		//释放资源
		inputStream.close();
		inputStream=null;
		
		return map;
	}
	
	/**
	 * 解析微信发来的消息请求（XML）
	 * 
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = new ByteArrayInputStream(
				request.getBytes("UTF-8"));

		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);

		// 得到xml根元素
		Element root = document.getRootElement();

		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		System.out.println("处理xml");
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
			System.out.println(e.getName() + "  " + e.getText());
		}

		System.out.println("处理xml完毕");
		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream=new XStream (new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				//对所有XML结点的转换都添加CDATA标记
				boolean cdata=true;
				
				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz){
					super.startNode(name, clazz);
				}
				
				protected void writeText(QuickWriter writer, String text){
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
			};
		}
	});
	
	
	/**
	 * 文本消息转换成XML
	 * @param textMessage
	 * @return xml
	 */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml",textMessage.getClass());
		return xstream.toXML(textMessage);
	}
}
