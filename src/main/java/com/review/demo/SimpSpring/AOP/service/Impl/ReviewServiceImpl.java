package com.review.demo.SimpSpring.AOP.service.Impl;

import com.review.demo.SimpSpring.AOP.Mapper.ReviewDao;
import com.review.demo.SimpSpring.AOP.service.ReviewService;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewDao reviewDao;

    @Override
    public int add() {
        List<String> list = new ArrayList();
        List<String> list2 = new LinkedList<>();
        /**线程安全建议使用ConcurrentHashMap() 代替 HashTable()*/
        ConcurrentMap<String,String> map = new ConcurrentHashMap();

        Comparable comparable;
        Comparator comparator;
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public List<String> queryList() {
        return null;
    }

    @Override
    public int del() {
        return 0;
    }
}
