package com.review.demo.Kafka.wrapper;


import com.review.demo.Kafka.dto.KafkaMessage;
import com.review.demo.Kafka.service.IKafkaPublish;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKafkaPublishWrapper implements IKafkaPublish {

    private static final Logger logger = LoggerFactory.getLogger(AbstractKafkaPublishWrapper.class);

    @Override
    public <T> Boolean send(KafkaMessage<T> kafkaMessage) {
        if (kafkaMessage == null
                || StringUtils.isEmpty(kafkaMessage.getApplicationName())
                || StringUtils.isEmpty(kafkaMessage.getMessageId())
                || StringUtils.isEmpty(kafkaMessage.getTopic())
                || kafkaMessage.getContent() == null) {
            logger.error(String.format("入参缺失,{%s}", kafkaMessage));
        }
        return publish(kafkaMessage);
    }

    public abstract <T> Boolean publish(KafkaMessage<T> kafkaMessage);

}
