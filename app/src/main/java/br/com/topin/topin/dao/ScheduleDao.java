package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Schedule;

/**
 * Created by dayvid on 19-01-2018.
 */

@Dao
public interface ScheduleDao {
    @Insert
    public void create(Schedule schedule);

    @Insert
    public void bulkCreate(List<Schedule> schedules);

    @Update
    public void update(Schedule schedule);

    @Query("SELECT * FROM schedule WHERE id = :id")
    public Schedule get(Long id);

    @Query("SELECT * FROM schedule")
    public List<Schedule> all();
}
