package com.mai.pilot_assistent.data.db.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.annotation.*;

import java.io.Serializable;

@Entity(nameInDb = "airport")
public class Airport implements Serializable {

    static final long serialVersionUID = 326578423423L;

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    @Expose
    @Property(nameInDb = "id_server")
    private String idServer;

    @Expose
    @Index(unique = true)
    @Property(nameInDb = "name_airport")
    private String nameAirport;

    @Expose
    @Property(nameInDb = "code_airport")
    private String codeAirport;

    @Generated(hash = 1384060604)
    public Airport(Long id, String idServer, String nameAirport,
            String codeAirport) {
        this.id = id;
        this.idServer = idServer;
        this.nameAirport = nameAirport;
        this.codeAirport = codeAirport;
    }

    @Generated(hash = 648016182)
    public Airport() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdServer() {
        return this.idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getNameAirport() {
        return this.nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getCodeAirport() {
        return this.codeAirport;
    }

    public void setCodeAirport(String codeAirport) {
        this.codeAirport = codeAirport;
    }
    
}
