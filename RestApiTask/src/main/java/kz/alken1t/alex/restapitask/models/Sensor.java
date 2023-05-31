package kz.alken1t.alex.restapitask.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sensor")
@Getter
@Setter
public class Sensor {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "У сенсора должно быть название")
    @Size(min = 3, max = 30, message = "Размер должен быть в диапазоне от 3 до 30")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
}