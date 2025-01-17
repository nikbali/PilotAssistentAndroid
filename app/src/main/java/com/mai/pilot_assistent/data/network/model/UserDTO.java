package com.mai.pilot_assistent.data.network.model;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("gender")
    private int gender;

    @SerializedName("birth")
    private String birth;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
