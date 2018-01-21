package br.com.topin.topin.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(
    indices = {
            @Index(value = "state_slug", name = "idx_city_state")
    },
    foreignKeys = {
            @ForeignKey(entity = State.class, parentColumns = "slug", childColumns = "state_slug")
    }
)
public class City {
    @PrimaryKey
    private String slug;
    private String name;

    @ColumnInfo(name = "state_slug")
    private String stateSlug;

    @Ignore
    public City() { }

    public City(String slug, String name, String stateSlug) {
        this.slug = slug;
        this.name = name;
        this.stateSlug = stateSlug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateSlug() {
        return stateSlug;
    }

    public void setStateSlug(String stateSlug) {
        this.stateSlug = stateSlug;
    }
}
