package com.gin.config.mvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

/**
 * @author hefeiyu 2021-10-27 17:15
 */
@Configuration
public class WebMvcConfiguration extends BaseWebMvcConfiguration {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/templates/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/");
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v3/api-docs").allowedOrigins("*");
    }
}
