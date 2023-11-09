package com.background.manager.exception;

import com.background.manager.exception.base.IBaseException;
import lombok.Getter;

@Getter
public enum BackgroundManagementResultCodeEnum  implements IBaseException {

    NOT_LOGIN_ERROR("A0001", "请重新登录"),
    NOT_ROLE_ERROR("A0002", "无此角色"),
    NOT_PERMISSION_ERROR("A0003", "无操作权限"),
    NOT_TOKEN("A0004","未提供Token"),
    TOKEN_INVALID_ERROR("A0005","Token已失效"),
    TOKEN_TIMEOUT("A0006","Token已超时"),
    BE_REPLACED("A0007","账号已在其他地方登录"),
    KICK_OUT("A0008","token已被踢下线"),
    API_DISABLED_ERROR("A0009", "接口已禁用"),
    DISABLE_LOGIN_ERROR("A00010","账号已被封禁"),
    AUTH_ERROR("A9999", "认证异常"),
    HANDLER_NOT_FOUND_ERROR("A0011", "Current Value Type Not Matching Handler"),
    SM2_ENCRYPTION_FAIL("A0012","国密加密报错" ),
    REJECT_ACCOUNT_FAIL("A0013","拒绝用户审核失败" ),
    APPROVE_ACCOUNT_FAIL("A0014","通过用户审核失败" ),
    ACCOUNT_DISABLE("A0015","账号已被封禁"),

    ENTITY_EXIT_ERROR("B0001","用户已存在"),
    ENTITY_NOT_EXIT_ERROR("B0002","用户不存在"),
    PASSWORD_INCONSISTENT_ERROR("B0003","注册密码和确认密码不一致"),
    PASSWORD_INCORRECT_ERROR("B0004","密码错误"),
    ROLE_NAME_REPEAT_ERROR("B0005","角色名称重复"),
    ROLE_FREEZE_ERROR("B0006","角色已停用，无法操作"),
    ROLE_ENGLISH_NAME_REPEAT_ERROR("B0007","角色英文名称重复"),
    PARENT_MENU_NOT_EXIT_ERROR("B0008","上级菜单不存在"),
    MENU_NAME_REPEAT_ERROR("B0009","权限菜单名称重复"),
    PATH_EXIT_ERROR("B0010","路由地址已存在"),
    ROLE_NOT_EXIT_ERROR("B0011","角色不存在"),
    PERMISSION_NOT_EXIT_ERROR("B0012","权限菜单不存在"),
    PERMISSION_FREEZE_ERROR("B0013","权限菜单已停用，无法操作"),
    USER_FREEZE_ERROR("B0014","用户已冻结,无法操作"),
    USER_UNAPPROVED_ERROR("B0015","用户未审核，无法操作"),
    USER_LOGIN_ID_REPEAT_ERROR("B0016","用户账号已存在"),

    KEYWORDS_REPEAT_ERROR("C0001","文章关键词重复"),
    ARTICLE_TITLE_REPEAT_ERROR("C0009","文章标题重复"),
    ARTICLE_NOT_EXIST_ERROR("C0002","文章不存在"),
    ARTICLE_FREEZE_ERROR("C0003","文章已被冻结,无法操作"),
    COLUMN_NAME_REPEAT_ERROR("C0004","栏目名称重复"),
    COLUMN_ENGLISH_NAME_REPEAT_ERROR("C0005","栏目英文名重复"),
    COLUMN_NOT_EXIST_ERROR("C0006","栏目不存在"),
    ACTIVITY_NAME_EXIST_ERROR("C0007","活动标题名称重复"),
    ACTIVITY_NOT_EXIST_ERROR("C0008","活动项目不存在"),
    IMAGE_TYPE_INCORRECT_ERROR("C0010","图片格式不正确"),
    MULTIPARTFILE_EMPTY_ERROR("C0011","视频文件为空" ),
    VIDEO_EXTENSION_ERROR("C0012","视频文件格式不正确" ),
    CAROUSEL_PAGE_TITLE_REPEAT_ERROR("C0013","轮播页标题名称重复" ),
    CAROUSEL_PAGE_NOT_EXIST_ERROR("C0014","轮播页对象不存在"),


    RESOURCE_ENTITY_NOT_EXIST("D0001","集群资源对象不存在" ),
    RESOURCE_DAILY_USAGE_ENTITY_NOT_EXIST("D0002","单日资源使用情况不存在" ),
    WORK_RUNNING_USAGE_ENTITY_EXIST("D0003","运行节点不存在"),
    TYPICAL_CLASS_ENTITY_TITLE_REPEAT_ERROR("D0004","典型案例实体标题重复" ),
    TYPICAL_CLASS_ENTITY_NOT_EXIST_ERROR("D0005","典型案例实体不存在"),
    PARTNERS_ENTITY_NOT_EXIST_ERROR("D0006","合作伙伴不存在" ),
    CUSTOMER_USAGE_ENTITY_NOT_EXIST("D0007","客户情况不存在" ),
    CONTRACT_NAME_REPEAT_ERROR("D0008","合同名称重复" ),
    CONTRACT_CODE_REPEAT_ERROR("D0009","合同编号重复" ),
    CONTRACT_ENTITY_NOT_EXIST_ERROR("D0010","合同不存在"),
    CONTRACT_TEMPLATE_NAME_REPEAT_ERROR("D0011", "合同模板重复"),
    CONTRACT_TEMPLATE_NOT_EXIST_ERROR("D0012","合同模板不存在"),
    WORK_ORDER_NOT_EXIST_ERROR("D0013","工单不存在"),
    ANNOUNCEMENT_TITLE_REPEAT_ERROR("D0014","公告标题重复" ),
    RESOURCE_APPLICATION_FORM_NOT_EXIST("D0015","资源申请表不存在"),
    CERTIFICATION_ENTITY_NOT_EXIT_ERROR("D0016","用户企业认证信息不存在"),
    OPERATION_REPETITIVE("D0017","请勿重复操作"),
    CS_USER_ID_FAIL("D0018","获取超算平台accountId失败" ),
    AI_PLATFORM_ERROR("D9999" ,"AI平台内部错误"),
    HPC_PLATFORM_ERROR("D9998","HPC平台内部错误");

    /**
     * 响应状态码
     */
    private final String code;
    /**
     * 响应信息
     */
    private final String message;

    BackgroundManagementResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getModuleName() {
        return "BACKGROUND-MANAGEMENT";
    }

}
