package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception{
        String fileR = "";
        String fileW = "";
        if (args.length>0){
            fileR = args[0];
            fileW = args[1];
        }
        BufferedReader bfr = new BufferedReader(new FileReader(fileR));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileW));
        ArrayList<String> arr = new ArrayList<>();
        while(bfr.ready()){
            arr.add(bfr.readLine());
        }
        ArrayList<String> arrRez = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String[] str = arr.get(i).split(" ");
            String strRez = "";
            for (int j = 0; j < str.length; j++) {
                Pattern p = Pattern.compile("^\\D*$");
                Matcher m = p.matcher(str[j]);
                if (!m.find())
                    strRez = strRez+str[j]+" ";
            }
            if (strRez.length()>0)
                arrRez.add(strRez);
        }
        for (int i = 0; i < arrRez.size(); i++) {
            bfw.write(arrRez.get(i));
        }
        bfr.close();
        bfw.close();

        /*BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter printWriter = new BufferedWriter(new FileWriter(args[1]));

        while (fileReader.ready()){
            String line =fileReader.readLine();
            String[] words = line.split(" ");
            for (String word : words)
                if (!word.matches("^\\D*$"))
                    printWriter.write(word + " ");
        }
        fileReader.close();
        printWriter.close();*/

    }
}
