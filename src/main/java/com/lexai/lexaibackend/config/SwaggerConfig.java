package com.lexai.lexaibackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LexAI - Legal Assistant API",
                version = "1.0",
                description = "AI-powered legal assistance platform for Indian law. " +
                        "Analyzes legal problems using RAG pipeline with Indian law knowledge base. " +
                        "Use /api/auth/login to get a JWT token, then click Authorize.",
                contact = @Contact(
                        name = "Kavya Agarwal",
                        url = "https://github.com/nova1128"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Enter your JWT token obtained from /api/auth/login"
)
public class SwaggerConfig {
}