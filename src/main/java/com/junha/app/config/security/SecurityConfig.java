package com.junha.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private AddLogoutSuccessHandler addLogoutSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors(cors -> cors.configurationSource(this.getCorsConfiguration()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/notice/add").hasRole("ADMIN")
                            .requestMatchers("/api/notice").authenticated()
                            .anyRequest().permitAll();
                })
                .formLogin(formLogin -> formLogin.disable())
                .logout(logout -> {
                    logout
                            .logoutUrl("/api/member/logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("accessToken", "refreshToken")
                            .logoutSuccessHandler(addLogoutSuccessHandler);
                })
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .httpBasic(http -> http.disable())
                .addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
                .addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
                ;

        return httpSecurity.build();
    }

    CorsConfigurationSource getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        
        // corsConfiguration.setAllowCredentials(true); 를 사용하면 setAllowedOrigins 사용 불가능
        // 이 때는 setAllowedOriginPatterns 사용 해야됌(*은 사용 안댐 이거 쓰면)
        // corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:*"));
        // corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // *은 안댐
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
