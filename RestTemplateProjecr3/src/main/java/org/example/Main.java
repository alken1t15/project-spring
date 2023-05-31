package org.example;

import org.example.dto.MeasurementDTO;
import org.example.dto.Sensor;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        getMeasurement();
    }

    private static void measurementsAdd() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Sensor sensor = new Sensor("Test3");

        HttpEntity<Sensor> requestEntity = new HttpEntity<>(sensor, headers);
        restTemplate.exchange("http://localhost:8080/sensors/registration", HttpMethod.POST, requestEntity, String.class);

        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int minValue = -100;
            int maxValue = 100;
            int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
            boolean randomBoolean = random.nextBoolean();

            MeasurementDTO measurementDTO = new MeasurementDTO(sensor, (float) randomNumber, randomBoolean);

            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MeasurementDTO> requestEntity2 = new HttpEntity<>(measurementDTO, headers2);

            restTemplate.exchange("http://localhost:8080/measurements/add", HttpMethod.POST, requestEntity2, String.class);
        }
    }


    private static void getMeasurement() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<MeasurementDTO>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<MeasurementDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType);

        List<MeasurementDTO> measurements = response.getBody();
        List<Float> numbers = new ArrayList<>();
        for (MeasurementDTO measurementDTO : measurements) {
            numbers.add(measurementDTO.getValue());
        }
        showGraphic(numbers);
    }

    private static void showGraphic(List<Float> numbers) {
        XYChart chart = new XYChartBuilder()
                .width(1920)
                .height(1080)
                .title("Графика")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("График температуры", numbers);

        new SwingWrapper<>(chart).displayChart();
    }
}