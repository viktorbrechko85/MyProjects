package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
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
            else if (args[0].equals("-u")) {
                String ID = AddFreeProbel(args[1], 8);
                String productName = AddFreeProbel(args[2], 30);
                String price = AddFreeProbel(args[3], 8);
                String quantity = AddFreeProbel(args[4], 4);


                UpdateTovar(fileName, ID, productName, price, quantity);
            }
            else if (args[0].equals("-d")) {
                String ID = AddFreeProbel(args[1], 8);
                DeleteTovar(fileName,ID);
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
    public static void DeleteTovar(String fileName, String ID) throws Exception
    {
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        ArrayList<String> arr = new ArrayList<>();
        while (bfr.ready()){
            String Str = bfr.readLine();
            if (Str.length()>0) arr.add(Str);
        }
        bfr.close();
        for (int i = 0; i < arr.size(); i++) {
            String str = arr.get(i);
            String idf = str.substring(0,8);
            if(ID.equals(idf)) {
                arr.remove(i);
                i--;
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < arr.size(); i++) {
            bw.write(arr.get(i)+System.lineSeparator());
        }

        bw.close();
    }
    public static void UpdateTovar(String fileName, String ID, String productName, String price, String quantity) throws Exception
    {
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));

        ArrayList<String> arr = new ArrayList<>();
        while (bfr.ready()){
            String Str = bfr.readLine();
            if (Str.length()>0){
                String idf = Str.substring(0,8);
                if(ID.equals(idf))
                {
                    String strnew = ID+productName+price+quantity;
                    arr.add(strnew);
                }
                else{
                    String strnew = Str;
                    arr.add(strnew);
                }
            }
        }
        bfr.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < arr.size(); i++) {
            bw.write(arr.get(i)+System.lineSeparator());
        }

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
