<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<style>
    .info-tip {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 12px;
        background-color: #fff;
        height: 35px;
        text-align: left;
    }
    #container{
    	margin-top: 80px;
    	margin-left: 20px
    }
</style>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=44310ff8f99dffe476fecd108bb12bcb"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="width:500px; height:300px">
		GPS服务器地址：
		<input type="text" id="gps_ip">
		<br/>
		GPS服务器端口号
		<input type="text" id="gps_port">
		<br/>
		<input type="button" value="获取地理位置" onclick="getLocation()">
	</div>
	
	<!-- 高德地图 -->
	<div id="container" style="width:500px; height:300px"></div>
	<div class="button-group">
	    <input id="setFitView" class="button" type="button" value="地图自适应显示"/>
	</div>
	<div class="info-tip">
	    <div id="centerCoord"></div>
	    <div id="tips"></div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
	
	<script>
		var lat = 0;
		var lng = 0;
		
	    var map = new AMap.Map('container', {
	        resizeEnable: true,
	        center: [116.397428, 39.90923],
	        zoom: 13
	    });    
	    map.clearMap();  // 清除地图覆盖物
	    var markers = [];
	    function getLocation(){
			var gpsIp = $("#gps_ip").val();
			var gpsPort = $("#gps_port").val();
			
			$.ajax({
				url:"/ssm/test/post",
				data:{"gpsIp":gpsIp,"gpsPort":gpsPort},
				type:"post",
				dataType:"json",
				async:false,
				success: function(data){
					lat = data.lat;
					lng = data.lng
					console.log("哈哈哈" + lat);
					markers = [{
				        icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png',
				        position: [lat, lng]
				    }];
					// 添加一些分布不均的点到地图上,地图上添加三个点标记，作为参照
				    markers.forEach(function(marker) {
				        new AMap.Marker({
				            map: map,
				            icon: marker.icon,
				            position: [marker.position[0], marker.position[1]],
				            offset: new AMap.Pixel(-12, -36)
				        });
				    });
				}
			});
		}
	    
	    
	    var center = map.getCenter();
	    var centerText = '当前中心点坐标：' + center.getLng() + ',' + center.getLat();
	    document.getElementById('centerCoord').innerHTML = centerText;
	    document.getElementById('tips').innerHTML = '成功添加三个点标记，其中有两个在当前地图视野外！';
	
	    // 添加事件监听, 使地图自适应显示到合适的范围
	    AMap.event.addDomListener(document.getElementById('setFitView'), 'click', function() {
	        var newCenter = map.setFitView();
	        document.getElementById('centerCoord').innerHTML = '当前中心点坐标：' + newCenter.getCenter();
	        document.getElementById('tips').innerHTML = '通过setFitView，地图自适应显示到合适的范围内,点标记已全部显示在视野中！';
	    });
	</script>
</body>
</html>