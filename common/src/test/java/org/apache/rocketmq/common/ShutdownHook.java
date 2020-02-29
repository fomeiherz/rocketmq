package org.apache.rocketmq.common;

import java.util.concurrent.Callable;

public class ShutdownHook {

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new MyThread(new Callable<Void>() {
            @Override
            public Void call() {
                System.out.println("Shutdown.");
                return null;
            }
        }));
        Thread.sleep(5000);
    }

    private static class MyThread extends Thread {

        Callable<Void> callable;

        public MyThread(Callable<Void> callable) {
            this.callable = callable;
        }

        @Override
        public void run() {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
