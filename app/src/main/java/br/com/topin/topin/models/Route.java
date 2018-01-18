package br.com.topin.topin.models;

/**
 * Created by dayvid on 18-01-2018.
 */

public class Route {
    private Long id;
    private String name;
    private String slug;
    private Line front;
    private Line back;
    private String city;

    public Route() { }

    public Route(Long id, String name, String slug, Line front, Line back, String city) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.front = front;
        this.back = back;
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

    public Line getFront() {
        return front;
    }

    public void setFront(Line front) {
        this.front = front;
    }

    public Line getBack() {
        return back;
    }

    public void setBack(Line back) {
        this.back = back;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
