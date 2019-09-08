package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by MarKiz on 27.05.2018.
 */
public class Message implements Serializable {
    private final MessageType type;
    private final String data;

    public  MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public Message(MessageType type) throws IOException {
        this.type = type;
        this.data = null;
    }

    public Message(MessageType type, String data) throws IOException {
        this.type = type;
        this.data = data;
    }
}
