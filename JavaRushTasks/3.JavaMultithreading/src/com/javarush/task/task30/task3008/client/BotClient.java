package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MarKiz on 29.05.2018.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
        @Override
        protected void processIncomingMessage(String message){
            if (message != null) {
                ConsoleHelper.writeMessage(message);
                SimpleDateFormat dateFormat = null;
                if (message.contains(": ")) {
                    String[] massiv = message.split(": ");
                    if (massiv.length == 2 && massiv[1] != null) {
                        String usrname = massiv[0];
                        String text = massiv[1];
                        switch (text) {
                            case "дата":
                                dateFormat = new SimpleDateFormat("d.MM.YYYY");
                                break;
                            case "день":
                                dateFormat = new SimpleDateFormat("d");
                                break;
                            case "месяц":
                                dateFormat = new SimpleDateFormat("MMMM");
                                break;
                            case "год":
                                dateFormat = new SimpleDateFormat("YYYY");
                                break;
                            case "время":
                                dateFormat = new SimpleDateFormat("H:mm:ss");
                                break;
                            case "час":
                                dateFormat = new SimpleDateFormat("H");
                                break;
                            case "минуты":
                                dateFormat = new SimpleDateFormat("m");
                                break;
                            case "секунды":
                                dateFormat = new SimpleDateFormat("s");
                                break;
                        }
                        if (dateFormat != null) {
                            sendTextMessage(String.format("Информация для %s: %s", usrname, dateFormat.format(Calendar.getInstance().getTime())));
                        }
                    }
                }


            }
        }
    }
    @Override
    protected SocketThread getSocketThread(){
        return new BotSocketThread();
    }
    @Override
    protected boolean shouldSendTextFromConsole(){
        return false;
    }
    @Override
    protected String getUserName()throws IOException {
        double X = Math.random()*100;
        String userName = "date_bot_" + (int)X;
        return userName;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
