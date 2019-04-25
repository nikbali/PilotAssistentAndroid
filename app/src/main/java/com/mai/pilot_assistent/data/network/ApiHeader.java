package com.mai.pilot_assistent.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mai.pilot_assistent.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {

    @Expose
    @SerializedName("Authorization")
    private String token;

    @Expose
    @SerializedName("Content-Type")
    private String content_type;

    @Inject
    public ApiHeader(@ApiInfo String token, @ApiInfo String content_type) {
        token = this.token;
        content_type = this.content_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
}
