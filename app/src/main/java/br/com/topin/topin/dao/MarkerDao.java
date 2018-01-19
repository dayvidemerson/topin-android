package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Marker;

/**
 * Created by dayvid on 18-01-2018.
 */

@Dao
public interface MarkerDao {
    @Insert
    public void create(Marker marker);

    @Insert
    public void bulkCreate(List<Marker> markers);

    @Update
    public void update(Marker marker);

    @Query("SELECT * FROM marker WHERE id = :id")
    public Marker get(Long id);

    @Query("SELECT * FROM marker")
    public List<Marker> all();
}
