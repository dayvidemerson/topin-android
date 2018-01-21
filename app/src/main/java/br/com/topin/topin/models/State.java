package br.com.topin.topin.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class State {
    @PrimaryKey
    private String slug;
    private String name;
    private String abbr;

    @Ignore
    public State() { }

    public State(String slug, String name, String abbr) {
        this.slug = slug;
        this.name = name;
        this.abbr = abbr;
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

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return name;
    }
}
