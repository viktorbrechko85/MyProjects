package com.javarush.task.task32.task3204;

import java.io.*;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        String[] alphabetBIG  = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] digital  = new String[]{"0","1","2","3","4","5","6","7","8","9"};
        String[] alphabetSmall  = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random random = new Random();
        String rez = "";
        int ind = 0;
        for (int i = 0; i < 8; i++) {
            if (i<4){
                ind = random.nextInt(alphabetBIG.length);
                rez = rez+alphabetBIG[ind];
            }
            else if ((i>=4) &(i<6)) {
                ind = random.nextInt(alphabetSmall.length);
                rez = rez+alphabetSmall[ind];
            }else{
                ind = random.nextInt(digital.length);
                rez = rez+digital[ind];
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(rez.getBytes());

        return outputStream;
    }
}