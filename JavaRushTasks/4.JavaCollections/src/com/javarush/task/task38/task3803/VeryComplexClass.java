package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() throws ClassCastException {
        Object ch = new Character('*');
        System.out.println((Byte)ch);
    }

    public void methodThrowsNullPointerException() throws NullPointerException {
        int[] array = null;
        System.out.println(array.length);
    }

    public static void main(String[] args) {

    }
}
