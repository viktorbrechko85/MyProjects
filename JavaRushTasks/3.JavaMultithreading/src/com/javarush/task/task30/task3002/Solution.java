package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        Integer b=0;

        if (s.indexOf("0")==0)
            if (s.indexOf("x")==1)
                b = Integer.parseInt(s.substring(2), 16);
            else if (s.indexOf("b")==1)
                b = Integer.parseInt(s.substring(2), 2);
            else b = Integer.parseInt(s.substring(1), 8);
        else
            b = Integer.parseInt(s);
        return b.toString();
    }
}
