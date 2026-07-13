package com.example.kafkamessage.repository;

import com.example.kafkamessage.entity.KafkaMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaMessageRepository extends JpaRepository<KafkaMessageEntity, Long> {
}
