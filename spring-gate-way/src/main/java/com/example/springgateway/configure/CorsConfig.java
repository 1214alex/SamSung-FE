package com.example.springgateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 프론트엔드 주소
        config.setAllowedOriginPatterns(List.of("http://localhost:5173"));

        config.setAllowCredentials(true); // 쿠키, Authorization 등 허용
        config.addAllowedHeader("*");     // 모든 헤더 허용
        config.addAllowedMethod("*");     // 모든 HTTP 메서드 허용
        config.addExposedHeader("Authorization"); // 토큰 읽을 수 있게

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
