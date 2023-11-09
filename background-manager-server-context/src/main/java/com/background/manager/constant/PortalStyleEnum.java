package com.background.manager.constant;


/**
 * @Description: 门户网站动态风格枚举类
 * @Author: 杜黎明
 * @Date: 2023/02/08 14:15:27
 * @Version: 1.0.0
 */
public enum PortalStyleEnum {

    STYLE_ONE(1, "风格样式1"),
    STYLE_TWO(2, "风格样式2"),
    STYLE_THREE(3, "风格样式3"),
    STYLE_FOUR(4, "风格样式4");
    private Integer number;

    private String description;

    PortalStyleEnum(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public  int getNumber(){
        return number;
    }

    public String getDescription(){
        return description;
    }

    public String findStyleByNumber(Integer number) {
        for (PortalStyleEnum portalStyleEnum : PortalStyleEnum.values()) {
            if (number.equals(portalStyleEnum.number)) {
                return portalStyleEnum.description;
            }
        }
        return null;
    }

}
