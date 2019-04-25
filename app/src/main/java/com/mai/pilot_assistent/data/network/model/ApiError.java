package com.mai.pilot_assistent.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {

    @Expose
    @SerializedName("errorText")
    private String errorText;

    public ApiError( String errorText) {
        this.errorText = errorText;
    }


    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
