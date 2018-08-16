package com.dior.qainfrasearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class VMStateApp {

    public static void main(String[] args) {
        SpringApplication.run(VMStateApp.class, args);
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo("CLOUD ENV API", "REST Api to be used to've read only information about available QA envs", "v1", "https://opensource.org/licenses/MIT", null, "LVMH", "https://opensource.org/licenses/MIT", new ArrayList<>());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dior.qainfrasearchapi.controller"))
                .paths(PathSelectors.ant("/env/*"))
                .build();
    }
}
