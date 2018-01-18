package br.com.topin.topin.models;

/**
 * Created by dayvid on 18-01-2018.
 */

public class PointLine extends Point {
    private Integer order;
    private Line line;

    public PointLine() { }

    public PointLine(Long id, Double longitude, Double latitude, Integer order, Line line) {
        super(id, longitude, latitude);
        this.order = order;
        this.line = line;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
