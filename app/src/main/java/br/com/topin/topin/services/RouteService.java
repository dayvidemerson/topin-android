package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Route;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RouteService {
    @GET("routes/{id}/")
    public Route get(@Path("id") Long id);

    @GET("routes/")
    public List<Route> all();

    @GET("routes/?city={city}")
    public List<Route> filter(@Path("city") String city);
}
