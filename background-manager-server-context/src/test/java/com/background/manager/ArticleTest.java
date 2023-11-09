package com.background.manager;

import cn.hutool.crypto.digest.MD5;
import com.background.manager.service.DeviceService;
import com.background.manager.util.HttpUtils;
import com.background.manager.util.Md5Util;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

import static com.background.manager.service.impl.DeviceServiceImpl.getRandomChar;

@SpringBootTest
public class ArticleTest {

    @Resource
    private DeviceService deviceService;

    @Test
    public void  test1(){
        System.out.println("测试类");



        //调用第三方接口，获取管理员token
        String getAccessTokenUrl="https://openapi.lechange.cn/openapi/accessToken";
        String sign="f0fa6eec6aae10ae8dd4ac81fd768998";
        String appId="lcc21c3a06f8084b7f";
        int time = Math.round(new Date().getTime() / 1000);
        String nonce="xe8kbo3sc0r";
        String body="{\n" +
                " \"system\": {\n" +
                "    \"ver\": \"1.0\", \n" +
                "    \"sign\":"+"\""+sign+"\""+",\n" +
                "    \"appId\":"+"\""+appId+"\""+",\n" +
                "    \"time\":"+time+",\n" +
                "    \"nonce\":"+"\""+nonce+"\""+"\n" +
                "  }\n" +
                "}";
        System.out.println(body);
        String accessToken="At_00006ac6e32d123141238f60147de7ec";
        String param2="{\n" +
                " \"system\": {\n" +
                "    \"ver\": \"1.0\", \n" +
                "    \"sign\":"+"\""+sign+"\""+",\n" +
                "    \"appId\":"+"\""+appId+"\""+",\n" +
                "    \"time\":"+time+",\n" +
                "    \"nonce\":"+"\""+nonce+"\""+"\n" +
                "  },\n" +
                "  \"params\": {\n" +
                "    \"token\":"+"\""+accessToken+"\""+",\n" +
                "    \"deviceId\": \"8H0BA74FACBA305\",\n" +
                "    \"channelId\": \"0\",\n" +
                "    \"type\": \"0\"\n" +
                "  }\n" +
                "}";
        System.out.println(param2);
        String s = HttpUtils.sendPost(getAccessTokenUrl, body);
        System.out.println(s);
    }

    @Test
    public void  test2(){
        int time = Math.round(new Date().getTime() / 1000);
        System.out.println(time);
        //根据Math.random().toString(36).substr(2);
        String nonce = getRandomChar(11);
        System.out.println(nonce);
        System.out.println("cuocnxzam58");
        //根据time、nonce和appSecret生产sign;
        String source="time:"+time+",nonce:"+nonce+",appSecret:b9be3b5e5a1d470ba33140df29be71";
        System.out.println(source);
        String sign = Md5Util.MD5Encode(source);
        String digestHex = MD5.create().digestHex(source);
        System.out.println(digestHex);
        System.out.println(sign);
        System.out.println("25c09a7f8423db6fdd2b451de05d9f49");
    }

    @Test
    public void  test3(){
        String str="object";
        String str1=null;
        Assert.assertNotNull("对象为空" ,str);
       // Assert.assertNotNull("对象必须不为空" ,str1);
        // Assert.assertNull("对象必须为空",str);
       // Assert.assertTrue("参数对象逻辑必须为true",ObjectUtil.isNull(str));
        System.out.println(ObjectUtils.nullSafeClassName(str));
        System.out.println(ObjectUtils.nullSafeHashCode(str));
    }



}
