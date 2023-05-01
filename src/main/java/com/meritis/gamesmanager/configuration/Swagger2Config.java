package com.meritis.gamesmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.meritis.gamesmanager.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointInfo());
    }

    public ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("Games Manager API")
                .description("Manage your games and tournaments")
                .contact(new Contact("Lucas Michalet", "-", "-"))
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}