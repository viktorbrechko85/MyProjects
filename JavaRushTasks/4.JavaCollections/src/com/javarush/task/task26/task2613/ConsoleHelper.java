package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en", Locale.ENGLISH);
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = null;
        try {
            line = bis.readLine();
            if (line.toLowerCase().equals("exit"))
                throw new InterruptOperationException();
        } catch (IOException e) {}

        return line;
    }

    public static String askCurrencyCode() throws  InterruptOperationException {
        String code = null;
            writeMessage(res.getString("choose.currency.code"));
            while (true) {
                code = readString();
                if (code.toLowerCase().equals("exit")) throw new InterruptOperationException();
                if (code.length() == 3)
                    break;
                else
                    writeMessage(res.getString("the.end"));

            }
            return code.toUpperCase();

    }

    public static Operation askOperation() throws InterruptOperationException {
        while(true){
            try {
                writeMessage(res.getString("choose.operation"));
                writeMessage(   "1 - " + res.getString("operation.INFO")+
                                ", 2 - " + res.getString("operation.DEPOSIT") +
                                ", 3 - " + res.getString("operation.WITHDRAW") +
                                ", 4 - " + res.getString("operation.EXIT"));
                String code = readString();
                int i = Integer.parseInt(code);
                return Operation.getAllowableOperationByOrdinal(i);
        } catch (IllegalArgumentException e) {
            writeMessage(res.getString("the.end"));
            continue;
        }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String line = "";
        String[] result;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        int nominal = 0;
        int count = 0;
        while (true){
            try {
                line=bis.readLine();
                result = line.split(" ");
                nominal = Integer.parseInt(result[0]);
                count = Integer.parseInt(result[1]);
            } catch (Exception e) {
                writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
                continue;
            }
            if (nominal <= 0 || count <= 0) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return result;
    }

    public static void printExitMessage(){
        writeMessage("By By");
    }


}
