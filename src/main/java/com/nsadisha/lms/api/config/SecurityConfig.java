package com.nsadisha.lms.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
//    private final UserAuthenticationFilter userAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.csrf().disable();

        http.authorizeHttpRequests().requestMatchers("/", "/js/**", "/css/**", "/images/**").permitAll();
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http.logout().logoutUrl("/logout").permitAll();

        http.authorizeHttpRequests().requestMatchers("/api/user/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/student/**").permitAll();

        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.addFilter(userAuthenticationFilter);


//        http.authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
