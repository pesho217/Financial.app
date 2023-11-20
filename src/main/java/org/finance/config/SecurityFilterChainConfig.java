package org.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityFilterChainConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests( auth -> {
                    auth
                            .requestMatchers(HttpMethod.GET, "/expense","/customer").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/expense").authenticated()
                            .requestMatchers(HttpMethod.POST, "/customer").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
            return http.build();
    }

}
