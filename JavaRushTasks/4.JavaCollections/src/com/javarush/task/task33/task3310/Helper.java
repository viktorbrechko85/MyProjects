package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    private static SecureRandom random;
    public static String generateRandomString(){
        random = new SecureRandom();
        return new BigInteger(130, random).toString(36);
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
