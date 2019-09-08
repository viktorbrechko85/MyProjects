package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }
        String shortStr = list.get(0);
        int shortIn = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length()<shortStr.length()) {
                shortStr = list.get(i);
                shortIn = i;
            }
        }
        //System.out.println(shortStr + " - I: " + shortIn);
        String longStr = list.get(0);
        int longIn = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length()>longStr.length()) {
                longStr = list.get(i);
                longIn = i;
            }
        }
       // System.out.println(longStr + " - I: " + longIn);
        if (shortIn<longIn)
            System.out.println(shortStr);
        else
            System.out.println(longStr);
    }
}
