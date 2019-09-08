package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader bfr = new BufferedReader(new FileReader(filename));
        ArrayList<String> arr = new ArrayList<>();
        while  (bfr.ready()){
            arr.add(bfr.readLine());
        }
        bfr.close();
        for (int i = 0; i < arr.size(); i++) {
            String str = fStrToRevers(arr.get(i));
            System.out.println(str);
        }
        }

    public static String fStrToRevers(String str){
        char[] arr = str.toCharArray();
        String rezstr="";
        for (int i = arr.length-1; i >= 0; i--) {
            rezstr = rezstr+arr[i];
        }
        return rezstr;
    }
}
