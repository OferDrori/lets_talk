package com.example.lets_talk;

public class Gruop {
    private String language;
    private int level;
    private int id;
    private int size;
    public Gruop(){

    }
    public Gruop(String language, int level, int id, int size) {
        this.language = language;
        this.level = level;
        this.id = id;
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
