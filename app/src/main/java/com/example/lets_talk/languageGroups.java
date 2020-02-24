package com.example.lets_talk;

import java.util.ArrayList;

public class languageGroups {
    private String name;
    private String language;
    private int level;
    private int id;
    private int size;
    private ArrayList<Gruop> gruops;

    public languageGroups(){

    }

    public languageGroups(String name, String language, int level, int id, int size, ArrayList<Gruop> gruops) {
        this.name = name;
        this.language = language;
        this.level = level;
        this.id = id;
        this.size = size;
        this.gruops = gruops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Gruop> getGruops() {
        return gruops;
    }

    public void setGruops(ArrayList<Gruop> gruops) {
        this.gruops = gruops;
    }
}
