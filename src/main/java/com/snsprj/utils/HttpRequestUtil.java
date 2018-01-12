package com.snsprj.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ch.qos.logback.core.util.StatusListenerConfigHelper;

public class HttpRequestUtil {

    public static void doGet(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                response.getStatusLine();
                System.out.println(response.getStatusLine());
                HttpEntity httpEntity = response.getEntity();
                EntityUtils.consume(httpEntity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    public static void doGet2(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                // Create a custom response handler
                @Override
                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {

                    int status = response.getStatusLine().getStatusCode();

                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpClient.execute(httpGet, responseHandler);
            System.out.println(responseBody);
        } finally {
            httpClient.close();
        }
    }

    public static void doPost(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                response.getStatusLine();
                HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }



    public static void main(String[] args) {

        String url = "http://www.baidu.com/";
        try {
            doGet2(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
