package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) throws FileNotFoundException {
        Properties prop = new Properties();
        String laststrafterdot = fileName.substring(fileName.lastIndexOf(".")+1);
        try {
        switch (laststrafterdot){
            case "xml":
               prop.loadFromXML(new FileInputStream(fileName));
               break;
            case "txt":
                prop.load(new FileReader(fileName));
                break;
            default:
                FileInputStream fileInputStream = new FileInputStream(fileName);
                prop.load(fileInputStream);
                fileInputStream.close();
                break;
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
