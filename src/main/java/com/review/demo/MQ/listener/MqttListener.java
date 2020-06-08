package com.review.demo.MQ.listener;
/**
 * Title : MqttListener.java
 * Package : com.review.demo.MQ.listener
 * Description :MQTT协议监听器
 * Create on : 16:00
 */
public abstract class MqttListener<T> {
    /**
     * 消息到达事件
     * @param topic
     * @param message
     */
    public abstract void messageArrived(String topic, T message);

    /**
     * 消息传递完成
     * @param topic
     */
    public abstract void deliveryComplete(String topic);

}
