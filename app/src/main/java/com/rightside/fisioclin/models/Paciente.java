package com.rightside.fisioclin.models;

import java.util.HashMap;

public class Paciente extends Pessoa {

    public Paciente() {
    }

    public Paciente(String id, String name, String profilePictureUrl, String email, String phoneNumber) {
        super(id, name, profilePictureUrl, email, phoneNumber);
        setAdmin(false);
    }

    public Paciente(String id, String name, String profilePictureUrl) {
        super(id, name, profilePictureUrl);
        setAdmin(false);
    }



    public HashMap<String, Object> returnPacient() {
        HashMap<String, Object> pacient = new HashMap<>();
        pacient.put("id", getId());
        pacient.put("name", getName());
        pacient.put("profilePictureUrl", getProfilePictureUrl());
        pacient.put("phoneNumber", getPhoneNumber());
        pacient.put("email", getEmail());
        return pacient;
    }

}
