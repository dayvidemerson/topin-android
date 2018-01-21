package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Marker;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarkerService {
    @GET("markers/")
    Call<Marker> get(@Query("id") Long id);

    @GET("markers/")
    Call<List<Marker>> all();

    @GET("markers/?city={city}")
    Call<List<Marker>> filter(@Path("city") String city);
}
