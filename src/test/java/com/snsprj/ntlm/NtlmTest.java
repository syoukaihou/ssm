package com.snsprj.ntlm;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class NtlmTest {
    public static void main(String[] args)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF,    authpref);
        //参数分别为用户名、密码、服务器url、工作域名称
        String username = "xiaohuaibao@snsprj.com";
        String password = "TongxiangyuTel13120000288";
        String serverName = "192.168.7.184";
        String domain = "snsprj.com";
        NTCredentials creds = new NTCredentials(username, password,     serverName, domain);
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        //设置要连接的目标名称、端口
        HttpHost target = new HttpHost(serverName, 80, "http");

        // Make sure the same context is used to execute logically related requests
        HttpContext localContext = new BasicHttpContext();

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("目标域名的详细url：serverName+端口+路径等");
        //下面是为请求加上一些header信息，来伪装浏览器
        httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");

        try
        {

            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
}
