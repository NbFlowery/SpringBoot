package com.nongboo.flowery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nongboo.flowery.util.AuthenticationInterceptor;
import com.nongboo.flowery.util.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JWTService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(jwtService, objectMapper))
                .order(1)
                .addPathPatterns("/**");
    }
}
