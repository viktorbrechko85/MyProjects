package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.recurse("tan(2025 ^ 0.5)", 0); //expected output 0.5 6

        solution.recurse("1+3+4*5/6-(25*4+(50/10)) + sin(2*(-5+1.5*4)+28)", 0);

        solution.recurse("sin(2*(-5+1.5*4)+28)", 0);

        solution.recurse("1+3+4*5/6-(25*4)+ sin(2*(-5+1.5*4)+28) + tan(2025 ^ 0.5) + 30 - 25*8 - 50/2 + 3", 0);

        solution.recurse("1+3+4*5/6-(25*4)+ sin(2*(-5+1.5*4)+28) + tan(2025 ^ 0.5) - tan(4025 ^ 0.5)", 0);

    }

    public void recurse(final String expression, int countOperation) {
        String expr=expression;
        String temp=null;

        //блок 0. Обработка строки при первом вхождении
        if (countOperation==0){
            expr = expression.replaceAll(" ","");
            countOperation=count(expr);
            if(expr.charAt(0)=='-'){expr='0'+expr;}
            expr=expr.replaceAll("\\(-","(0-");
        }
        // блок 1. Проверка на окончание работы(является ли строка числом)
        if(isNum(expr)){
            NumberFormat nf = new DecimalFormat("#.##");
            Double d = Double.parseDouble(expression);
            //System.out.println(String.format("%s %d",nf.format(d), countOperation));
            System.out.println(nf.format(d) + " " + countOperation);
            //System.out.println(roundDouble(Double.parseDouble(expr),2)+" "+countOperation);
            return;
        }
        // блок 2. если строка - не число
        else if(expr.contains("(")){        //проверяем на наличие "(" и ищем "самую глубокую"
            int n = expr.lastIndexOf("(");
            temp = expr.substring(n+1, expr.indexOf(")",n));       //выражение внутри скобок
            String trig = "trig";
            if(n>2){trig=expr.substring(n-3,n);}        //выделили возможную тригонометрию
            if(isNum(temp)){                            // проверка на число
                double t=Double.parseDouble(temp);
                switch (trig){                              // переключатель на тригонометрию и обычный оператор
                    case "sin":
                        t=roundDouble(Math.sin(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    case "cos":
                        t=roundDouble(Math.cos(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    case "tan":
                        t=roundDouble(Math.tan(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    default:                    // формируем строку, убрав скобки
                        temp = expr.substring(0,n).concat(Double.toString(t));
                        expr=temp.concat(expr.substring(expr.indexOf(')',n)+1));
                }
            }
            else {                //внутри скобок математическое выражение, работаем с ним
                temp = mathStr(temp);
                expr=expr.substring(0,n+1).concat(temp).concat(expr.substring(expr.indexOf(")",n)));      //сформировали строку с модифицированным выражением в скобках
                //countOperation++;

            }
        }
        else {                          //скобок нет, работаем с матем.выражением
            expr=mathStr(expr);
            //countOperation++;
        }

        recurse(expr,countOperation);
    }
    // вспомогательные методы
    public boolean isNum(String str){
        if(str==null)return false;
        return str.matches("^[-+]?\\d+(\\.\\d+)?");};   //проверка на число

    public double roundDouble(Double d,int r){      //округление до заданной цифры после запятой
        int dec = (int)Math.pow(10,r);
        Double x = (double)Math.round(d*dec);
        return x/dec;}

    public String trigStr(String expr,int n,double t){      //формирование новой строки для случая геометрии
        String result = "";
        if(n>3){result=expr.substring(0,n-3);}
        result = result.concat(Double.toString(t));
        if(expr.indexOf(")",n)<expr.length()-1){
            result=result.concat(expr.substring(expr.indexOf(")",n)+1));}
        return result;}

    public String mathStr(String temp){
        String result=null;
        String sign;
        double[] num={0,0,0,0};
        int st=0;
        int fin=0;
        if(temp.contains("^")){ sign = "^";}
        else if(temp.contains("*")){
            if(temp.substring(0,temp.indexOf("*")).contains("/")){sign="/";}
            else{sign="*";}
        }
        else if(temp.contains("/")){sign="/";}
        else if (temp.contains("+")){
            if(!temp.substring(1,temp.indexOf("+")).contains("-")){sign="+";}
            else{sign="-";}}
        else {sign="-";}
        switch (sign){
            case "^":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(Math.pow(num[0],num[1]),4));
                break;
            case "/":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]/num[1],4));
                break;
            case "*":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]*num[1],4));
                break;
            case "+":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]+num[1],4));
                break;
            case "-":
                if(temp.indexOf(sign)!=0){
                    num=operandNum(temp,temp.indexOf(sign));}
                else {num=operandNum(temp,temp.indexOf(sign,1));}
                result=String.valueOf(roundDouble(num[0]-num[1],4));
                break;
        }
        st=(int)num[2];
        fin=(int)num[3];
        if(st!=0){result=temp.substring(0,st).concat(result);}
        if(fin !=(temp.length()-1)){result=result.concat(temp.substring(fin+1));}
        return result;}

    public int count(String expr){
        int result=0;
        String[] signt = {"sin","cos","tan"};
        for (int i = 0; i <signt.length ; i++) {
            if(expr.contains(signt[i])){String []f = expr.split(signt[i]);
                result+=(f.length-1);}
        }
        char [] sign = {'^','*','/','+','-'};
        char[] str = expr.toCharArray();
        for (int i = 0; i <sign.length; i++) {
            for (int j = 0; j < str.length; j++) {
                if(str[j]==sign[i]){
                    result++;
                }
            }
        }
        return result;
    }
    public double[] operandNum(String temp,int signPosition){
        double[] numOperation =new double[4];
        String num1Str = "";
        String num2Str = "";
        int st = signPosition-1;
        int fin = signPosition+1;
        while (true){
            if((st>-1)&&(Character.isDigit(temp.charAt(st))||(temp.charAt(st)=='.'))){
                num1Str=String.valueOf(temp.charAt(st)).concat(num1Str);
                st--;
            }
            else break;
        }
        st++;
        if((st !=0)&&(temp.charAt(st-1)=='-')){
            if((st==1)||((!Character.isDigit(temp.charAt(st-2)))&&(temp.charAt(st-2)!='(')&&(temp.charAt(st-2)!=')'))){
                num1Str="-".concat(num1Str);
                st--;}}
        while (fin<temp.length()){
            if((temp.charAt(fin)=='-')&&(num2Str.length()==0)){
                num2Str="-";
                fin++;
            }
            if(Character.isDigit(temp.charAt(fin))||(temp.charAt(fin)=='.')){
                num2Str=num2Str.concat(String.valueOf(temp.charAt(fin)));
                fin++;
            }
            else break;
        }
        fin--;
        numOperation[0] = Double.parseDouble(num1Str);
        numOperation[1] = Double.parseDouble(num2Str);
        numOperation[2] = st;
        numOperation[3] = fin;

        return numOperation;
    }




