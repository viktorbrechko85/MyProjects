package com.javarush.task.task19.task1924;

import javafx.scene.control.Separator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;

/* 
������ �����
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0,"����");
        map.put(1,"����");
        map.put(2,"���");
        map.put(3,"���");
        map.put(4,"������");
        map.put(5,"����");
        map.put(6,"�����");
        map.put(7,"����");
        map.put(8,"������");
        map.put(9,"������");
        map.put(10,"������");
        map.put(11,"����������");
        map.put(12,"����������");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = conReader.readLine();
        conReader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
       // BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"CP1251"));
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            for (Map.Entry<Integer, String> pair : map.entrySet())
                line = line.replaceAll("\\b" + pair.getKey().intValue() + "\\b", pair.getValue());
            System.out.println(line);
        }
        fileReader.close();
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String FileName = reader.readLine();
        reader.close();

        BufferedReader bfr = new BufferedReader(new FileReader(FileName));
        ArrayList<String> arr = new ArrayList<>();
        while(bfr.ready()){
            arr.add(bfr.readLine());
        }
        for (int i = 0; i < arr.size(); i++) {
            String[] str = arr.get(i).split(" ");
            String strH = "";
                for (int j = 0; j < str.length; j++) {
                    if (!str[j].equals("")) {
                        if (str[j].matches(("^\\d*$"))) {
                            if (map.containsKey(Integer.parseInt(str[j]))) {
                                str[j] = str[j].replace(str[j], map.get(Integer.parseInt(str[j])));
                                strH = strH + str[j] + " ";
                            }
                            else
                                strH = strH + str[j] + " ";
                        } else
                            strH = strH + str[j] + " ";
                    }
                }
            arr.remove(i);
            arr.add(i,strH);
        }
        bfr.close();
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }*/
    }
}
