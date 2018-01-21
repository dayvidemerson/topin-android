package br.com.topin.topin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.topin.topin.models.Route;

@Dao
public interface RouteDao {
    @Insert
    void create(Route route);

    @Insert
    void bulkCreate(List<Route> routes);

    @Update
    void update(Route route);

    @Query("SELECT * FROM route WHERE id = :id")
    Route get(Long id);

    @Query("SELECT * FROM route")
    List<Route> all();
}
