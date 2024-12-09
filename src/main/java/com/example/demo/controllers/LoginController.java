package com.example.demo.controllers;

import com.example.demo.dto.LoginDto;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для управления процессом входа пользователей.
 * Обрабатывает запросы для отображения страницы входа и выполнения аутентификации.
 */
@Controller
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    /**
     * Конструктор контроллера для входа.
     *
     * @param userService            сервис для управления пользователями
     * @param authenticationManager  менеджер аутентификации
     */
    public LoginController(UserService userService, @Qualifier("authenticationManager") AuthenticationManager authenticationManager){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы входа.
     *
     * @return имя шаблона страницы входа ("Login")
     */
    @GetMapping("/login")
    public String showLogin(){
        return "Login";
    }

    /**
     * Обрабатывает POST-запрос для выполнения входа пользователя.
     * Метод выполняет аутентификацию пользователя с использованием введённых данных.
     * Если аутентификация успешна, пользователь перенаправляется на главную страницу.
     * Если аутентификация неудачна, отображается страница входа с сообщением об ошибке.
     *
     * @param loginDto объект {@link LoginDto}, содержащий имя пользователя и пароль
     * @param model    объект {@link Model}, используемый для передачи данных в представление
     * @return имя шаблона для перенаправления или отображения
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto loginDto, Model model){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/Home";
        } catch(Exception e) {
            model.addAttribute("errorMessage", "Неверное имя пользователя или пароль");
            return "Login";
        }
    }
}