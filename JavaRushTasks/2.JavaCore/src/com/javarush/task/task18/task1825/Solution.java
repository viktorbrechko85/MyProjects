package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args)throws IOException {
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrFileName = new ArrayList<>();
        ArrayList<Integer> arrSort = new ArrayList<>();
        String filename=reader.readLine();
        while (!filename.equals("end")){
            arrFileName.add(filename);
            filename = reader.readLine();
        }
        File tmp = new File (arrFileName.get(0));
        String currentPath = tmp.getParent();
        String lastFileName = tmp.getName();
        String outputFileName = currentPath+"\\"+lastFileName.substring(0, lastFileName.indexOf(".part"));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(outputFileName));
        for (int i = 0; i <  arrFileName.size(); i++) {
            int por = Integer.parseInt(arrFileName.get(i).substring(arrFileName.get(i).indexOf(".part")+5,arrFileName.get(i).length()));
            arrSort.add(por);
        }
        Collections.sort(arrSort);
        for (int i = 0; i < arrSort.size(); i++) {
            String file = outputFileName+".part"+arrSort.get(i);
            if (arrFileName.contains(file))
            {
                BufferedReader bfr = new BufferedReader(new FileReader(file));

                while (bfr.ready()){
                    String Str = bfr.readLine();
                    bfw.write(Str);
                }
                bfr.close();
            }

        }
        bfw.close();*/
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> parts = new ArrayList<String>();
        FileInputStream fileInputStream = null;
        String nextFileName = null;
        //Читаем файлы пока не "end"
        while (true) {
            if ("end".equals(nextFileName = scanner.nextLine())) break;
            else parts.add(nextFileName);
        }
        scanner.close();
        //Сортируем коллекцию
        Collections.sort(parts, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        //Выходной файловый поток
        String outputFileName = parts.get(0).split(".part")[0];
        FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
        //Сливаем файлы
        for (String partsFileNames : parts) {
            fileInputStream = new FileInputStream(partsFileNames);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            fileInputStream.close();
        }
        fileOutputStream.close();
        System.out.println("Объединение файлов выполнено!");
    }
}
