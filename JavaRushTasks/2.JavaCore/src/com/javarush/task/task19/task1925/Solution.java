package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    private static String FileName1="";
    private static String FileName2="";
    private static String StrRez="";

    public static void main(String[] args) throws Exception {
        String fileName1 = args[0];//"e:\9.txt"
        String fileName2 = args[1];//"e:\10.txt"

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2));
        StringBuffer outText = new StringBuffer("");
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] words = line.split(" ");
            for (String word : words)
                if (word.length() > 6)
                    outText.append(word).append(",");
        }

        outText.delete(outText.length() - 1, outText.length());
        fileWriter.write(outText.toString());
        fileWriter.close();
        fileReader.close();
    }
        /*if(args.length>0)
        {
            FileName1 = args[0];
            FileName2 = args[1];
        }
        BufferedReader fr = new BufferedReader(new FileReader(FileName1));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(FileName2));
        while (fr.ready()) {
            String[] line = fr.readLine().split(" ");
            for (int i = 0; i < line.length; i++) {
                if (line[i].length()>6){
                    bfw.write(line[i]);
                    if (i+1<line.length)
                        bfw.write(",");
                }

            }
        }

        fr.close();
        bfw.close();
    }*/
}
