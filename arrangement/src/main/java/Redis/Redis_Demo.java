package Redis;

import redis.clients.jedis.Jedis;

/**
 * Created by YuiSol on 2017/9/8.
 */
public class Redis_Demo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.81",6379);
        System.out.println(jedis.append("测试".getBytes(), "你好".getBytes()));

    }
}
