package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader;

        result = mapper.readValue(new FileReader(new File(fileName)), clazz);
        return result;
    }

    public static void main(String[] args) {

    }
}
