package com.colin.servlet;

import com.colin.service.CoreService;
import com.colin.service.StreamToString;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoreServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 2459342642282911982L;
  String sToken = "weixinCourse";
  String sCorpID = "wx46e1fbca190b2e85";
  String sEncodingAESKey = "PeXuyN91zG2OjNmfTNips9cbGW3qZQARto6qpRmLFMs";
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html;charset=utf-8");
    
    request.setCharacterEncoding("utf-8");
    

    String msg_signature = request.getParameter("msg_signature");
    
    String timestamp = request.getParameter("timestamp");
    
    String nonce = request.getParameter("nonce");
    
    String echostr = request.getParameter("echostr");
    

    System.out.println("request=" + request.getRequestURL());
    PrintWriter out = response.getWriter();
    String sEchoStr = null;
    try
    {
      WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(this.sToken, this.sEncodingAESKey, this.sCorpID);
      sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
      System.out.println("verifyurl echostr: " + sEchoStr);
    }
    catch (AesException e)
    {
      e.printStackTrace();
    }
    if (sEchoStr == null) {
      sEchoStr = this.sToken;
    }
    out.print(sEchoStr);
    out.close();
    out = null;
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setCharacterEncoding("utf-8");
    request.setCharacterEncoding("utf-8");
    

    String msg_signature = request.getParameter("msg_signature");
    
    String timestamp = request.getParameter("timestamp");
    
    String nonce = request.getParameter("nonce");
    

    InputStream inputStream = request.getInputStream();
    String postData = StreamToString.convertStreamToString(inputStream);
    System.out.println(postData);
    
    String msg = "";
    
    WXBizMsgCrypt wxcpt = null;
    try
    {
      wxcpt = new WXBizMsgCrypt(this.sToken, this.sEncodingAESKey, this.sCorpID);
      msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
    }
    catch (AesException e1)
    {
      e1.printStackTrace();
    }
    System.out.println("msg" + msg);
    

    String respMessage = CoreService.processRequest(msg);
    System.out.println("respMessage" + respMessage);
    
    String encryptMsg = "";
    try
    {
      encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
    }
    catch (AesException e)
    {
      e.printStackTrace();
    }
    PrintWriter out = response.getWriter();
    out.print(encryptMsg);
    out.close();
  }
}
