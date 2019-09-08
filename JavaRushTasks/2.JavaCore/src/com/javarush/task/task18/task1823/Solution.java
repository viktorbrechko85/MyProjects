package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename=reader.readLine();
        while (!filename.equals("exit")){
            ReadThread rt = new ReadThread(filename);
            rt.start();
            filename = reader.readLine();
        }
    }

    public static class ReadThread extends Thread  {
        private String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run(){
            try
            {
                resultMap.put(this.fileName,maxByteFromFile(this.fileName));
            }
            catch(Exception e)
            {

            }

        }

        private int maxByteFromFile(String fileName) throws IOException {
            int maxByte=0;
            FileInputStream fis = new FileInputStream(fileName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());
            HashMap<Byte,Integer> map = new HashMap<>();
            int c;
            for (int i = 0; i < buffer.length; i++) {
                byte b = buffer[i];
                c=0;
                for (int j = 0; j < buffer.length; j++) {
                    if (b==buffer[j]){
                        c++;
                    }
                }
                map.put(b,c);
            }
            int fMc=0;
            for (byte key : map.keySet()) {
                if (fMc<map.get(key))
                {
                    fMc = map.get(key);
                    maxByte = key;
                }
            }
            fis.close();
            return maxByte;
        }
    }
}
