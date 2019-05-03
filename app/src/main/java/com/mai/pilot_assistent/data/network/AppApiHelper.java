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
    public Single<LogoutResponse> doLogoutApiCall() {
        return null;
    }

}

