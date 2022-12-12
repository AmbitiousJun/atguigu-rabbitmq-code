package cn.ambitiousjun.rabbitmq.two;

import cn.ambitiousjun.rabbitmq.util.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 任务队列（生产者）
 * @author Ambitious
 * @date 2022/12/7
 */
public class Task01 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 获取连接通道
        Channel channel = RabbitMqUtils.getChannel();
        // 2 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 3 从控制台中读入消息，并推送到队列中
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String msg = scanner.next();
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
                System.out.println("消息发送成功: " + msg);
            }
        }
    }
}
