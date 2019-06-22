package io.example.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class Redis {
    private static final Logger logger = LoggerFactory.getLogger(Redis.class);
    private static final Redis INSTANCE = new Redis();
    private static final JedisPool jedisPool = new JedisPool("localhost", 6379); // Can give host, port and other configs to the constructor

    private Redis(){}

    public static Redis getInstance() {
        return INSTANCE;
    }

    /**
     * Inserts key -> value in Redis db with given expire seconds.
     * @param key key
     * @param val value
     * @param expireSeconds number of second until the entry is removed from db
     * @return true if the record is properly created, false otherwise
     */
    public boolean createKeyValEntry(String key, String val, int expireSeconds){
        logger.info("Creating val associated with key {}.", key);
        try (Jedis jedis = jedisPool.getResource()) {
            if(jedis.setex(key, expireSeconds, val).equals("OK")){
                return true;
            }
        }
        return false;
    }

    public String readValAssociatedWithKey(String key){
        logger.info("Reading val associated with key {}.", key);
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.get(key);
        }
    }
}
