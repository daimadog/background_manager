package com.background.manager.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConsoleConfigProperties {

    @Value("${sm2.server.zs.privateKey}")
    private String zsServerPrivate;
    @Value("${sm2.zs.publicKey}")
    private String zsPublic;
    @Value("${zs.console.url}")
    private String zsConsoleUrl;
    @Value("${zs.manage.url}")
    private String zsManageUrl;
    @Value("${cs.console.url}")
    private String csConsoleUrl;
    @Value("${zs.manage.userId}")
    private String managerUserId;

    public String getZsServerPrivate() {
        return zsServerPrivate;
    }

    public void setZsServerPrivate(String zsServerPrivate) {
        this.zsServerPrivate = zsServerPrivate;
    }

    public String getZsPublic() {
        return zsPublic;
    }

    public void setZsPublic(String zsPublic) {
        this.zsPublic = zsPublic;
    }

    public String getZsConsoleUrl() {
        return zsConsoleUrl;
    }

    public void setZsConsoleUrl(String zsConsoleUrl) {
        this.zsConsoleUrl = zsConsoleUrl;
    }

    public String getZsManageUrl() {
        return zsManageUrl;
    }

    public void setZsManageUrl(String zsManageUrl) {
        this.zsManageUrl = zsManageUrl;
    }

    public String getCsConsoleUrl() {
        return csConsoleUrl;
    }

    public void setCsConsoleUrl(String csConsoleUrl) {
        this.csConsoleUrl = csConsoleUrl;
    }

    public String getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(String managerUserId) {
        this.managerUserId = managerUserId;
    }
}
