package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private String currencyCode;
    private static Map<Integer, Integer> denominations = new HashMap<>();


    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int result = 0;
        for(Map.Entry<Integer, Integer> entry:denominations.entrySet()){
            result = result+entry.getKey()*entry.getValue();
        }
        return result;
    }

    public boolean hasMoney(){
        return denominations.size()!=0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount<=getTotalAmount() ? true : false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int summa = expectedAmount;

        Map<Integer, Integer> result = new LinkedHashMap<>();
        Map<Integer, Integer> map = new LinkedHashMap<>();
        denominations.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .forEach(e ->map.put(e.getKey(),e.getValue()));

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int nominal = entry.getKey();
            int count = entry.getValue();
            int countable = 0;
            if (summa>=nominal) {
                countable = summa/nominal;
                if (countable>count) countable = count;
                if(countable<=count){
                    result.put(nominal, countable);
                    summa = summa-nominal*(countable);
                    map.put(nominal,count-countable);
                }
            }
        }
        int totalres = 0;
        for(Map.Entry<Integer, Integer> entry:result.entrySet()){
            totalres = totalres+entry.getKey()*entry.getValue();
        }
        if (totalres!=expectedAmount) {
            throw new NotEnoughMoneyException();
        }
        if (map.containsValue(0)) {
            Map<Integer, Integer> map2 = new LinkedHashMap<>();
            map.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                    .forEach(e ->map2.put(e.getKey(),e.getValue()));
            for(Map.Entry<Integer, Integer> en:map2.entrySet()){
                if (en.getValue()==0) map.remove(en.getKey());
            }
        }
        denominations = map;

        result.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .forEach(e ->result.put(e.getKey(),e.getValue()));
        return result;
    }
}
