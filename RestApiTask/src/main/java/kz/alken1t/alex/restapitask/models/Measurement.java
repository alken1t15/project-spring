package kz.alken1t.alex.restapitask.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    @NotNull(message = "Значение не должно быть пустым")
    private Sensor sensor;

    @Min(value = -100, message = "Значение должно быть в диапазоне от -100 до 100")
    @Max(value = 100, message = "Значение должно быть в диапазоне от -100 до 100")
    @NotNull(message = "Значение не должно быть пустым")
    private Float value;

    @NotNull(message = "Значение не должно быть пустым")
    private Boolean raining;

    private LocalDateTime date;
}