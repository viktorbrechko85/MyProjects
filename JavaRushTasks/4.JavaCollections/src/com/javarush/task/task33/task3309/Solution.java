package com.javarush.task.task33.task3309;

import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {
        String result = "";
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Object.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(obj, writer);

        //преобразовываем в строку все записанное в StringWriter
        result = writer.toString();
        if (result.indexOf(tagName)>-1){
            String tag = "<"+tagName+">";
            String comm = "<!--" + comment + "-->" + tag;
            result = result.replace(tag,comm);
            tag = "<"+tagName+"/>";
            comm = "<!--" + comment + "-->" + tag;
            result = result.replace(tag,comm);
        }
        return result;
    }

    public static class First{
        public String second;

        public First() {}

        public void setSecond(String second) {
            this.second = second;
        }
    }

    public static void main(String[] args) throws JAXBException {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><first><second>some string</second><second>some string</second><second><![CDATA[need CDATA because of < and >]]></second><second/></first>";
        String tagName = "second";
        String comment = "comments";
        //преобразовываем в строку все записанное в StringWriter

        if (s.indexOf(tagName)>-1){
            String tag = "<"+tagName+">";
            String comm = "<!--" + comment + "-->" + tag;
            s = s.replace(tag,comm);
            tag = "<"+tagName+"/>";
            comm = "<!--" + comment + "-->" + tag;
            s = s.replace(tag,comm);
        }
        System.out.println(s);
    }


}




