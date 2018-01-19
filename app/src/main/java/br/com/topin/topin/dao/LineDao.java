package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Line;

/**
 * Created by dayvid on 18-01-2018.
 */

@Dao
public interface LineDao {
    @Insert
    public void create(Line line);

    @Insert
    public void bulkCreate(List<Line> lines);

    @Update
    public void update(Line line);

    @Query("SELECT * FROM line WHERE id = :id")
    public Line get(Long id);

    @Query("SELECT * FROM line")
    public List<Line> all();
}
