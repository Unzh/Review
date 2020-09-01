package com.review.demo.Kafka.service;

import com.review.demo.Kafka.dto.KafkaMessage;

public interface IKafkaPublish {

    <T> Boolean send(KafkaMessage<T> kafkaMessage);
}
