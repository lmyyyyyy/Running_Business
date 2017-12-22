package com.running.business.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liumingyu
 * @create 2017-11-27 上午11:22
 */
/*
 * Restful API 访问路径:
 * http://IP:port/{context-path}/swagger-ui.html
 * eg:http://localhost:8080/jd-config-web/swagger-ui.html
 */
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.running.business.controller"})
@Configuration
public class RestApiConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.running.business.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("跑商swagger测试")
                .termsOfServiceUrl("http://lblogg.cn")
                .contact("刘明宇")
                .version("1.1")
                .build();
    }
}