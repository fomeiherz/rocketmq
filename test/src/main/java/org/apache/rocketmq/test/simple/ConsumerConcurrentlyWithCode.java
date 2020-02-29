package org.apache.rocketmq.test.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

public class ConsumerConcurrentlyWithCode {

    public static void main(String[] args) throws InterruptedException, MQClientException, IOException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TopicTestGroup");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        String filterCode = MixAll.file2String("E:\\source\\rocketmq\\test\\src\\main\\java\\org\\apache\\rocketmq\\test\\simple\\MessageFilterImpl.java");
        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "org.apache.rocketmq.test.simple.MessageFilterImpl", filterCode);
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
