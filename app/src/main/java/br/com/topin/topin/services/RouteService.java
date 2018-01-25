package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Route;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RouteService {
    @GET("routes/{id}/")
    Route get(@Path("id") Long id);

    @GET("routes/")
    List<Route> all();

    @GET("routes/")
    List<Route> filter(@Query("city") String city);
}
