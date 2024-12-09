package com.example.demo.entity;

import jakarta.persistence.*;

/**
 * Класс представляет сущность холодильника для работы с базой данных.
 */
@Entity
@Table(name = "fridges")
public class Fridge {

    /**
     * Уникальный идентификатор холодильника.
     * Генерируется автоматически с использованием стратегии IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Бренд холодильника.
     */
    private String brand;

    /**
     * Модель холодильника.
     */
    private String model;

    /**
     * Цена холодильника.
     */
    private int price;

    /**
     * Описание холодильника.
     */
    private String description;

    /**
     * Вместимость холодильника в литрах.
     */
    private int capacity;

    /**
     * Конструктор без параметров (обязательный для JPA).
     */
    public Fridge() {
    }

    /**
     * Конструктор с параметрами для инициализации объекта Fridge.
     *
     * @param brand       бренд холодильника
     * @param model       модель холодильника
     * @param price       цена холодильника
     * @param description описание холодильника
     * @param capacity    вместимость холодильника
     */
    public Fridge(String brand, String model, int price, String description, int capacity){
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setDescription(description);
        setCapacity(capacity);
    }

    /**
     * Возвращает идентификатор холодильника.
     *
     * @return идентификатор холодильника
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор холодильника.
     *
     * @param id идентификатор холодильника
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает бренд холодильника.
     *
     * @return бренд холодильника
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд холодильника. Бренд не может быть пустым.
     *
     * @param brand бренд холодильника
     * @throws IllegalArgumentException если бренд пустой
     */
    public void setBrand(String brand) {
        if (!brand.isEmpty()){
            this.brand = brand;
        }else{
            throw new IllegalArgumentException("Brand cannot be empty");
        }

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
     * Устанавливает модель холодильника. Модель не может быть пустой.
     *
     * @param model модель холодильника
     * @throws IllegalArgumentException если модель пустая
     */
    public void setModel(String model) {
        if (!model.isEmpty()){
            this.model = model;
        }else{
            throw new IllegalArgumentException("Model cannot be empty");
        }
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
     * Устанавливает цену холодильника. Цена должна быть больше 0.
     *
     * @param price цена холодильника
     * @throws IllegalArgumentException если цена меньше или равна 0
     */
    public void setPrice(int price) {
        if (price > 0){
            this.price = price;
        }else{
            throw new IllegalArgumentException("Price cannot be less than 0");
        }

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
     * Устанавливает описание холодильника. Описание не может быть пустым.
     *
     * @param description описание холодильника
     * @throws IllegalArgumentException если описание пустое
     */
    public void setDescription(String description) {
        if (!description.isEmpty()){
            this.description = description;
        }else{
            throw new IllegalArgumentException("Description cannot be empty");
        }

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
     * Устанавливает вместимость холодильника. Вместимость должна быть в диапазоне от 1 до 1000 литров.
     *
     * @param capacity вместимость холодильника
     * @throws IllegalArgumentException если вместимость не входит в допустимый диапазон
     */
    public void setCapacity(int capacity) {
        if (capacity > 0 && capacity <= 1000){
            this.capacity = capacity;
        }else{
            throw new IllegalArgumentException("Capacity must be in range from 1 to 1000");
        }

    }

    /**
     * Переопределяет метод toString для представления объекта Fridge в виде строки.
     *
     * @return строковое представление холодильника
     */
    @Override
    public String toString() {
        return "Fridge{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                '}';
    }
}
