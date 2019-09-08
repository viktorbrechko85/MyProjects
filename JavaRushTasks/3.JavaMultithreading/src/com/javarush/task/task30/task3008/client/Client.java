package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * Created by oper on 28.05.2018.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;
    private static final String IP_ADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private Pattern pattern;

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " connected to chat!");
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " left from chat!");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this){
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException{
            Message message;

            while (!clientConnected) {
                try {
                    message = connection.receive();
                } catch (ClassNotFoundException e) {
                    throw new IOException("Unexpected MessageType");
                }
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else {
                    if (message.getType() == MessageType.NAME_ACCEPTED) {notifyConnectionStatusChanged(true);}
                    else throw new IOException("Unexpected MessageType");}

            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT){
                    processIncomingMessage(message.getData());
                }else
                    if (message.getType() == MessageType.USER_ADDED)
                        {informAboutAddingNewUser(message.getData());}
                    else
                        if (message.getType() == MessageType.USER_REMOVED)
                            {informAboutDeletingNewUser(message.getData());}
                        else throw new IOException("Unexpected MessageType");
            }
        }
        @Override
        public void run(){
            try {
                String addrSrv = getServerAddress();
                int port = getServerPort();
                Socket socket = new Socket(addrSrv, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                wait();
            }
        }catch (InterruptedException e){
            ConsoleHelper.writeMessage("Thread not wait to connerct server");
            System.exit(1);
        }
        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected)  {
                try {
                    String str = ConsoleHelper.readString();
                    if (str.equals("exit")) clientConnected = false;
                    if (shouldSendTextFromConsole())
                        sendTextMessage(str);
                }catch (IOException e){}
            }
        }else{
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }

    }

    protected String getServerAddress() throws IOException{
        String IP = null;
        ConsoleHelper.writeMessage("Enter IP server (if localserver enter 127.0.0.1): ");
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        IP = ConsoleHelper.readString();
        if (!pattern.matcher(IP).matches())
            ConsoleHelper.writeMessage("Incorrect IP addr");
        return IP;
    }

    protected int getServerPort() throws IOException{
        int port = 0;
        ConsoleHelper.writeMessage("Enter Port server: ");
        port = ConsoleHelper.readInt();
        return port;
    }

    protected String getUserName()throws IOException{
        String userName = null;
        ConsoleHelper.writeMessage("Enter User name: ");
        userName = ConsoleHelper.readString();
        return userName;
    }
    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
            clientConnected = true;
        }catch (IOException e){
            ConsoleHelper.writeMessage("Invalid sending message!");
            clientConnected = false;
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

}


