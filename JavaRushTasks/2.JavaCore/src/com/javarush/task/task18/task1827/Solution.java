package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import javafx.scene.control.Separator;

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        if (args.length>0){
        if (args[0].equals("-c")) {
            String productName = AddFreeProbel(args[1], 30);
            String price = AddFreeProbel(args[2], 8);
            String quantity = AddFreeProbel(args[3], 4);
            String ID = AddFreeProbel(Integer.toString(SearchLastID(fileName)+1),8);

            AddNewTovar(fileName, ID, productName, price, quantity);
        }
        }
    }

    public static int SearchLastID(String fileName) throws Exception
    {
        int ID = 0;
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        ArrayList<Integer> arr = new ArrayList<>();
        while (bfr.ready()){
            String Str = bfr.readLine();
            if (Str.length()>0) arr.add(Integer.parseInt(Str.substring(0,8).trim()));
        }
        bfr.close();
        for (int i = 0; i < arr.size(); i++) {
            if (ID<arr.get(i))
                ID = arr.get(i);
        }
        return ID;
    }

    public static void AddNewTovar(String fileName, String ID, String productName, String price, String quantity) throws Exception
    {
        /*FileOutputStream fos = new FileOutputStream(fileName);
        String str = System.lineSeparator() + ID+productName+price+quantity;
        fos.write(str.getBytes());
        fos.close();*/
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true));
        String str = ID+productName+price+quantity;
        //bw.newLine();
        bw.write(str);
        bw.close();
    }

    public static  String AddFreeProbel(String str, int count){
        String rez=str;
        if (rez.length()<count)
        {
            for (int i = rez.length(); i < count; i++) {
                rez=rez+" ";
            }
        }
        return rez;
    }
}
