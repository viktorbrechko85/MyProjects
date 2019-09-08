package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import javafx.beans.InvalidationListener;



import java.io.IOException;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MarKiz on 20.06.2018.
 */
public class Tablet extends Observable {
    public final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }
    public Order  createOrder() {
        Order order = null;
        try{
            order = new Order(this);
            if(!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                try
                {
                    new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
                }
                catch (NoVideoAvailableException e)
                {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }
                setChanged();
                notifyObservers(order);
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
