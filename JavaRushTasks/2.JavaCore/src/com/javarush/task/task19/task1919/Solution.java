package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName="";
        if (args[0].length()>0)
            fileName = args[0];
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        HashMap<String,Double> map = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();
        while(bfr.ready()){
            String[] str = bfr.readLine().split(" ");
            if (!map.containsKey(str[0])) {
                map.put(str[0], Double.parseDouble(str[1]));
                arr.add(str[0]);
            }else{
                Double zp = map.get(str[0])+Double.parseDouble(str[1]);
                map.put(str[0],zp);
            }
        }
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i) + " " + map.get(arr.get(i)));
        }
        bfr.close();
    }
}
