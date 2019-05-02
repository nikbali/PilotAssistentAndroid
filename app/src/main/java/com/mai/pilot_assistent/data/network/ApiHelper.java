package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.network.model.*;

import io.reactivex.Single;

import java.io.File;
import java.util.List;

public interface ApiHelper {

    Single<LoginResponse> doServerLoginApiCall(LoginRequest request);

    Single<List<AircraftResponse>> doServerGetAircraftsApiCall();

    Single<AircraftResponse> doServerCreateAircraftApiCall(File image, CreateAircraftRequest request);

    Single<RegistrationResponse> doServerRegistrationApiCall(RegistrationRequest request);

    Single<LogoutResponse> doLogoutApiCall();

}
