package com.example.kafkamessage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class DelayConfig {

    private final AtomicLong delayMs;

    public DelayConfig(@Value("${app.response.delay-ms:0}") long initialDelay) {
        this.delayMs = new AtomicLong(initialDelay);
    }

    public long get() {
        return delayMs.get();
    }

    public void set(long ms) {
        delayMs.set(ms);
    }
}
