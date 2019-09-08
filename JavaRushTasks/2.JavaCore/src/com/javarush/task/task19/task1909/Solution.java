package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(bufferedReader.readLine()));

        ArrayList<Character> temp = new ArrayList<>();
        ArrayList<Character> result = new ArrayList<>();

        while (reader.ready()){
            temp.add((char)reader.read());
        }

        for (Character x : temp){
            if (x.equals('.'))
                writer.write('!');
            else writer.write(x);
        }

        bufferedReader.close();
        reader.close();
        writer.close();
    }
}
