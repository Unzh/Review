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


   /* @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        char[] charArr = sb.toString().toCharArray();
        int hash = 0;
        for(char c : charArr) {
            hash = hash * 131 + c;
        }
        return hash;
    }*/

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            return user.getName().equals(this.name);
        }
        return false;
    }
}
