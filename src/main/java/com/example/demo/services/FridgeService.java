package com.example.demo.services;

import com.example.demo.entity.Fridge;
import com.example.demo.repositories.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для работы с холодильниками.
 * Предоставляет методы для выполнения операций с объектами Fridge.
 */
@Service
public class FridgeService {
    private final FridgeRepository fridgeRepository;

    /**
     * Конструктор класса FridgeService.
     * Используется для внедрения зависимостей.
     *
     * @param fridgeRepository репозиторий для работы с холодильниками
     */
    @Autowired
    public FridgeService(FridgeRepository fridgeRepository){
        this.fridgeRepository = fridgeRepository;
    }

    /**
     * Получение списка всех холодильников.
     *
     * @return список объектов Fridge
     */
    public List<Fridge> findAll(){
        var fridges =fridgeRepository.findAll();
        return fridges;
    }

    /**
     * Поиск холодильника по идентификатору.
     *
     * @param id идентификатор холодильника
     * @return объект Optional с найденным холодильником или пустой Optional, если объект не найден
     */
    public Optional<Fridge> findById(Long id){
        return fridgeRepository.findById(id);
    }

    /**
     * Сохранение нового холодильника в базу данных.
     *
     * @param fridge объект Fridge для сохранения
     * @return сохраненный объект Fridge
     */
    public Fridge save (Fridge fridge){
        return fridgeRepository.save(fridge);
    }

    /**
     * Удаление холодильника по идентификатору.
     *
     * @param id идентификатор холодильника
     */
    public void deleteById(Long id){
        fridgeRepository.deleteById(id);
    }

    /**
     * Обновление информации о холодильнике.
     *
     * @param id идентификатор холодильника
     * @param updateFridge объект Fridge с обновленными данными
     * @return обновленный объект Fridge
     * @throws RuntimeException если холодильник с указанным идентификатором не найден
     */
    public Fridge updateFridge(Long id, Fridge updateFridge){
        if (fridgeRepository.findById(id).isPresent()){
            updateFridge.setId(id);
            return fridgeRepository.save(updateFridge);
        }else{
            throw new RuntimeException("No such id in the DB");
        }
    }

    /**
     * Поиск холодильников по диапазону цен.
     *
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return список холодильников, попадающих в указанный диапазон цен
     */
    public List<Fridge> findByPriceBetween (int minPrice, int maxPrice) {
        return fridgeRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Поиск холодильников по диапазону вместимости.
     *
     * @param minCapacity минимальная вместимость
     * @param maxCapacity максимальная вместимость
     * @return список холодильников, попадающих в указанный диапазон вместимости
     */
    public List<Fridge> findByCapacityBetween(int minCapacity, int maxCapacity){
        return fridgeRepository.findByCapacityBetween(minCapacity, maxCapacity);
    }

    /**
     * Поиск холодильников по бренду.
     *
     * @param brand название бренда
     * @return список холодильников указанного бренда
     */
    public List<Fridge> findByBrand(String brand){
        return fridgeRepository.findByBrand(brand);
    }
}
