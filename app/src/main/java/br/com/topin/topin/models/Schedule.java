package br.com.topin.topin.models;

import java.sql.Time;

/**
 * Created by dayvid on 18-01-2018.
 */

public class Schedule {
    private Long id;
    private String[] weekdays;
    private Time time;
    private Line line;

    public Schedule() { }

    public Schedule(Long id, String[] weekdays, Time time, Line line) {
        this.id = id;
        this.weekdays = weekdays;
        this.time = time;
        this.line = line;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String[] weekdays) {
        this.weekdays = weekdays;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
