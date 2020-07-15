package ru.itis.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("redis");
        System.out.println("Server is running: " + jedis.ping());
        SpringApplication.run(RedisApplication.class, args);
    }
}
