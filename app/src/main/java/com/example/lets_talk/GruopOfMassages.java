package com.example.lets_talk;

import java.util.ArrayList;

public class GruopOfMassages {
    private String name;
    private int size;
    private ArrayList<Message> massages ;
    public GruopOfMassages() {

    }
    public GruopOfMassages(String name, int size, ArrayList<Message> massages) {
        this.name = name;
        this.size = size;
        this.massages = massages;
    }
    public GruopOfMassages(String name, int size) {
        this.name = name;
        this.size = size;
        this.massages =  new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
