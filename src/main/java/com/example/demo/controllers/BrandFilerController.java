package com.example.demo.controllers;

import com.example.demo.entity.Fridge;
import com.example.demo.services.FridgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Контроллер для фильтрации холодильников по бренду.
 */
@Controller
public class BrandFilerController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для работы с холодильниками
     */
    public BrandFilerController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы фильтрации холодильников по бренду.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "BrandFilter"
     */
    @GetMapping("/all/brandfilter")
    public String BrandFilter(Model model) {
        List<Fridge> fridges = fridgeService.findAll();
        model.addAttribute("fridges", fridges);
        return "BrandFilter";
    }

    /**
     * Обрабатывает POST-запрос для фильтрации холодильников по указанному бренду.
     *
     * @param brand название бренда, по которому осуществляется фильтрация
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "BrandFilter"
     */
    @PostMapping("/all/brandfilter")
    public String BrandFilterPost(@RequestParam("brand") String brand, Model model) {
        if (brand == null || brand.trim().isEmpty()) {
            model.addAttribute("errorMessage", "Название бренда не может быть пустым.");
            return "BrandFilter";
        }

        List<Fridge> fridges = fridgeService.findByBrand(brand.trim());
        if (fridges.isEmpty()) {
            model.addAttribute("errorMessage", "Холодильники с указанным брендом не найдены.");
        }

        model.addAttribute("fridges", fridges);
        return "BrandFilter";
    }
}
