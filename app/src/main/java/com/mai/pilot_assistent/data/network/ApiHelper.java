package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.*;
import io.reactivex.Single;

import java.io.File;
import java.util.List;

public interface ApiHelper {

    /**
     * Login + Logout
     */
    Single<LoginResponse> doServerLoginApiCall(LoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    /**
     * Registration
     */
    Single<RegistrationResponse> doServerRegistrationApiCall(RegistrationRequest request);

    /**
     * Aircrafts
     */
    Single<List<AircraftResponse>> doServerGetAircraftsApiCall();

    Single<AircraftResponse> doServerCreateAircraftApiCall(File image, CreateAircraftRequest request);

    /**
     * Airports
     */
    Single<List<AirportResponse>> doServerGetAirportsApiCall();

    /**
     * Flights
     */
    Single<List<CreateFlightResponse>> doServerGetFlightsApiCall();

    Single<CreateFlightResponse> doServerCreateFlightApiCall(CreateFlightRequest request);


}
