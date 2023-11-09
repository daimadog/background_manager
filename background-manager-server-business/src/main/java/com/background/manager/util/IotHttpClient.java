package com.background.manager.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
public class IotHttpClient {
    public final static String CHATSET = "UTF-8";
    private String url;
    private MediaType mediaType;
    private ConnMethod connMethod;
    private List<NameValuePair> headerParams;
    private List<NameValuePair> bodyParams;
    private JSONObject jsonParam;
    private SSLContext sslContext;
    private String xsrf;

    protected IotHttpClient(Builder builder){
        this.url = builder.url;
        this.mediaType = builder.mediaType;
        this.connMethod = builder.connMethod;
        this.headerParams = builder.headerParams;
        this.bodyParams = builder.bodyParams;
        this.jsonParam = builder.jsonParam;
        this.sslContext = builder.sslContext;
    }

    public String getXsrf() {
        return xsrf;
    }

    public void setXsrf(String xsrf) {
        this.xsrf = xsrf;
    }

    public String execute(){
        if(url == null || url.isEmpty()){
            log.error("Http Request Error: url cannot be empty");
            return null;
        }
        if("post".equals(connMethod.getMethod()) && mediaType == null){
            log.error("Http Post Request Error: media type cannot be null");
            return null;
        }
        try (CloseableHttpClient httpClient = buildHttpClient()) {
            CloseableHttpResponse response;
            if ("get".equals(connMethod.getMethod())) {
                String URL = url;
                if(bodyParams != null && !bodyParams.isEmpty()){
                    URL += "?" + bodyParams.get(0).getName() + "=" + bodyParams.get(0).getValue();
                    for(int i=1;i<bodyParams.size();i++){
                        URL += "&" + bodyParams.get(i).getName() + "=" + bodyParams.get(i).getValue();
                    }
                }

                log.info("Http Get Request: {}, {}, {}", url, headerParams, bodyParams);
                HttpGet getRequest = new HttpGet(URL);
                Iterator<NameValuePair> iter = headerParams.iterator();
                while(iter.hasNext()){
                    NameValuePair pair = iter.next();
                    if(pair.getValue() != null && !pair.getValue().isEmpty()){
                        getRequest.setHeader(pair.getName(),pair.getValue());
                    }
                }
                response = httpClient.execute(getRequest);
            } else if ("post".equals(connMethod.getMethod()) || "put".equals(connMethod.getMethod())) {
                HttpEntityEnclosingRequestBase postRequest;
                if("post".equals(connMethod.getMethod())){
                    postRequest = new HttpPost(url);
                }else{
                    postRequest = new HttpPut(url);
                }
                postRequest.setHeader(CONTENT_TYPE, mediaType.getValue());
                Iterator<NameValuePair> iter = headerParams.iterator();
                while(iter.hasNext()){
                    NameValuePair pair = iter.next();
                    if(pair.getValue() != null && !pair.getValue().isEmpty()){
                        postRequest.setHeader(pair.getName(),pair.getValue());
                    }
                }
                log.info("Http Post Request: {}, {}, {}", url, headerParams, bodyParams);
                if (MediaType.APPLICATION_FORM_URLENCODED.equals(mediaType)) {
                    postRequest.setEntity(new UrlEncodedFormEntity(bodyParams, CHATSET));
                } else if(MediaType.APPLICATION_JSON.equals(mediaType)){
                    JSONObject json = new JSONObject();
                    for(NameValuePair pair : bodyParams){
                        if(pair.getValue() != null && pair.getValue().startsWith("{")&&pair.getValue().endsWith("}")){
                            JSONObject value = JSON.parseObject(pair.getValue());
                            json.put(pair.getName(),value);
                        }else if(pair.getValue() != null){
                            json.put(pair.getName(), pair.getValue());
                        }
                    }
                    if(!jsonParam.isEmpty()){
                        json.putAll(jsonParam);
                    }
                    log.info("Http Post Request: {}, {}, {}", url, headerParams, json);
                    postRequest.setEntity(new StringEntity(json.toString(), CHATSET));
                } else if(MediaType.APPLICATION_XML.equals(mediaType)){
                    String postData = getXmlBody(bodyParams);
                    postRequest.setEntity(new StringEntity(postData,CHATSET));
                }else{
                    postRequest.setEntity(new UrlEncodedFormEntity(bodyParams, CHATSET));
                }
                response = httpClient.execute(postRequest);
            }else {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, CHATSET);
            log.info("Http Response: {}, {}", statusCode, result);
            if(statusCode == 200) {
                Header[] cookieHeaders = response.getHeaders("Set-Cookie");
                for(int i=0;i<cookieHeaders.length;i++){
                    String cookie = cookieHeaders[i] == null ? null : cookieHeaders[i].getValue();
                    if(cookie != null && cookie.indexOf("XSRF-TOKEN=") >= 0){
                        String temp = cookie.substring("XSRF-TOKEN=".length());
                        int indexOfComma = temp.indexOf(";");
                        if(indexOfComma >= 0){
                            xsrf = temp.substring(0,indexOfComma);
                        }else{
                            xsrf = temp;
                        }
                        break;
                    }
                }
                return result;
//                String cookie = response.getFirstHeader("Set-Cookie").getValue();
//                if(cookie != null && cookie.indexOf("XSRF-TOKEN=") >= 0){
//                    String temp = cookie.substring("XSRF-TOKEN=".length());
//                    int indexOfComma = temp.indexOf(";");
//                    if(indexOfComma >= 0){
//                        xsrf = temp.substring(0,indexOfComma);
//                    }else{
//                        xsrf = temp;
//                    }
//                }
            }else{
                return null;
            }
        } catch (IOException e) {
            log.error("Http Request Error: {}",e.getMessage(),e);
            return null;
        }
    }

