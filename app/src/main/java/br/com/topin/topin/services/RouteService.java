package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Route;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RouteService {
    @GET("routes/{id}/")
    Call<Route> get(@Path("id") Long id);

    @GET("routes/")
    Call<List<Route>> all();

    @GET("routes/")
    Call<List<Route>> filter(@Query("city") String city);
}
