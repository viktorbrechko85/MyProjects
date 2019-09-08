package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //запоминаем настоящий PrintStream в специальную переменную
        String promo = "JavaRush - курсы Java онлайн";
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);

        //Вызываем функцию, которая ничего не знает о наших манипуляциях
        testString.printSomething();


        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = outputStream.toString();


        //Возвращаем все как было
        System.setOut(consoleStream);
        String[] str = result.split("\n");
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]+System.lineSeparator());
            if((i+1)%2==0)
                System.out.print(promo+System.lineSeparator());
        }
        //System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
