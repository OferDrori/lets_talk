package com.example.lets_talk;

import java.util.ArrayList;

public class LanguageGroups {
    private String language;
    private int level;
    private int id;
    private int size;
    private ArrayList<GruopOfMassages> gruopOfMassages;

    public LanguageGroups(){

    }

    public LanguageGroups( String language, int level, int id, int size, ArrayList<GruopOfMassages> gruopOfMassages) {

        this.language = language;
        this.level = level;
        this.id = id;
        this.size = size;
        this.gruopOfMassages = gruopOfMassages;
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

    public ArrayList<GruopOfMassages> getGruopOfMassages() {
        return gruopOfMassages;
    }

    public void setGruopOfMassages(ArrayList<GruopOfMassages> gruopOfMassages) {
        this.gruopOfMassages = gruopOfMassages;
    }
}
