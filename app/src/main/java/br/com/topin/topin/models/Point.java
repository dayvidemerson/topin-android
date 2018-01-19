package br.com.topin.topin.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dayvid on 18-01-2018.
 */

@Entity(
    indices = @Index(value = "line_id", name = "idx_point_line"),
    foreignKeys = @ForeignKey(
        entity = Line.class,
        parentColumns = "id",
        childColumns = "line_id",
        onDelete = ForeignKey.CASCADE,
        onUpdate= ForeignKey.CASCADE
    )
)
public class Point {
    @PrimaryKey
    private Long id;
    private Double longitude;
    private Double latitude;
    private Integer order;

    @ColumnInfo(name = "line_id")
    private Long lineId;

    @Ignore
    public Point() { }

    public Point(Long id, Double longitude, Double latitude, Integer order, Long lineId) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.order = order;
        this.lineId = lineId;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
}
