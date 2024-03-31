package com.leferi.notification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//import com.leferi.lcmd.base.security.JwtTokenProvider;

@RequiredArgsConstructor
@Configuration
public class AuthConfig {
//    private String jwtKey = "JWT_KEY";

//    @Bean
//    public JwtTokenProvider jwtTokenProvider() {
//        JwtTokenProvider provider = new JwtTokenProvider();
//        return provider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
