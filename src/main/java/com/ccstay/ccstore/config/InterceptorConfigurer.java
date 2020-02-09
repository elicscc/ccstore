package com.ccstay.ccstore.config;

import com.ccstay.ccstore.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
//    /**
//     * Tomcat配置 禁用某些请求
//     *
//     * @return
//     */
//    @Bean
//    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addContextCustomizers(context -> {
//            SecurityConstraint securityConstraint = new SecurityConstraint();
//            securityConstraint.setUserConstraint("CONFIDENTIAL");
//            SecurityCollection collection = new SecurityCollection();
//            collection.addPattern("/*");
//            collection.addMethod("HEAD");
//            collection.addMethod("PUT");
//            collection.addMethod("DELETE");
//            collection.addMethod("OPTIONS");
//            collection.addMethod("TRACE");
//            collection.addMethod("COPY");
//            collection.addMethod("SEARCH");
//            collection.addMethod("PROPFIND");
//            securityConstraint.addCollection(collection);
//            context.addConstraint(securityConstraint);
//        });
//        return factory;
//    }

    /**
     * 黑名单/白名单
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration ir = registry.addInterceptor(loginInterceptor);
        // 黑名单
        ir.addPathPatterns("/**");

        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/robots.txt");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        // 注册和登录控制器
        patterns.add("/users/login");
        patterns.add("/users/reg");
        patterns.add("/web/pay/**");
        // 省市区
        patterns.add("/districts/**");
        // 商品信息
        patterns.add("/products/**");
        // 白名单
        ir.excludePathPatterns(patterns);
    }

}
