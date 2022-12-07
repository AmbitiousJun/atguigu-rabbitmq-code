package cn.ambitiousjun.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * @author Ambitious
 * @date 2022/9/5 10:46
 */
public class Publisher {

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
        // 2 声明队列
        // Queue.DeclareOk queueDeclare(
        //    String queue,  队列名称
        //    boolean durable,  消息是否进行持久化（默认存放在内存）
        //    boolean exclusive,  是否排它（独占），即是否只能由一个消费者进行消费
        //    boolean autoDelete,  当最后一个消费者断开连接时，是否自动删除队列
        //    Map<String, Object> arguments) throws IOException; 高级参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 3 发送消息
        String msg = "hello world!";
        // void basicPublish(
        //     String exchange, 交换机名称，传递空字符串则不使用交换机
        //     String routingKey,  路由 key，当不使用交换机时，这里填的是队列名称
        //     BasicProperties props,  属性参数，可以不填
        //     byte[] body) throws IOException;  发送的消息体
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("消息发送成功了");
        conn.close();
    }
}
