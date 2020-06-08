package com.review.demo.MQ.eunms;

/**
 * Title : ExchangeType.java
 * Package : com.review.demo.MQ.eunms
 * Description : RabbitMQ 交换器类型
 * Create on : 14:32
 *
 */
public enum ExchangeType {

    FANOUT("fanout"), DIRECT("direct"), TOPIC("topic");
    private String key;

    ExchangeType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
