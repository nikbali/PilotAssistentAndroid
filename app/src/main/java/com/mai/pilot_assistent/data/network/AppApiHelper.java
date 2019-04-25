package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.LoginRequest;
import com.mai.pilot_assistent.data.network.model.LoginResponse;
import com.mai.pilot_assistent.data.network.model.LogoutResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
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
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader)
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

}

