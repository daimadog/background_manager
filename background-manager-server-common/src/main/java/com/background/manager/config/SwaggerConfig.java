package com.background.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description:swagger配置类
 * @Author: 杜黎明
 * @Date: 2022/09/07 16:55:02
 * @Version: 1.0.0
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private boolean isEnableSwagger;

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("超算中心后台权限管理系统")
                .description("超算中心后台权限管理系统Swagger接口测试后台")
                .contact(new Contact("duliming", "", ""))
                .termsOfServiceUrl("http://localhost:8099/")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isEnableSwagger)
                .groupName("Background-management")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.background.manager.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
