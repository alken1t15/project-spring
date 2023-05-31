package kz.alken1t.alex.restapitask.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SensorDTO {
    @NotEmpty(message = "У сенсора должно быть название")
    @Size(min = 3, max = 30, message = "Размер должен быть в диапазоне от 3 до 30")
    private String name;
}