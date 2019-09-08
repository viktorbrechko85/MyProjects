package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarKiz on 20.06.2018.
 */
public class ConsoleHelper {
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws Exception{

        writeMessage("Выберите блюда. Для завершения наберите 'exit'.");
        writeMessage(Dish.allDishesToString());
        List<Dish> resDish = new ArrayList<>();
        while (true){
            String dishToOrder = readString();
            if (dishToOrder.equalsIgnoreCase("exit")) {
                break;
            }
            if(dishToOrder.isEmpty()){
                writeMessage("Блюдо не выбрано");
                continue;
            }
            boolean found = false;
            for(Dish d : Dish.values())
                if(d.name().equalsIgnoreCase(dishToOrder)) {
                    resDish.add(d);
                    found = true;
                }
            if(!found){
                writeMessage("Нет такого блюда");
            }
        }
        return resDish;
    }
}
