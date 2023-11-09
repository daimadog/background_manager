package com.background.manager.constant;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class StatusConstant {

    /**发票服务**/
    //未开票
    public static final Integer RECEIPT_NOT_INVOICED=0;
    //开票完成
    public static final Integer RECEIPT_INVOICED=1;

    //同意开票
    public static final Integer RECEIPT_APPROVE=1;
    //驳回开票
    public static final Integer RECEIPT_REJECT=2;


    /**文章类型**/
    //原创
    public static final Integer ORIGINAL=1;

    public static final String ORIGINAL_NAME="原创";

    //转载
    public static final Integer REPRINT=0;

    public static final String REPRINT_NAME="转载";


    /**文章状态**/
    //正常
    public static final Integer NORMAL=0;

    //冻结
    public static final Integer FREEZE=1;


    /**父节点栏目**/
    public static  final  Integer parent_Id=0;
    /**未处理**/
    public static  final Integer PENDING=0;
    /**已处理**/
    public static  final Integer PENDED=1;

    /**合同模板状态**/
    public static final Integer CONTRACT_TEMPLATE_NOT_DOWNLOAD = 0;

    public static final Integer CONTRACT_TEMPLATE__DOWNLOAD = 1;

    /**关闭工单操作**/
    public static final Integer CLOSE_WORK_ORDER = 2;

    /**消息方向-1从运营到用户**/
    public static final Integer DIRECTION_OPERATION_TO_USER = 1;
}
