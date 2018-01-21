package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.Line;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LineService {
    @GET("lines/")
    public Call<Line> get(@Query("id") Long id);

    @GET("lines/")
    public Call<List<Line>> all();
}
