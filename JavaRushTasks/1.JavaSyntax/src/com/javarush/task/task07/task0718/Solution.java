package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }
        int lenStr = list.get(0).length();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length()<lenStr){
                System.out.println(i);
                break;
            }
            else
                lenStr = list.get(i).length();
        }
    }
}

