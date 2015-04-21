package com.colin.message.req;

/**
 *  文本消息 
 * @author 琪
 * @date 2014-09-10
 */
public class TextMessage extends BaseMessage{
    //消息内容
	private String Content;
	

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
