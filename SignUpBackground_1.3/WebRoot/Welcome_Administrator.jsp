<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Welcome_Administrator.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    当前登录管理员:${username }<br>
    <a href="Add.jsp">添加员工</a>
    <table border="1">
    	<tr><td>员工微信号</td><td>名字</td><td>部门</td><td>工作地</td><td>操作</td></tr>
		<s:iterator value="list1">
		<tr>
			<td>
				<s:property value="weixinId" />
			</td>
			<td>
				<s:property value="name" />
			</td>
			<td>
				<s:property value="department" />
			</td>
			<td>
				<s:property value="workPlace" />
			</td>
			<td>
				<a href="delete?weixinId=${weixinId }">删除</a>
				<a href="goUpdate?weixinId=${weixinId }">更新</a>
			</td>
		</tr>
		</s:iterator>
    </table>
    
    <table border="1">
    	<tr><td>记录ID</td><td>员工微信号</td><td>签到时间</td><td>实际签到地</td><td>工作地</td><td>是否有效(1有效/0无效)</td></tr>
		<s:iterator value="list2">
		<tr>
			<td>
				<s:property value="id" />
			</td>
			<td>
				<s:property value="weixinId" />
			</td>
			<td>
				<s:property value="recordTime" />
			</td>
			<td>
				<s:property value="realPlace" />
			</td>
			<td>
				<s:property value="workPlace" />
			</td>
			<td>
				<s:property value="effectiveness" />
			</td>
		</tr>
		</s:iterator>
    </table>
  </body>
</html>
