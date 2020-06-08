package com.review.demo.SimpSpring.AOP.Interfaces;

public interface ForMula {

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
