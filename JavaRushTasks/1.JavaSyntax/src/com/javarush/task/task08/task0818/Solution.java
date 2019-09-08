package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("F1",1000);
        map.put("F2",2000);
        map.put("F3",200);
        map.put("F4",499);
        map.put("F5",555);
        map.put("F6",1500);
        map.put("F7",100);
        map.put("F8",666);
        map.put("F9",777);
        map.put("F10",150);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> map1 = new HashMap<>();
        for (String key : map.keySet()) {
            if (map.get(key) < 500)
                map1.put(key,map.get(key));
        }
        for (String key : map1.keySet()) {
            if (map.containsKey(key))
                map.remove(key);
        }
    }

    public static void main(String[] args) {
    }
}