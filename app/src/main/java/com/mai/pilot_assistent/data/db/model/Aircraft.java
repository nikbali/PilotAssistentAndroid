package com.mai.pilot_assistent.data.db.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.annotation.*;

import java.io.Serializable;

@Entity(nameInDb = "aircraft")
public class Aircraft implements Serializable {

    static final long serialVersionUID = 32323423423L;

    @Id(autoincrement = true)
    private Long id;

    /**
     * Базовый аэродром
     */
    @Property(nameInDb = "airport_id")
    private Long airportId;

    @Expose
    @Property(nameInDb = "name")
    private String name;

    @Index(unique = true)
    @Expose
    @Property(nameInDb = "id_server")
    private String idServer;

    /**
     * Регистрационный номер или бортовой номер
     */
    @Index(unique = true)
    @Expose
    @Property(nameInDb = "reg_number")
    private String registrationName;

    /**
     * Год производства
     */
    @Expose
    @Property(nameInDb = "year")
    private Integer year;

    /**
     * Длина
     */
    @Expose
    @Property(nameInDb = "length")
    private Double length;

    /**
     * Размах крыльев
     */
    @Expose
    @Property(nameInDb = "wingspan")
    private Double wingspan;

    /**
     * URL изображения
     */
    @Expose
    @Property(nameInDb = "image_url")
    private String imageUrl;

    /**
     * Высота
     */
    @Expose
    @Property(nameInDb = "height")
    private Double height;

    /**
     * Крейсерская скорость
     */
    @Expose
    @Property(nameInDb = "cruising_speed")
    private Double cruisingSpeed;

    /**
     * Максимальная скорость
     */
    @Expose
    @Property(nameInDb = "max_speed")
    private Double maxSpeed;

    /**
     * Мощность двигателя (л.с)
     */
    @Expose
    @Property(nameInDb = "engine_power")
    private Double enginePower;

    @Generated(hash = 606047874)
    public Aircraft(Long id, Long airportId, String name, String idServer,
            String registrationName, Integer year, Double length, Double wingspan,
            String imageUrl, Double height, Double cruisingSpeed, Double maxSpeed,
            Double enginePower) {
        this.id = id;
        this.airportId = airportId;
        this.name = name;
        this.idServer = idServer;
        this.registrationName = registrationName;
        this.year = year;
        this.length = length;
        this.wingspan = wingspan;
        this.imageUrl = imageUrl;
        this.height = height;
        this.cruisingSpeed = cruisingSpeed;
        this.maxSpeed = maxSpeed;
        this.enginePower = enginePower;
    }

    @Generated(hash = 1271278373)
    public Aircraft() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAirportId() {
        return this.airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdServer() {
        return this.idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getRegistrationName() {
        return this.registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getLength() {
        return this.length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWingspan() {
        return this.wingspan;
    }

    public void setWingspan(Double wingspan) {
        this.wingspan = wingspan;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getCruisingSpeed() {
        return this.cruisingSpeed;
    }

    public void setCruisingSpeed(Double cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getEnginePower() {
        return this.enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }
}
