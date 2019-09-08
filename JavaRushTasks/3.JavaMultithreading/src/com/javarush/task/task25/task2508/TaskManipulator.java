package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    private Thread thread;
    public void start(String threadName){
        Thread thread = new Thread(this, threadName);
        this.thread = thread;
        thread.start();

    };
    public void stop(){thread.interrupt();};
    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                Thread.sleep(0);
                System.out.println(thread.getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}

    }
}
