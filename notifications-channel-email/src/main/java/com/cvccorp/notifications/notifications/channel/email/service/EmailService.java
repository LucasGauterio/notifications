package com.cvccorp.notifications.notifications.channel.email.service;

import com.cvccorp.notifications.notifications.channel.email.dto.RequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

    private NotificationTopicProducer producer;

    public void process(String key, String jsonMessage) {
        try {
            RequestMessage message = new JsonMapper().readValue(jsonMessage, RequestMessage.class);
            log.info("notification {}", message.getNotification());
            log.info("configuration {}", message.getConfiguration());
            log.info("content {}", message.getContent());
            HashMap<String, String> statusMap = message.getChannelStatusMap();
            if(statusMap==null)
                statusMap = new HashMap<>();
            statusMap.put("email","delivered");
            message.setChannelStatusMap(statusMap);
            producer.sendMessage(key, message);
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
