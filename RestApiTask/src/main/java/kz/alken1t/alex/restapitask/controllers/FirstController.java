package kz.alken1t.alex.restapitask.controllers;

import jakarta.validation.Valid;
import kz.alken1t.alex.restapitask.dto.MeasurementDTO;
import kz.alken1t.alex.restapitask.dto.SensorDTO;
import kz.alken1t.alex.restapitask.models.Sensor;
import kz.alken1t.alex.restapitask.service.SensorService;
import kz.alken1t.alex.restapitask.util.SensorErrorResponse;
import kz.alken1t.alex.restapitask.util.SensorNotCreatedException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class FirstController {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationNewSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.saveSensor(sensorDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
