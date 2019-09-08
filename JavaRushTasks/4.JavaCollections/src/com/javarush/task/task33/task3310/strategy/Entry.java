package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public int hashCode(){
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (o instanceof Entry) {
            Entry e = (Entry)o;
            if ((key==e.getKey()) && value.equals(e.getValue()))
                return true;
        }
        return false;
    }
    public String toString(){
        return key + "=" + value;
    }
}
