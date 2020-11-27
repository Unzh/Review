package com.review.demo.simpleSpringBoot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/***
 * 指定配置文件的前缀属性，实体具体字段和配置文件字段名一致
 * 如在上述代码中，字段为 appKey，则自动获取 oss.appKey 的值
 * 将其映射到 appKey 字段中，这样就完成了自动的注入
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/11/27
 */
@Component
@ConfigurationProperties(prefix = "oss")
public class SimpleConfiguration {
    private String appKey;
    private String appSecret;
    private String bucket;
    private String endPoint;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "AliyunAuto{" + "appKey='" + appKey + '\'' + ", appSecret='" + appSecret + '\'' + ", bucket='" + bucket + '\'' + ", endPoint='" + endPoint + '\'' + '}';
    }
}