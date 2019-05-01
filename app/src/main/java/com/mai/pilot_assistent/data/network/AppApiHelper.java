package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.*;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class AppApiHelper implements ApiHelper {

    private String token;

    @Inject
    public AppApiHelper(String token){
        this.token = token;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
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

