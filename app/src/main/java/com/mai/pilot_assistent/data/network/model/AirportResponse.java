package com.mai.pilot_assistent.data.network.model;

import com.mai.pilot_assistent.data.db.model.Airport;

public class AirportResponse {
    private String id;
    private String airportIdApi;
    private String nameAirport;
    private String codeAirport;
    private String latitude;
    private String longitude;
    private String timezone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirportIdApi() {
        return airportIdApi;
    }

    public void setAirportIdApi(String airportIdApi) {
        this.airportIdApi = airportIdApi;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getCodeAirport() {
        return codeAirport;
    }

    public void setCodeAirport(String codeAirport) {
        this.codeAirport = codeAirport;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    public Airport toAirport(){
        Airport airport = new Airport();
        airport.setIdServer(this.id);
        airport.setNameAirport(this.nameAirport);
        airport.setCodeAirport(this.codeAirport);
        return airport;
    }
}
