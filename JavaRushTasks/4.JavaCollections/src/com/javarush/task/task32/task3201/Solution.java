package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        Long seek = Long.parseLong(args[1]);
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        if (raf.length()<seek)
            seek = raf.length();
        raf.seek(seek);
        raf.write(args[2].getBytes());
        raf.close();
    }
}
