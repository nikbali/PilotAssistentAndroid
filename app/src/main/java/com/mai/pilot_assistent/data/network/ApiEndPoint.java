package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.BuildConfig;


public final class ApiEndPoint {


    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL
            + "/auth/signin";

    public static final String ENDPOINT_SERVER_REGISTRATION = BuildConfig.BASE_URL
            + "/auth/signup";


    private ApiEndPoint() {

    }

}
