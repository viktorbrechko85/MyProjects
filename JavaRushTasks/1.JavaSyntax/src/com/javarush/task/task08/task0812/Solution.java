package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.Inflater;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10 ; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }
        int r;
        int count=1;
        int countRez=1;
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) == list.get(i+1)){
                count++;
                if (count>countRez)
                    countRez=count;
            }
            else
                count=1;
        }
        System.out.println(countRez);
    }
}