package com.learn.maksymgromov.socialnetwork.model;

import java.io.Serializable;

public class User implements Serializable {

    private String info;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String university;
    private String school;
    private String twitter;
    private String phone;

    public User() {}

    public User(String info, String name, String surname, String dateOfBirth, String university, String school, String twitter, String phone) {
        this.info = info;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.university = university;
        this.school = school;
        this.twitter = twitter;
        this.phone = phone;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
