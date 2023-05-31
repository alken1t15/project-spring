package org.example.dto;




public class MeasurementDTO {

    public MeasurementDTO(Sensor sensor, Float value, Boolean raining) {
        this.sensor = sensor;
        this.value = value;
        this.raining = raining;
    }

    private Sensor sensor;

    private Float value;

    private Boolean raining;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }
}