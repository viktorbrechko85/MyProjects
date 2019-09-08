package com.javarush.task.task35.task3512;

public class Generator<T> {
    public Class<T> cl;

    public Generator(Class<T> cl) {
        this.cl = cl;
    }


    T newInstance() throws IllegalAccessException, InstantiationException {
        return (T) cl.newInstance();
    }
}
