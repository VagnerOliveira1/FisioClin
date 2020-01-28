package com.rightside.fisioclin.models;

public class Doctor extends Person {


    public Doctor(String name, String profilePictureUrl, String datOfBirth, String sex, String phoneNumber) {
        super(name, profilePictureUrl, datOfBirth, sex, phoneNumber);
        setAdmin(true);
    }

    @Override
    public void setAdmin(boolean admin) {
        super.setAdmin(admin);
    }
}
