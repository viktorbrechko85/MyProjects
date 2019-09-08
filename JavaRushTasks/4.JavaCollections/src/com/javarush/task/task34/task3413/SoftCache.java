package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        if (!cacheMap.containsKey(key))
            return null;

        return softReference.get();
        //напишите тут ваш код
    }

    public AnyObject put(Long key, AnyObject value) {
        if (!cacheMap.containsKey(key))
            return null;
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        AnyObject value1 = softReference.get();
        softReference.clear();
        return value1;
        //напишите тут ваш код
    }

    public AnyObject remove(Long key) {
        if (!cacheMap.containsKey(key))
            return null;
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        AnyObject value1 = softReference.get();
        softReference.clear();
        return value1;
        //напишите тут ваш код
    }
}