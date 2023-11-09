package com.background.manager.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ZsCsProperties {

    @Value("${sm2.server.zs.privateKey}")
    public static String zsServerPrivate;
    @Value("${sm2.zs.publicKey}")
    public static String zsPublic;
    @Value("${zs.console.url}")
    public static String zsConsoleUrl;
    @Value("${zs.manage.url}")
    public static String zsManageUrl;
    @Value("${cs.console.url}")
    public static String csConsoleUrl;
    @Value("${zs.manage.userId}")
    public static String managerUserId;


}
