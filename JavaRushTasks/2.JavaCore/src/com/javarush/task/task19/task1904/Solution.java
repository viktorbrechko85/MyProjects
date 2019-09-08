package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner{
        private final Scanner fileScanner;
        PersonScannerAdapter(Scanner fileScanner){
            this.fileScanner = fileScanner;
        }
        public Person read() throws IOException{
            String[] str = this.fileScanner.nextLine().split(" ");
            String firstName = str[1];
            String middleName = str[2];
            String lastName = str[0];
            String date = str[3]+"."+str[4]+"."+str[5];;

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = null;
            try {
                birthDate = format.parse(date);
            }
            catch (Exception e) {

            }
            return new Person(firstName,middleName,lastName,birthDate);
        };

        public void close() throws IOException{
            this.fileScanner.close();
        };
    }
}
