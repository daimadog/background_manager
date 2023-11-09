package com.background.manager.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class SmsUtil {
    //用于格式化鉴权头域，给“X-WSSE”参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    //用于格式化鉴权头域，给“Authorization”参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";
    //APP接入地址 + 接口访问URI
    private static final String URL = "https://rtcsms.cn-north-1.myhuaweicloud.com:10743/sms/batchSendSms/v1";
    //状态报告接收地址，为空或者不填表示不接收状态报告
    private static final String STATUSCALLBACK = "";

    private static final String CODE = "code";
    private static final String SUCCESS_CODE = "000000";

    public static final String appKey = "0XnT4oEifIqvQn5p8LGVK7ig4gFI";
    public static final String appSecret = "m2Op22l3T3MqEc17v5iw6U9sF225";
    public static final String sender = "8823031527988";
    public static final String templateId = "186d4fbdab814821b45bf1f3e2702af8";
    public static final String signature = "宁波人工智能超算中心";

    public static void sendSms(String phoneNum){
        String receiver = "+86" + phoneNum;
        String body = buildRequestBody(sender, receiver, templateId,STATUSCALLBACK,signature);
        String wsseHeader = buildWsseHeader(appKey, appSecret);
        log.info("send sms to " + phoneNum);
        try {
            CloseableHttpClient client = HttpClients.custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
                            (x509CertChain, authType) -> true).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
            HttpResponse response = client.execute(RequestBuilder.create("POST")
                    .setUri(URL)
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
                    .addHeader("X-WSSE", wsseHeader)
                    .setEntity(new StringEntity(body)).build());

            if(response.getEntity() != null){
                String entity = EntityUtils.toString(response.getEntity());
                log.info("sms response entity: " + entity);
                JSONObject responseObj = JSON.parseObject(entity);
                String code = responseObj.getString(CODE);
                if(SUCCESS_CODE.equals(code)){
                    log.info("send sms code success.");
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (KeyStoreException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    static String buildRequestBody(String sender, String receiver, String templateId,
                                   String statusCallbackUrl,String signature) {

        List<NameValuePair> keyValues = new ArrayList<NameValuePair>();
        keyValues.add(new BasicNameValuePair("from", sender));
        keyValues.add(new BasicNameValuePair("to", receiver));
        keyValues.add(new BasicNameValuePair("templateId", templateId));
        keyValues.add(new BasicNameValuePair("statusCallback", statusCallbackUrl));
        keyValues.add(new BasicNameValuePair("signature", signature));
        //如果JDK版本是1.6，可使用：URLEncodedUtils.format(keyValues, Charset.forName("UTF-8"));
        return URLEncodedUtils.format(keyValues, StandardCharsets.UTF_8);
    }

    static String buildWsseHeader(String appKey, String appSecret) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");

        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);
        String passwordDigestBase64Str = Base64.encodeBase64String(hexDigest.getBytes(Charset.forName("utf-8")));
        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

}
