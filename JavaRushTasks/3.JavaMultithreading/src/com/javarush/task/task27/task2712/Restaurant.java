package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

/**
 * Created by MarKiz on 20.06.2018.
 */
public class Restaurant {
    public static void main(String[] args) {
        Cook firstCook = new Cook("Amigo");
        Tablet tablet = new Tablet(5);
        Waiter waiter = new Waiter();
        tablet.addObserver(firstCook);
        firstCook.addObserver(waiter);
        tablet.createOrder();

    }
}
