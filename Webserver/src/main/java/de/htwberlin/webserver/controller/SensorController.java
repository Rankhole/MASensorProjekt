package de.htwberlin.webserver.controller;

import de.htwberlin.webserver.model.Sensor;
import de.htwberlin.webserver.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class SensorController {

    private final SensorRepository sensorRepository;

    private RestTemplate restTemplate;

    private final int updateInterval = 5000;

    private final String readSensorUrl = "http://192.168.2.156:5000/readsensor";

    private Logger logger;

    @Autowired
    public SensorController(SensorRepository sensorRepository, RestTemplate restTemplate) {
        this.sensorRepository = sensorRepository;
        this.restTemplate = restTemplate;
        logger = LoggerFactory.getLogger(SensorController.class);
    }

    @GetMapping("/showSensorData")
    public String showSensorData(Model model) {
        List<Sensor> sensorData = sensorRepository.findAll();
        logger.info(sensorData.toString());
        model.addAttribute("sensorData", sensorData);

        return "showSensorData";
    }

    @Scheduled(fixedRate = updateInterval)
    private void updateSensorData() {
        try {
            ResponseEntity<Sensor> result = restTemplate.getForEntity(readSensorUrl, Sensor.class);
            assert result.getStatusCode() == HttpStatus.OK;
            Sensor newestSensorData = result.getBody();
            assert newestSensorData != null;
            newestSensorData.setDateTime(System.currentTimeMillis());
            logger.info("Temperature: " + newestSensorData.getTemperature() + ", Humidity: " + newestSensorData.getHumidity() + ", Time: " + newestSensorData.getDateTime());
            sensorRepository.saveAndFlush(newestSensorData);
        } catch (Exception e) {
            logger.warn(e.toString());
        }
    }
}
