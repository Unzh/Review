package com.review.demo.Kafka.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KafkaMessage<T> implements Serializable {
    private static final long serialVersionUID = -4978117645051860147L;

    /** 消息 id */
    private String messageId;

    /** 应用名 */
    private String applicationName;

    /**
     * 消息主题
     * @see TopicConstants
     */
    private String topic;

    /** 消息体 */
    private T content;

    /** 是否自动提交,默认自动提交 */
    private Boolean autoAck = true;
}
