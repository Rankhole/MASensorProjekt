package de.htwberlin.webserver.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double temperature;

    @Column
    private Double humidity;

    @Column
    private Long dateTime;

    public Sensor() {

    }

    public Sensor(Long id, Double temperature, Double humidity, Long localDateTime) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.dateTime = localDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
