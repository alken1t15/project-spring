package kz.alken1t.alex.restapitask.repository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kz.alken1t.alex.restapitask.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor,Long> {

    Sensor findByName(@NotEmpty(message = "У сенсора должно быть название") @Size(min = 3, max = 30, message = "Размер должен быть в диапазоне от 3 до 30") String name);

    List<Sensor> findAllByName(@NotEmpty(message = "У сенсора должно быть название") @Size(min = 3, max = 30, message = "Размер должен быть в диапазоне от 3 до 30") String name);

}