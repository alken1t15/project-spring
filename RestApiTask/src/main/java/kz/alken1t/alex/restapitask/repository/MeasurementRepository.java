package kz.alken1t.alex.restapitask.repository;

import kz.alken1t.alex.restapitask.models.Measurement;
import org.hibernate.annotations.HQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query("select count(*) from Measurement m where m.raining=true")
    Integer rainyDaysCount();
}