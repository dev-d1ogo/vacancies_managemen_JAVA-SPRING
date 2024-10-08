package br.diogo.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean // Override do metodo gerenciado pelo proprio Spring
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/company/**").permitAll()
                            .requestMatchers("/candidate/**").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated();
                });

        return http.build();
    }

    @Bean
    PasswordEncoder hashPassword(){
        return new BCryptPasswordEncoder();
    }
}
