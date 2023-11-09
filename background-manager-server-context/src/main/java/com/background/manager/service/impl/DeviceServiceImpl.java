package com.background.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.service.DeviceService;
import com.background.manager.util.HttpUtils;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.background.manager.util.Md5Util;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import org.springframework.stereotype.Service;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Override
    public String getKitToken() {
        String appId="lcc21c3a06f8084b7f";
        //1、获取time、nonce和sign
        int time = Math.round(new Date().getTime()/1000);
        String nonce = getRandomChar(11);
        String source="time:"+time+","+"nonce:"+nonce+",appSecret:b9be3b5e5a1d470ba33140df29be71";
        String sign = Md5Util.MD5Encode(source);
        //2、调用第三方接口，获取access_token
        String getAccessTokenUrl="https://openapi.lechange.cn/openapi/accessToken";
        String param1="{\n" +
                " \"system\": {\n" +
                "    \"ver\": \"1.0\", \n" +
                "    \"sign\":"+"\""+sign+"\""+",\n" +
                "    \"appId\":"+"\""+appId+"\""+",\n" +
                "    \"time\":"+time+", \n" +
                "    \"nonce\":"+"\""+nonce+"\""+"\n" +
                "  }\n" +
                "}";
        String result1 = HttpUtils.sendPost(getAccessTokenUrl, param1);
        Map<String,JSONObject> map1=(Map)JSON.parse(result1);
        Map <String,JSONObject> result=(Map)map1.get("result");
        Map <String,JSONObject> data= (Map) result.get("data");
        Map<String,Object> value=(Map)data;
        String accessToken = (String) value.get("accessToken");
        //3、根据获取到的管理员token、deviceId、channelId、type获取到轻应用播放token
        int newTime = Math.round(new Date().getTime()/1000)+1;
        String newNonce = getRandomChar(11);
        String newSource="time:"+newTime+","+"nonce:"+newNonce+",appSecret:b9be3b5e5a1d470ba33140df29be71";
        String newSign = Md5Util.MD5Encode(newSource);
        String getKitTokenUrl="https://openapi.lechange.cn/openapi/getKitToken";
        String param2="{\n" +
                " \"system\": {\n" +
                "    \"ver\": \"1.0\", \n" +
                "    \"sign\":"+"\""+newSign+"\""+",\n" +
                "    \"appId\":"+"\""+appId+"\""+",\n" +
                "    \"time\":"+newTime+",\n" +
                "    \"nonce\":"+"\""+newNonce+"\""+"\n" +
                "  },\n" +
                "  \"params\": {\n" +
                "    \"token\":"+"\""+accessToken+"\""+",\n" +
                "    \"deviceId\": \"8H0BA74FACBA305\",\n" +
                "    \"channelId\": \"0\",\n" +
                "    \"type\": \"0\"\n" +
                "  }\n" +
                "}";
        String result2 = HttpUtils.sendPost(getKitTokenUrl, param2);
        Map<String,JSONObject> map2=(Map)JSON.parse(result2);
        Map <String,JSONObject> result3 =(Map) map2.get("result");
        Map <String,JSONObject> data1= (Map) result3.get("data");
        Map<String,Object> value1=(Map)data1;
        String kitToken = (String) (value1.get("kitToken"));
        return kitToken;
    }

    /**
     * Description: 随机生成0-9，a-z的11位字符串
     * @param length 字符串长度
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/12/14 10:08:15
     */
    public static String getRandomChar(int length) {
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(36)]);
        }
        return buffer.toString();
    }


}
