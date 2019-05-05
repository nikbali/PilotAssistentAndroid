package com.mai.pilot_assistent.data;


import android.content.Context;
import com.mai.pilot_assistent.data.db.DbHelper;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.User;
import com.mai.pilot_assistent.data.network.ApiHelper;
import com.mai.pilot_assistent.data.network.model.*;
import com.mai.pilot_assistent.data.prefs.PreferencesHelper;
import com.mai.pilot_assistent.di.ApplicationContext;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.List;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Observable<User> getUserByUsername(String username) {
        return mDbHelper.getUserByUsername(username);
    }

    @Override
    public Observable<Long> insertAircraft(Aircraft aircraft) {
        return mDbHelper.insertAircraft(aircraft);
    }

    @Override
    public Observable<Void> insertListAircraft(List<Aircraft> aircraftList) {
        return mDbHelper.insertListAircraft(aircraftList);
    }

    @Override
    public Observable<List<Aircraft>> getAllAircrafts() {
        return mDbHelper.getAllAircrafts();
    }

    @Override
    public Observable<Aircraft> getAircraftByServerId(String serverId) {
        return mDbHelper.getAircraftByServerId(serverId);
    }


    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Single<List<AircraftResponse>> doServerGetAircraftsApiCall() {
        return mApiHelper.doServerGetAircraftsApiCall();
    }

    @Override
    public Single<AircraftResponse> doServerCreateAircraftApiCall(File image, CreateAircraftRequest request) {
        return mApiHelper.doServerCreateAircraftApiCall(image,request);
    }

    @Override
    public Single<RegistrationResponse> doServerRegistrationApiCall(RegistrationRequest request) {
        return mApiHelper.doServerRegistrationApiCall(request);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentName() {
        return mPreferencesHelper.getCurrentName();
    }

    @Override
    public void setCurrentName(String name) {
        mPreferencesHelper.setCurrentName(name);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void setCurrentUsername(String userName) {
        mPreferencesHelper.setCurrentUsername(userName);
    }

    @Override
    public String getCurrentUsername() {
        return mPreferencesHelper.getCurrentUsername();
    }


    @Override
    public void updateUserInfoPrefs(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String name) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUsername(userName);
        setCurrentUserEmail(email);
        setCurrentName(name);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfoPrefs(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }


}
