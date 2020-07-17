package ru.itis.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    @Bean("redisUrl")
    public String redisUrl() {
        return "redis://redis:6379/";
    }

    @Bean
    public RedissonClient redisClient(@Autowired @Qualifier("redisUrl") String redisUrl) {
        Config config = new Config();
        config.useSingleServer().setAddress(redisUrl);
        return Redisson.create(config);
    }

    @Bean("visitsBucket")
    public RBucket<Integer> visitsBucket(@Autowired RedissonClient redisClient) {
        return redisClient.getBucket("VISIT_COUNT");
    }
}
