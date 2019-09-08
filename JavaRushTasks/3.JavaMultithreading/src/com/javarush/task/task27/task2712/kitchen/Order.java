package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarKiz on 20.06.2018.
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet)throws Exception {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
    public int getTotalCookingTime(){
        int cookingTime = 0;
        for (Dish dish: dishes) {
            cookingTime += dish.getDuration();
        }
        return cookingTime;
    }
    public boolean isEmpty(){
        return dishes.isEmpty();
    }
    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet;
    }
}
