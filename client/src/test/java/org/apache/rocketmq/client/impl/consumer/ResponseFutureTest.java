package org.apache.rocketmq.client.impl.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class ResponseFutureTest {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void testCountDownLatch() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }).start();
        try {
            boolean isZero = countDownLatch.await(1000, TimeUnit.MILLISECONDS);
            System.out.println(isZero);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
