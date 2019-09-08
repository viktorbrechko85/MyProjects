package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        FileUtils fu = new FileUtils();
        File fileresult = new File(args[1]);
        File filedest = new File(fileresult.getParent() + "/allFilesContent.txt");
        fu.renameFile(fileresult, filedest);
        ArrayList<File> list = new ArrayList<>();
        try(FileOutputStream fos = new FileOutputStream(filedest, true)){
        File path = new File(args[0]);

            Files.walkFileTree(path.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().length() > 50) {
                        FileUtils.deleteFile(file.toFile());
                    } else {
                        list.add(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            Collections.sort(list, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file : list) {
                if (!file.equals(filedest)) {
                    FileReader reader = new FileReader(file);
                    while (reader.ready()) fos.write(reader.read());
                    reader.close();
                    fos.write("\n".getBytes());
                }
            }
            fos.close();
        };



    }


}
