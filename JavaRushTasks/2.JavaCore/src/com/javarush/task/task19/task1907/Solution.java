package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = conReader.readLine();
        conReader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        int count = 0;
//        int index = 0;
        String word = "world";
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] words = line.toString().split("\\W");
            for (String s : words)
                if (s.equals(word))
                    count++;
//            while ((index = s.indexOf(word, index+1)) >= 0)
//                count++;
        }
        fileReader.close();

        System.out.println(count);
       /* BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        reader.close();
        FileReader fr = new FileReader(filename1);
        int cWorld = 0;
        int i = 0;
        int[] str = new int[5];
        while   (fr.ready()){
            int data = fr.read();

            if (data>=65 && data<=122){
                i++;

                str[i-1]= data;
                if (i%5==0)
                {
                    i=0;
                    if (str[0] == 119 && str[1] == 111 && str[2] == 114 && str[3] == 108 && str[4] == 100)
                        cWorld++;
                    str = new int[5];
                }
            }
            else
                i=0;

        }
        fr.close();
        System.out.println(cWorld);*/
    }
}
