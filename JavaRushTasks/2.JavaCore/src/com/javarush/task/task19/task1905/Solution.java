package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }
    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;
        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }
        public String getCountryCode(){
            String country = this.customer.getCountryName();
            String Key = "";
            for (Map.Entry entry : countries.entrySet()) {
                if (entry.getValue().equals(country))
                {
                    Key = entry.getKey().toString();
                }
            }
            return Key;
        };        //example UA
        public String getCompany(){
            return this.customer.getCompanyName();
        };            //example JavaRush Ltd.
        public String getContactFirstName(){
            String[] str = this.contact.getName().split(",");
            return str[1].trim();
        };   //example Ivan
        public String getContactLastName(){
            String[] str = this.contact.getName().split(",");
            return str[0].trim();
        };    //example Ivanov
        public String getDialString(){
            return "callto://" + this.contact.getPhoneNumber().replace("(","").replace(")","").replace("-","");
        };         //example callto://+380501234567
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
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