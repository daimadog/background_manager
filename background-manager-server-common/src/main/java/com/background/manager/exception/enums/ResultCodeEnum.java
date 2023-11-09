package com.background.manager.exception.enums;

import com.background.manager.exception.base.IBaseException;
import lombok.Getter;


@Getter
public enum ResultCodeEnum implements IBaseException {

    SUCCESS("200", "成功"),
    FAIL("201","失败" ),
    PARAM_ERROR("A0001", "参数错误"),
    FORBIDDEN_ERROR("A0002", "无权限访问"),
    DATA_OVERDUE_ERROR("A0003", "数据已过期,请刷新后重试"),
    UNSAFE_ERROR("A0004", "当前账户非安全状态"),
    UNKNOWN_ERROR("B0001", "系统开小差啦"),
    METHOD_NOT_SUPPORT("B0002", "方法不被支持"),
    NULL_POINT("B0003", "空指针异常"),
    ENTITY_EXIST("B0004", "实体已存在"),
    ENTITY_NOT_EXIST("B0005", "实体不存在"),
    HTTP_CLIENT_ERROR("B0006", "客户端连接异常"),
    ID_NOT_EXIST("B0007", "记录ID不能为空"),
    CREATE_FAIL("B0008", "新增失败"),
    UPDATE_FAIL("B0009", "修改失败"),
    DELETE_FAIL("B0010", "删除失败"),
    ENUM_DEFINITION_ERROR("B0011", "系统异常定义错误"),
    LOGIN_FAIL("B0012","登录失败"),
    REGISTER_FAIL("B0013","注册失败"),
    AUDIT_FAIL("B0014","审核失败"),
    UPLOAD_FAIL("B0015","上传失败" ),
    PROCESS_FAIL("B0016","处理失败"),
    PARAM_INVALID("B0017","手机号码格式不正确"),
    SMS_TOO_FREQUENT("B0018","短信验证码已发送，请在1分钟后重试");
    /**
     * 响应状态码
     */
    private final String code;
    /**
     * 响应信息
     */
    private final String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getModuleName() {
        return "CMOS-COMMON";
    }
}
