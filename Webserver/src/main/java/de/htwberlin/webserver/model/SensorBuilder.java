package de.htwberlin.webserver.model;

import lombok.Builder;


@Builder
public class SensorBuilder {
    private Long id;
    private Double temperature;
    private Double humidity;
    private Long dateTime;

    public SensorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public SensorBuilder setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public SensorBuilder setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public SensorBuilder setDateTime(Long localDateTime) {
        this.dateTime = localDateTime;
        return this;
    }

    public Sensor createSensor() {
        return new Sensor(id, temperature, humidity, dateTime);
    }
}