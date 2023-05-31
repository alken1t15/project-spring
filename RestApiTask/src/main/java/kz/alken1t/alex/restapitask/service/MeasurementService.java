package kz.alken1t.alex.restapitask.service;

import kz.alken1t.alex.restapitask.dto.MeasurementDTO;
import kz.alken1t.alex.restapitask.models.Measurement;
import kz.alken1t.alex.restapitask.models.Sensor;
import kz.alken1t.alex.restapitask.repository.MeasurementRepository;
import kz.alken1t.alex.restapitask.util.MeasurementNotCreatedException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    public void saveMeasurement(MeasurementDTO measurementDTO) throws MeasurementNotCreatedException{
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        if (measurement.getSensor().getName()==null){
            throw new MeasurementNotCreatedException("Поле должно быть заполненным!");
        }
        Sensor sensor = sensorService.findSensorByName(measurement.getSensor().getName());
        if(sensor==null){
            throw new MeasurementNotCreatedException("Такого сенсора нету!");
        }
        measurement.setSensor(sensor);
        measurement.setDate(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<MeasurementDTO> findAllMeasurements(){
        List<Measurement> measurements = measurementRepository.findAll();
        List<MeasurementDTO> measurementDTOS = new ArrayList<>();
        for (Measurement measurement : measurements){
            MeasurementDTO measurementDTO =modelMapper.map(measurement,MeasurementDTO.class);
            measurementDTOS.add(measurementDTO);
        }
        return measurementDTOS;
    }

    public Integer rainyDaysCount(){
        return measurementRepository.rainyDaysCount();
    }
}