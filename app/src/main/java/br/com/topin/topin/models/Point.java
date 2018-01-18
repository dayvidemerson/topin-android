package br.com.topin.topin.models;

/**
 * Created by dayvid on 18-01-2018.
 */

public abstract class Point {
    protected Long id;
    protected Double longitude;
    protected Double latitude;

    public Point() { }

    public Point(Long id, Double longitude, Double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
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
}
