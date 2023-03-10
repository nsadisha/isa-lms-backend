package com.nsadisha.lms.api.config;

import com.nsadisha.lms.api.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.authorizeHttpRequests().requestMatchers("/auth/**").permitAll();

        http.authorizeHttpRequests().requestMatchers("/user/**").authenticated();
        http.authorizeHttpRequests().requestMatchers("/student/**").hasAuthority("STUDENT");
        http.authorizeHttpRequests().requestMatchers("/teacher/**").hasAuthority("TEACHER");
        http.authorizeHttpRequests().requestMatchers("/management/**").hasAuthority("MANAGEMENT_STAFF");

        http.authorizeHttpRequests().requestMatchers("/course/create/**").hasAuthority("TEACHER");
        http.authorizeHttpRequests().requestMatchers("/course/enroll/**", "/course/unenroll/**").hasAuthority("STUDENT");
        http.authorizeHttpRequests().anyRequest().permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
