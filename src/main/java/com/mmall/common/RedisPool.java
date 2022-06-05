package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool pool;

    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20")); // 最大连接数

    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "10")); // 连接池中最大的idle（空闲）状态jedis实例个数

    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2")); // 连接池中最小的idle（空闲）状态jedis实例个数

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true")); // 用于标记在borrow一个jedis实例时是否需要验证，若为true，则说明borrow到的实例一定是有效的

    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true")); // 用于标记在return一个jedis实例时是否需要验证，若为true，则说明return的实例一定是有效的

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");

    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞，若为false，则耗尽后直接抛出异常，若为true，则阻塞直到超时，默认为true
        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2); // timeout为超时时间，单位为毫秒
    }

    static {
        initPool();
    }

    public static Jedis getResource() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = getResource();
        jedis.set("testKey", "testValue");
        returnResource(jedis);
        pool.destroy(); // 销毁连接池中的所有连接
        System.out.println("end of program");
    }

}
