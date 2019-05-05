package com.mai.pilot_assistent.data.network;

import com.mai.pilot_assistent.BuildConfig;


public final class ApiEndPoint {


    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL
            + "/auth/signin";

    public static final String ENDPOINT_SERVER_REGISTRATION = BuildConfig.BASE_URL
            + "/auth/signup";

    public static final String GET_AIRCRAFTS = BuildConfig.BASE_URL
            + "/aircraft/get_all";

    public static final String CREATE_AIRCRAFT = BuildConfig.BASE_URL
            + "/aircraft/create";

    public static final String GET_AIRPORTS = BuildConfig.BASE_URL
            + "/airport/getAll";

    private ApiEndPoint() {

    }

}
