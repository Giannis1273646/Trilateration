package org.trilateration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Nodes {

    private String id;
    private double x;
    private double y;
    private double distance;

    @JsonCreator
    public Nodes(       @JsonProperty("id") String id,
                        @JsonProperty("x") double x,
                        @JsonProperty("y") double y,
                        @JsonProperty("distance") double distance){
        this.id = id;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistance() {return distance;}

    public void setId(String NewId) {
        this.id = NewId;
    }

    public void setX(double NewX) {
        this.x = NewX;
    }
    public void setY(double NewY) {
        this.y = NewY;
    }

    public void setDistance(double NewDistance) {
        this.x = NewDistance;
    }
}
