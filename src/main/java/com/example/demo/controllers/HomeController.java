package com.example.demo.controllers;

import com.example.demo.services.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.GrantedAuthority;

/**
 * Контроллер для главной страницы приложения.
 * Этот контроллер отвечает за обработку запросов на отображение главной страницы,
 * а также за передачу информации о пользователе (роль и имя) в модель.
 */
@Controller
public class HomeController {

    /**
     * Конструктор контроллера HomeController.
     * В данном случае не принимает параметров, так как не использует явных зависимостей.
     */
    @Autowired
    public HomeController() {
    }

    /**
     * Обрабатывает запросы на главную страницу приложения.

     * В зависимости от статуса аутентификации пользователя, метод определяет его имя и роль
     * (администратор или пользователь), а затем добавляет эту информацию в модель.
     *
     * @param model          объект {@link Model}, который используется для передачи данных в представление
     * @param authentication объект {@link Authentication}, содержащий информацию о текущем пользователе
     * @return имя HTML-шаблона "Home", которое будет использоваться для отображения главной страницы
     */
    @GetMapping({"/", "/home"})
    public String Home(Model model, Authentication authentication) {
        if (authentication != null) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            boolean isAdmin = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN"));
            boolean isUser = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_USER"));

            model.addAttribute("username", username);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("isUser", isUser);
        } else {
            model.addAttribute("isAdmin", false);
            model.addAttribute("isUser", false);
        }

        return "Home";
    }
}