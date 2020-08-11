package com.example.gateway

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()
                  .pathMatchers(HttpMethod.POST, "/oauth/token").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf().disable()
                .build()
    }
}