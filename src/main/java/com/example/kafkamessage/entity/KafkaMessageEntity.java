package com.example.kafkamessage.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kafka_messages")
public class KafkaMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msg_id")
    private String msgId;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "method")
    private String method;

    @Column(name = "uri")
    private String uri;

    public KafkaMessageEntity() {}

    public KafkaMessageEntity(String msgId, String timestamp, String method, String uri) {
        this.msgId = msgId;
        this.timestamp = timestamp;
        this.method = method;
        this.uri = uri;
    }

    public Long getId() { return id; }
    public String getMsgId() { return msgId; }
    public String getTimestamp() { return timestamp; }
    public String getMethod() { return method; }
    public String getUri() { return uri; }
}
