package com.example.lets_talk;

public class User {

    private String firstName;
    private String LastName;
    private String email;
    private long id;
    private String location;
    private String img;
    private  int score;


    public User() {
    }

    public User(String firstName, String LastName,String email,String location,long id) {
        this.firstName = firstName;
        this.LastName = LastName;
        this.email = email;
        this.location=location;
        this.id=id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String farstName) {
        this.firstName = farstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
