package br.com.topin.topin.models;

/**
 * Created by dayvid on 18-01-2018.
 */

public class Marker extends Point {
    private String type;
    private String description;
    private String city;

    public Marker() { }

    public Marker(Long id, Double longitude, Double latitude, String type, String description, String city) {
        super(id, longitude, latitude);
        this.type = type;
        this.description = description;
        this.city = city;
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
