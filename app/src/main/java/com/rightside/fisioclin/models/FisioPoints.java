package com.rightside.fisioclin.models;

import java.io.Serializable;

public class FisioPoints implements Serializable {


    public FisioPoints() {
    }

    private int points = 0;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
