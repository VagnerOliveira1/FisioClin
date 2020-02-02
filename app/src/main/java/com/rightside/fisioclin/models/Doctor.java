package com.rightside.fisioclin.models;

import android.net.Uri;

import java.util.HashMap;

public class Doctor extends Person {

    public Doctor() {
    }

    public Doctor(String id, String name, String profilePictureUrl) {
        super(id, name, profilePictureUrl);
        setAdmin(true);
    }

    @Override
    public void setAdmin(boolean admin) {
        super.setAdmin(admin);
    }


    public HashMap<String, Object> returnDoctor() {
        HashMap<String, Object> doctor = new HashMap<>();
        doctor.put("id", getId());
        doctor.put("name", getName());
        doctor.put("profilePictureUrl", getProfilePictureUrl());
        return doctor;
    }

}
