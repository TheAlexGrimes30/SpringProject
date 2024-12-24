package com.example.demo.controllers;

import com.example.demo.entity.Notification;
import com.example.demo.repositories.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NotificationController {

    private final NotificationRepository repository;

    public NotificationController(NotificationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/admin/notifications")
    public String getNotifications(Model model) {
        List<Notification> notifications = repository.findAll();
        model.addAttribute("notifications", notifications);
        return "Notifications";
    }

    @PostMapping("/admin/clearNotifications")
    public String clearNotifications() {
        repository.deleteAll();
        return "redirect:/admin/notifications"; 
    }
}
