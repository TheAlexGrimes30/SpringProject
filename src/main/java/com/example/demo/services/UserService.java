package com.example.demo.services;

import com.example.demo.entity.Fridge;
import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Сервис для управления пользователями и их аутентификацией.
 * Этот класс предоставляет методы для регистрации пользователей, поиска пользователей
 * и загрузки данных для аутентификации.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для создания экземпляра сервиса пользователей.
     *
     * @param userRepository репозиторий для работы с пользователями
     * @param passwordEncoder объект для шифрования паролей
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param username имя пользователя
     * @param email    email пользователя
     * @param password пароль пользователя
     * @param role     роль пользователя (если не указана, будет назначена роль {@link Role#USER})
     */
    public void registerUser(String username, String email, String password, Role role) {
        if (role == null) {
            role = Role.USER;
        }
        Users user = new Users(username, email, passwordEncoder.encode(password), role);
        userRepository.save(user);
    }

    /**
     * Ищет пользователя по имени пользователя.
     *
     * @param username имя пользователя
     * @return объект {@link Optional}, содержащий пользователя, если он найден,
     * или пустой объект {@link Optional}, если пользователь не найден
     */
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Возвращает список всех пользователей в системе.
     *
     * @return список объектов {@link Users}
     */
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    /**
     * Загружает пользователя по имени пользователя для аутентификации.
     *
     * @param username имя пользователя
     * @return объект {@link UserDetails}, содержащий данные пользователя
     * @throws UsernameNotFoundException если пользователь с заданным именем не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}