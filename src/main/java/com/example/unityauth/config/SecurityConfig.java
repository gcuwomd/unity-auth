package com.example.unityauth.config;

import com.example.unityauth.Fliter.CusUsernamePasswordAuthentication;
import com.example.unityauth.Service.UserDetailsServiceImpl;
import com.example.unityauth.hander.CusAuthenticationFailureHandler;
import com.example.unityauth.hander.CusAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl usersDetailsServices;
    @Autowired
    CusAuthenticationSuccessHandler cusAuthenticationSuccessHandler;
    @Autowired
    CusAuthenticationFailureHandler cusAuthenticationFailureHandler;

    @Bean
    public Object passwordEncoder(){
        return  PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public CusUsernamePasswordAuthentication cusUsernamePasswordAuthentication() throws Exception {
        CusUsernamePasswordAuthentication filter = new CusUsernamePasswordAuthentication();
        // 创建 DaoAuthenticationProvider 作为认证提供者
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(usersDetailsServices); // 设置自定义的 UserDetailsService
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(authenticationProvider));
        filter.setAuthenticationSuccessHandler(cusAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(cusAuthenticationFailureHandler);
        filter.setAuthenticationManager(providerManager);
        filter.setFilterProcessesUrl("/login"); // 设置登录接口 URL
        return  filter;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http .authorizeHttpRequests((authz) -> authz.requestMatchers("test").permitAll()
                        .anyRequest().authenticated()).csrf(csrf->csrf.disable())
//                .formLogin(Customizer.withDefaults())
                .addFilterAt(cusUsernamePasswordAuthentication(), UsernamePasswordAuthenticationFilter.class);
        ;

        // 允许登录接口通过认证

        // 其他接口需要身份验证

//         关闭CSRF保护,基于token
        return http.build();

    }
    }



