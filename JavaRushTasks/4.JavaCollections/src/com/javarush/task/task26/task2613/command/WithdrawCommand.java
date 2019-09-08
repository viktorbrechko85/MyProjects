package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en", Locale.ENGLISH);
    @Override
    public void execute() throws InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        Map<Integer, Integer> result = new HashMap<>();
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator =  CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while(true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                int amount = Integer.parseInt(ConsoleHelper.readString());
                if (!currencyManipulator.isAmountAvailable(amount)){
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                result = currencyManipulator.withdrawAmount(amount);
                for(Map.Entry<Integer, Integer> entry: result.entrySet()){
                    ConsoleHelper.writeMessage("    "+entry.getKey() + " - " + entry.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),amount, currencyCode));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            } catch (InterruptOperationException e) {
                ConsoleHelper.writeMessage("by by");
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            break;
        }
    }
}
