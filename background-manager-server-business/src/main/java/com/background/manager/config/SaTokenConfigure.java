package com.background.manager.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.service.BackgroundMenuService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * @Description: sa-token配置类
 * @Author: 杜黎明
 * @Date: 2022/09/29 14:47:05
 * @Version: 1.0.0
 */
@Configuration( proxyBeanMethods = false)
@AllArgsConstructor
@EnableConfigurationProperties({SecurityProperties.class})
public class SaTokenConfigure implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenConfigure.class);

    private  SecurityProperties securityProperties;

    private final BackgroundMenuService backgroundMenuService;

    /**
     * Description: 代码配置Sa-token配置(会覆盖application.yml中的配置)
     * @return {@link SaTokenConfig }
     * @author 杜黎明
     * @date 2022/11/24 17:11:13
     */
//    @Bean
//    @Primary
//    public SaTokenConfig getSaTokenConfigPrimary() {
//        SaTokenConfig config = new SaTokenConfig();
//        config.setTokenName("Authorization");
//        config.setTimeout(86400L);
//        config.setActivityTimeout(7200L);
//        config.setIsConcurrent(false);
//        config.setIsShare(false);
//        config.setTokenStyle("uuid");
//        config.setIsLog(false);
//        config.setIsReadBody(false);
//        config.setIsReadCookie(false);
//        return config;
//    }

    /**
     * Description: 注册认证权限拦截器
     * @param registry
     * @author 杜黎明
     * @date 2022/09/29 14:52:24
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录认证拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(securityProperties.getExcludes());
        //自定义权限认证拦截器
        registry.addInterceptor(new SaRouteInterceptor((req,res,handler) ->{
            List<PermissionDTO> permissionDTOList=backgroundMenuService.getPermissionList();
            for (PermissionDTO permission:permissionDTOList){
                SaRouter.match(permission.getPath(),r-> StpUtil.checkPermission(permission.getPerms()));
            }
        }));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
