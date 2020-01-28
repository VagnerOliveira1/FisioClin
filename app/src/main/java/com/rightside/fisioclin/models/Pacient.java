package com.rightside.fisioclin.models;

import java.util.List;

public class Pacient extends Person {

    private List<Consult> consultList;

    public Pacient(String name, String profilePictureUrl, String datOfBirth, String sex, String phoneNumber) {
        super(name, profilePictureUrl, datOfBirth, sex, phoneNumber);
    }

    public List<Consult> getConsultList() {
        return consultList;
    }

    public void setConsultList(List<Consult> consultList) {
        this.consultList = consultList;
    }


}
