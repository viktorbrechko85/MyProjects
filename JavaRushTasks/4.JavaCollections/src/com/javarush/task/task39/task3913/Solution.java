package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String query = "get ip for user = \"Amigo\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";//br.readLine();
        String query = "get ip for user = \"Amigo\"";//br.readLine();
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser.execute(query));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
        System.out.println("User Amigo Events = " + logParser.getNumberOfSuccessfulAttemptToSolveTask(18,null, null));
        System.out.println("User Amigo = " + logParser.getIPsForUser("Amigo", new Date(), null));
        System.out.println("User Amigo(getDateWhenUserLoggedFirstTime) = " + logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null).toString());
        System.out.println(Event.WRITE_MESSAGE.toString() + " = " + logParser.getIPsForEvent(Event.WRITE_MESSAGE, new Date(), null));
        System.out.println(Event.SOLVE_TASK.toString() + " = " + logParser.getIPsForEvent(Event.SOLVE_TASK, new Date(), null));
        System.out.println(Status.OK.toString() + " = " + logParser.getIPsForStatus(Status.OK, new Date(), null));
        System.out.println(Status.ERROR.toString() + " = " + logParser.getIPsForStatus(Status.ERROR, new Date(), null));
        System.out.println(Status.FAILED.toString() + " = " + logParser.getIPsForStatus(Status.FAILED, new Date(), null));
    }
}