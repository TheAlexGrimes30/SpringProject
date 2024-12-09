package com.example.demo.controllers;

import com.example.demo.dto.FridgeDto;
import com.example.demo.entity.Fridge;
import com.example.demo.services.FridgeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для обработки добавления нового холодильника.
 */
@Controller
public class AddFridgeController {

    private final FridgeService fridgeService;

    /**
     * Конструктор для внедрения зависимости сервиса холодильников.
     *
     * @param fridgeService сервис для работы с холодильниками
     */
    @Autowired
    public AddFridgeController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    /**
     * Метод для обработки GET-запроса на страницу добавления нового холодильника.
     *
     * @param model объект Model для передачи данных в представление
     * @return имя HTML-страницы "AddFridge"
     */
    @GetMapping("/admin/addfridge")
    public String Home(Model model) {
        model.addAttribute("fridgeDto", new FridgeDto());
        System.out.println("Добавление нового товара");
        return "AddFridge";
    }

    /**
     * Метод для обработки POST-запроса на добавление нового холодильника.
     *
     * @param fridgeDto объект DTO с данными о холодильнике
     * @param result объект BindingResult для проверки наличия ошибок валидации
     * @param model объект Model для передачи данных в представление
     * @return имя HTML-страницы "AddFridge"
     */
    @PostMapping("/admin/addfridge")
    public String SubmitForm(@Valid @ModelAttribute("fridgeDto") FridgeDto fridgeDto,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("fridgeDto", fridgeDto);
            return "AddFridge";
        }
        Fridge fridge = new Fridge(fridgeDto.getBrand(), fridgeDto.getModel(), fridgeDto.getPrice(),
                fridgeDto.getDescription(), fridgeDto.getCapacity());
        fridgeService.save(fridge);
        return "redirect:/home";
    }
}
