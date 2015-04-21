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
    
    <title>My JSP 'Update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		#map_area{height:0px;width:0px;}
	</style>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
	<script type="text/javascript" src="js/move.js"></script>
	<script type="text/javascript">
	var map,point,marker=null,geocoder;
	var listener;
	var map_area,oWorkPlace,oWeixinId,oName,oDepartment;
	var latLng;
		window.onload=function()
		{
			point = new qq.maps.LatLng(23.047354,113.401576);
			map_area = document.getElementById("map_area");
			oWorkPlace = document.getElementById("workPlace");
			oWeixinId = document.getElementById("weixinId");
			oName = document.getElementById("name");
			oDepartment = document.getElementById("department");
			oWeixinId.onfocus=close;
			oName.onfocus = close;
			oDepartment.onfocus = close;
			oWorkPlace.onfocus=function()
			{
			    map= new qq.maps.Map(map_area,{
					center:point,
					zoom:13
				});
				//给地图添加点击的响应事件
				listener = qq.maps.event.addListener(map,"click",getLocation);
				//marker
				marker = new qq.maps.Marker({
					position:point,
					map:map
				});
				startMove(map_area,{width:600,height:500});
			};

			geocoder = new qq.maps.Geocoder({
				complete : function(result)
				{
					address = result.detail.address;
					latLng = result.detail.location;
					document.getElementById("workPlace").value=address;
					document.getElementById("myLng").value = latLng.getLng();
					document.getElementById("myLat").value = latLng.getLat();
				}
			});
		};
		function close()
		{
			if(listener != undefined)
			{
				startMove(map_area,{width:0,height:0});
				qq.maps.event.removeListener(listener);
				map = null;
			}
		}
		function getLocation(e)
		{
			//鼠标点击地图时:1.改变marker;2.将经纬度显示给用户看;3.获取相应经纬度的地址信息并显示给用户看;4.将经纬度和地址信息放到隐藏input对象中提交给服务器
			marker.setMap(null);
			marker = new qq.maps.Marker({
				position:e.latLng,
				map:map
			});
			geocoder.getAddress(e.latLng);
		}
	</script>
</head>
  
  <body>
    <form action="update" method="post">
    	微信ID:<input type="text" name="weixinId" id="weixinId" value="${weixinId }" readonly="true"></br>
    	名字:<input name="name" id="name" value="${name }"/></br>
    	部门:<input name="department" id="department" value="${department }"/></br>
    	工作地点:<input name="workPlace" id="workPlace" size="30" value="${workPlace }"/></br>
    	<input type="hidden" id="myLng" name="myLng" />
    	<input type="hidden" id="myLat" name="myLat" />
    	<div id="map_area"></div>
    	<input type="submit" value="更新" />
    </form>
  </body>
</html>
