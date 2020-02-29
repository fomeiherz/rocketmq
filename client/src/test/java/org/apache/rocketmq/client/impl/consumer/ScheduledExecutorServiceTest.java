package org.apache.rocketmq.client.impl.consumer;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    @Test
    public void testScheduledExecutorService() {
        // 多个调用时，是否会单线程执行方法
        // ×× 期待结果，单线程执行，应该会顺序执行，并且耗时25s
        // 会一次性执行
        ScheduledExecutorService scheduledExecutorService = Executors
                .newSingleThreadScheduledExecutor(new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "PullMessageServiceScheduledThread");
                    }
                });
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date().toString() + String.format(",ThreadName=%s, i=%s, do something...", Thread.currentThread().getName(), finalI));
                }
            }, 5000, TimeUnit.MILLISECONDS);
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
