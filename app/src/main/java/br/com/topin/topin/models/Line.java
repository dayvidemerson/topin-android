package br.com.topin.topin.models;

/**
 * Created by dayvid on 18-01-2018.
 */

public class Line {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private PointLine[] points;

    public Line() { }

    public Line(Long id, String name, String slug, String description, PointLine[] points) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.points = points;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PointLine[] getPoints() {
        return points;
    }

    public void setPoints(PointLine[] points) {
        this.points = points;
    }
}
