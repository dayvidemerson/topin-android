package br.com.topin.topin.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import br.com.topin.topin.converters.StringListConverter;

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

    private String hour;

    @TypeConverters(StringListConverter.class)
    private List<String> weekdays;

    @ColumnInfo(name = "line_id")
    private Long lineId;

    @Ignore
    public Schedule() { }

    public Schedule(Long id, List<String> weekdays, String hour, Long lineId) {
        this.id = id;
        this.weekdays = weekdays;
        this.hour = hour;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
}
