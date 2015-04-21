<<<<<<< HEAD
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Add.jsp' starting page</title>
    
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
    <form action="add" method="post">
    	微信ID:<input name="weixinId" id="weixinId" /></br>
    	名字:<input name="name" id="name" /></br>
    	部门:<input name="department" id="department" /></br>
  		工作地点:<input name="workPlace" id="workPlace" size="30"/></br>
    	<input type="hidden" id="myLng" name="myLng" />
    	<input type="hidden" id="myLat" name="myLat" />
    	<div id="map_area"></div>
    	<input type="submit" value="添加" />
    </form>
  </body>
</html>
=======
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		#map_area{height:500px;width:600px;}
	</style>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=7PNBZ-AFP3U-FC2V2-2LAVK-FMWC3-7SBNK&libraries=place"></script>
	<script type="text/javascript" src="js/move.js"></script>
	<script type="text/javascript">
	var marker=null,point,listener,geocoder,map,pivot=0,searchService;
	//$函数实现jquery的$符号功能，获取指定id的doom元素
	function $(id)
	{
		return document.getElementById(id);
	}
	window.onload = function()
	{
		point = new qq.maps.LatLng(39,115);
		map = new qq.maps.Map($("map_area"),{
			center:point,
			zoom:13
		});
		marker = new qq.maps.Marker({
			map:map,
			position:point,
		});
		//给地址栏添加自动补全功能
		var ap = new qq.maps.place.Autocomplete($("workPlace"));
		qq.maps.event.addListener(ap,"confirm",function(res){
			searchService.search(res.value);
		});
		searchService = new qq.maps.SearchService({
			complete:function(results){
					var pois = results.detail.pois;
					//如果获取到了数据，取最准确的第一个地址作为工作地
					if(1 <= pois.length)
					{
						//document.getElementById("workPlace").value=address;
						//alert("lng="+pois[0].latLng.getLng()+",lat="+pois[0].latLng.getLat());
						$("myLng").value = pois[0].latLng.getLng();
						$("myLat").value = pois[0].latLng.getLat();
						marker.setMap(null);
						marker  = new qq.maps.Marker({
							map:map,
							position:pois[0].latLng
						});
						map.setCenter(pois[0].latLng);
						pivot = 1;
					}
				}
		});
		//给地图添加点击的响应事件
		listener = qq.maps.event.addListener(map,"click",getLocation);
		//地址解析
		geocoder = new qq.maps.Geocoder({
				complete : function(result)
				{
					address = result.detail.address;
					latLng = result.detail.location;
					document.getElementById("workPlace").value=address;
					document.getElementById("myLng").value = latLng.getLng();
					document.getElementById("myLat").value = latLng.getLat();
					pivot = 1;
				}
		});
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
	//表单项非空监测
	function check()
	{
		if($("weixinId").value.length<1){
			alert("请输入微信ID！");
			return false;
		}else if($("name").value.length<1){
			alert("请输入名字!");
			return false;
		}else if($("department").value.length<1){
			alert("请输入部门名称!");
			return false;
		}else if($("workPlace").value.length<1){
			alert("请输入工作地!");
			return false;
		}else if(0==pivot){
			return false;
		}
		return true;
	}
	</script>
  </head>
  
  <body>
    <form action="add" method="post" onsubmit="return check();">
    	微信ID:<input name="weixinId" id="weixinId" /></br>
    	名字:<input name="name" id="name" /></br>
    	部门:<input name="department" id="department" /></br>
  		工作地点:<input name="workPlace" id="workPlace" size="30"/></br>
    	<input type="hidden" id="myLng" name="myLng" />
    	<input type="hidden" id="myLat" name="myLat" />
    	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;display:none;"></div>
    	<div id="map_area"></div>
    	<input type="submit" value="添加" />
    </form>
  </body>
</html>
>>>>>>> origin/master
