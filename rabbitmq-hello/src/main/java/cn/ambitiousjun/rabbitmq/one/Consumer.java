package cn.ambitiousjun.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者代码
 * @author Ambitious
 * @date 2022/9/5 16:11
 */
public class Consumer {

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.17.128");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        // 2 定义回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody());
            System.out.println("消费者成功接收到消息: " + msg);
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息接收失败了!");
        };
        // 3 开启监听
        // String basicConsume(
        //     String queue,  队列名称
        //     boolean autoAck,  是否自动发送应答
        //     DeliverCallback deliverCallback,  成功接收消息的回调
        //     CancelCallback cancelCallback) throws IOException;  消息接收
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
