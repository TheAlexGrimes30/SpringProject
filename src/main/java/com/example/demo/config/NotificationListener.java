package com.example.demo.config;

import com.example.demo.entity.Notification;
import com.example.demo.repositories.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final NotificationRepository repository;

    public NotificationListener(NotificationRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "notificationQueue")
    public void receiveNotification(String message) {
        System.out.println("Получено уведомление: " + message);
        Notification notification = new Notification(message);
        repository.save(notification);
    }
}
