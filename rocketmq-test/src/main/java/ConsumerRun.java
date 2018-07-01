import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author gantrylau
 * @since 2018/5/15
 */
public class ConsumerRun {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("192.168.33.10:9876");
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("PushTopic", "*");
            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(
                    new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(
                                List<MessageExt> list,
                                ConsumeConcurrentlyContext Context) {
                            MessageExt messageExt = list.get(0);
                            System.out.println("msg:\t" + new String(messageExt.getBody()));
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
            );
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
