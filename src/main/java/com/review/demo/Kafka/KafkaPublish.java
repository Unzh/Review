package com.review.demo.Kafka;

import com.alibaba.fastjson.JSONObject;
import com.review.demo.Kafka.dto.KafkaMessage;
import com.review.demo.Kafka.handler.KafkaSendResultHandler;
import com.review.demo.Kafka.wrapper.AbstractKafkaPublishWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component("commonKafkaPublish")
public class KafkaPublish extends AbstractKafkaPublishWrapper {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSubscribe.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaSendResultHandler producerListener;

    @Override
    public <T> Boolean publish(KafkaMessage<T> kafkaMessage) {
        kafkaTemplate.setProducerListener(producerListener);
        String applicationName = kafkaMessage.getApplicationName();
        String topic = kafkaMessage.getTopic();
        String messageId = kafkaMessage.getMessageId();
        Object content = kafkaMessage.getContent();
        String kafkaValue = JSONObject.toJSONString(content);

        try {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, kafkaValue);
            future.get();
        } catch (Exception e) {
            logger.error(String.format("kafka 调用出现异常,messageId{%s},发起服务为{%s},topic 为{%s},消息体为{%s}", messageId, applicationName, topic, kafkaValue));
            return false;
        }

        return true;
    }
}
