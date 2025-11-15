package org.groupware.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("Cove One GroupWare API")
            .description("코브원 그룹웨어 서비스")
            .version("1..0.0");
    }

    private SecurityScheme createAPIKeyScheme(){
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
            .group("auth")
            .pathsToMatch("/auth/**")
            .build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder()
            .group("member")
            .pathsToMatch("/member/**")
            .build();
    }

    @Bean
    public GroupedOpenApi departmentApi() {
        return GroupedOpenApi.builder()
            .group("department")
            .pathsToMatch("/department/**")
            .build();
    }

    @Bean
    public GroupedOpenApi approvalApi() {
        return GroupedOpenApi.builder()
            .group("approval")
            .pathsToMatch("/approval/**")
            .build();
    }

    @Bean
    public GroupedOpenApi commonCodeApi() {
        return GroupedOpenApi.builder()
            .group("common-code")
            .pathsToMatch("/common-codes/**")
            .build();
    }

}