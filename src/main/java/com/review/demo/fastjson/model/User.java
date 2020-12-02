package com.review.demo.fastjson.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User {
    private String id;

    private String userId;

    private String type;

    private Integer status;

    private Integer totalAmount;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;
}
