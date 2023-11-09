package com.background.manager.constant;

public enum ResourceEnum {

    REAL_TIME_PERFORMANCES(1,"实时性能"),
    AI_RESOURCE(2,"AI资源"),
    AI_COMPUTING_POWER_OCCUPANCY(3,"AI算力占用率"),
    NODE_USAGE(4,"节点使用情况"),
    HPC_OCCUPANCY(5,"HPC占用");


    private Integer type;
    private String resource;

    ResourceEnum(Integer type, String resource) {
        this.type=type;
        this.resource=resource;
    }

    public String findResourceByType(Integer type){
        for (ResourceEnum resourceEnum: ResourceEnum.values()){
            if (type.equals(resourceEnum.type)){
                return resourceEnum.resource;
            }
        }
        return null;
    }

}
