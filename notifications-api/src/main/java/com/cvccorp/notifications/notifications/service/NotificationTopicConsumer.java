package com.cvccorp.notifications.notifications.service;

import com.cvccorp.notifications.notifications.config.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationTopicConsumer {

    private final CallbackService service;

    @KafkaListener(groupId = Constants.GROUP_ID, topicPartitions = {@TopicPartition(topic = Constants.TOPIC, partitions = {Constants.CONSUMER_PARTITION_STRING})})
    public void consume(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received key {} message [{}] from partition-{} with offset-{}",
                key,
                message,
                partition,
                offset);
        service.process(key, message);
    }

}
