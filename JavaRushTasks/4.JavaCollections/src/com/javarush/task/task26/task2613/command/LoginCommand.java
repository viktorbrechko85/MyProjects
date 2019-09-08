package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand  implements Command  {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards", Locale.ENGLISH);
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en", Locale.ENGLISH);
    @Override
    public void execute() throws InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        ConsoleHelper.writeMessage(res.getString("before"));
        String creaditCard="";
        while(true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            try {
                creaditCard = ConsoleHelper.readString();
                String pinCard = ConsoleHelper.readString();
                long numberCart = Long.parseLong(creaditCard);
                int pinCardint = Integer.parseInt(pinCard);
                if (validCreditCards.containsKey(creaditCard)){
                    if (validCreditCards.getString(creaditCard).equals(pinCard)){
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),creaditCard));
                        break;
                    }
                    else{
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),creaditCard));
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                        ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                        continue;
                    }
                }
                else{
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),creaditCard));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
            } catch (InterruptOperationException e) {
                throw new InterruptOperationException ();
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),creaditCard));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }catch (Exception e) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),creaditCard));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

        }
    }
}
