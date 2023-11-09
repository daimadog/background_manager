package com.background.manager.exception.enums;

import com.background.manager.exception.base.IBaseException;

public enum SecurityCodeEnum implements IBaseException {
    NOT_LOGIN_ERROR("A0001", "请重新登录"),
    NOT_ROLE_ERROR("A0002", "无此角色"),
    NOT_PERMISSION_ERROR("A0003", "无操作权限"),
    DISABLE_LOGIN_ERROR("A0004", "账户已被封停"),
    TOKEN_INVALID_ERROR("A0005", "Token已失效"),
    API_DISABLED_ERROR("A0006", "接口已禁用"),
    AUTH_ERROR("A9999", "认证异常");

    private String code;
    private String message;

    private SecurityCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getModuleName() {
        return "CLOUD-SECURITY";
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
