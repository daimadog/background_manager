package com.background.manager.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FtpProperties {

    @Value("${FTP.address}")
    private String host;
    // 端口
    @Value("${FTP.port}")
    private int port;
    // ftp用户名
    @Value("${FTP.username}")
    private String userName;
    // ftp用户密码
    @Value("${FTP.password}")
    private String passWord;
    // 文件在服务器端保存的主目录
    @Value("${FTP.basePath}")
    private String basePath;
    // 访问图片时的基础url
    @Value("${IMAGE.BASE.URL}")
    private String baseUrl;

}