    private String getXmlBody(List<NameValuePair> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>\r\n");
        for(NameValuePair pair : list){
            sb.append("<" + pair.getName() + ">" + pair.getValue() + "</" + pair.getName() + ">" + "\r\n");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static final class Builder {
        private String url;
        private MediaType mediaType;
        private ConnMethod connMethod;
        private List<NameValuePair> headerParams;
        private List<NameValuePair> bodyParams;
        private JSONObject jsonParam;
        private SSLContext sslContext;

        public Builder() {
            headerParams = new ArrayList<>();
            bodyParams = new ArrayList<>();
            jsonParam = new JSONObject();
            connMethod = ConnMethod.HTTP_GET; //默认以http的方式
            sslContext = defaultSSLContext();
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder mediaType(MediaType mediaType){
            this.mediaType = mediaType;
            return this;
        }

        public Builder connMethod(ConnMethod connMethod){
            this.connMethod = connMethod;
            return this;
        }

        public Builder sslContext(SSLContext sslContext){
            this.sslContext = sslContext;
            return this;
        }

        public Builder addHeaderParam(String name,String value){
            headerParams.add(new BasicNameValuePair(name,value));
            return this;
        }

        public Builder addBodyParam(String name, String value){
            bodyParams.add(new BasicNameValuePair(name,value));
            return this;
        }

        public Builder addBodyParam(boolean condition, String name, String value){
            if(condition){
                bodyParams.add(new BasicNameValuePair(name,value));
            }
            return this;
        }

        public Builder setJsonParam(JSONObject jsonParam){
            if(jsonParam != null){
                this.jsonParam = jsonParam;
            }
            return this;
        }

        public IotHttpClient build(){
            return new IotHttpClient(this);
        }
    }

    private CloseableHttpClient buildHttpClient(){
        if("http".equals(connMethod.getProtocol())){
            return HttpClientBuilder.create().build();
        }else if("https".equals(connMethod.getProtocol())){
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(csf).build();
        }else{
            return null;
        }
    }

    private static SSLContext defaultSSLContext(){
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType)->true;
        try {
            return SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public enum ConnMethod {
        HTTP_GET("http","get"),
        HTTPS_GET("https","get"),
        HTTP_POST("http","post"),
        HTTPS_POST("https","post"),
        HTTP_PUT("http","put"),
        HTTPS_PUT("https","put");

        ConnMethod(String protocol, String method) {
            this.protocol = protocol;
            this.method = method;
        }

        private String protocol;
        private String method;

        public String getProtocol() {
            return protocol;
        }

        public String getMethod(){
            return method;
        }
    }

    public enum MediaType {
        APPLICATION_JSON("application/json"),
        APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
        APPLICATION_XML("application/xml");

        private String value;

        MediaType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
