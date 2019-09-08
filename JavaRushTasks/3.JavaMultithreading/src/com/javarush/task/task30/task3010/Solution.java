package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
    private static TreeMap<Integer,Character> alphabet = new TreeMap<>();
    static
    {
        alphabet.put(10,'A');
        alphabet.put(11,'B');
        alphabet.put(12,'C');
        alphabet.put(13,'D');
        alphabet.put(14,'E');
        alphabet.put(15,'F');
        alphabet.put(16,'G');
        alphabet.put(17,'H');
        alphabet.put(18,'I');
        alphabet.put(19,'J');
        alphabet.put(20,'K');
        alphabet.put(21,'L');
        alphabet.put(22,'M');
        alphabet.put(23,'N');
        alphabet.put(24,'O');
        alphabet.put(25,'P');
        alphabet.put(26,'Q');
        alphabet.put(27,'R');
        alphabet.put(28,'S');
        alphabet.put(29,'T');
        alphabet.put(30,'U');
        alphabet.put(31,'V');
        alphabet.put(32,'W');
        alphabet.put(33,'X');
        alphabet.put(34,'Y');
        alphabet.put(35,'Z');
    }
    public static void main(String[] args) {

        try {
            String s = args[0];
            char[] c = s.toCharArray();
            for (char letter : c)
            {
                if(!Character.isLetterOrDigit(letter)) {
                    System.out.println("incorrect");
                    return;
                }
            }

            boolean hasLetter=false;
            for (char letter : c)
            {
                if(Character.isLetter(letter)) {
                    hasLetter=true;
                    break;
                }
            }

            int minSystemIndex=0;
            if(!hasLetter)
            {
                for (int i = 9; i>0; i--) {
                    if(s.contains(String.valueOf(i)))
                    {
                        minSystemIndex=i+1;
                        break;
                    }
                }
                if(minSystemIndex==0) minSystemIndex=2;
                System.out.println(minSystemIndex);
            }
            else
            {

                SortedMap<Integer,Character> sortedMap = new TreeMap<>(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer k1, Integer k2) {
                        return k2.compareTo(k1);
                    }

                });
                sortedMap.putAll(alphabet);

                for(Map.Entry<Integer,Character> pair : sortedMap.entrySet())
                {
                    if(s.contains(String.valueOf(pair.getValue())) ||
                            s.contains(String.valueOf(pair.getValue()).toLowerCase()))
                    {
                        minSystemIndex=pair.getKey()+1;
                        break;
                    }
                }
                System.out.println(minSystemIndex);
            }
        } catch (Exception e) {

        }

    }
}