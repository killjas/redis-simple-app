package ru.itis.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private static int Counter = 0;

    @GetMapping("/redis")
    public ResponseEntity<?> count() {
        RedissonClient redissonClient = getRedisson();
        RBucket<String> bucket = redissonClient.getBucket("simpleObject");
        bucket.set(String.valueOf(Counter++));
        redissonClient.shutdown();
        return ResponseEntity.ok().build();
    }

    private RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
