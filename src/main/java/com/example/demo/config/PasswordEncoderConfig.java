package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурационный класс для настройки кодировщика паролей.
 * Этот класс предоставляет компонент {@link PasswordEncoder}, который
 * используется для шифрования паролей с использованием алгоритма BCrypt.
 * В данный момент этот класс закомментирован и не будет участвовать в
 * процессе конфигурации приложения.
 */

@Configuration
public class PasswordEncoderConfig {

    /**
     * Создает бин {@link PasswordEncoder}, который шифрует пароли с использованием BCrypt.
     *
     * @return объект {@link PasswordEncoder} для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
