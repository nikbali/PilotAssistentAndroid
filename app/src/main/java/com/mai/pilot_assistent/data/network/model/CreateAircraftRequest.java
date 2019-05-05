package com.mai.pilot_assistent.data.network.model;

public class CreateAircraftRequest {
    private String name;
    private String registrationName;
    private String baseAirportId;
    private String year;
    private String length;
    private String wingspan;
    private String height;
    private String cruisingSpeed;
    private String maxSpeed;
    private String enginePower;

    public CreateAircraftRequest(String name, String registrationName, String baseAirportId, String year, String length, String wingspan, String height, String cruisingSpeed, String maxSpeed, String enginePower) {
        this.name = name;
        this.registrationName = registrationName;
        this.baseAirportId = baseAirportId;
        this.year = year;
        this.length = length;
        this.wingspan = wingspan;
        this.height = height;
        this.cruisingSpeed = cruisingSpeed;
        this.maxSpeed = maxSpeed;
        this.enginePower = enginePower;
    }

    public CreateAircraftRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWingspan() {
        return wingspan;
    }

    public void setWingspan(String wingspan) {
        this.wingspan = wingspan;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(String cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getBaseAirportId() {
        return baseAirportId;
    }

    public void setBaseAirportId(String baseAirportId) {
        this.baseAirportId = baseAirportId;
    }
}