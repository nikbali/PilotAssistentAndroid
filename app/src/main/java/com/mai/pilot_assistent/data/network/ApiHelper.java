package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.*;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest request);

    Single<RegistrationResponse> doServerRegistrationApiCall(RegistrationRequest request);

    Single<LogoutResponse> doLogoutApiCall();

}
