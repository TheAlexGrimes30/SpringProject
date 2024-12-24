package com.example.demo.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(String message){
        rabbitTemplate.convertAndSend("notificationExchange", "notification.routingKey", message);
        System.out.println("Отправлено сообщение: " + message);
    }

}
