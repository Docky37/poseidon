package com.nnk.springboot;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.classmate.TypeResolver;
import com.nnk.springboot.dto.BidListFullDTO;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entry class of Poseidon Application that contains the main method.
 *
 * @author Thierry Schreiner
 */
@SpringBootApplication
@EnableSwagger2
public class PoseidonApplication {

    @Autowired
    private TypeResolver typeResolver;
    /**
     * Main method, entry point of the Poseidon application.
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }

    /**
     * Empty class constructor.
     */
    protected PoseidonApplication() {
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.regex("(?!/error.*).*"))
                .apis(RequestHandlerSelectors.basePackage("com.nnk.springboot"))
                .build()
                .apiInfo(infoDetails())
                .additionalModels(typeResolver.resolve(BidListFullDTO.class));
    }

    private ApiInfo infoDetails() {
        return new ApiInfo(
        "Poseidon Inc. application",
        "Financial Market Tools",
        "v1.1",
        "Free to use",
        new springfox.documentation.service.Contact("Thierry Schreiner", "http://doc.poseidon.com", "doc@poseidon.com"),
        "API License",
        "http://nkk.com",
        Collections.emptyList());
    }
}
