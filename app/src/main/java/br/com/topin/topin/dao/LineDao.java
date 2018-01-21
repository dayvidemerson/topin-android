package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Line;

@Dao
public interface LineDao {
    @Insert
    void create(Line line);

    @Insert
    void bulkCreate(List<Line> lines);

    @Update
    void update(Line line);

    @Query("SELECT * FROM line WHERE id = :id")
    Line get(Long id);

    @Query("SELECT * FROM line")
    List<Line> all();
}
