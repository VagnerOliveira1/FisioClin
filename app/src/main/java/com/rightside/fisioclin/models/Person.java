package com.rightside.fisioclin.models;

import android.net.Uri;

public class Person {

    private String id;
    private String name;
    private String profilePictureUrl;
    private String datOfBirth;
    private String sex;
    private String phoneNumber;
    private boolean admin;

    public Person() {
    }

    public Person(String id, String name, String profilePictureUrl) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        setAdmin(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(String datOfBirth) {
        this.datOfBirth = datOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
