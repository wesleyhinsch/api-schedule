package com.sicred.api.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket agendaApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("agenda-api-v1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.sicred.api.schedule.controller"))
            .paths(regex("/api/agenda/v1.0.*"))
            .build()
            .apiInfo(
                new ApiInfoBuilder()
                    .version("v1.0")
                    .title("Api Agenda v1.0")
                    .description("Api to create voting minutes")
                    .contact(new Contact("Wesley Hinsch", "https://www.linkedin.com/in/wesleyhinsch/","wesleyhinsch@gmail.com"))
                    .build()
            );
    }

    @Bean
    public Docket voteApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("vote-api-v1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.sicred.api.schedule.controller"))
            .paths(regex("/api/vote/v1.0.*"))
            .build()
            .apiInfo(
                new ApiInfoBuilder()
                    .version("v1.0")
                    .title("Api Vote v1.0")
                    .description("Api to link votes to agendas ")
                    .contact(new Contact("Wesley Hinsch", "https://www.linkedin.com/in/wesleyhinsch/","wesleyhinsch@gmail.com"))
                    .build()
            );
    }
}
