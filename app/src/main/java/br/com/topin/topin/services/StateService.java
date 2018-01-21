package br.com.topin.topin.services;

import java.util.List;

import br.com.topin.topin.models.State;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StateService {
    @GET("states/")
    Call<State> get(@Query("id") Long id);

    @GET("states/")
    Call<List<State>> all();
}
