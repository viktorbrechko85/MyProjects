package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception{
        try {
            BufferedReader readname = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new FileReader(readname.readLine()));

            readname.close();

            while (reader.ready()) {
                String str = reader.readLine();
                String teg = "<".concat(args[0]);
                String upteg = "</".concat(args[0].concat(">"));

                Pattern s = Pattern.compile(teg);
                Pattern e = Pattern.compile(upteg);

                Matcher start = s.matcher(str);
                Matcher end = e.matcher(str);

                Stack<Integer> starty = new Stack<>();

                while (start.find()) {
                    end.find();
                    int b = start.start();
                    int off = end.end();
                    int i = b;
                    String res = str.substring(b, off);
                    String sustr = str.substring(b, off);
                    while (sustr.substring(0 + teg.length()).contains(teg)) {
                        if (start.find() && end.find()) {
                            b = start.start();
                            int c = end.end();
                            sustr = str.substring(b, c);
                            res = str.substring(i, c);
                        }
                    }
                    if (!res.substring(0 + teg.length()).contains(teg)) {
                        System.out.println(res);
                    } else {
                        System.out.println(res);
                        res = res.substring(res.indexOf('>'), res.length() - upteg.length());
                        Matcher mo = s.matcher(res);
                        while (mo.find()) {
                            starty.push(mo.start());
                        }
                        Matcher f = e.matcher(res);
                        while (!starty.empty()) {
                            if (f.find()) {
                                int j = f.end();
                                String r = res.substring(starty.pop(), j);
                                System.out.println(r);
                            }
                        }
                    }
                }
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        String tag="";
        if (args.length>0)
            tag = args[0];
        String[] str = null;
        String s = "";
        while(bfr.ready()){
            s = s+bfr.readLine();
        }
        // Заменяем <span> на <span > чтобы не искать по подстроке. Потом вернем обратно
        s = s.replace("<" + tag + ">", "<" + tag + " >");
        int count = 0;
        Pattern p = Pattern.compile("</" + tag + ">");
        Matcher m = p.matcher(s);
        while (m.find())
            count++;
        Map<Integer, Integer> map = new TreeMap<>();
        int pos = -1;
        for (int i = 0; i < count * 2; i++) {
            int posTagBegin = s.indexOf("<" + tag + " ", pos + 1);
            int posTagEnd = s.indexOf("</" + tag + ">", pos + 1);
            if (posTagBegin >= 0 && posTagBegin < posTagEnd) {
                pos = posTagBegin;
                map.put(posTagBegin, null);
            } else {
                ArrayList<Integer> keys = new ArrayList<>(map.keySet());
                for (int j = keys.size() - 1; j >= 0; j--) {
                    if (map.get(keys.get(j)) == null) {
                        map.put(keys.get(j), posTagEnd);
                        break;
                    }
                }
                pos = posTagEnd;
            }
        }
        // Вывод
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(s.substring(entry.getKey(), entry.getValue() + tag.length() +3).replace("<" + tag + " >", "<" + tag + ">"));
        }
        bfr.close();*/

    }
}
