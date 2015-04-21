package com.colin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest
{
  private static final String url = "http://apis.map.qq.com/ws/geocoder/v1/";
  private static final String key = "7PNBZ-AFP3U-FC2V2-2LAVK-FMWC3-7SBNK";
  
  public static String sendGet(String Latitude, String Longitude)
  {
    String result = "";
    BufferedReader in = null;
    try
    {
      String urlNameString = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + Latitude + "," + Longitude + "&key="+key;
      
      URL realUrl = new URL(urlNameString);
      URLConnection connection = realUrl.openConnection();
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1");
      connection.connect();
      Map<String, List<String>> map = connection.getHeaderFields();
      for (String key : map.keySet()) {
        System.out.println(key + "--->" + map.get(key));
      }
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      while ((line = in.readLine()) != null)
      {
        result = result + line;
      }
    }
    catch (Exception e)
    {
      System.out.println("发送get请求出现异常" + e);
      e.printStackTrace();
      try
      {
        if (in != null) {
          in.close();
        }
      }
      catch (Exception e2)
      {
        e2.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (in != null) {
          in.close();
        }
      }
      catch (Exception e2)
      {
        e2.printStackTrace();
      }
    }
    return result;
  }
}
