package com.example.demo.controllers;

import com.example.demo.entity.Fridge;
import com.example.demo.services.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с отображением списка всех холодильников.
 */
@Controller
public class AllFridgesController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для управления холодильниками
     */
    @Autowired
    public AllFridgesController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    /**
     * Обрабатывает GET-запрос для отображения всех холодильников из базы данных.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "Fridges"
     */
    @GetMapping("/all/fridges")
    public String GetAllFromDb(Model model) {
        List<Fridge> allFridges = fridgeService.findAll();
        model.addAttribute("fridges", allFridges);
        return "Fridges";
    }
}
