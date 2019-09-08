package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List <String> list = new ArrayList<>();
        File path = new File(root);

        Files.walkFileTree(path.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.toFile().isDirectory() && (file.toFile().getAbsolutePath().indexOf(".log")>0)){
                    list.add(file.toFile().getAbsolutePath());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return list;

    }

    public static void main(String[] args) throws IOException {
        List <String> list = getFileTree("C:/logs/");
        System.out.println(list.toString());
    }
}
