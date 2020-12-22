package com.cvccorp.notifications.notifications.controller;

import com.cvccorp.notifications.notifications.dto.RequestMessage;
import com.cvccorp.notifications.notifications.dto.ResponseMessage;
import com.cvccorp.notifications.notifications.service.NotificationTopicProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/notification")
public class NotificationsController {

    private final NotificationTopicProducer producer;

    @PostMapping(value = "/publish")
    public ResponseMessage sendMessageToNotificationTopic(@RequestBody RequestMessage message) {
        return new ResponseMessage(this.producer.sendMessage(null, message), new HashMap<>());
    }

}
