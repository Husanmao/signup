<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Soso.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <script charset="utf-8" src="http://map.soso.com/api/v2/main.js?key=7PNBZ-AFP3U-FC2V2-2LAVK-FMWC3-7SBNK"></script> -->
	<style>
	#map_container{height:500px;width:600px;float:left;}
	.info{width:350px;float:left;margin:0 0 0 10px;}
    label{width:80px;float:left;}
	.detail{padding:10px;border:1px solid #aaccaa}
	
	</style>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
	<script type="text/javascript">
		var map,marker,point,marker=null,geocoder;
		//经纬度
		var x,y;
		//地址
		var address;
		var init = function()
		{
			point = new qq.maps.LatLng(23.047354,113.401576);
		    map= new soso.maps.Map(document.getElementById("map_container"),{
				center:point,
				zoom:13
			});
			//给地图添加点击的响应事件
			qq.maps.event.addListener(map,"click",getLocation);
			//marker
			marker = new qq.maps.Marker({
				position:point,
				map:map
			});
			
			geocoder = new qq.maps.Geocoder({
				complete : function(result)
				{
					address = result.detail.address;
					document.getElementById("myAddr").value = address;
					document.getElementById("iAddress").innerHTML = result.detail.address;
				}
			});
			
		};
		
		function getLocation(e)
		{
			//鼠标点击地图时:1.改变marker;2.将经纬度显示给用户看;3.获取相应经纬度的地址信息并显示给用户看;4.将经纬度和地址信息放到隐藏input对象中提交给服务器
			marker.setMap(null);
			marker = new qq.maps.Marker({
				position:e.latLng,
				map:map
			});
			x = e.latLng.getLat();
			y = e.latLng.getLng();
			document.getElementById("Lng").innerHTML = y;
			document.getElementById("Lat").innerHTML = x;
			geocoder.getAddress(e.latLng);
			document.getElementById("myLng").value = y;
			document.getElementById("myLat").value = x;
		}
	</script>


  
  <body onload="init()">
   	<form action="location" method="post">
   		<div id="map_container"></div>
			<div class="info">
 			<h4>请选择工作地点:</h4>
 			<div class="detail">
 				经度:<p><span id="Lng"></span><input type="hidden" id="myLng" name="myLng" /></p>
 				纬度:<p><span id="Lat"></span><input type="hidden" id="myLat" name="myLat" /></p>
 				地址:<p><span id="iAddress"></span><input type="hidden" id="myAddr" name="myAddr" /></p>
 			</div>
 			<input type="submit" value="确定"/>
			</div>
   	</form>
  </body>
</html>
