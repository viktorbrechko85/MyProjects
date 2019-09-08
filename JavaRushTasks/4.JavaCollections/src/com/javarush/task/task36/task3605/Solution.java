package com.javarush.task.task36.task3605;

import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        TreeSet<Character> letters = new TreeSet<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            while (fileReader.ready()) {
                String s = fileReader.readLine().toLowerCase().replaceAll("[^\\p{Alpha}]",""); //\s\p{Punct}
                for (int i = 0; i < s.length(); i++)
                    letters.add(s.charAt(i));
            }
            fileReader.close();
        }

        Iterator<Character> iterator = letters.iterator();
        int n = letters.size() < 5 ? letters.size() : 5;

        for (int i = 0; i < n; i++) {
            System.out.print((iterator.next()));
        }

    }
}
