package br.com.topin.topin.converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Time;

public class TimeConverter {
    @TypeConverter
    public Time fromLong(Long time) {
        return time == null ? null : new Time(time);
    }

    @TypeConverter
    public Long fromTime(Time time) {
        return time.getTime();
    }
}
