package com.background.manager.constants;

public enum PasswordRuleEnum {

    UPPER_CASE(1,"大写"),
    LOWER_CASE(2,"小写"),
    SPECIAL_CHARACTER(3,"特殊字符"),
    UNDERLINE(4,"下划线"),
    NUMBER(5,"数字");


    private Integer type;

    private String rule;

    private PasswordRuleEnum(Integer type,String rule){
        this.type=type;
        this.rule=rule;
    }

    public Integer getType(){
        return type;
    }
    public String getRule(){
        return rule;
    }


    public static String findRuleByType(Integer type) {
        for (PasswordRuleEnum passwordRuleEnum : PasswordRuleEnum.values()) {
            if (type.equals(passwordRuleEnum.type)) {
                return passwordRuleEnum.rule;
            }
        }
        return null;
    }



}
