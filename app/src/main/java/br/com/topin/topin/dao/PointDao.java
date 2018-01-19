package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Point;

/**
 * Created by dayvid on 18-01-2018.
 */

@Dao
public interface PointDao {
    @Insert
    public void create(Point point);

    @Insert
    public void bulkCreate(List<Point> points);

    @Update
    public void update(Point point);

    @Query("SELECT * FROM point WHERE id = :id")
    public Point get(Long id);

    @Query("SELECT * FROM point")
    public List<Point> all();

    @Query("SELECT * FROM point WHERE line_id = :lineId")
    public List<Point> filter(Long lineId);
}
