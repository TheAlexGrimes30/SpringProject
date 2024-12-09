package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) для передачи данных пользователя.
 * <p>
 * Этот класс используется для передачи данных при регистрации пользователя.
 */
public class UserDto {

    /**
     * Имя пользователя.
     * <p>
     * Поле не должно быть пустым и должно содержать от 8 до 64 символов.
     */
    @NotBlank(message = "Username must not be empty")
    @Size(min = 8, max = 64, message = "Username must be between 8 and 64 characters")
    private String username;

    /**
     * Электронная почта пользователя.
     * <p>
     * Поле не должно быть пустым и должно быть валидным адресом электронной почты.
     */
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    /**
     * Пароль пользователя.
     * <p>
     * Поле не должно быть пустым и должно содержать не менее 8 символов.
     */
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * Роль пользователя.
     * <p>
     * Поле может принимать значения, определенные в {@link Role}.
     */
    private Role role;

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
     * Получить адрес электронной почты пользователя.
     *
     * @return адрес электронной почты
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установить адрес электронной почты пользователя.
     *
     * @param email адрес электронной почты
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Получить роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Установить роль пользователя.
     *
     * @param role роль пользователя
     */
    public void setRole(Role role) {
        this.role = role;
    }
}