package com.example.demo.repositories;

import com.example.demo.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями {@link Fridge}.
 * Предоставляет методы для выполнения операций с базой данных.
 */
public interface FridgeRepository extends JpaRepository<Fridge, Long> {

    /**
     * Возвращает список холодильников с ценой в указанном диапазоне.
     *
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список холодильников с ценой в заданном диапазоне
     */
    List<Fridge> findByPriceBetween(int minPrice, int maxPrice);

    /**
     * Возвращает список холодильников с вместимостью в указанном диапазоне.
     *
     * @param minCapacity минимальная вместимость
     * @param maxCapacity максимальная вместимость
     * @return список холодильников с вместимостью в заданном диапазоне
     */
    List<Fridge> findByCapacityBetween(int minCapacity, int maxCapacity);

    /**
     * Возвращает список холодильников указанного бренда.
     *
     * @param brand бренд холодильника
     * @return список холодильников с заданным брендом
     */
    List<Fridge> findByBrand(String brand);

}
