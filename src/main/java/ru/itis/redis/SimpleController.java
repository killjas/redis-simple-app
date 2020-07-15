package ru.itis.redis;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private final RedissonClient redisClient;

    public SimpleController(RedissonClient redisClient) {
        this.redisClient = redisClient;
    }

    @GetMapping("/redis")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(visits());
    }

    private int visits() {
        RBucket<String> bucket = redisClient.getBucket("VISITS");
        String value = bucket.get();
        if (value == null) {
            value = "0";
        }
        int visits = Integer.parseInt(value);
        bucket.set(String.valueOf(visits + 1));
        return visits;
    }
}
