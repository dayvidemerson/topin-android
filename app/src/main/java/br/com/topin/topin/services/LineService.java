package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Line;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LineService {
    @GET("lines/{id}/")
    Call<Line> get(@Path("id") Long id);

    @GET("lines/")
    Call<List<Line>> all();

    @GET("lines/")
    Call<List<Line>> filter(@Query("city") String city);
}
