package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String ADMIN_QUEUE_NAME = "adminQueue";
    public static final String EXCHANGE_NAME = "adminExchange";
    public static final String ROUTING_KEY = "admin.routingKey";
    public static final String NOTIFICATION_QUEUE_NAME = "notificationQueue";
    public static final String NOTIFICATION_EXCHANGE_NAME = "notificationExchange";
    public static final String NOTIFICATION_ROUTING_KEY = "notification.routingKey";

    @Bean
    public Queue adminQueue() {
        return new Queue(ADMIN_QUEUE_NAME, true);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME, true); // Убедитесь, что очередь объявлена
    }

    @Bean
    public DirectExchange adminExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE_NAME); // Убедитесь, что есть обмен для уведомлений
    }

    @Bean
    public Binding adminBinding(Queue adminQueue, DirectExchange adminExchange) {
        return BindingBuilder.bind(adminQueue).to(adminExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(NOTIFICATION_ROUTING_KEY); // Привязка очереди и обмена
    }
}