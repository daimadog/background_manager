package com.background.manager.constants;

import lombok.Data;

@Data
public class UserStatusConstant {
    /**
     * 用户正常状态
     */
    public static final Integer NORMAL_STATUS=0;

    /**
     * 用户冻结状态
     */
    public static final Integer FREEZE_STATUS=1;

    /**
     * 用户未审核状态
     */
    public static final Integer UNAPPROVED=0;

    /**
     * 用户通过审核状态
     */
    public static final Integer APPROVED=1;

    /**
     * 拒绝用户审核状态
     */
    public static final Integer Reject=2;

    /**
     * 用户未删除状态
     */
    public static final Integer NOT_DELETE_STATUS=0;

    /**
     * 用户删除状态
     */
    public static final Integer DELETE_STATUS=1;

    /**
     * 角色正常状态
     */
    public static final Integer NORMAL_ROLE_STATUS=0;

    /**
     * 角色冻结状态
     */
    public static final Integer FREEZE_ROLE_STATUS=1;
    /**
     * 父节点
     */
    public static  final  Integer FIRST_MENU_ID=0;

    /**
     * 登录成功
     */
    public static  final  Integer LOGIN_SUCCESS=1;

    /**
     * 登录成功信息
     */
    public static final String LOGIN_SUCCESS_MESSAGE="登录成功";
    /**
     * 登录失败
     */
    public static final Integer LOGIN_FAIL=2;
    /**
     * 未知地址
     */
    public static final String UN_KNOWN = "XX XX";
    /**
     * 开启“记住我”模式
     */
    public static final Integer REMEMBER_ME=1;

    /**
     * 关闭“记住我”模式
     */
    public static final Integer NOT_REMEMBER_ME=0;

    /**
     * 默认ip地址
     */
    public static final String INTRANET_IP = "内网IP";
    /**
     * ip地址查询
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip={ip}&json=true";
    /**
     * 操作日志key值
     */
    public static final String APILOG_OBJ_SAVE_KEY = "APILOG_OBJ_SAVE_KEY";
    /**
     * 普通运营人员
     */
    public static final Integer OPERATION_USER=0;
    /**
     * 管理员标识
     */
    public static final Integer ADMINISTRATOR_USER=1;

    /**
     * 失败登录次数
     */
    public static final Integer LOGIN_FAIL_NUM=5;
    /**
     *  账号封禁时间 （s）
     */
    public static final Integer DISABLE_TIME=600;

}
