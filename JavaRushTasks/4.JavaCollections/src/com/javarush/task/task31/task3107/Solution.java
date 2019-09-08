package com.javarush.task.task31.task3107;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;
    boolean hidd = false;
    boolean exec = false;
    boolean dir = false;
    boolean writ = false;


    public static void main(String[] args) {

    }
    public Solution(String pathToFile) {
        try{
            Path pth = Paths.get(pathToFile);
            if (Files.isHidden(pth))
                hidd = true;
            if (Files.isExecutable(pth))
                exec = true;
            if (Files.isDirectory(pth))
                dir = true;
            if (Files.isWritable(pth))
                writ = true;
            fileData = getFileData();
        }catch (NullPointerException e){
            fileData = getNullFileData(e);
        } catch (IOException e) {
            fileData = getNullFileData(e);
        }
    }

    public FileData getFileData() {

        return new ConcreteFileData(hidd, exec, dir, writ);
    }



    public FileData getNullFileData(Exception e) {
        return new NullFileData(e);
    }
}
