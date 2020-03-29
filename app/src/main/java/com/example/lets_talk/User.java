package com.example.lets_talk;

public class User {

    private String fullName;
    private String email;
    private long id;
    private String location;
    private String photoPath;
    private  int score;
    private String password;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", location='" + location + '\'' +
                ", img='" + photoPath + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public User() {
    }

    public User(String fullName,String email,String location,long id,String password,String description,String photoPath) {
        this.fullName = fullName;
        this.email = email;
        this.location=location;
        this.id=id;
        this.password=password;
        this.description=description;
        this.score=0;
        this.photoPath=photoPath;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
