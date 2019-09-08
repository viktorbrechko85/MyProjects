package com.javarush.task.task29.task2909.car;

/**
 * Created by MarKiz on 26.05.2018.
 */
public class Truck extends Car {
    public Truck(int numberOfPassengers) {
        super(numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_TRUCK_SPEED;
    }
}
