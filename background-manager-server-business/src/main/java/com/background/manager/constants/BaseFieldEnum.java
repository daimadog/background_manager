package com.background.manager.constants;

import lombok.Getter;

/**
 * @Description: 基础实体字段枚举类
 * @Author: 杜黎明
 * @Date: 2022/09/29 11:21:24
 * @Version: 1.0.0
 */
@Getter
public enum BaseFieldEnum {

    CREATE_NAME("creator","creator","创建者"),
    CREATE_IP("creatorIp","creator_ip","创建者ip"),
    CREATE_TIME("createTime","create_time","创建者时间"),
    MODIFIER_NAME("modifier","modifier","更新者"),
    MODIFIER_IP("modifierIp","modifier_ip","更新者ip"),
    MODIFY_TIME("modifyTime","modify_time","更新时间");

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 数据库名称
     */
    private String columnName;
    /**
     * 描述信息
     */
    private  String description;

    BaseFieldEnum(String fieldName, String columnName, String description){
        this.fieldName=fieldName;
        this.columnName=columnName;
        this.description=description;
    }

    private Boolean existFieldName(String fieldName){
        for (BaseFieldEnum value: BaseFieldEnum.values()){
            if (fieldName.equals(value.getFieldName())){
                return true;
            }
        }
        return false;
    }

    private Boolean existColumnName(String columnName){
        for (BaseFieldEnum value: BaseFieldEnum.values()){
            if (columnName.equals(value.getColumnName())){
                return true;
            }
        }
        return false;
    }







}
