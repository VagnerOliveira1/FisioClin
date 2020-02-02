package com.rightside.fisioclin.models;

import android.net.Uri;

import java.util.List;

public class Pacient extends Person {

    private List<Consult> consultList;

    public Pacient(String id, String name, String profilePictureUrl) {
        super(id, name, profilePictureUrl);
        setAdmin(false);
    }

    public List<Consult> getConsultList() {
        return consultList;
    }

    public void setConsultList(List<Consult> consultList) {
        this.consultList = consultList;
    }


}
