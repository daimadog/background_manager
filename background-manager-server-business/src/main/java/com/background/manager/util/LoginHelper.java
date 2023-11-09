package com.background.manager.util;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.config.BackgroundAuthContext;
import com.background.manager.constants.DeviceTypeEnum;
import com.background.manager.model.BackgroundUser;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: Sa-Token辅助工具类
 * @Author: 杜黎明
 * @Date: 2022/09/29 14:11:09
 * @Version: 1.0.0
 */
public class LoginHelper {

    public LoginHelper() {
    }

    public static <T extends BackgroundUser> void login(T backgroundUser){
        StpUtil.login(backgroundUser.getLoginId(), DeviceTypeEnum.PC.getDevice());
        setBackground(backgroundUser);
    }

    public static <T extends BackgroundUser> void loginByDevice(T backgroundUser,DeviceTypeEnum deviceTypeEnum){
        StpUtil.login(backgroundUser.getLoginId(), deviceTypeEnum.getDevice());
        setBackground(backgroundUser);
    }

    public static <T extends BackgroundUser> void AutomaticLogin(T backgroundUser){
        StpUtil.login(backgroundUser.getLoginId(), true);
        setBackground(backgroundUser);
    }

    public static SaTokenInfo getTokenInfo(){
        return StpUtil.getTokenInfo();
    }

    private static <T extends BackgroundUser> void setBackground(T backgroundUser) {
        BackgroundAuthContext.put("backgroundUser",backgroundUser);
        StpUtil.getTokenSession().set("backgroundUser",backgroundUser);
    }

    public static <T extends BackgroundUser> BackgroundUser getLoginUser(){
        BackgroundUser backgroundUser = (BackgroundUser) BackgroundAuthContext.get("backgroundUser");
        if(backgroundUser!=null){
            return backgroundUser;
        }else {
            SaSession tokenSession = StpUtil.getTokenSession();
            BackgroundUser background = (BackgroundUser) tokenSession.get("backgroundUser");
            BackgroundAuthContext.put("backgroundUser",background);
            return background;
        }
    }

    public static <T extends BackgroundUser> String getLoginId() {
        BackgroundUser backgroundUser = (BackgroundUser) BackgroundAuthContext.get("backgroundUser");
        if (ObjectUtil.isNull(backgroundUser)){
            String loginId = StpUtil.getLoginIdAsString();
            if (ObjectUtil.isEmpty(loginId)){
                throw new UtilException("登录用户：loginId异常 =>"+loginId);
            }else {
                return loginId;
            }
        }else {
            return backgroundUser.getLoginId();
        }
    }

    public static <T extends BackgroundUser> void  logout(T backgroundUser){
        clearOwnerCache(backgroundUser.getLoginId());
        StpUtil.logout(backgroundUser.getLoginId());
    }

    public static <T extends BackgroundUser> void  logout(T backgroundUser,DeviceTypeEnum deviceTypeEnum){
        clearOwnerCache(backgroundUser.getLoginId());
        StpUtil.logout(backgroundUser.getLoginId(),deviceTypeEnum.getDevice());
    }

    public static <T extends BackgroundUser> void  logout(String loginId){
        clearOwnerCache(loginId);
        StpUtil.logout(loginId);
    }

    public static <T extends BackgroundUser> void  logout(String loginId,DeviceTypeEnum deviceTypeEnum){
        clearOwnerCache(loginId);
        StpUtil.logout(loginId,deviceTypeEnum.getDevice());
    }

    private static void clearOwnerCache(String loginId) {
        if (loginId.equals(StpUtil.getLoginIdAsString())){
            clearCache();
        }
    }

    private static void clearCache() {
        BackgroundAuthContext.removeContext();
    }

    private static <T extends BackgroundUser> void kickOut(T backgroundUser){
        StpUtil.kickout(backgroundUser.getLoginId());
    }

    private static <T extends BackgroundUser> void kickOut(T backgroundUser,DeviceTypeEnum deviceTypeEnum){
        StpUtil.kickout(backgroundUser.getLoginId(),deviceTypeEnum.getDevice());
    }

    private static <T extends BackgroundUser> void kickOut(String loginId){
        StpUtil.kickout(loginId);
    }

    private static <T extends BackgroundUser> void kickOut(String loginId,DeviceTypeEnum deviceTypeEnum){
        StpUtil.kickout(loginId,deviceTypeEnum.getDevice());
    }

}
