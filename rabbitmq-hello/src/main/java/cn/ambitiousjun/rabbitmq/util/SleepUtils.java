package cn.ambitiousjun.rabbitmq.util;

/**
 * 睡眠工具类
 * @author Ambitious
 * @date 2022/12/12
 */
public class SleepUtils {

    /**
     * 睡眠若干秒
     * @param timeout 睡眠时间（秒）
     */
    public static void sleep(int timeout) {
        try {
            Thread.sleep(1000L * timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
