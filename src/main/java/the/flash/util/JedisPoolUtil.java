package the.flash.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 这里写你的功能描述
 *
 * @author 陈飞
 */
public class JedisPoolUtil {
    private static JedisPool jedisPool;

    static {
        if (jedisPool == null) {
            try {
                init();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化Jedis连接池
     *
     * @throws IOException
     */
    private static void init() throws IOException {
        String host = "localhost";
        int port = 6379;
        String auth = "123456";
        int poolTimeOut = 2000;
        //判断使用默认的配置方式还是采用自定义配置方式,
        jedisPool = new JedisPool(new GenericObjectPoolConfig(), host, port, poolTimeOut, auth);


    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
