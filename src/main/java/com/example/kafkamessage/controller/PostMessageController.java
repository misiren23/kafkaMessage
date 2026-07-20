package com.example.kafkamessage.controller;

import com.example.kafkamessage.config.DelayConfig;
import com.example.kafkamessage.dto.KafkaMessagePayload;
import com.example.kafkamessage.dto.MessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostMessageController {

    private static final String TOPIC = "postedmessages";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final DelayConfig delayConfig;

    public PostMessageController(KafkaTemplate<String, String> kafkaTemplate,
                                 ObjectMapper objectMapper,
                                 DelayConfig delayConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.delayConfig = delayConfig;
    }

    @PostMapping("/post-message")
    public ResponseEntity<Void> postMessage(@RequestBody MessageRequest request,
                                            HttpServletRequest httpRequest) throws InterruptedException {
        long delayMs = delayConfig.get();
        if (delayMs > 0) {
            Thread.sleep(delayMs);
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        KafkaMessagePayload payload = new KafkaMessagePayload(
                request.getMsgId(),
                timestamp,
                httpRequest.getMethod(),
                httpRequest.getRequestURI()
        );

        try {
            String json = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, json).get();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
