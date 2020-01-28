package com.rightside.fisioclin.models;

public class Consult {

    private Hour hour;
    private Pacient pacient;

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Pacient getPacient() {
        return pacient;
    }



}
