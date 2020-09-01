package com.review.demo.Kafka.wrapper;

import com.alibaba.fastjson.JSONObject;
import com.review.demo.Kafka.service.IKafkaSubscribe;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;


public class AbstractKafkaSubscribeWrapper implements IKafkaSubscribe {

    private Logger logger = LoggerFactory.getLogger(AbstractKafkaSubscribeWrapper.class);



}
