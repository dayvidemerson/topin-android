package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Route;

/**
 * Created by dayvid on 19-01-2018.
 */

@Dao
public interface RouteDao {
    @Insert
    public void create(Route route);

    @Insert
    public void bulkCreate(List<Route> routes);

    @Update
    public void update(Route route);

    @Query("SELECT * FROM route WHERE id = :id")
    public Route get(Long id);

    @Query("SELECT * FROM route")
    public List<Route> all();
}
