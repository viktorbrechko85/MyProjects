package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
Вернемся к классу Shortener:
3.1. Добавь в него поле Long lastId. Проинициализируй его нулем. Это поле будет
отвечать за последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище.
3.2. Добавь поле StorageStrategy storageStrategy в котором будет храниться стратегия хранения данных.
3.3. Добавь конструктор, который принимает StorageStrategy и инициализирует соответствующее поле класса.
3.4. Реализуй метод getId, он должен:
3.4.1. Проверить есть ли переданное значение в хранилище, если есть - вернуть его ключ.
3.4.2. Если преданного значения нет в хранилище, то:
3.4.2.1. Увеличить значение lastId на единицу;
3.4.2.2. Добавить в хранилище новую пару ключ-значение (новое значение lastId и переданную строку);
3.4.2.3. Вернуть новое значение lastId.
3.5. Реализуй метод getString, он должен вернуть строку по заданному идентификатору (ключу).
3.6. Предусмотреть возможность вызова методов getId и getString из разных потоков добавив соответствующий модификатор к заголовкам методов.
* */
public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string){
        Long longID = 0L;
        if (storageStrategy.containsValue(string))
            longID = storageStrategy.getKey(string);
        else{
            lastId++;
            storageStrategy.put(lastId, string);
            longID = lastId;
        }
        return longID;
    }

    public synchronized String getString(Long id){
        return storageStrategy.getValue(id);
    }
}
