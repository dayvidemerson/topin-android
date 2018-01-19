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
    indices = {
        @Index(value = "front_id", name = "idx_route_front"),
        @Index(value = "back_id", name = "idx_route_back")
    },
    foreignKeys = {
        @ForeignKey(entity = Line.class, parentColumns = "id", childColumns = "front_id"),
        @ForeignKey(entity = Line.class, parentColumns = "id", childColumns = "back_id")
    }
)
public class Route {
    @PrimaryKey
    private Long id;
    private String name;
    private String slug;
    private String city;

    @ColumnInfo(name = "front_id")
    private Long frontId;

    @ColumnInfo(name = "back_id")
    private Long backId;

    @Ignore
    public Route() { }

    public Route(Long id, String name, String slug, Long frontId, Long backId, String city) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.frontId = frontId;
        this.backId = backId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getFrontId() {
        return frontId;
    }

    public void setFrontId(Long frontId) {
        this.frontId = frontId;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }
}
