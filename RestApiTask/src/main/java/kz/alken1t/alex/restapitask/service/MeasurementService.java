package kz.alken1t.alex.restapitask.service;

import kz.alken1t.alex.restapitask.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
}