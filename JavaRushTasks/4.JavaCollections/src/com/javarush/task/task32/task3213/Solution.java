package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }
    public static String decode(StringReader reader, int key) throws IOException {
        int a;
        StringBuffer res = new StringBuffer();

        try {
            while ((a = reader.read()) != -1) {
                res.append(Character.toString((char) (a + key)));
            }
        } catch (Exception e) {
            return new String();
        }

        return res.toString();
    }
// Мой вариант
    /*public static String decode(StringReader reader, int key) throws IOException {
        String[] alphabetBIG  = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] alphabetsmall  = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        try {
            BufferedReader br = new BufferedReader(reader);
            String rez = "";
            String strIn = br.readLine();

            for (int i = 0; i < strIn.length(); i++) {
                char p = strIn.charAt(i);
                boolean bigAlf = false;
                boolean smallAlf = false;
                int indSym = 0;
                for (int j = 0; j < alphabetBIG.length; j++) {
                    if (p == alphabetBIG[j].charAt(0)) {
                        bigAlf = true;
                        if (j >= 3)
                            indSym = j + key;
                        else
                            indSym = alphabetBIG.length + j + key;
                    } else if (p == alphabetsmall[j].charAt(0)) {
                        smallAlf = true;
                        if (j >= 3)
                            indSym = j + key;
                        else
                            indSym = alphabetBIG.length + j + key;
                    }
                    if (bigAlf) {
                        rez = rez + alphabetBIG[indSym];
                        break;
                    }
                    if (smallAlf) {
                        rez = rez + alphabetsmall[indSym];
                        break;
                    }

                }
            }
            return rez;
        }
        catch (Exception e){
            return new String();
        }
    }*/
}
