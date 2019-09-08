package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by MarKiz on 21.06.2018.
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ",cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(arg);
    }
}
