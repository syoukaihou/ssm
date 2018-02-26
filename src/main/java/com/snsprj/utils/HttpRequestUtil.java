package com.snsprj.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author skh
 *
 * 基于apache.http.client的http请求工具
 */
public class HttpRequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    private static final String UTF8 = "utf-8";

    private static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";


    /**
     * @author skh
     *
     * http get请求
     *
     * @param url 访问地址
     * @param dataMap 参数
     * @return String 返回值
     * @throws IOException 异常
     */
    public static String doGet(String url, Map<String, String> dataMap) throws IOException {

        String result;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            // 装填请求参数
            List<NameValuePair> nvps = new ArrayList<>();

            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }

            //转换为键值对
            String str = EntityUtils.toString(new UrlEncodedFormEntity(nvps, UTF8));

            HttpGet httpGet = new HttpGet(url + "?" + str);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {

                int status = response.getStatusLine().getStatusCode();

                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            result = httpClient.execute(httpGet, responseHandler);

        } finally {
            httpClient.close();
        }
        return result;
    }

    /**
     * @author skh
     *
     * http post 请求
     *
     * @param url 请求地址
     * @throws IOException IO异常
     */
    public static String doPost(String url, Map<String, String> dataMap) throws IOException {

        // 返回值
        String result;

        // 创建默认的httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            // 创建post请求对象
            HttpPost httpPost = new HttpPost(url);

            // 装填请求参数
            List<NameValuePair> nvps = new ArrayList<>();

            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }

            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, UTF8));

            // 设置header信息
            //指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", CONTENT_TYPE);
            httpPost.setHeader("User-Agent", USER_AGENT);

            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                response.getStatusLine();

                // 获取结果实体
                HttpEntity entity = response.getEntity();

                result = EntityUtils.toString(entity, UTF8);

                // 关流
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return result;
    }

    /**
     * @author SKH
     *
     * https信任所有
     *
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient getHttpClient(){

        SSLConnectionSocketFactory sslsf = null;

        try {

            //信任所有
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();

            sslsf = new SSLConnectionSocketFactory(sslContext);

        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
}
