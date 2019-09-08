package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;


import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en", Locale.ENGLISH);
    @Override
    public void execute() throws  InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            ConsoleHelper.writeMessage(res.getString("before"));
            String currencyCode = ConsoleHelper.askCurrencyCode();
            String[] nomcount = ConsoleHelper.getValidTwoDigits(currencyCode);

            CurrencyManipulator currencyManipulator =  CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            currencyManipulator.addAmount(Integer.parseInt(nomcount[0]), Integer.parseInt(nomcount[1]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(nomcount[0]), nomcount[1]));
        }  catch (NumberFormatException e) {
             ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
