package com.javarush.task.task31.task3113;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
C:\1
*/
public class Solution {
    public static int countDir = 0;
    public static int countFiles = 0;
    public static long countByte = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        reader.close();
        Path pth = Paths.get(path);
        if (!Files.isDirectory(pth)) {
            System.out.printf(pth.toAbsolutePath().toString() + " - не папка");
            return;
        }

           Files.walkFileTree(pth, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    countDir++;
                    return FileVisitResult.CONTINUE;
                }

                // размер файла: content.length
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    countByte = countByte + attrs.size();
                    countFiles++;
                    return FileVisitResult.CONTINUE;
                }

            });
            System.out.println("Всего папок - " + (countDir-1));
            System.out.println("Всего файлов - " + countFiles);
            System.out.println("Общий размер - " + countByte);
    }
}
