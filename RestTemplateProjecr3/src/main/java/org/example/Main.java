package org.example;

import org.example.dto.MeasurementDTO;
import org.example.dto.Sensor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i< 1000; i++){
            Random random = new Random();
            int minValue = -100;
            int maxValue = 100;
            int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
            boolean randomBoolean = random.nextBoolean();

            MeasurementDTO measurementDTO = new MeasurementDTO(new Sensor("Test"), (float) randomNumber,randomBoolean);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MeasurementDTO> requestEntity = new HttpEntity<>(measurementDTO, headers);

            restTemplate.exchange("http://localhost:8080/measurements/add", HttpMethod.POST, requestEntity, String.class);
        }
//        Integer integer = restTemplate.getForObject("http://localhost:8080/measurements/rainyDaysCount", Integer.class);
//        System.out.println(integer);
    }
}