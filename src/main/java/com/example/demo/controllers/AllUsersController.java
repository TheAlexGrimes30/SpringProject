package com.example.demo.controllers;

import com.example.demo.entity.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Контроллер для управления отображением списка всех пользователей.
 */
@Controller
public class AllUsersController {

    private final UserService userService;

    /**
     * Конструктор класса AllUsersController.
     * Выполняет внедрение зависимости {@link UserService}.
     *
     * @param userService объект {@link UserService}, используемый для работы с пользователями
     */
    @Autowired
    public AllUsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обрабатывает GET-запрос для получения списка всех пользователей.
     * Данный метод извлекает всех пользователей с помощью {@link UserService#findAll()},
     * добавляет их в модель и возвращает имя HTML-шаблона для отображения.
     *
     * @param model объект {@link Model}, используемый для передачи данных в представление
     * @return имя HTML-страницы "Users", где будет отображаться список пользователей
     */
    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        List<Users> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "Users";
    }


}
