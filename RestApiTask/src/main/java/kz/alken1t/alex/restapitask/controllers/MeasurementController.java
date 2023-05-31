package kz.alken1t.alex.restapitask.controllers;

import jakarta.validation.Valid;
import kz.alken1t.alex.restapitask.dto.MeasurementDTO;
import kz.alken1t.alex.restapitask.service.MeasurementService;
import kz.alken1t.alex.restapitask.util.MeasurementErrorResponse;
import kz.alken1t.alex.restapitask.util.MeasurementNotCreatedException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/measurements")
@AllArgsConstructor
@RestController
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }

        measurementService.saveMeasurement(measurementDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDTO>> measurements() {
        return new ResponseEntity<>(measurementService.findAllMeasurements(), HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> rainyDaysCount() {
        return new ResponseEntity<>(measurementService.rainyDaysCount(), HttpStatus.OK);
    }

}