package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;

    }

    /*Интерфейс QLQuery*/

    @Override
    public Set<Object> execute(String query) {
        Set<Object> res = new HashSet<>();
        if (query == null || query.isEmpty()) return res;
        Pattern p = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher m = p.matcher(query);
        String field1 = null;
        String field2 = null;
        String value1 = null;
        Date dateFrom = null;
        Date dateTo = null;
        if (m.find()) {
            field1 = m.group(1);
            field2 = m.group(3);
            value1 = m.group(4);
            String d1 = m.group(6);
            String d2 = m.group(7);
            try {
                dateFrom = getDateFromStr(d1);
            } catch (Exception e) {
                dateFrom = null;
            }
            try {
                dateTo = getDateFromStr(d2);
            } catch (Exception e) {
                dateTo = null;
            }
            switch (field1) {
                case "ip":{
                    res.addAll(getAllIPs(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "user":{
                    res.addAll(getAllUsers(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "date":{
                    res.addAll(getAllDates(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "event":{
                    res.addAll(getAllEventsQuery(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "status":{
                    res.addAll(getAllStatusQuery(field2, value1, dateFrom, dateTo));
                    break;
                }

            }
        }
        return res;
    }
    private Predicate<List<String>> getFilter(String field, String value){
        Predicate<List<String>> filter = null;
        if (value==null)
            return s->(!s.get(0).equals(""));
        if (getArrIndFromSet(field)==3)
            filter = s->getSimpleEventName(s.get(getArrIndFromSet(field))).equals(value);
        else
            filter = s->s.get(getArrIndFromSet(field)).equals(value);

        return filter;
    }

    private Set<String> getCollect(String field, String value, Date after, Date before, int index) {
        return getListStream(field, value, after, before)
                .map(list -> list.get(index))
                .collect(Collectors.toSet());
    }

    private Stream<List<String>> getListStream(String field, String value, Date after, Date before) {
        return getListStream2(after, before)
                .filter(getFilter(field, value));
    }

    private Stream<List<String>> getListStream2(Date after, Date before) {
        return getStreamForObjects().stream()
                .map(line -> Arrays.asList(line.split("\\t+")))
                .filter(s -> isCompatibleDate(getDateFromStr(s.get(2)).getTime(), after, before));
    }

    private Set<String> getAllIPs(String field, String value, Date after, Date before) {
        Set<String> IP = getCollect(field, value, after, before, 0);
        return IP;
    }



    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> IP = getCollect(field, value, after, before, 1);
        return IP;
    }

    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> IP = getListStream(field, value, after, before)
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }



    private Set<Event> getAllEventsQuery(String field, String value, Date after, Date before) {
        Set<Event> IP = getListStream(field, value, after, before)
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    private Set<Status> getAllStatusQuery(String field, String value, Date after, Date before) {
        Set<Status> IP = getListStream(field, value, after, before)
                .map(list -> Status.valueOf(list.get(4)))
                .collect(Collectors.toSet());
        return IP;
    }

    private int getArrIndFromSet(String field){
        switch (field){
            case "ip": return 0;
            case "user": return 1;
            case "date": return 2;
            case "event": return 3;
            case "status": return 4;
        }
        return 0;
    }

    /*Интерфейс EventQuery*/
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .map(list -> getSimpleEventName(list.get(3)))
                .collect(Collectors.toSet());
        return IP.size();
    }



    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> IP = getListStream2(after, before)
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> IP = getListStream2(after, before)
                .filter(s->s.get(0).contains(ip))
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> IP = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> IP = getListStream2(after, before)
                .filter(s->s.get(4).contains("FAILED"))
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> IP = getListStream2(after, before)
                .filter(s->s.get(4).contains("ERROR"))
                .map(list -> Event.valueOf(getSimpleEventName(list.get(3))))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        long c = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.SOLVE_TASK.toString() + " " + task))
                .count();
        return (int)c;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int c = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.DONE_TASK.toString() + " " + task))
                .map(e -> 1)
                .reduce(0, Integer::sum);

        return c;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.SOLVE_TASK.toString()))
                .map(list -> getNumberTaskEventName(list.get(3)))
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum))
                );
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.DONE_TASK.toString()))
                .map(list -> getNumberTaskEventName(list.get(3)))
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum))
                );
        return map;
    }


    /*Интерфейс DateQuery*/

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(event.toString()))
                .filter(s->s.get(1).contains(user))
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> IP = getListStream2(after, before)
                .filter(s->s.get(4).contains(Status.FAILED.toString()))
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> IP = getListStream2(after, before)
                .filter(s->s.get(4).contains(Status.ERROR.toString()))
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date dateFirst = null;
        if (user==null || user.equals(""))
            return dateFirst;

        dateFirst = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.LOGIN.toString()))
                .filter(s->s.get(1).contains(user))
                .map(list -> getDateFromStr(list.get(2)))
                .sorted()
                .findFirst()
                .orElse(null);
        return dateFirst;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date dateFirst = null;
        if (user==null || user.equals(""))
            return dateFirst;

        dateFirst = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .filter(s->s.get(3).contains(Event.SOLVE_TASK.toString() + " " + task))
                .map(list -> getDateFromStr(list.get(2)))
                .sorted()
                .findFirst()
                .orElse(null);


        return dateFirst;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date dateFirst = null;
        if (user==null || user.equals(""))
            return dateFirst;

        dateFirst = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .filter(s->s.get(3).contains(Event.DONE_TASK.toString() + " " + task))
                .map(list -> getDateFromStr(list.get(2)))
                .sorted()
                .findFirst()
                .orElse(null);
        return dateFirst;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> IP = new HashSet<>();
        if (user==null || user.equals(""))
            return IP;
        IP = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .filter(s->s.get(3).contains(Event.WRITE_MESSAGE.toString()))
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> IP = new HashSet<>();
        if (user==null || user.equals(""))
            return IP;
        IP = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .filter(s->s.get(3).contains(Event.DOWNLOAD_PLUGIN.toString()))
                .map(list -> getDateFromStr(list.get(2)))
                .collect(Collectors.toSet());
        return IP;
    }

    /*Интерфейс UserQuery*/
    @Override
    public Set<String> getAllUsers() {
        Set<String> IP = getStreamForObjects().stream()
                .map(line -> Arrays.asList(line.split("\\t+")))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .map(list -> getSimpleEventName(list.get(3)))
                .collect(Collectors.toSet());
        return IP.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(0).contains(ip))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.LOGIN.toString()))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }


    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.DOWNLOAD_PLUGIN.toString()))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.WRITE_MESSAGE.toString()))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.SOLVE_TASK.toString()))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.SOLVE_TASK.toString()+" " + task))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.DONE_TASK.toString()))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(Event.DONE_TASK.toString()+" " + task))
                .map(list -> list.get(1))
                .collect(Collectors.toSet());
        return IP;
    }
    private String getSimpleEventName(String event){
        return event.indexOf(" ")>0 ? event.substring(0,event.indexOf(" ")):event;
    }

    private int getNumberTaskEventName(String event){
        return event.indexOf(" ")>0 ? Integer.parseInt(event.substring(event.indexOf(" ")+1)):0;
    }

    /*Интерфейс IPQuery*/
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> IP = getUniqueIPs(after, before);
        return IP.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .map(list -> list.get(0))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(1).contains(user))
                .map(list -> list.get(0))
                .collect(Collectors.toSet());
        return IP;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(3).contains(event.toString()))
                .map(list -> list.get(0))
                .collect(Collectors.toSet());
        return IP;
    }


    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IP = getListStream2(after, before)
                .filter(s->s.get(4).contains(status.toString()))
                .map(list -> list.get(0))
                .collect(Collectors.toSet());
        return IP;
    }

    public Set<String> getIPsForObject(String user, Event event, Status status, Date after, Date before){
        Set<String> IP = new HashSet<>();

            try {
                List<String> listFiles = getFileTreeStream(logDir.toString());
                for(String filename:listFiles){
                    List<String> listAllLine = Files.readAllLines(Paths.get(filename));
                    for(String line:listAllLine){
                        String obj = "";
                        boolean checkuser = false;
                        boolean checkstatus = false;
                        boolean checkevent = false;
                        if (user==null && event==null && status == null)
                        {
                            checkuser = true;
                            checkstatus = true;
                            checkevent = true;
                        }else if (user!=null) {
                            obj = getPartLineForInd(line, 1);
                            checkuser = obj.equals(user);
                        }
                        else if (event!=null) {
                            if (event.equals(Event.DONE_TASK) || event.equals(Event.SOLVE_TASK)) {
                                String[] sh = getPartLineForInd(line, 3).split(" ");
                                obj = sh[0];
                            }
                            else obj = getPartLineForInd(line, 3);

                            checkevent = obj.equals(event.toString());
                        }
                        else if (status != null) {
                            obj = getPartLineForInd(line, 4);
                            checkstatus = obj.equals(status.toString());
                        }
                        Date dateLog = getDateFromStr(getPartLineForInd(line,2));
                        long lineDateTime = getDateFromStr(getPartLineForInd(line,2)).getTime();
                        if ((isCompatibleDate(lineDateTime, after, before)) && (checkuser || checkevent || checkstatus)){
                              IP.add(getPartLineForInd(line,0));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return IP;
    }

    private Set<String> getStreamForObjects(){
        Set<String> result = new HashSet<>();
        try {
            List<String> listFiles = getFileTreeStream(logDir.toString());
            for(String falename:listFiles) {
                result.addAll(Files.lines(Paths.get(falename), StandardCharsets.UTF_8)
                        .collect(Collectors.toSet()));
        }
        return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime < before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime > after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime > after.getTime() && lineDateTime < before.getTime()) {
                return true;
            }
        }
        return false;
    }

    private Date getDateFromStr(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    private String getPartLineForInd(String str, int index){
        String[] string = str.split("\\t");
        return string[index];
    }

    private  List<String> getFileTree(String root) throws IOException {
        List <String> list = new ArrayList<>();
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getAbsolutePath().indexOf(".log")>0) {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    private List<String> getFileTreeStream(String root){
        try (Stream<Path> walk = Files.walk(Paths.get(root))) {
            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".log"))
                    .collect(Collectors.toList());
        return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}