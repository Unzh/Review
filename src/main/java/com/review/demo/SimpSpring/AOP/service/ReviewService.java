package com.review.demo.SimpSpring.AOP.service;

import java.util.List;

public interface ReviewService {

    int  add();
    int update();
    List<String> queryList();
    int  del();
}
