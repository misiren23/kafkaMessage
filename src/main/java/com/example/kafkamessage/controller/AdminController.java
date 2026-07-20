package com.example.kafkamessage.controller;

import com.example.kafkamessage.config.DelayConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DelayConfig delayConfig;

    public AdminController(DelayConfig delayConfig) {
        this.delayConfig = delayConfig;
    }

    @GetMapping("/delay")
    public ResponseEntity<Map<String, Long>> getDelay() {
        return ResponseEntity.ok(Map.of("delay-ms", delayConfig.get()));
    }

    @PostMapping("/delay")
    public ResponseEntity<Map<String, Long>> setDelay(@RequestParam long ms) {
        delayConfig.set(ms);
        return ResponseEntity.ok(Map.of("delay-ms", delayConfig.get()));
    }
}
