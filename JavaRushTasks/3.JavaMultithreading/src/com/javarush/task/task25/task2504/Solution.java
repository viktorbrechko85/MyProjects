package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread tr : threads){
            //System.out.println(tr.getState());
            switch (tr.getState()){
                case NEW:
                    tr.start();
                    break;
                case WAITING:
                    tr.interrupt( );
                    break;
                case TIMED_WAITING:
                    tr.interrupt( );
                    break;
                case BLOCKED:
                    tr.interrupt( );
                    break;
                case RUNNABLE:
                    tr.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(tr.getPriority());

            }
        }
    }

    public static void main(String[] args) {

    }
}
