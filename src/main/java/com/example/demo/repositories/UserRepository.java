package com.example.demo.repositories;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link Users}.
 * Этот интерфейс предоставляет методы для выполнения операций CRUD с сущностью {@link Users},
 * а также дополнительные пользовательские запросы.
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username имя пользователя для поиска
     * @return объект {@link Optional}, содержащий {@link Users}, если пользователь с таким именем существует,
     * или пустой объект {@link Optional}, если пользователь не найден
     */
    Optional<Users> findByUsername(String username);
}