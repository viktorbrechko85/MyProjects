package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        BufferedReader bfr = new BufferedReader(new FileReader(fileName1));
        while (bfr.ready()){
            String s = bfr.readLine();
            if (s.startsWith(args[0]))
                System.out.println(s);
        }
        bfr.close();
    }
}
