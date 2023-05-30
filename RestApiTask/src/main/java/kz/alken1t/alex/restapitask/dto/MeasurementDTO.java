package kz.alken1t.alex.restapitask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {
    private Float value;

    private Boolean raining;

    private SensorDTO sensorDTO;
}