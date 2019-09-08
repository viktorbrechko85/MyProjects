package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception  {
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
        HashMap<String, Double> mapRez = new HashMap<>();
        ArrayList<String> arrMax = new ArrayList<>();
        Double maxZp=0.0;
        for (Map.Entry<String, Double> entry: map.entrySet()){
            if (maxZp<=entry.getValue()){
                maxZp = entry.getValue();
                //mapRez.clear();
                mapRez.put(entry.getKey(),maxZp);
                arrMax.add(entry.getKey());
            }
        }
        Collections.sort(arrMax);
        for (int i = 0; i < arrMax.size(); i++) {
            System.out.println(arrMax.get(i));
        }
        bfr.close();
    }
}
