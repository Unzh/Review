package com.review.demo.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  Question 4 的实体类
 *  成绩单
 */
@Data
@AllArgsConstructor
public class Transcript {

    private String lesson;

    private int score;

    private String studentName;

}
