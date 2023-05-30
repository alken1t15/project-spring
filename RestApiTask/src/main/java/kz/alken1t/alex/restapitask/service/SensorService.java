package kz.alken1t.alex.restapitask.service;

import kz.alken1t.alex.restapitask.dto.SensorDTO;
import kz.alken1t.alex.restapitask.models.Sensor;
import kz.alken1t.alex.restapitask.repository.SensorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public void saveSensor(SensorDTO sensorDTO){
        modelMapper.map(sensorDTO, Sensor.class)
    }

    public Sensor findByName(String name){
        sensorRepository.findByName(name);
        return null;
    }
}