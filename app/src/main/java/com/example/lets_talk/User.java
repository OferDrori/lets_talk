package com.example.lets_talk;

public class User {

    private String firstName;
    private String LastName;
    private String email;
    private long id;
    private String location;
    private String img;
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
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", location='" + location + '\'' +
                ", img='" + img + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public User() {
    }

    public User(String firstName, String LastName,String email,String location,long id,String password,String description) {
        this.firstName = firstName;
        this.LastName = LastName;
        this.email = email;
        this.location=location;
        this.id=id;
        this.password=password;
        this.description=description;
        this.score=0;
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
