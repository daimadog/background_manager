package com.background.manager.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.constants.UserStatusConstant;

public class AddressUtils {
    /**
     * ip地址查询
     * @param ip
     * @return
     */
    public static String getRealAddressByIP(String ip) {
        String address = UserStatusConstant.UN_KNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return UserStatusConstant.INTRANET_IP;
        }
        HttpResponse response = HttpRequest.get(UserStatusConstant.IP_URL).execute();
        if (200!=response.getStatus()) {
            return "获取位置失败";
        }else {
        JSONObject resJson = JSONObject.parseObject(response.body());
        JSONArray resArr = JSONArray.parseArray(resJson.getString("data"));
        String region = resJson.getString("pro");
        resJson = JSONObject.parseObject("" + resArr.get(0));
        return resJson.getString("location");
        }
    }


}
