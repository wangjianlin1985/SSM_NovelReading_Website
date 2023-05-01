package com.xnnovel.xy.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>后台HTTPS请求封装
 * SSL:Http和TCP之间的加密层
 * 需要用到另外一个JSONUtils工具类
 * Created by yanghl on 2016/12/21.
 */
public class HttpClientUtils {
    private static HttpClientBuilder httpClientBuilder;
    private static HttpClient httpClient;
    private static  final String encode = "GBK";

    private static int MAX_CONN_TOTAL = 10;           // 连接池中的最大连接数
    private static int MAX_CONN_PER_ROUTE = 10;       // 连接同一个route最大的并发数

    private static int CONNECT_REQUEST_TIMEOUT = 1000*60; // 从连接池中获取可用连接最大超时时间 单位：毫秒
    private static int CONNECT_TIMEOUT = 1000*60;         // 连接目标url最大超时 单位：毫秒
    private static int SOCKET_TIMEOUT = 1000*60;          // 等待响应（读数据）最大超时 单位：毫秒


    /**
     * 静态块
     * 当程序第一次调用httpClient时，
     * 自动初始化httpClient
     */
    static{

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT) // 单位为毫秒
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        httpClient = HttpClientBuilder.create().setMaxConnTotal(MAX_CONN_TOTAL) // 连接池中的最大连接数
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE) // 分配给同一个route最大的并发数
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 指定请求URL作GET请求
     * @param url       --请求URL
     * @return String   --返回参数: 字符串
     * @throws IOException
     */
    public static String get(String url) throws IOException {

        HttpGet method = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode < 300){
                HttpEntity entity = httpResponse.getEntity();
                return EntityUtils.toString(entity, encode);
            }else{
                throw new IOException("http get[" + url + "] failed,statuCode [" + statusCode + "].");
            }
        }
        finally {
            method.releaseConnection();
        }
    }

    /**
     * 指定URL作GET请求
     * @param url   --请求URL
     * @param t     --返回参数类型
     * @param <T>   --返回参数类型为泛型
     * @return      --返回参数：对象
     * @throws IOException
     */
    public static <T> T doGet(String url,Class<T> t) throws IOException{
        return JSONUtils.parse(get(url),t);
    }


    /**
     * 模拟Post表单(application/x-www-form-urlencoded)提交
     * 参数在request请求内容是以name=1&passwors2形式传递
     * @param url    --调用地址
     * @param params --表单参数   if need order, use LinkedHashMap
     * @return
     */
    public static String post(String url, Map<String, String> params) throws IOException{
        HttpPost method = new HttpPost(url);
        method.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encode.toLowerCase());
        if(params != null || params.size() > 0){
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> set = params.keySet();
            for (String k:set){
                nvps.add(new BasicNameValuePair(k,params.get(k)));
            }
            method.setEntity(new UrlEncodedFormEntity(nvps, encode));
        }
        return post(url, method);
    }

    /**
     * 参数类型为String的Post调用
     * @param url  --调用地址
     * @param body --String类型的参数，可以为JSON字符串
     * @return
     * @throws IOException
     */
    public static String post(String url, String body) throws IOException{
        HttpPost method = new HttpPost(url);
        if(body != null) {
            method.setEntity(new StringEntity(body));
        }
        return post(url, method);
    }

    /**
     * 基础方法
     * @param url    --调用地址
     * @param method --方法
     * @return
     * @throws IOException
     */
    private static String post(String url, HttpPost method) throws IOException{
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode < 300){
                HttpEntity entity = httpResponse.getEntity();
                return EntityUtils.toString(entity);
            }else{
                throw new IOException("http post[" + url + "] failed,statuCode [" + statusCode + "].");
            }
        }
        finally {
            method.releaseConnection();
        }
    }

    /**
     * 参数类型为对象的Post调用
     * @param url  调用地址
     * @param body Object参数
     * @return
     * @throws IOException
     */
    public static String postJson(String url, Object body) throws IOException{
        HttpPost method = new HttpPost(url);
        if(body != null) {
            method.setEntity(new StringEntity(JSONUtils.toString(body), ContentType.APPLICATION_JSON));
        }
        return post(url, method);
    }


    /**
     * 参数类型为对象的Post调用：和postJson一样
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String putJson(String url, Object body) throws IOException{
        HttpPut method = new HttpPut(url);
        if(body != null) {
            method.setEntity(new StringEntity(JSONUtils.toString(body), ContentType.APPLICATION_JSON));
        }
        try {
            HttpResponse httpResponse = httpClient.execute(method);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode < 300){
                HttpEntity entity = httpResponse.getEntity();
                return EntityUtils.toString(entity);
            }else{
                throw new IOException("http put[" + url + "] failed,statuCode [" + statusCode + "].");
            }
        }
        finally {
            method.releaseConnection();
        }
    }


    /**
     * 带自定义报文头POST请求
     * @param url           --请求URL
     * @param body          --请求参数
     * @param headerMap     --请求头
     * @return              --返回参数类型：String
     * @throws IOException
     */
    public static String doPostJson(String url,Object body,Map<String,String>headerMap) throws IOException {
        HttpPost post = new HttpPost(url);
        if(headerMap!=null&&headerMap.size()>0){
            Iterator it = headerMap.keySet().iterator();
            while(it.hasNext()){
                String key = (String) it.next();
                post.addHeader(key,headerMap.get(key));
            }
        }
        try {
            String res;
            if(body != null) {
                String jsonbody=JSONUtils.toString(body);
                System.out.println("request body--->:"+jsonbody);
                post.setEntity(new StringEntity(jsonbody, ContentType.APPLICATION_JSON));
            }
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode < 300){
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
            else{
                throw new IOException("http get[" + url + "] failed,statuCode [" + statusCode + "].");
            }
            return res;
        }
        catch (Exception e){
            if(!post.isAborted()) {
                post.abort();
            }
            throw new IOException(e);
        }
    };
}
