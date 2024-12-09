package com.example.demo.controllers;

import com.example.demo.entity.Fridge;
import com.example.demo.services.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Контроллер для фильтрации холодильников по вместимости.
 */
@Controller
public class CapacityFilterController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для работы с холодильниками
     */
    @Autowired
    public CapacityFilterController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы фильтрации холодильников по вместимости.
     * Изначально отображает все холодильники с вместимостью в диапазоне от 1 до 1000.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "CapacityFilter"
     */
    @GetMapping("/all/capacityfilter")
    public String CapacityFilter(Model model){
        List<Fridge> fridges = fridgeService.findByCapacityBetween(1, 1000);
        model.addAttribute("fridges", fridges);
        return "CapacityFilter";
    }

    /**
     * Обрабатывает POST-запрос для фильтрации холодильников по указанному диапазону вместимости.
     * Проверяет корректность введенных значений и возвращает результат фильтрации.
     *
     * @param minCapacity минимальная вместимость
     * @param maxCapacity максимальная вместимость
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "CapacityFilter"
     */
    @PostMapping("/all/capacityfilter")
    public String PriceFilter(@RequestParam("min") int minCapacity, @RequestParam("max") int maxCapacity, Model model){

        if (minCapacity <= 0 || maxCapacity <= 0) {
            if (minCapacity <= 0)
                model.addAttribute("minErrorMessage", "Вместимость не может быть меньше 0!");
            if (maxCapacity <= 0)
                model.addAttribute("maxErrorMessage", "Вместимость не может быть меньше 0!");
            return "CapacityFilter";
        }

        if (minCapacity > 1000 || maxCapacity > 1000) {
            if (minCapacity > 1000)
                model.addAttribute("minErrorMessage", "Вместимость не может быть больше 1000!");
            if (maxCapacity > 1000)
                model.addAttribute("maxErrorMessage", "Вместимость не может быть больше 1000!");
            return "CapacityFilter";
        }

        if (minCapacity > maxCapacity) {
            model.addAttribute("minErrorMessage", "Минимальная вместимость" +
                    " не может быть больше максимальной!");
            return "PriceFilter";
        }

        List<Fridge> fridges = fridgeService.findByCapacityBetween(minCapacity, maxCapacity);
        model.addAttribute("fridges", fridges);
        return "CapacityFilter";
    }
}
