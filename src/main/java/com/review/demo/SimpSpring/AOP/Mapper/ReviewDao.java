package com.review.demo.SimpSpring.AOP.Mapper;


import com.review.demo.SimpSpring.AOP.model.User;

public interface ReviewDao {

    int addUser(User user);

    int updateUser(User user);
}
