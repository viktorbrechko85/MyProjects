package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        BufferedReader bfr = new BufferedReader(new FileReader(fileName1));
        ArrayList<Integer> arr = new ArrayList<>();
        while (bfr.ready()){
            String[] strMas = bfr.readLine().split(" ");
            for (int i = 0; i < strMas.length; i++) {
                arr.add(Math.round(Float.parseFloat(strMas[i])));
            }
        }
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName2));
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str = str + arr.get(i) + " ";
        }
        bfw.write(str);
        bfr.close();
        bfw.close();



    }
}
