package com.javarush.task.task29.task2912;

/**
 * Created by MarKiz on 19.06.2018.
 */
public abstract class AbstractLogger implements Logger{
    public int level;
    public Logger next;

    public AbstractLogger(int level) {
        this.level = level;
    }

    public void setNext(Logger next) {
        this.next = next;
    }

    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }
}
