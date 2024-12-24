package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.services.NotificationService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Контроллер для управления процессом регистрации пользователей.
 * Обрабатывает запросы для отображения формы регистрации и регистрации новых пользователей.
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    private final NotificationService notificationService;

    /**
     * Конструктор контроллера для регистрации.
     *
     * @param userService сервис для управления пользователями
     */
    public RegistrationController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    /**
     * Обрабатывает GET-запрос для отображения формы регистрации.
     * Добавляет в модель пустую форму пользователя и список доступных ролей.
     *
     * @param model объект {@link Model}, используемый для передачи данных в представление
     * @return имя шаблона страницы регистрации ("Register")
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("roles", List.of("USER", "ADMIN"));
        return "Register";
    }

    /**
     * Обрабатывает POST-запрос для регистрации нового пользователя.
     * <p>
     * Если регистрация проходит успешно, пользователь перенаправляется на страницу входа.
     * В случае ошибки отображается форма регистрации с сообщением об ошибке.
     *
     * @param userDto объект {@link UserDto}, содержащий данные для регистрации пользователя
     * @param model   объект {@link Model}, используемый для передачи данных в представление
     * @return имя шаблона для перенаправления или отображения
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userForm") UserDto userDto, Model model) {
        try {
            userService.registerUser(
                    userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getPassword(),
                    userDto.getRole()
            );
            model.addAttribute("successMessage", "User registered successfully");
            String notificationMessage = String.format(
                    "Новый пользователь зарегистрирован: %s (%s) с ролью %s",
                    userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getRole()
            );
            notificationService.sendNotification(notificationMessage);

            return "redirect:/Login";
        } catch (Exception e) {
            System.out.println("Ошибка" + e.getMessage());
            model.addAttribute("errorMessage", "Ошибка регистрации: ");
            model.addAttribute("roles", List.of("USER", "ADMIN"));

            return "Register";
        }
    }
}