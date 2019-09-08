package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
       /* synchronized (obj1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }*/
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
        Thread threadTest = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        solution.someMethodWithSynchronizedBlocks(o1, o2);
                    }
                });
        threadTest.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        return threadTest.getState() == Thread.State.BLOCKED;
        //return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
