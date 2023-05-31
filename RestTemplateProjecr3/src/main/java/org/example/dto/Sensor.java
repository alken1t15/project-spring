package org.example.dto;


public class Sensor {

    public Sensor() {
    }

    private String name;

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}