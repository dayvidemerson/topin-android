package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CityService {
    @GET("cities/")
    Call<City> get(@Query("id") Long id);

    @GET("cities/")
    Call<List<City>> all();

    @GET("cities/")
    Call<List<City>> filter(@Query("state") String state);
}
