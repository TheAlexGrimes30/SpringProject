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
 * Контроллер для фильтрации холодильников по диапазону цен.
 */
@Controller
public class PriceFilterController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для работы с холодильниками
     */
    @Autowired
    public PriceFilterController(FridgeService fridgeService){
        this.fridgeService  = fridgeService;
    }

    /**
     * Обрабатывает GET-запросы для отображения всех холодильников
     * в пределах максимально возможного диапазона цен.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "PriceFilter"
     */
    @GetMapping("/all/pricefilter")
    public String PriceFilter(Model model){
        List<Fridge> fridges = fridgeService.findByPriceBetween(1, Integer.MAX_VALUE);
        model.addAttribute("fridges", fridges);
        return "PriceFilter";
    }

    /**
     * Обрабатывает POST-запросы для фильтрации холодильников по диапазону цен.
     *
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @param model объект {@link Model} для передачи данных и сообщений об ошибках в представление
     * @return имя HTML-страницы "PriceFilter"
     */
    @PostMapping("/all/pricefilter")
    public String PriceFilter(@RequestParam("min") int minPrice, @RequestParam("max") int maxPrice, Model model){

        if (minPrice <= 0 || maxPrice <= 0) {
            if (minPrice <= 0)
                model.addAttribute("minErrorMessage", "Цена не может быть меньше 0!");
            if (maxPrice <= 0)
                model.addAttribute("maxErrorMessage", "Цена не может быть меньше 0!");
            return "PriceFilter";
        }

        if (minPrice > maxPrice) {
            model.addAttribute("minErrorMessage", "Минимальная цена не может быть больше максимальной!");
            return "PriceFilter";
        }

        List<Fridge> fridges = fridgeService.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("fridges", fridges);
        return "PriceFilter";
    }


}
