package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*
Первая стратегия готова, пришло время ее протестить. Для этого:
6.1. Создай класс Solution, если ты не сделал это раньше.
6.2. Добавь в класс Solution реализации вспомогательных статических методов:
6.2.1. Set<Long> getIds(Shortener shortener, Set<String> strings). Этот метод должен для переданного множества строк возвращать множество идентификаторов. Идентификатор для каждой отдельной строки нужно получить, используя shortener.
6.2.2. Set<String> getStrings(Shortener shortener, Set<Long> keys). Метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
При реальном использовании Shortener, задача получить из множества строк множество идентификаторов и наоборот скорее всего не встретится, это нужно исключительно для тестирования.
6.2.3. testStrategy(StorageStrategy strategy, long elementsNumber). Метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber. Реализация метода должна:
6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
6.2.3.2. Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии и
заданного множества элементов.
Время вывести в миллисекундах.
При замере времени работы метода можно пренебречь переключением процессора на другие потоки, временем,
которое тратится на сам вызов, возврат значений и вызов методов получения времени (даты).
Замер времени произведи с использованием объектов типа Date.
6.2.3.5. Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии и полученного в
предыдущем пункте множества идентификаторов.
6.2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества,
которое было возвращено методом getStrings. Если множества одинаковы, то выведи "Тест пройден.",
иначе "Тест не пройден.".
6.2.4. Добавь метод main(). Внутри метода протестируй стратегию HashMapStorageStrategy с помощью 10000 элементов.
6.3. Проверь, что программа работает и тест пройден.
* */
public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        testStrategy(hashMapStorageStrategy, 10000);
        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy, 10000);
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        testStrategy(fileStorageStrategy, 100);
        OurHashBiMapStorageStrategy ohbmss = new OurHashBiMapStorageStrategy();
        testStrategy(ohbmss, 10000);
        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        testStrategy(dualHashBidiMapStorageStrategy, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for(String str:strings){
            result.add(shortener.getId(str));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for(Long lng:keys){
            result.add(shortener.getString(lng));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> strGenerate = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strGenerate.add(Helper.generateRandomString());
        }
        boolean test = true;
        Shortener shortener = new Shortener(strategy);
        Date date1 = new Date();
        Set<Long> setKeys = getIds(shortener, strGenerate);
        Date date2 = new Date();
        long deltaTime = date2.getTime() - date1.getTime();
        Helper.printMessage(Long.toString(deltaTime));
        Date date3 = new Date();
        Set<String> setString = getStrings(shortener, setKeys);
        Date date4 = new Date();
        deltaTime = date4.getTime() - date3.getTime();
        Helper.printMessage(Long.toString(deltaTime));
        if (strGenerate.equals(setString))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");

    }
}
