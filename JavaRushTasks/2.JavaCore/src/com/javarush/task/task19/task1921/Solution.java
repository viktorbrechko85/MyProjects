package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        String filename = args[0];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            while (bufferedReader.ready()){
                String name = "";
                int year = 0;
                int month = 0;
                int day = 0;
                String[] dannie = bufferedReader.readLine().split(" ");
                if (dannie.length==4){
                    name = dannie[0];
                    day = Integer.parseInt(dannie[1]);
                    month = Integer.parseInt(dannie[2]);
                    year = Integer.parseInt(dannie[3]);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                    java.util.Date  date = simpleDateFormat.parse(day+" "+month+" "+year);
                    PEOPLE.add(new Person(name, date));
                }else if (dannie.length==5){
                    name = dannie[0]+" "+dannie[1];
                    day = Integer.parseInt(dannie[2]);
                    month = Integer.parseInt(dannie[3]);
                    year = Integer.parseInt(dannie[4]);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                    java.util.Date  date = simpleDateFormat.parse(day+" "+month+" "+year);
                    PEOPLE.add(new Person(name, date));
                }else if (dannie.length==6){
                    name = dannie[0]+" "+dannie[1]+" "+dannie[2];
                    day = Integer.parseInt(dannie[3]);
                    month = Integer.parseInt(dannie[4]);
                    year = Integer.parseInt(dannie[5]);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                    java.util.Date  date = simpleDateFormat.parse(dannie[dannie.length-3] +" " +dannie[dannie.length-2]+" "+ dannie[dannie.length-1]);
                    PEOPLE.add(new Person(name, date));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    }

        /*String fileName="";
        if (args[0].length()>0)
            fileName = args[0];
        BufferedReader bfr = new BufferedReader(new FileReader(fileName));
        String persName = "";
        String dateB = "";
        while(bfr.ready()) {
            String[] str = bfr.readLine().split(" ");
            persName = "";
            dateB =  "";
            for (int i = 0; i < str.length; i++) {
                Pattern p = Pattern.compile("^[a-zA-Z-]+");
                Matcher m = p.matcher(str[i]);
                if (m.matches())
                    persName = persName+str[i]+" ";
                else{
                    if (i+1!=str.length)
                        dateB = dateB + str[i]+".";
                    else
                        dateB = dateB+str[i];
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date birthDate = null;
            birthDate = format.parse(dateB);
            PEOPLE.add(new Person(persName.trim(), birthDate));

        }
        bfr.close();
    }*/
//}
