//package com.snsprj.test;
//
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//
///**
// * Created by skh
// * on 2017/6/2.
// */
//public class TestAliSMS {
//
//    private static final String SMS_SEND_URL = "http://gw.api.taobao.com/router/rest";
//    private static final String APPKEY = "23893647";
//    private static final String SECRET = "646bdc5adbe4c7541077e644034fd4a0";
//
//    private static final String SIGNNAME= "阿里大于测试专用";
//    private static final String TEMPLATEID= "SMS_69890384";
//
//
//    public static void main(String[] args){
//
//        DefaultTaobaoClient client = new DefaultTaobaoClient(SMS_SEND_URL, APPKEY, SECRET);
//
//        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//        req.setExtend("123456");
//        req.setSmsType("normal");
//        req.setSmsFreeSignName(SIGNNAME);
//        req.setSmsParamString("{'customer':'宝哥'}");
//        req.setRecNum("13120000287");
//        req.setSmsTemplateCode(TEMPLATEID);
//        AlibabaAliqinFcSmsNumSendResponse rsp = null;
//        try {
//            rsp = client.execute(req);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        System.out.println(rsp.getBody());
//
//    }
//
//}
