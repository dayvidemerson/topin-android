package br.com.topin.topin.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

@Entity
public class Marker {
    @PrimaryKey
    private Long id;
    private String name;
    private String slug;
    private Double longitude;
    private Double latitude;
    private String type;
    private String description;
    private String city;

    @Ignore
    public Marker() { }

    public Marker(Long id, String name, String slug, Double longitude, Double latitude, String type, String description, String city) {
        this.id = id;
        this.name = name;
        this.slug = slug;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }
}
