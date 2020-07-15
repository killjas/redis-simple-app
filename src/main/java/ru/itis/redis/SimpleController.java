package ru.itis.redis;

import org.redisson.api.RBucket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SimpleController {
    private final RBucket<Integer> visitsBucket;

    public SimpleController(RBucket<Integer> visitsBucket) {
        this.visitsBucket = visitsBucket;
    }

    @GetMapping("/redis")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(visits());
    }

    private int visits() {
        Optional<Integer> value = Optional.ofNullable(visitsBucket.get());
        int visits = value.orElse(0);
        visitsBucket.set(visits + 1);
        return visits;
    }
}
