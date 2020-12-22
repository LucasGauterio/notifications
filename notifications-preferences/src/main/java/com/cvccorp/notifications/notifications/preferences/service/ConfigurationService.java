package com.cvccorp.notifications.notifications.preferences.service;

import com.cvccorp.notifications.notifications.preferences.dto.Channel;
import com.cvccorp.notifications.notifications.preferences.dto.Configuration;
import com.cvccorp.notifications.notifications.preferences.dto.RequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ConfigurationService {

    private NotificationTopicProducer producer;

    public void process(String key, String jsonMessage) {
        try {
            RequestMessage message = new JsonMapper().readValue(jsonMessage, RequestMessage.class);

            log.info("notification {}", message.getNotification());

            Configuration configuration = new Configuration();
            List<Channel> channels = new ArrayList<>();
            Channel email = new Channel();
            Map<String, String> emailConfig = new HashMap<>();
            emailConfig.put("from", "def@abc.com");
            emailConfig.put("to", "abc@def.com");
            emailConfig.put("cc", "abc1@def.com;abc2@def.com");
            emailConfig.put("cco", "abc3@def.com;abc4@def.com");
            emailConfig.put("template", "1a2b3c4d");
            email.setType("email");
            email.setConfiguration(emailConfig);
            channels.add(email);
            configuration.setChannels(channels);

            message.setConfiguration(configuration);
            log.info("configuration {}", configuration);
            producer.sendMessage(key, message);
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
