package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс для настройки безопасности Spring Security.
 * Этот класс отвечает за настройку цепочки фильтров безопасности, обработку авторизации,
 * аутентификации и управления доступом к различным маршрутам.
 * В данный момент код закомментирован, чтобы исключить его из выполнения.
 */

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param userDetailsService сервис для работы с пользовательскими данными
     * @param passwordEncoder    кодировщик паролей
     */
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Создает и настраивает цепочку фильтров безопасности.
     *
     * @param http объект {@link HttpSecurity} для конфигурации
     * @return настроенная цепочка фильтров безопасности
     * @throws Exception если возникнет ошибка конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/home").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/all/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().hasRole("USER")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Создает бин {@link AuthenticationManager}, который управляет процессом аутентификации.
     *
     * @param authConfig объект {@link AuthenticationConfiguration} для настройки аутентификации
     * @return объект {@link AuthenticationManager} для управления аутентификацией
     * @throws Exception если возникнет ошибка при создании
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}