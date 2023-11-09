package com.background.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 注入BCryptPasswordEncoderUtil
 * @Author: 杜黎明
 * @Date: 2023/04/12 17:32:43
 * @Version: 1.0.0
 */
@Configuration
public class BCryptPasswordEncoderConfig {

    @Bean
    public BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil(){
        return new BCryptPasswordEncoderUtil();
    }

}
