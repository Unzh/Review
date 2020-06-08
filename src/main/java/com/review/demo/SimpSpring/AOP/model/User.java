package com.review.demo.SimpSpring.AOP.model;


import javax.xml.crypto.Data;

public class User {

    private String name;

    private String nick;

    private Data birthData;

    public User(String name, String nick, Data birthData) {
        this.name = name;
        this.nick = nick;
        this.birthData = birthData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Data getBirthData() {
        return birthData;
    }

    public void setBirthData(Data birthData) {
        this.birthData = birthData;
    }
}
