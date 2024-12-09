package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) для передачи данных входа пользователя.
 * Этот класс используется для хранения данных пользователя, вводимых на странице входа.
 */
public class LoginDto {

    /**
     * Имя пользователя.
     * Поле не должно быть пустым и должно содержать от 8 до 64 символов.
     */
    @NotBlank(message = "Username must not be empty")
    @Size(min=8, max=64, message = " Username must be between 8 and 64 characters")
    private String username;

    /**
     * Пароль пользователя.
     * Поле не должно быть пустым и должно содержать не менее 8 символов.
     */
    @NotBlank(message = "Password must not be empty")
    @Size(min=8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * Получить имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Установить имя пользователя.
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получить пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установить пароль пользователя.
     *
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
