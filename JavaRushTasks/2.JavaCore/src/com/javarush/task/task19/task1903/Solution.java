package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }
    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;
        IncomeDataAdapter(IncomeData data){
            this.data = data;
        }
        public String getCompanyName(){
            return data.getCompany();
        };        //example JavaRush Ltd.

        public String getCountryName(){
           return countries.get(data.getCountryCode());
        };

        public String getName(){
            return data.getContactLastName() + ", " + data.getContactFirstName();
        };               //example Ivanov, Ivan

        public String getPhoneNumber(){
            String result = "+";
            StringBuilder sb = new StringBuilder(result);
            sb.append(data.getCountryPhoneCode());
            sb.append("(");
            String number = String.valueOf(data.getPhoneNumber());
            // дополняем при необходимости до 10 символов
            if (number.length() < 10) {
                StringBuilder localSB = new StringBuilder("");
                int spaceCount = 10 - number.length();
                for (int i = 1; i <= spaceCount; i++) {
                    localSB.append("0");
                }
                localSB.append(number);
                number = localSB.toString();
            }
            sb.append(number.substring(0,3));
            sb.append(")");
            sb.append(number.substring(3,6));
            sb.append("-");
            sb.append(number.substring(6,8));
            sb.append("-");
            sb.append(number.substring(8,10));
            result = sb.toString();
            return result;
        };
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}