package kz.alken1t.alex.restapitask.service;

import kz.alken1t.alex.restapitask.dto.SensorDTO;
import kz.alken1t.alex.restapitask.models.Sensor;
import kz.alken1t.alex.restapitask.repository.SensorRepository;
import kz.alken1t.alex.restapitask.util.SensorNotCreatedException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public void saveSensor(SensorDTO sensorDTO) throws SensorNotCreatedException {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        Sensor getDataBase = sensorRepository.findByName(sensor.getName());
        if (getDataBase != null) {
            throw new SensorNotCreatedException("Такой сенсор уже есть");
        }
        save(sensor);
    }

    public Sensor findSensorByName(String name) {
        return sensorRepository.findByName(name);
    }
}