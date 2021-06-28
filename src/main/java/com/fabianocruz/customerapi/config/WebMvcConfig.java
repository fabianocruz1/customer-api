package com.fabianocruz.customerapi.config;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.fabianocruz.customerapi"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(new ApiKey("Basic", "Authorization", "header")));
	 }

	 private SecurityContext securityContext() {
		 return SecurityContext.builder()
				 .securityReferences(defaultAuth())
				 .forPaths(PathSelectors.ant("/**"))     
				 .build();
	 }

	 List<SecurityReference> defaultAuth() {
		 return Arrays.asList(new SecurityReference("Basic", new AuthorizationScope[] {new AuthorizationScope("global", "accessEverything")}));
	 }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
}