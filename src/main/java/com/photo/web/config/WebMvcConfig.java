package com.photo.web.config;

import com.photo.web.interceptor.backLoginIntercetor;
import com.photo.web.interceptor.frontendLoginIntercetor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;

/**
 * @Create 2020-01-17 7:12
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.filepath}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + filePath,
                "classpath:static/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/backup/go/to/login").setViewName("/backup/member-login");
        registry.addViewController("/backup/go/to/index").setViewName("/backup/index");
        registry.addViewController("/go/to/login").setViewName("/frontend/member-login");
        registry.addViewController("/go/to/register").setViewName("/frontend/member-register");
        registry.addViewController("/go/to/file").setViewName("/file");
        registry.addViewController("/go/to/personinfo").setViewName("/frontend/frontend-personinfo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new backLoginIntercetor()).addPathPatterns("/backup/**")
                .excludePathPatterns("/backup/**/**login");
        ArrayList<String> InterceptorPathList = new ArrayList<>();
        InterceptorPathList.add("/go/to/personinfo");
        registry.addInterceptor(new frontendLoginIntercetor()).addPathPatterns(InterceptorPathList);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*"). //允许跨域的域名，可以用*表示允许任何域名使用
                allowedMethods("*"). //允许任何方法（post、get等）
                allowedHeaders("*"); //允许任何请求头
    }
}