package com.automarket;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity // 1. Кажемо базі: "Створи для цього таблицю"
public class Product extends PanacheEntity { // 2. Отримуємо магічні методи (persist, listAll)

    // 3. Робимо поля PUBLIC (це фішка Panache, щоб не писати купу геттерів)
    public String brand;        // Марка (BMW)
    public String model;        // Модель (X5)
    public String licensePlate; // Номер (AA0000BB)
    public double price;        // Ціна за день
    public boolean isAvailable; // Чи вільна

    // 4. Порожній конструктор (ОБОВ'ЯЗКОВО для бази даних)
    public Product() {}

    // 5. Зручний конструктор для нас
    public Product(String brand, String model, String licensePlate, double price) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.price = price;
        this.isAvailable = true; // За замовчуванням вільна
    }
}