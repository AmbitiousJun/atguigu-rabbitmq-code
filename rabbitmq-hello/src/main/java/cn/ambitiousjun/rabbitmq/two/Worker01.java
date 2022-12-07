package cn.ambitiousjun.rabbitmq.two;

import cn.ambitiousjun.rabbitmq.util.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作线程（消费者） 1 号
 * @author Ambitious
 * @date 2022/12/7
 */
public class Worker01 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 获取连接通道
        Channel channel = RabbitMqUtils.getChannel();
        // 2 定义消息接收回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(consumerTag + ":成功接收到消息:" + new String(message.getBody()));
        };
        // 3 定义取消接收回调
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + ":消息取消接收");
        };
        System.out.println("C2正在等待接收消息......");
        // 4 消费消息
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
