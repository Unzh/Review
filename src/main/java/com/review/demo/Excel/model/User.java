package com.review.demo.Excel.model;


public class User {

    private String name;

    private String employeeId;

    private String post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User() {
    }

    public User(String name, String employeeId, String post) {
        this.name = name;
        this.employeeId = employeeId;
        this.post = post;
    }
}
