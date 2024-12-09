package com.example.demo.entity;

import jakarta.persistence.*;

/**
 * Класс-сущность, представляющий пользователя в системе.
 * Этот класс используется для хранения информации о пользователях в базе данных.
 */
@Entity
@Table(name = "users")
public class Users {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя пользователя. Должно быть уникальным и не может быть пустым.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Электронная почта пользователя. Должна быть уникальной, валидной и не может быть пустой.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Пароль пользователя. Не может быть пустым.
     */
    @Column(nullable = false, unique = true)
    private String password;

    /**
     * Роль пользователя в системе.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Конструктор без параметров для использования фреймворком JPA.
     */
    public Users() {
    }

    /**
     * Конструктор для создания нового пользователя.
     *
     * @param username имя пользователя
     * @param email    электронная почта пользователя
     * @param password пароль пользователя
     * @param role     роль пользователя
     */
    public Users(String username, String email, String password, Role role) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    /**
     * Возвращает уникальный идентификатор пользователя.
     *
     * @return идентификатор пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор пользователя.
     *
     * @param id идентификатор пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param username имя пользователя
     * @throws IllegalArgumentException если имя пустое
     */
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username;
    }

    /**
     * Возвращает электронную почту пользователя.
     *
     * @return электронная почта пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает электронную почту пользователя.
     *
     * @param email электронная почта пользователя
     * @throws IllegalArgumentException если почта пустая или недействительная
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }
        this.email = email;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя
     * @throws IllegalArgumentException если пароль пустой
     */
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.password = password;
    }

    /**
     * Возвращает роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя
     * @throws IllegalArgumentException если роль равна null
     */
    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }

    /**
     * Возвращает строковое представление объекта пользователя.
     *
     * @return строковое представление пользователя
     */
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}