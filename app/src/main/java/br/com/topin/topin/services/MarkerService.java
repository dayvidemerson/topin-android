package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Marker;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarkerService {
    @GET("markers/{id}/")
    Call<Marker> get(@Path("id") Long id);

    @GET("markers/")
    Call<List<Marker>> all();

    @GET("markers/")
    Call<List<Marker>> filter(@Query("city") String city);
}
