package com.javarush.task.task26.task2601;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        int mediana = GetMediana(array);
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - mediana) - Math.abs(o2 - mediana);
            }
        };
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        Collections.sort(list, comp);
        Integer[] rez = list.toArray(new Integer[list.size()]);
        return rez;

    }

    public static int GetMediana(Integer[] array){
        int mediana=0;
        Arrays.sort(array);
        if(!(array.length % 2 ==0))
            mediana = array[array.length/2];
        else{
           int i =  array.length/2;
            mediana = (array[i] + array[i-1])/2;
        }
        return mediana;
    }


}
