package com.mai.pilot_assistent.data.network.model;

public class CreateFlightResponse {

    private String id;
    private AirportResponse origin;
    private AirportResponse destination;
    private String flightNumber;
    private String departureDateTime;
    private String arrivalDateTime;
    private AircraftResponse aircraft;
    private String status;
    private UserDTO pilot;

    public String getId() {
        return id;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AirportResponse getOrigin() {
        return origin;
    }

    public void setOrigin(AirportResponse origin) {
        this.origin = origin;
    }

    public AirportResponse getDestination() {
        return destination;
    }

    public void setDestination(AirportResponse destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public AircraftResponse getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftResponse aircraft) {
        this.aircraft = aircraft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getPilot() {
        return pilot;
    }

    public void setPilot(UserDTO pilot) {
        this.pilot = pilot;
    }
}
