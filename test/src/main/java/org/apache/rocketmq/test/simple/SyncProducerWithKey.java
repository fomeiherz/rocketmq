package org.apache.rocketmq.test.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.UUID;

public class SyncProducerWithKey {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("TopicTestGroup");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */, "MyNameIsKey",
                ("{\"a\":1}").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        msg.putUserProperty("a", "1");
        //Call send message to deliver message to one of brokers.
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
