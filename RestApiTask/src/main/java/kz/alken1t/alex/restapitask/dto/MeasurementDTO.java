package kz.alken1t.alex.restapitask.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kz.alken1t.alex.restapitask.models.Sensor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MeasurementDTO {

    @NotNull(message = "Значение не должно быть пустым")
    private Sensor sensor;

    @Min(value = -100, message = "Значение должно быть в диапазоне от -100 до 100")
    @Max(value = 100, message = "Значение должно быть в диапазоне от -100 до 100")
    @NotNull(message = "Значение не должно быть пустым")
    private Float value;

    @NotNull(message = "Значение не должно быть пустым")
    private Boolean raining;
}