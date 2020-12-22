package com.cvccorp.notifications.notifications.preferences.service;

import com.cvccorp.notifications.notifications.preferences.config.Constants;
import com.cvccorp.notifications.notifications.preferences.dto.RequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationTopicProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, RequestMessage message) {
        if (key == null)
            key = UUID.randomUUID().toString();
        message.setProducer(Constants.GROUP_ID);
        String jsonMessage = "";
        try {
            jsonMessage = new JsonMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.kafkaTemplate.send(Constants.TOPIC, Constants.PRODUCER_PARTITION_INT, key, jsonMessage);
        log.info("Produce message [{}] to partition-{} with key {}", jsonMessage, Constants.PRODUCER_PARTITION_INT, key);
    }

}
