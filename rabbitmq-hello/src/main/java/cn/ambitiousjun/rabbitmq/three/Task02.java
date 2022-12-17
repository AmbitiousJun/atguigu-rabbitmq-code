package cn.ambitiousjun.rabbitmq.three;

import cn.ambitiousjun.rabbitmq.util.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 测试消息应答
 * 生产者
 * @author Ambitious
 * @date 2022/12/12
 */
public class Task02 {

    public static final String ACK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 获取连接
        Channel channel = RabbitMqUtils.getChannel();
        // 2 声明队列
        channel.queueDeclare(ACK_QUEUE_NAME, false, false, false, null);
        // 3 从控制台中读取消息并推送到队列中
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                String message = sc.next();
                channel.basicPublish("", ACK_QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("生产者发送了消息：" + message);
            }
        }
    }
}
