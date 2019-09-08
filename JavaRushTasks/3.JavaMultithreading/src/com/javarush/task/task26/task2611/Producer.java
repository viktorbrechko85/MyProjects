package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MarKiz on 27.05.2018.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;
    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            Integer i = 1;
            while (true) {
                map.put(i.toString(), "Some text for " + i);
                i++;
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }

    }
}
