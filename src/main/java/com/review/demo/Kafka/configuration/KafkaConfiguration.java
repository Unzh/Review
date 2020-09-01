package com.review.demo.Kafka.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfiguration {

    @Value("${zk.server.url:127.0.0.1}")
    private String ZkConnection;

    @Value("${spring.kafka.consumer.group-id:}")
    private String group;

    @Value("${spring.kafka.template.default-topic:}")
    private String topic;

}
