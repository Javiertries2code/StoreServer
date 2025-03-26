package com.elorrieta.storeapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

    @Bean
    public Token testToken() {
        return new Token(1L, null, TokenType.BEARER, false, false, null);
    }
}