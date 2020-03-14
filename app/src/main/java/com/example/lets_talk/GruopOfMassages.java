package com.example.lets_talk;

import java.util.ArrayList;

public class GruopOfMassages {
    private String name;
    private String description;
    private int size;
    private ArrayList<Message> massages;

    public GruopOfMassages() {

    }

    public GruopOfMassages(String name, String description, int size, ArrayList<Message> massages) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.massages = massages;
    }
    public GruopOfMassages(String name, String description) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.massages = massages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Message> getMassages() {
        return massages;
    }

    public void setMassages(ArrayList<Message> massages) {
        this.massages = massages;
    }
}