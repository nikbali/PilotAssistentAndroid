package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.data.network.model.LoginRequest;
import com.mai.pilot_assistent.data.network.model.LoginResponse;
import com.mai.pilot_assistent.data.network.model.LogoutResponse;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

}
