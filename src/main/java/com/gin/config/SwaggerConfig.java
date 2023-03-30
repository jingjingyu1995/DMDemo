package com.gin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Configuration
public class SwaggerConfig {

    @Bean
    @Profile({"dev", "test"})
    public Docket aTest() {
        AlternateTypeRule newRule = AlternateTypeRules.newRule(Date.class, Long.class);
        ApiInfo apiInfo = new ApiInfo("DMDemo", "DMDemo接口文档",
                "1.0", "", null, "", "",
                new ArrayList<>(0));
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes( )
                .apiInfo(apiInfo)
                .enable(true)
                .alternateTypeRules(newRule);
    }
}
