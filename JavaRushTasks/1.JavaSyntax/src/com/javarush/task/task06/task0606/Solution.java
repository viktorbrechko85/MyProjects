package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int Chislo = Integer.parseInt(reader.readLine());
        while ((Chislo / 10)>=0){
            int Ch2 = Chislo%10;
            Chislo = Chislo/10;
            if ((Ch2%2)!=0) odd++;
            else even++;
            if (Chislo==0) break;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
