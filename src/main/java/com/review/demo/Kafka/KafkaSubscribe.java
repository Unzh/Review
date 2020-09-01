package com.review.demo.Kafka;

import com.alibaba.fastjson.JSONObject;
import com.review.demo.Kafka.constant.TopicConstants;
import com.review.demo.Kafka.wrapper.AbstractKafkaSubscribeWrapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("kafkaSubscribe")
public class KafkaSubscribe extends AbstractKafkaSubscribeWrapper {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSubscribe.class);

    @KafkaListener(topics = TopicConstants.REG_PAY, id = "pay")
    public void listenPay(ConsumerRecord<String, String> record) throws Exception {
        logger.info(String.format("kafka 挂号收费消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
    }

    @KafkaListener(topics = TopicConstants.COMMON_PAY, id = "pay")
    public void listenXXXPay(ConsumerRecord<String, String> record, Acknowledgment ack) throws Exception {
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
        //自定义手动应答方式
        if (new Random().nextInt(100) < 50) {
            ack.acknowledge();
            logger.info(String.format("kafka 综合收费消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        }

    }

}
