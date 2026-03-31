package Jwt_Security.JWT_Project.config;

import Jwt_Security.JWT_Project.security.JwtFilter;
import Jwt_Security.JWT_Project.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/encode").permitAll()
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .requestMatchers("/api/user").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class); // 🔥 IMPORTANT

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService); // ✅ pass in constructor
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}