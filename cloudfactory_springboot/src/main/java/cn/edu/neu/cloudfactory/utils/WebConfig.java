package cn.edu.neu.cloudfactory.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//全局配置类
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
/*
1、与访问路径
2、请求资源
3、方法
4、允许携带
5、最大时间
* */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "null")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
