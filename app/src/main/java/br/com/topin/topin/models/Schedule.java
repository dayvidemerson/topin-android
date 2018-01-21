package br.com.topin.topin.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Time;
import java.util.List;

import br.com.topin.topin.converters.StringListConverter;
import br.com.topin.topin.converters.TimeConverter;

@Entity(
    indices = @Index(value = "line_id", name = "idx_schedule_line"),
    foreignKeys = @ForeignKey(
            entity = Line.class,
            parentColumns = "id",
            childColumns = "line_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate= ForeignKey.CASCADE
    )
)
public class Schedule {
    @PrimaryKey
    private Long id;

    @TypeConverters(TimeConverter.class)
    private Time time;

    @TypeConverters(StringListConverter.class)
    private List<String> weekdays;

    @ColumnInfo(name = "line_id")
    private Long lineId;

    @Ignore
    public Schedule() { }

    public Schedule(Long id, List<String> weekdays, Time time, Long lineId) {
        this.id = id;
        this.weekdays = weekdays;
        this.time = time;
        this.lineId = lineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<String> weekdays) {
        this.weekdays = weekdays;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
}
