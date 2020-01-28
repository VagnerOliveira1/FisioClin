package com.rightside.fisioclin.models;

public class Person {

    private String name;
    private String profilePictureUrl;
    private String datOfBirth;
    private String sex;
    private String phoneNumber;
    private boolean admin;

    public Person(String name, String profilePictureUrl, String datOfBirth, String sex, String phoneNumber) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.datOfBirth = datOfBirth;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        setAdmin(false);
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
