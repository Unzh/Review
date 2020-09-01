package com.review.demo.Kafka.handler;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSendResultHandler implements ProducerListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaSendResultHandler.class);

    @Override
    public void onSuccess(String s, Integer integer, Object o, Object o2, RecordMetadata recordMetadata) {
        log.info("手动 ack 方式Kafka Message send success : " + s);
    }

    @Override
    public void onError(String s, Integer integer, Object o, Object o2, Exception e) {
        log.error("手动 ack 方式 Kafka Message send error : " + s, e);
    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }
}
