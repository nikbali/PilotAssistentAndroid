package com.mai.pilot_assistent.data.network.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

    @SerializedName("accessToken")
    private  String accessToken;

    @SerializedName("errorText")
    private String errorText;

    private  UserDTO userProfile;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public UserDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserDTO userProfile) {
        this.userProfile = userProfile;
    }
}
