import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author gantrylau
 * @since 2018/5/11
 */
public class ProducerRun {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.33.10:9876");
        producer.setVipChannelEnabled(false);
        producer.start();
        for (int i = 0; i < 1000; i++) {
            Message m = new Message();
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("PushTopic" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
