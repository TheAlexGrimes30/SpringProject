package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Главный класс приложения Spring Boot для запуска сервера и конфигурации компонентов приложения.
 * <p>
 * Аннотирован с помощью {@link SpringBootApplication} для включения автоконфигурации Spring Boot и запуска
 * приложения.
 * Аннотирован с помощью {@link EntityScan} для указания пакета, где находятся сущности (Entity).
 * Аннотирован с помощью {@link EnableJpaRepositories} для указания пакета с репозиториями JPA.
 * </p>
 *
 * @author Автор: Гопиенко Александр КИ22-17/2Б 6 Вариант
 */
@EntityScan("com.example.demo.entity")
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
public class DemoApplication {

	/**
	 * Главный метод для запуска приложения.
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
