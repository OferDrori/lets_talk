package com.example.lets_talk;

public class User {

    private String name;
    private long id;
    private boolean genger;
    private String img;
    private  int score;


    public User() {
    }

    public User(String name, long id, boolean genger) {
        this.name = name;
        this.id = id;
        this.genger = genger;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isGenger() {
        return genger;
    }

    public User setGenger(boolean genger) {
        this.genger = genger;
        return this;
    }

}
