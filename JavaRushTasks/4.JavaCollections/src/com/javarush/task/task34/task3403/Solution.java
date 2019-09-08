package com.javarush.task.task34.task3403;

import java.util.ArrayList;
import java.util.List;

/*
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public  void recurse(int n) {
        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.print(a + " ");
                    recurse(n / a);
                } else {
                    System.out.print(a);
                }
                return;
            }
            a++;
        }

    }


}
