package com.damon.ai.expense.trackerr.aiexpensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.damon.ai.expense.trackerr.aiexpensetracker.exception.CustomAccessDeniedHandler;
import com.damon.ai.expense.trackerr.aiexpensetracker.exception.CustomAuthenticationEntryPoint;
import com.damon.ai.expense.trackerr.aiexpensetracker.filter.AuditFilter;
import com.damon.ai.expense.trackerr.aiexpensetracker.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final AuditFilter auditFilter;
        private final CustomAuthenticationEntryPoint authenticationEntryPoint;
        private final CustomAccessDeniedHandler accessDeniedHandler;

        public SecurityConfig(
                        JwtAuthFilter jwtAuthFilter,
                        AuditFilter auditFilter,
                        CustomAuthenticationEntryPoint authenticationEntryPoint,
                        CustomAccessDeniedHandler accessDeniedHandler) {

                this.jwtAuthFilter = jwtAuthFilter;
                this.auditFilter = auditFilter;
                this.authenticationEntryPoint = authenticationEntryPoint;
                this.accessDeniedHandler = accessDeniedHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                        throws Exception {

                http.csrf(csrf -> csrf.disable())

                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/api/auth/**",
                                                                "/api/financeusers/register",
                                                                "/h2-console/**",
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())

                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(authenticationEntryPoint)
                                                .accessDeniedHandler(accessDeniedHandler))

                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .headers(headers -> headers
                                                .frameOptions(frame -> frame.disable()));

                http.addFilterBefore(
                                jwtAuthFilter,
                                UsernamePasswordAuthenticationFilter.class);

                http.addFilterAfter(
                                auditFilter,
                                JwtAuthFilter.class);

                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration configuration)
                        throws Exception {

                return configuration.getAuthenticationManager();
        }
}