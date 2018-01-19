package br.com.topin.topin.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.topin.topin.dao.LineDao;
import br.com.topin.topin.dao.MarkerDao;
import br.com.topin.topin.dao.PointDao;
import br.com.topin.topin.dao.RouteDao;
import br.com.topin.topin.dao.ScheduleDao;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.models.Marker;
import br.com.topin.topin.models.Point;
import br.com.topin.topin.models.Route;
import br.com.topin.topin.models.Schedule;

/**
 * Created by dayvid on 19-01-2018.
 */

@Database(
        version = 1,
        entities = { Line.class, Marker.class, Point.class, Route.class, Schedule.class }
)
public abstract class AppDatabase extends RoomDatabase {
    abstract public LineDao lineDao();

    abstract public MarkerDao markerDao();

    abstract public PointDao pointDao();

    abstract public RouteDao routeDao();

    abstract public ScheduleDao scheduleDao();
}
