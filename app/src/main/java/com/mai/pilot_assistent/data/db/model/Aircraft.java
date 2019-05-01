package com.mai.pilot_assistent.data.db.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.annotation.*;

@Entity(nameInDb = "aircraft")
public class Aircraft {

    @Id(autoincrement = true)
    private Long id;

    @Expose
    @Property(nameInDb = "name")
    private String name;

    @Index(unique = true)
    @Expose
    @Property(nameInDb = "id_server")
    private String idServer;

    /**
     * Год производства
     */

    @Expose
    @Property(nameInDb = "year")
    private Integer year;

    /**
     * Длина
     */
    @Expose
    @Property(nameInDb = "length")
    private Double length;

    /**
     * Размах крыльев
     */
    @Expose
    @Property(nameInDb = "wingspan")
    private Double wingspan;

    /**
     * URL изображения
     */
    @Expose
    @Property(nameInDb = "image_url")
    private String imageUrl;

    /**
     * Высота
     */
    @Expose
    @Property(nameInDb = "height")
    private Double height;

    /**
     * Крейсерская скорость
     */
    @Expose
    @Property(nameInDb = "cruising_speed")
    private Double cruisingSpeed;

    /**
     * Максимальная скорость
     */
    @Expose
    @Property(nameInDb = "max_speed")
    private Double maxSpeed;

    /**
     * Мощность двигателя (л.с)
     */
    @Expose
    @Property(nameInDb = "engine_power")
    private Double enginePower;

    @Generated(hash = 586692628)
    public Aircraft(Long id, String name, String idServer, Integer year, Double length, Double wingspan, String imageUrl, Double height, Double cruisingSpeed, Double maxSpeed, Double enginePower) {
        this.id = id;
        this.name = name;
        this.idServer = idServer;
        this.year = year;
        this.length = length;
        this.wingspan = wingspan;
        this.imageUrl = imageUrl;
        this.height = height;
        this.cruisingSpeed = cruisingSpeed;
        this.maxSpeed = maxSpeed;
        this.enginePower = enginePower;
    }

    @Generated(hash = 586691638)
    public Aircraft() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public void setWingspan(Double wingspan) {
        this.wingspan = wingspan;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(Double cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (id != null ? !id.equals(aircraft.id) : aircraft.id != null) return false;
        if (name != null ? !name.equals(aircraft.name) : aircraft.name != null) return false;
        if (idServer != null ? !idServer.equals(aircraft.idServer) : aircraft.idServer != null) return false;
        if (year != null ? !year.equals(aircraft.year) : aircraft.year != null) return false;
        if (length != null ? !length.equals(aircraft.length) : aircraft.length != null) return false;
        if (wingspan != null ? !wingspan.equals(aircraft.wingspan) : aircraft.wingspan != null) return false;
        if (imageUrl != null ? !imageUrl.equals(aircraft.imageUrl) : aircraft.imageUrl != null) return false;
        if (height != null ? !height.equals(aircraft.height) : aircraft.height != null) return false;
        if (cruisingSpeed != null ? !cruisingSpeed.equals(aircraft.cruisingSpeed) : aircraft.cruisingSpeed != null)
            return false;
        if (maxSpeed != null ? !maxSpeed.equals(aircraft.maxSpeed) : aircraft.maxSpeed != null) return false;
        return enginePower != null ? enginePower.equals(aircraft.enginePower) : aircraft.enginePower == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idServer != null ? idServer.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (wingspan != null ? wingspan.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (cruisingSpeed != null ? cruisingSpeed.hashCode() : 0);
        result = 31 * result + (maxSpeed != null ? maxSpeed.hashCode() : 0);
        result = 31 * result + (enginePower != null ? enginePower.hashCode() : 0);
        return result;
    }
}
