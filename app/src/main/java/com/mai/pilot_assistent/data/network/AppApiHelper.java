package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.*;
import com.mai.pilot_assistent.data.prefs.PreferencesHelper;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.List;

@Singleton
public class AppApiHelper implements ApiHelper {

    private PreferencesHelper prefs;

    @Inject
    public AppApiHelper(PreferencesHelper prefs){
        this.prefs = prefs;
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .setContentType("application/json; charset=utf-8")
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }


    @Override
    public Single<List<AircraftResponse>> doServerGetAircraftsApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_AIRCRAFTS)
                .addHeaders("Authorization", String.format("Bearer %s", prefs.getAccessToken()))
                .build()
                .getObjectListSingle(AircraftResponse.class);
    }

    @Override
    public Single<AircraftResponse> doServerCreateAircraftApiCall(File file, CreateAircraftRequest request) {
        return Rx2AndroidNetworking
                .upload(ApiEndPoint.CREATE_AIRCRAFT)
                .addHeaders("Authorization", String.format("Bearer %s", prefs.getAccessToken()))
                .setContentType("multipart/form-data")
                .addMultipartFile("image", file)
                .addMultipartParameter("name", request.getName())
                .addMultipartParameter("registrationName", request.getRegistrationName())
                .addMultipartParameter("airportId", request.getBaseAirportId())
                .addMultipartParameter("year", request.getYear())
                .addMultipartParameter("length", request.getLength())
                .addMultipartParameter("height", request.getHeight())
                .addMultipartParameter("wingspan", request.getWingspan())
                .addMultipartParameter("cruisingSpeed", request.getCruisingSpeed())
                .addMultipartParameter("maxSpeed", request.getMaxSpeed())
                .addMultipartParameter("enginePower", request.getEnginePower())
                .build()
                .getObjectSingle(AircraftResponse.class);
    }

    @Override
    public Single<RegistrationResponse> doServerRegistrationApiCall(RegistrationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_REGISTRATION)
                .setContentType("application/json; charset=utf-8")
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(RegistrationResponse.class);
    }

    @Override
    public Single<List<AirportResponse>> doServerGetAirportsApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_AIRPORTS)
                .addHeaders("Authorization", String.format("Bearer %s", prefs.getAccessToken()))
                .build()
                .getObjectListSingle(AirportResponse.class);
    }

    @Override
    public Single<List<CreateFlightResponse>> doServerGetFlightsApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_FLIGHTS)
                .addHeaders("Authorization", String.format("Bearer %s", prefs.getAccessToken()))
                .build()
                .getObjectListSingle(CreateFlightResponse.class);
    }

    @Override
    public Single<CreateFlightResponse> doServerCreateFlightApiCall(CreateFlightRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CREATE_FLIGHT)
                .addHeaders("Authorization", String.format("Bearer %s", prefs.getAccessToken()))
                .setContentType("application/json; charset=utf-8")
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(CreateFlightResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return null;
    }

}

