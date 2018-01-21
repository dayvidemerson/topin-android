package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Schedule;

@Dao
public interface ScheduleDao {
    @Insert
    void create(Schedule schedule);

    @Insert
    void bulkCreate(List<Schedule> schedules);

    @Update
    void update(Schedule schedule);

    @Query("SELECT * FROM schedule WHERE id = :id")
    Schedule get(Long id);

    @Query("SELECT * FROM schedule")
    List<Schedule> all();
}
