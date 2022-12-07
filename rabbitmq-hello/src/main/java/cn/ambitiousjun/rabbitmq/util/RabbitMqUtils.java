package cn.ambitiousjun.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通用代码抽取成工具类
 * @author Ambitious
 * @date 2022/12/7
 */
public class RabbitMqUtils {

    /**
     * 获取 RabbitMQ 连接对象
     * @return 连接对象
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // 1 创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.17.128");
        factory.setUsername("admin");
        factory.setPassword("123");
        // 2 创建连接
        return factory.newConnection();
    }

    /**
     * 获取 RabbitMQ 连接通道
     * @return 连接通道
     */
    public static Channel getChannel() throws IOException, TimeoutException {
        // 1 获得连接
        Connection conn = getConnection();
        // 2 返回通道
        return conn.createChannel();
    }
}
