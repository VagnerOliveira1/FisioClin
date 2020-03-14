package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class Medico extends User implements Serializable {

    private String crefito = "";

    public Medico() {

    }

    public Medico(String id, String name, String profilePictureUrl, String email, String crefito) {
        super(id, name, profilePictureUrl, email);
        this.crefito = crefito;
        setAdmin(true);
    }

    public Medico(String id, String name, String profilePictureUrl) {
        super(id, name, profilePictureUrl);
        setAdmin(true);
    }

    public boolean contain(String text) {
        return getName().toLowerCase().contains(text);
    }

    @Override
    public void setAdmin(boolean admin) {
        super.setAdmin(admin);
    }


    public String getCrefito() {
        return crefito;
    }

    public void setCrefito(String crefito) {
        this.crefito = crefito;
    }

    public HashMap<String, Object> returnDoctor() {
        HashMap<String, Object> doctor = new HashMap<>();
        doctor.put("id", getId());
        doctor.put("name", getName());
        doctor.put("profilePictureUrl", getProfilePictureUrl());
        doctor.put("phoneNumber", getPhoneNumber());
        doctor.put("crefito", getCrefito());
        doctor.put("endereco", getEndereco());


        return doctor;
    }

}
