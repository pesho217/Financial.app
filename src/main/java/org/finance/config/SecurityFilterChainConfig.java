package org.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityFilterChainConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests( auth -> {
                    auth
                            .requestMatchers(HttpMethod.POST, "/expense").permitAll()
                            .requestMatchers(HttpMethod.POST, "/customer").permitAll()
                            .requestMatchers(HttpMethod.GET,"/customer", "/expense").permitAll()
                            .anyRequest().permitAll();
//                            hasRole("ADMIN");
                })
//                .csrf(AbstractHttpConfigurer::disable)
                    .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            return http.build();
    }

}
