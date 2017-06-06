<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=44310ff8f99dffe476fecd108bb12bcb"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<title>点标记</title>
	<style type="text/css">
		#container{
			
		}
	</style>
</head>
<body>
	<div id="container"></div>
	<script type="text/javascript">
	    //初始化地图对象，加载地图
	    var map = new AMap.Map("container", {
	        resizeEnable: true,
	        center: [116.397428, 39.90923],//地图中心点
	        zoom: 13 //地图显示的缩放级别
	    });
	    //添加点标记，并使用自己的icon
	    new AMap.Marker({
	        map: map,
			position: [116.405467, 39.907761],
	        icon: new AMap.Icon({            
	            size: new AMap.Size(40, 50),  //图标大小
	            image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
	            imageOffset: new AMap.Pixel(0, -60)
	        })        
	    });
	</script>
</body>
</html>