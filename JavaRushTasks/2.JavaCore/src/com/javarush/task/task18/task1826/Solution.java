package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        if (args[0].equals("-e")) {
            encrypt(args[1],args[2]);
        } else if (args[0].equals("-d")) {
            decrypt(args[1],args[2]);
        }
        /*String par = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];
        switch(args[0]) {
            case "-e":
                FyleCrypt(fileName,fileOutputName);
                break;
            case "-d":
                FyleUnCrypt(fileName,fileOutputName);
                break;
        }*/
        //FileCriptUnCript("c://1/11.txt","c://1/12.txt",par);

    }
    static void encrypt (String InputFileName, String OutputFileName) throws Exception{


        FileInputStream inputStream = new FileInputStream(InputFileName);
        FileOutputStream outputStream = new FileOutputStream(OutputFileName);

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int b = inputStream.read();
            outputStream.write(encrypt(b));
        }

        inputStream.close();
        outputStream.close();

    }

    static void decrypt (String InputFileName, String OutputFileName) throws Exception{
        FileInputStream inputStream = new FileInputStream(InputFileName);
        FileOutputStream outputStream = new FileOutputStream(OutputFileName);

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int b = inputStream.read();
            outputStream.write(decrypt(b));
        }

        inputStream.close();
        outputStream.close();
    }

    static int encrypt (int b) {
        if (b<127) {
            return ++b;
        }
        else {
            return -128;
        }
    }

    static int decrypt (int b) {
        if (b!=-128) {
            return --b;
        }
        else {
            return 127;
        }
    }
    /*public static void FyleCrypt(String fileSours,String fileDest) throws IOException{
            String str="";
            ArrayList<String> arr = new ArrayList<>();
            BufferedReader bfr = new BufferedReader(new FileReader(fileSours));
            while (bfr.ready()){
                str  = bfr.readLine();
                arr.add(str);
            }

            bfr.close();
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileDest));
            for (int i = arr.size(); i > 0; i--) {
                str = arr.get(i-1);
                byte[] buffer = str.getBytes();
                for (int j = buffer.length; j > 0; j--) {
                    bfw.write(buffer[j-1]+1);
                }

            }
            bfw.close();

    }
    public static void FyleUnCrypt(String fileSours,String fileDest) throws IOException{
        String str="";
        ArrayList<String> arr = new ArrayList<>();
        BufferedReader bfr = new BufferedReader(new FileReader(fileSours));
        while (bfr.ready()){
            str  = bfr.readLine();
            arr.add(str);
        }
        bfr.close();
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileDest));
        for (int i = arr.size(); i > 0; i--) {
            str = arr.get(i-1);
            byte[] buffer = str.getBytes();
            for (int j = buffer.length; j > 0; j--) {
                bfw.write(buffer[j-1]-1);
            }
        }
        bfw.close();
    }*/

   /* public static void FileCriptUnCript(String fileSours,String fileDest,String par) throws IOException
    {

        try{

            if (par.equals("-e"))
            {
                String str="";
                ArrayList<String> arr = new ArrayList<>();
                BufferedReader bfr = new BufferedReader(new FileReader(fileSours));
                while (bfr.ready()){
                    str  = bfr.readLine()+System.lineSeparator();
                    arr.add(str);
                }
                bfr.close();
                BufferedWriter bfw = new BufferedWriter(new FileWriter(fileDest));
                for (int i = arr.size(); i > 0; i--) {
                    str = arr.get(i-1);
                    byte[] buffer = str.getBytes();
                    for (int j = buffer.length; j > 0; j--) {
                        bfw.write(buffer[j-1]+1);
                    }

                }
                bfw.close();
            }
            else if (par.equals("-d"))
            {
                String str="";
                ArrayList<String> arr = new ArrayList<>();
                BufferedReader bfr = new BufferedReader(new FileReader(fileSours));
                while (bfr.ready()){
                    str  = bfr.readLine();
                    arr.add(str);
                }
                bfr.close();
                BufferedWriter bfw = new BufferedWriter(new FileWriter(fileDest));
                for (int i = arr.size(); i > 0; i--) {
                    str = arr.get(i-1);
                    byte[] buffer = str.getBytes();
                    for (int j = buffer.length; j > 0; j--) {
                        bfw.write(buffer[j-1]-1);
                    }
                }
                bfw.close();
            }
        }
        catch (FileNotFoundException e){

        }



    }*/

}
