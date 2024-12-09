package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Класс DTO (Data Transfer Object) для передачи данных о холодильнике.
 * Используется для валидации входных данных при взаимодействии с API.
 */
public class FridgeDto {

    /**
     * Бренд холодильника. Не может быть пустым.
     */
    @NotBlank(message = "Brand must not be empty")
    private String brand;

    /**
     * Модель холодильника. Не может быть пустой.
     */
    @NotBlank(message = "Model must not be empty")
    private String model;

    /**
     * Цена холодильника. Должна быть больше 0.
     */
    @Range(min = 1, message = "Price must be greater than 0")
    private int price;

    /**
     * Описание холодильника. Не может быть пустым.
     */
    @NotBlank(message = "Description must not be empty")
    private String description;

    /**
     * Вместимость холодильника в литрах. Должна быть в диапазоне от 1 до 1000.
     */
    @Range(min = 1, max=1000, message = "Capacity must be in range from 1 to 1000")
    private int capacity;

    /**
     * Возвращает бренд холодильника.
     *
     * @return бренд холодильника
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд холодильника.
     *
     * @param brand бренд холодильника
     */
    public void setBrand (String brand) {
        this.brand = brand;
    }

    /**
     * Возвращает модель холодильника.
     *
     * @return модель холодильника
     */
    public String getModel() {
        return model;
    }

    /**
     * Устанавливает модель холодильника.
     *
     * @param model модель холодильника
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Возвращает цену холодильника.
     *
     * @return цена холодильника
     */
    public int getPrice() {
        return price;
    }

    /**
     * Устанавливает цену холодильника.
     *
     * @param price цена холодильника
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Возвращает описание холодильника.
     *
     * @return описание холодильника
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание холодильника.
     *
     * @param description описание холодильника
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает вместимость холодильника.
     *
     * @return вместимость холодильника
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Устанавливает вместимость холодильника.
     *
     * @param capacity вместимость холодильника
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
