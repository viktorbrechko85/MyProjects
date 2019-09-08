package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException  {
        int seek = Integer.parseInt(args[1]);
        String strArgs = args[2];
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        raf.seek(seek);
        byte[] rStr = new byte[strArgs.length()];
        raf.read(rStr, 0, strArgs.length());
        String str = new String(rStr);
        String strFlag = str.equals(strArgs) ? "true" : "false";
        raf.seek(raf.length());
        raf.write(strFlag.getBytes());
        raf.close();
    }
}
