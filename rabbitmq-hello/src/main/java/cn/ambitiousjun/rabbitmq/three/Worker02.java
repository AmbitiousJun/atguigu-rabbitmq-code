package cn.ambitiousjun.rabbitmq.three;

import cn.ambitiousjun.rabbitmq.util.RabbitMqUtils;
import cn.ambitiousjun.rabbitmq.util.SleepUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 测试消息应答
 * 消费者 01 处理消息需要 1 秒时间
 * @author Ambitious
 * @date 2022/12/12
 */
public class Worker02 {

    public static final String ACK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 获取连接通道
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("这是消费者 C1，处理消息只需要 1 秒");
        // 2 监听队列，消费消息
        channel.basicConsume(
            ACK_QUEUE_NAME,
            false,
            (consumerTag, message) -> {
                SleepUtils.sleep(1);
                System.out.println("消费者接收到了消息：" + new String(message.getBody()));
                // 手动发送应答
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            },
            consumerTag -> {
                System.out.println("消息取消发送了....");
            }
        );
    }
}
