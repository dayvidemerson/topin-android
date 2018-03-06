package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Schedule;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ScheduleService {
    @GET("schedules/{id}/")
    Call<Schedule> get(@Path("id") Long id);

    @GET("schedules/")
    Call<List<Schedule>> all();

    @GET("schedules/")
    Call<List<Schedule>> filter(@Query("line") String slug, @Query("weekdays") String weekday);
}
