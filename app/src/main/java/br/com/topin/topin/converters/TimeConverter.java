package br.com.topin.topin.converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Time;

/**
 * Created by dayvid on 19-01-2018.
 */

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
