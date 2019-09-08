package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();

        FileOutputStream fos = new FileOutputStream(fileName1,true);
        FileInputStream fis1 = new FileInputStream(fileName2);
        FileInputStream fis2 = new FileInputStream(fileName3);

        byte[] buffer1 = new byte[fis1.available()];
        fis1.read(buffer1, 0, fis1.available());

        byte[] buffer2 = new byte[fis2.available()];
        fis2.read(buffer2, 0, fis2.available());

        fos.write(buffer1, 0, buffer1.length);
        fos.write(buffer2, 0, buffer2.length);

        fos.close();
        fis1.close();
        fis2.close();
    }
}
