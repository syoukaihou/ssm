package com.snsprj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snsprj.utils.HttpRequest;
import com.snsprj.vo.LoginVO;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/post", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String testPost() {

        // 第一步：获取授权
        // TODO ip地址和端口号要通过参数获取
        String url = "http://60.195.248.66:89/gpsonline/GPSAPI";

        String param = "version=1&method=loginSystem&name=yzfm&pwd=123qwe";

        String authResult = HttpRequest.sendPost(url, param);

        JSONObject authObject = JSON.parseObject(authResult);

        int uid = (int) authObject.get("uid");

        String uKey = (String) authObject.get("uKey");

        // 第二步：获取设备信息
        url = "http://60.195.248.66:89/gpsonline/GPSAPI";

        StringBuffer bParam = new StringBuffer("version=1&method=loadVehicles");
        bParam.append("&uid=");
        bParam.append(uid);
        bParam.append("&uKey=");
        bParam.append(uKey);

        param = bParam.toString();

        // json结构：
        // {[{[{},{}]}]}
        String deviceResult = HttpRequest.sendPost(url, param);

        JSONObject deviceObject = JSON.parseObject(deviceResult);

        System.out.println("设备信息==" + deviceResult);

        Object group = deviceObject.get("groups");

        JSONArray jArray = JSON.parseArray(group.toString());

        JSONObject vehicles = JSON.parseObject(jArray.get(0).toString());

        JSONArray vehicleArray = JSON.parseArray(vehicles.get("vehicles")
                .toString());

        vehicleArray.get(0);
        vehicleArray.get(1);

        JSONObject loadometer = JSON
                .parseObject(vehicleArray.get(1).toString());

        int vid = (int) loadometer.get("id");
        String vKey = (String) loadometer.get("vKey");

        System.out.println("当前结果==》" + vehicleArray.get(1));

        // 第三步：获取GPS数据
        String gpsUrl = "http://60.195.248.66:89/gpsonline/GPSAPI";

        StringBuffer gpsParam = new StringBuffer(
                "version=1&method=loadLocation");

        // &vid=8627860&vKey=03cff4a98ebadc8d1246b815641e7092
        gpsParam.append("&vid=");
        gpsParam.append(vid);
        gpsParam.append("&vKey=");
        gpsParam.append(vKey);

        String gpsResult = HttpRequest.sendPost(gpsUrl, gpsParam.toString());

        Object locationObjectTemp = JSON.parseObject(gpsResult).get("locs");

        JSONObject locationObject = JSON.parseObject(JSON
                .parseArray(locationObjectTemp.toString()).get(0).toString());

        // 经度
        locationObject.get("lat");
        // 纬度
        locationObject.get("lng");

        return locationObject.toString();
    }

    @RequestMapping(value = "/view")
    public String getTestJsp() {

        return "jsps/test";
    }

    @RequestMapping(value = "post/login", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public String testLogin(@Valid LoginVO loginVO, Errors errors) {

        if (errors.hasErrors()) {
            Map<String, String> errorMap = new HashMap<String, String>();

            System.out.println("出现了验证错误！！");

            List<FieldError> fieldErrorList = errors.getFieldErrors();
            

            for (int i = 0; i < fieldErrorList.size(); i++) {
                FieldError fieldError = fieldErrorList.get(i);
                String objectName = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                errorMap.put(objectName, defaultMessage);
            }

            return JSON.parseObject(JSON.toJSONString(errorMap)).toJSONString();
            
            //toJSONString()

        }

        return loginVO.getUsername() + "----->" + loginVO.getPassword();
    }
    
    @RequestMapping(value="login",method=RequestMethod.GET)
    public String getLogin(){

        return "jsps/login";
    }

}
