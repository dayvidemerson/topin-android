package br.com.topin.topin.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dayvid on 18-01-2018.
 */

@Entity
public class Marker {
    @PrimaryKey
    private Long id;
    private Double longitude;
    private Double latitude;
    private String type;
    private String description;
    private String city;

    @Ignore
    public Marker() { }

    public Marker(Long id, Double longitude, Double latitude, String type, String description, String city) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.description = description;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
