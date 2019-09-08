package com.javarush.task.task18.task1815;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements  ATableInterface{
        String newHeaderText;
        ATableInterface ATableInterface;
        public TableInterfaceWrapper(ATableInterface ATableInterface){
            this.ATableInterface = ATableInterface;
        }
        @Override
        public void setModel (List rows){
            System.out.println(rows.size());
            ATableInterface.setModel(rows);
        }
        @Override
        public String getHeaderText(){
            return ATableInterface.getHeaderText().toUpperCase();
        }
        @Override
        public void setHeaderText(String newHeaderText){
            ATableInterface.setHeaderText(newHeaderText);
        }
    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}