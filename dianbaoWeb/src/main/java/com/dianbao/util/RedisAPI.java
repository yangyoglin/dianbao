package com.dianbao.util;


import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisAPI {
	
	// Redis服务器IP
    private static String ADDR = "127.0.0.1";

    // Redis的端口号
    private static int PORT = 6379;
	// 可用连接实例的最大数目，默认值为8
    private static int MAX_ACTIVE = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

     private static JedisPool jedisPool = null;
     
     /**
      * 初始化Redis连接池
      */
     static {
         try {
             JedisPoolConfig config = new JedisPoolConfig();
             config.setMaxIdle(MAX_ACTIVE);
             config.setMaxTotal(MAX_ACTIVE);
             config.setMaxIdle(MAX_IDLE);
             config.setMaxWaitMillis(MAX_WAIT);
             config.setTestOnBorrow(TEST_ON_BORROW);
             jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    /**
     * @Title: 根据KEY获取值
     * @author YYL 2020年7月2日 上午9:46:07
     * @param key
     */
    public String get(String key){
        Jedis jedis=jedisPool.getResource();
        String value= jedis.get(key);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return value;
    }

    /**
     * @Title: 添加
     * @author YYL 2020年7月2日 上午9:46:49
     * @param key
     * @param value
     */
    public String set(String key,String value){
        Jedis jedis=jedisPool.getResource();
        String result= jedis.set(key, value);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return result;
    }

    /**
     * @Title: 添加,带超时时间
     * @author YYL 2020年7月2日 上午9:47:10
     * @param key
     * @param seconds     单位 秒, 2 * 60 * 60 = 2小时
     * @param value
     */
    public String set(String key,int seconds,String value){
        Jedis jedis=jedisPool.getResource();
        String result=jedis.setex(key, seconds, value);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return value;
    }

    /**
     * @Title: 查看某个KEY是否存在
     * @author YYL 2020年7月2日 上午9:47:44
     * @param key
     */
    public boolean exists(String key){
        Jedis jedis=jedisPool.getResource();
        boolean result=jedis.exists(key);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return result;
    }

    /**
     * @Title: 查看超时时间
     * @author YYL 2020年7月2日 上午9:48:05
     * @param key
     */
    public Long ttl(String key){
        Jedis jedis=jedisPool.getResource();
        Long result=jedis.ttl(key);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return result;

    }

    /**
     * @Title: 删除
     * @author YYL 2020年7月2日 上午9:48:25
     * @param key
     */
    public Long dell(String key){
        Jedis jedis=jedisPool.getResource();
        Long result=jedis.del(key);
        //jedisPool.returnResource(jedis);
        jedis.close();
        return result;

    }
    
    /**
     * @Title: 根据KEY更新过期时间
     * @author YYL 2020年7月3日 上午9:19:37
     * @param key
     * @param seconds     单位 秒, 2 * 60 * 60 = 2小时
     */
    public void expire(String key,int seconds) {
        Jedis jedis=jedisPool.getResource();
    	jedis.expire(key, seconds);
        //jedisPool.returnResource(jedis);
        jedis.close();
    }
}


