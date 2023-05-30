package kz.alken1t.alex.restapitask.repository;

import kz.alken1t.alex.restapitask.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}