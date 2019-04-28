package com.mai.pilot_assistent.data.prefs;

import com.mai.pilot_assistent.data.DataManager;

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(String userId);

    String getCurrentName();

    void setCurrentName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    void setCurrentUsername(String userName);

    String getCurrentUsername();

    String getAccessToken();

    void setAccessToken(String accessToken);

}
