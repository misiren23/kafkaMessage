package com.example.kafkamessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaMessagePayload {

    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("method")
    private String method;

    @JsonProperty("uri")
    private String uri;

    public KafkaMessagePayload() {}

    public KafkaMessagePayload(String msgId, String timestamp, String method, String uri) {
        this.msgId = msgId;
        this.timestamp = timestamp;
        this.method = method;
        this.uri = uri;
    }

    public String getMsgId() { return msgId; }
    public String getTimestamp() { return timestamp; }
    public String getMethod() { return method; }
    public String getUri() { return uri; }
}
