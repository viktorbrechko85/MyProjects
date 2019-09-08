package com.javarush.task.task31.task3110;

/**
 * Created by MarKiz on 01.06.2018.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = bis.readLine();
        return text;
    }

    public static int readInt() throws IOException {
        String text = readString();
        return Integer.parseInt(text.trim());
    }
}

