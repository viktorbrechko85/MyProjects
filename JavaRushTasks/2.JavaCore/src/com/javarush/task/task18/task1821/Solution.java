package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        String fileName = args[0];
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer, 0, fis.available());
        HashMap<Byte,Integer> map = new HashMap<>();
        ArrayList<Byte> arr = new ArrayList<>();
        int c;
        for (int i = 0; i < buffer.length; i++) {
            byte b = buffer[i];
            c=0;
            for (int j = 0; j < buffer.length; j++) {
                if (b==buffer[j]){
                    c++;
                    if ((c==1) && (!map.containsKey(b)))
                        arr.add(b);
                }
            }
            map.put(b,c);
        }
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((char)(byte)arr.get(i) + " " + map.get(arr.get(i)));
        }
        fis.close();

    }
}
