package com.javarush.task.task25.task2506;

/**
 * Created by MarKiz on 25.05.2018.
 */
public class LoggingStateThread extends Thread {
    Thread tr;
    public LoggingStateThread(Thread tr){
        this.tr = tr;
    }

    @Override
    public void run() {
        Thread.State currentState = tr.getState();
        System.out.println(currentState);
        super.run();
        while (!currentState.equals(State.TERMINATED)) {
            Thread.State newState = tr.getState();
            if (!newState.equals(currentState)) {
                System.out.println(newState);
                currentState = newState;
            }
        }
        this.interrupt();
    }
}
