package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Point;

@Dao
public interface PointDao {
    @Insert
    void create(Point point);

    @Insert
    void bulkCreate(List<Point> points);

    @Update
    void update(Point point);

    @Query("SELECT * FROM point WHERE id = :id")
    Point get(Long id);

    @Query("SELECT * FROM point")
    List<Point> all();

    @Query("SELECT * FROM point WHERE line_id = :lineId")
    List<Point> filter(Long lineId);
}
