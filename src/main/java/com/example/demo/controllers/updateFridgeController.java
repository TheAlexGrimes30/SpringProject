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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для обновления информации о холодильнике.
 */
@Controller
public class updateFridgeController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для работы с холодильниками
     */
    @Autowired
    public updateFridgeController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    /**
     * Обрабатывает GET-запросы для отображения формы редактирования информации о холодильнике.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "UpdateFridge"
     */
    @GetMapping("/admin/updatefridge")
    public String EditFridge(Model model) {
        model.addAttribute("fridgeDto", new FridgeDto());
        return "UpdateFridge";
    }

    /**
     * Обрабатывает POST-запросы для обновления информации о холодильнике.
     * Если форма содержит ошибки, она будет возвращена для исправления. Если холодильник с указанным ID не найден, будет выведено сообщение об ошибке.
     *
     * @param id          идентификатор холодильника, который нужно обновить
     * @param fridgeDto   объект {@link FridgeDto}, содержащий обновленные данные холодильника
     * @param result      результат валидации данных формы
     * @param model       объект {@link Model} для передачи данных и сообщений в представление
     * @return имя HTML-страницы для редиректа или возврата с ошибкой
     */
    @PostMapping("/admin/updatefridge")
    public String EditFridge(@RequestParam Long id, @Valid @ModelAttribute("fridgeDto") FridgeDto fridgeDto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fridgeDto", fridgeDto);
            return "UpdateFridge";
        }

        if (fridgeService.findById(id).isEmpty()) {
            model.addAttribute("errorMessage", "Ошибка: Отсутствует такой ID в БД.");
            model.addAttribute("fridgeDto", fridgeDto);
            return "UpdateFridge";
        }


        Fridge fridge = new Fridge(fridgeDto.getBrand(), fridgeDto.getModel(), fridgeDto.getPrice(),
                fridgeDto.getDescription(), fridgeDto.getCapacity());
        fridgeService.updateFridge(id, fridge);
        return "redirect:/all/fridges";
    }

}
