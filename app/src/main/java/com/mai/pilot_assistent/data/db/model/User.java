package com.mai.pilot_assistent.data.db.model;

import org.greenrobot.greendao.annotation.*;

import java.util.Date;
import java.util.Set;

@Entity(nameInDb = "user")
public class User {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Index(unique = true)
    private String idServer;

    private String email;
    private int gender;
    private Date birth;
    private String username;

    @Keep
    public User(Long id, String name, String idServer, String email, int gender, Date birth, String username) {
        this.id = id;
        this.name = name;
        this.idServer = idServer;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.username = username;
    }

    @Keep
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}