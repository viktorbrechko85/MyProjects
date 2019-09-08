package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws  Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        ArrayList<String> arr = new ArrayList<>();
        while(bfr.ready()){
            arr.add(bfr.readLine());
        }
        int c = 0;

        for (int i = 0; i < arr.size(); i++) {
            String[] str = arr.get(i).split(" ");
            c=0;
            for (int j = 0; j < str.length; j++) {
                for (int k = 0; k < words.size(); k++) {
                    if (str[j].equals(words.get(k))){
                        c++;
                        break;
                    }
                }
            }
            if (c==2)
                System.out.println(arr.get(i));
        }
        bfr.close();
    }
}
