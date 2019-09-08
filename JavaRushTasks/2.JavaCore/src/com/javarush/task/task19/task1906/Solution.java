package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        FileReader fr = new FileReader(filename1);
        FileWriter fw = new FileWriter(filename2);
        int c=0;
        while(fr.ready()){
            c++;
            int i = fr.read();
            if (c%2==0)
                fw.write(i);
        }
        fr.close();
        fw.close();
    }
}
