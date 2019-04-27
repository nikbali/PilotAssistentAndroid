package com.mai.pilot_assistent.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RegistrationResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
