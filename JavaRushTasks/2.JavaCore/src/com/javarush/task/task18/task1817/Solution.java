package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        int count = 0;
        FileInputStream fs = new FileInputStream(fileName);
        int i=-1;
        while((i=fs.read())!=-1){


        }
    }
}
