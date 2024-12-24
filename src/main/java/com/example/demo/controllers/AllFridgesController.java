package com.example.demo.controllers;

import com.example.demo.entity.Fridge;
import com.example.demo.services.FridgeService;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки запросов, связанных с отображением списка всех холодильников.
 */
@Controller
public class AllFridgesController {

    private final FridgeService fridgeService;
    private final NotificationService notificationService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для управления холодильниками
     */
    @Autowired
    public AllFridgesController(FridgeService fridgeService, NotificationService notificationService){
        this.fridgeService = fridgeService;
        this.notificationService = notificationService;
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

    @PostMapping("/all/buy")
    public String buyFridge(@RequestParam("id") Long id, Model model){
        try{
            Optional<Fridge> fridge = fridgeService.findById(id);
            if (fridge.isPresent()){
                model.addAttribute("successMessage", "Холодильник " + id + " куплен успешно!");
                String message = "Холодильник: " + fridge + " был куплен!";
                notificationService.sendNotification(message);
            }else {
                model.addAttribute("errorMessage", "Холодильник не найден!");
            }
        }catch (Exception e) {
            model.addAttribute("errorMessage", "Ошибка при покупке: " + e.getMessage());
        }

        List<Fridge> allFridges = fridgeService.findAll();
        model.addAttribute("fridges", allFridges);
        return "Fridges";
    }
}
