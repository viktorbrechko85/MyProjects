package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.nio.Buffer;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName="";
        FileInputStream fis;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            fileName = reader.readLine();
            while (!fileName.equals(""))
            {
                fis = new FileInputStream(fileName);
                fileName = reader.readLine();
                fis.close();
            }
        }
        catch(FileNotFoundException fe)
        {
            System.out.println(fileName);

        }
    }
}
