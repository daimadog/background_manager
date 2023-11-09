package com.background.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: 运营后台启动类
 * @Author: 杜黎明
 * @Date: 2022/09/28 10:12:09
 * @Version: 1.0.0
 */
@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class BackGroundManagerApplication {

    /**
     * Description: 跨域处理
     * @return {@link CorsFilter }
     * @author 杜黎明
     * @date 2022/11/15 17:02:04
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    public static void main(String[] args) {
         SpringApplication.run(BackGroundManagerApplication.class,args);
    }


}


