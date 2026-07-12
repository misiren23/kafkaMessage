package com.example.kafkamessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {

    @JsonProperty("msg_id")
    private String msgId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
