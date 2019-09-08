package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    static char[] ArrUpp = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    static char[] ArrLow = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        int count = 0;
        FileInputStream fs = new FileInputStream(fileName);
        int i=-1;
        while((i=fs.read())!=-1){
            if (FindEnglishBook((char)i))
                count++;

        }
        System.out.print(count);
        fs.close();
    }

    public static boolean FindEnglishBook(char Findsymbol){
        boolean Rez = false;
        for (int i = 0; i < ArrUpp.length; i++) {
            if ((ArrUpp[i]==Findsymbol) || (ArrLow[i]==Findsymbol))
            {
                Rez = true; break;
            }
        }
        return Rez;
    }
}
