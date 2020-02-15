package com.rightside.fisioclin.models;

import java.util.HashMap;

public class Medico extends User {

    public Medico() {
    }

    public Medico(String id, String name, String profilePictureUrl) {
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
