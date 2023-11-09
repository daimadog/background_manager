package com.background.manager.handler;

import cn.dev33.satoken.spring.SpringMVCUtil;
import com.background.manager.constants.BaseFieldEnum;
import com.background.manager.util.IpUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @Description: mp自动填充拦截器
 * @Author: 杜黎明
 * @Date: 2022/09/29 10:42:45
 * @Version: 1.0.0
 */
@Component
public class AutowiredMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BaseFieldEnum.CREATE_TIME.getFieldName(), LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject, BaseFieldEnum.MODIFY_TIME.getFieldName(), LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,BaseFieldEnum.CREATE_IP.getFieldName(), String.class,IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
        this.strictInsertFill(metaObject,BaseFieldEnum.MODIFIER_IP.getFieldName(), String.class,IpUtils.getIpAddr(SpringMVCUtil.getRequest()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /*解决mp自动填充策略：如果属性有值则不覆盖*/
        this.setFieldValByName(BaseFieldEnum.MODIFY_TIME.getFieldName(),LocalDateTime.now(),metaObject);
        this.setFieldValByName(BaseFieldEnum.MODIFIER_IP.getFieldName(),IpUtils.getIpAddr(SpringMVCUtil.getRequest()),metaObject);
    }
}
