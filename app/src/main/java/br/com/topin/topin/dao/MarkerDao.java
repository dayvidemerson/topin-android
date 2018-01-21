package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Marker;

@Dao
public interface MarkerDao {
    @Insert
    void create(Marker marker);

    @Insert
    void bulkCreate(List<Marker> markers);

    @Update
    void update(Marker marker);

    @Query("SELECT * FROM marker WHERE id = :id")
    Marker get(Long id);

    @Query("SELECT * FROM marker")
    List<Marker> all();
}