/*
    public void recurse(final String expression, int countOperation) {
        Locale.setDefault(Locale.ENGLISH);
        if (countOperation > 0){
            NumberFormat nf = new DecimalFormat("#.##");
            Double d = Double.parseDouble(expression);
            //System.out.println(String.format("%s %d",nf.format(d), countOperation));
            System.out.println(nf.format(d) + " " + countOperation);
        }
        else {
            List<String> out = new ArrayList<>();
            Deque<String> stackPostfix = new ArrayDeque<>();
            Deque<Double> stack = new ArrayDeque<>();

            StringTokenizer tokenizer = new StringTokenizer(expression.replace(" ", "").toLowerCase(), delimiters(), true);
            String prev = "";
            String curr = "";

            while (tokenizer.hasMoreTokens()) {
                curr = tokenizer.nextToken();

                if (!tokenizer.hasMoreTokens() && isOperator(curr)){
                    System.out.println("Некорректное выражение.");
                    break;
                }

                if (isFunction(curr)) stackPostfix.push(curr);
                else if (isDelimiter(curr)) {
                    if (curr.equals("(")) stackPostfix.push(curr);
                    else if (curr.equals(")")) {
                        while (!stackPostfix.peek().equals("(")) {
                            out.add(stackPostfix.pop());
                            if (stackPostfix.isEmpty()) {
                                System.out.println("Скобки не согласованы.");
                            }
                        }
                        stackPostfix.pop();
                        if (!stackPostfix.isEmpty() && isFunction(stackPostfix.peek())) {
                            out.add(stackPostfix.pop());
                        }
                    }
                    else {
                        if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                            curr = "u-";
                        }
                        else {
                            while (!stackPostfix.isEmpty() && (priority(curr) <= priority(stackPostfix.peek()))){
                                out.add(stackPostfix.pop());
                            }
                        }
                        stackPostfix.push(curr);
                    }
                }

                else {
                    out.add(curr);
                }
                prev = curr;
            }

            while (!stackPostfix.isEmpty()) {
                if (isOperator(stackPostfix.peek())) out.add(stackPostfix.pop());
                else {
                    System.out.println("Скобки не согласованы.");
                    break;
                }
            }
            for (String x: out){
                switch (x) {
                    case "sin":
                        stack.push(Math.sin(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "cos":
                        stack.push(Math.cos(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "tan":
                        stack.push(Math.tan(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "^": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(Math.pow(a, b));
                        countOperation++;
                        break;
                    }
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        countOperation++;
                        break;
                    case "-": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(a - b);
                        countOperation++;
                        break;
                    }
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        countOperation++;
                        break;
                    case "/": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(a / b);
                        countOperation++;
                        break;
                    }
                    case "u-":
                        stack.push(-stack.pop());
                        countOperation++;
                        break;
                    default:
                        stack.push(Double.valueOf(x));
                        break;
                }
            }

            recurse(stack.pop().toString(), countOperation);
        }
    }
*/
   /* public void recurse23(final String expression, int countOperation) {
        Locale.setDefault(Locale.ENGLISH);
        String sb = expression.replaceAll("\\s+","");

        Double dRes = 0.0;
        Matcher m = null;
        Deque<String> stackBr = new ArrayDeque<>();
        int countOper = getCountOperation(expression);

        if (countOperation==-1)
            System.out.println(String.format("%s %d",expression, countOperation));
        else if (countOperation > 0){
            System.out.println(String.format("%s %d",expression, countOperation));
        }else
        if (countOper == 0) {
            System.out.println("Неверное выражение!");
        }
        else {

            while (!isNumeric(sb)) {
                int brOpen = 0;
                int brClose = 0;


                if (sb.indexOf("(") > 0) {
                    if (sb.indexOf(")") > 0) {
                        int indLIstBr = sb.indexOf(")");
                        for (int i = sb.indexOf("("); i <= sb.length(); i++) {
                            if (sb.charAt(i) == '(') brOpen++;
                            if (sb.charAt(i) == ')') brClose++;
                            if (brOpen == brClose){
                                indLIstBr = i;
                                break;
                            }
                        }
                        String help = "";
                        if (brOpen == brClose) {
                            help = sb.substring(sb.indexOf("("), indLIstBr + 1);
                        }

                        stackBr = getDeqBracket(help);
                        String oper = "";

                        while (stackBr.size() > 0) {
                            String exp = stackBr.poll();
                            if (!oper.equals("")) {
                                exp = exp.replace(oper, dRes.toString());
                            }
                            dRes = getResOper(exp);
                            oper = "(" + exp + ")";
                            String s = sb.substring(sb.indexOf("(") - 3, sb.indexOf("("));
                            if (!isFunction2(s)) {
                                sb = sb.replace(oper, dRes.toString());
                            } else if (stackBr.size() == 0) {
                                sb = sb.replace(oper, "(" + dRes.toString() + ")");
                                s = sb.substring(sb.indexOf("(") - 3, sb.indexOf(")") + 1);
                                dRes = getResOper(s);
                                sb = sb.replace(s, dRes.toString());
                            } else {
                                sb = sb.replace(oper, dRes.toString());
                            }
                        }
                    }
                } else {
                    dRes = getResOper(sb);
                    sb = sb.replace(sb, dRes.toString());
                }
            }
            recurse23(new DecimalFormat("#.##").format(dRes), countOper);
        }

    }
*/
    /*private Deque<String> getDeqBracket(String expr){
        Deque<String> stackBr = new ArrayDeque<>();
        StringBuilder sh = new StringBuilder(expr);
        while(sh.indexOf("(")>=0){
            stackBr.push(sh.substring(sh.indexOf("(")+1,sh.lastIndexOf(")")));
            sh.delete(0,sh.indexOf("(")+1);
            sh.delete(sh.lastIndexOf(")"),sh.lastIndexOf(")")+1);
        }
        return stackBr;
    }

    public Double getResOper(String expr){
        Double result = 0.0;
        char[] signs = {'^', '/', '*', '-', '+'};
        Matcher m = null;
        //StringBuilder sb = new StringBuilder(expr.trim());
        String sb = expr.replaceAll("\\s+","");
        int indH = 0;
        boolean flagFunc = false;

        if (!isFunction2(sb)){
            sb = sb.replace("(","").replace(")","");
        }else flagFunc = true;
        Double r = 0.0;
        while(!isNumeric(sb)) {
            if (flagFunc){
                switch(sb.substring(0,3)){
                    case "sin":{
                        Double d = Double.parseDouble(sb.substring(4,sb.length()-1));
                        r = Math.sin(Math.toRadians(d));
                        break;
                    }
                    case "cos":{
                        Double d = Double.parseDouble(sb.substring(4,sb.length()-1));
                        r = Math.cos(Math.toRadians(d));
                        break;
                    }
                    case "tan":{
                        Double d = Double.parseDouble(sb.substring(4,sb.length()-1));
                        r = Math.tan(Math.toRadians(d));
                        break;
                    }
                }
                sb = sb.replace(sb, r.toString());
            }else {
                for (char c : signs) {
                    String pattern = String.format("-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*", c);
                    Pattern p = Pattern.compile(pattern);
                    m = p.matcher(sb);
                    while (m.find()) {
                        String h = m.group();
                        indH = sb.indexOf(h);
                        String[] help = h.split("\\" + c);
                        while (help[0].equals("")){
                            help[0] = "-"+help[1];
                            help[1] = help[2];
                        }

                        switch (c) {
                            case '^': {
                                r = Math.pow(Double.parseDouble(help[0]), Double.parseDouble(help[1]));
                                break;
                            }
                            case '/': {
                                r = Double.parseDouble(help[0]) / Double.parseDouble(help[1]);
                                break;
                            }

                            case '*': {
                                r = Double.parseDouble(help[0]) * Double.parseDouble(help[1]);
                                break;
                            }
                            case '+': {
                                r = Double.parseDouble(help[0]) + Double.parseDouble(help[1]);
                                break;
                            }
                            case '-': {
                                r = Double.parseDouble(help[0]) - Double.parseDouble(help[1]);
                                break;
                            }
                        }
                        sb = sb.replace(h, r.toString());
                        sb = sb.replace("+-","-");
                    }
                }
            }
        }
        result = Double.parseDouble(sb.toString());
        return result;
    }
*/
   /* private static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public int getCountOperation(String expression){
        int bracketOpen = 0;
        int bracketClose = 0;
        int countOperation = 0;
        StringBuilder sb = new StringBuilder(expression);
        String pattern = "sin|cos|tan|[\\(\\*\\/\\^%\\+\\-)]";
        while(sb.length()>0){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(sb);
            if (m.find()){
                if (m.group().equals("("))
                    bracketOpen++;
                else if (m.group().equals(")"))
                    bracketClose++;
                else countOperation++;
                sb.delete(0,sb.indexOf(m.group())+1);
            }
            else sb.delete(0,sb.length());
        }
        if (bracketOpen!=bracketClose)
            countOperation=0;
        return countOperation;
    }
    private static boolean isFunction2(String token) {
        return token.contains("sin") || token.contains("cos") || token.contains("tan");
    }
*/
    /*public void recurse233(final String expression, int countOperation) {
        //implement
        //implement
        Locale.setDefault(Locale.ENGLISH);
        if (countOperation > 0){
            System.out.println(String.format("%s %d",expression, countOperation));
        }
        else {
            List<String> out = new ArrayList<>();
            Deque<String> stackPostfix = new ArrayDeque<>();
            Deque<Double> stack = new ArrayDeque<>();

            StringTokenizer tokenizer = new StringTokenizer(expression.replace(" ", "").toLowerCase(), delimiters(), true);
            String prev = "";
            String curr = "";

            while (tokenizer.hasMoreTokens()) {
                curr = tokenizer.nextToken();

                if (!tokenizer.hasMoreTokens() && isOperator(curr)){
                    System.out.println("Некорректное выражение.");
                    break;
                }

                if (isFunction(curr)) stackPostfix.push(curr);
                else if (isDelimiter(curr)) {
                    if (curr.equals("(")) stackPostfix.push(curr);
                    else if (curr.equals(")")) {
                        while (!stackPostfix.peek().equals("(")) {
                            out.add(stackPostfix.pop());
                            if (stackPostfix.isEmpty()) {
                                System.out.println("Скобки не согласованы.");
                            }
                        }
                        stackPostfix.pop();
                        if (!stackPostfix.isEmpty() && isFunction(stackPostfix.peek())) {
                            out.add(stackPostfix.pop());
                        }
                    }
                    else {
                        if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                            curr = "u-";
                        }
                        else {
                            while (!stackPostfix.isEmpty() && (priority(curr) <= priority(stackPostfix.peek()))){
                                out.add(stackPostfix.pop());
                            }
                        }
                        stackPostfix.push(curr);
                    }
                }

                else {
                    out.add(curr);
                }
                prev = curr;
            }

            while (!stackPostfix.isEmpty()) {
                if (isOperator(stackPostfix.peek())) out.add(stackPostfix.pop());
                else {
                    System.out.println("Скобки не согласованы.");
                    break;
                }
            }
            for (String x: out){
                switch (x) {
                    case "sin":
                        stack.push(Math.sin(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "cos":
                        stack.push(Math.cos(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "tan":
                        stack.push(Math.tan(Math.toRadians(stack.pop())));
                        countOperation++;
                        break;
                    case "^": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(Math.pow(a, b));
                        countOperation++;
                        break;
                    }
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        countOperation++;
                        break;
                    case "-": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(a - b);
                        countOperation++;
                        break;
                    }
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        countOperation++;
                        break;
                    case "/": {
                        Double b = stack.pop(), a = stack.pop();
                        stack.push(a / b);
                        countOperation++;
                        break;
                    }
                    case "u-":
                        stack.push(-stack.pop());
                        countOperation++;
                        break;
                    default:
                        stack.push(Double.valueOf(x));
                        break;
                }
            }

            recurse233(new DecimalFormat("#.##").format(stack.pop()), countOperation);
        }
    }
*/
    public Solution() {
        //don't delete
    }

  //private static String operators() {
    //  return "+-*/^";
   //}

    /*private static String delimiters() {
        return "()" + operators();
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) return true;
        for (int i = 0; i < operators().length(); i++) {
            if (token.charAt(0) == operators().charAt(i)) return true;
        }
        return false;
    }



    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters().length(); i++) {
            if (token.charAt(0) == delimiters().charAt(i)) return true;
        }
        return false;
    }

    private static boolean isFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan");
    }


    private static int priority(String token) {
        switch (token) {
            case "(":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return 4;
    }*/
}
