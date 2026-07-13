package com.example.kafkamessage.listener;

import com.example.kafkamessage.dto.KafkaMessagePayload;
import com.example.kafkamessage.entity.KafkaMessageEntity;
import com.example.kafkamessage.repository.KafkaMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    private final KafkaMessageRepository repository;
    private final ObjectMapper objectMapper;

    public KafkaMessageListener(KafkaMessageRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "postedmessages", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        try {
            KafkaMessagePayload payload = objectMapper.readValue(message, KafkaMessagePayload.class);
            KafkaMessageEntity entity = new KafkaMessageEntity(
                    payload.getMsgId(),
                    payload.getTimestamp(),
                    payload.getMethod(),
                    payload.getUri()
            );
            repository.save(entity);
            log.info("Сохранено сообщение: msg_id={}", payload.getMsgId());
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения: {}", message, e);
        }
    }
}
