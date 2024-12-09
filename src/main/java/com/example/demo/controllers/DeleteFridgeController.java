package com.example.demo.controllers;

import com.example.demo.services.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для удаления холодильников из базы данных.
 */
@Controller
public class DeleteFridgeController {

    private final FridgeService fridgeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param fridgeService объект сервиса {@link FridgeService} для работы с холодильниками
     */
    @Autowired
    public DeleteFridgeController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы удаления холодильников.
     *
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "DeleteFridge"
     */
    @GetMapping("/admin/deletefridge")
    public String DeleteFridge(Model model){
        return "DeleteFridge";
    }

    /**
     * Обрабатывает POST-запрос для удаления холодильника по идентификатору.
     * В случае успешного удаления происходит перенаправление на список холодильников.
     * Если возникает ошибка, отображается сообщение об ошибке.
     *
     * @param id идентификатор холодильника, который необходимо удалить
     * @param model объект {@link Model} для передачи данных в представление
     * @return имя HTML-страницы "DeleteFridge" в случае ошибки или перенаправление на "/fridges" при успешном удалении
     */
    @PostMapping("/admin/deletefridge")
    public String DeleteFridge(@RequestParam Long id, Model model){
        try{
            fridgeService.deleteById(id);
            return "redirect:/all/fridges";
        }catch(Exception e){
            model.addAttribute("errorMessage", "Ошибка" + e.getMessage());
            return "DeleteFridge";
        }
    }


}
