package com.snsprj.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.Test;

/**
 * Created by skh on 2017/7/7.
 */
public class TestAliMail {

    @Test
    public void testSendMail(){
        String accessKey = "";
        String accessSecret = "";

        // 控制台创建的发信地址
        String accountName = "noreply@snsprj.cn";

        // 发信人昵称
        String fromAlias = "薪付宝平台";

        // 控制台创建的标签
        String tagName = "sysMail";

        // 目标地址
        String toAddress = "syoukaihou@gmail.com";

        // 邮件主题
        String subject = "申请资料提交成功";

        // 邮件正文
        String htmlBody = "申请资料提交成功";

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //
            request.setAccountName(accountName);
            request.setFromAlias(fromAlias);
            request.setAddressType(1);
            request.setTagName(tagName);
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
