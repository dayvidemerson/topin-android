package br.com.topin.topin;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import br.com.topin.topin.models.Marker;
import br.com.topin.topin.services.MarkerService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dayvid on 19-01-2018.
 */

public class ApiTest {
    @Test
    public void get_markers() throws Exception {
        MarkerService service = Api.getRetrofit().create(MarkerService.class);
        service.all().enqueue(new Callback<List<Marker>>() {
            @Override
            public void onResponse(Call<List<Marker>> call, Response<List<Marker>> response) {
                try {
                    List<Marker> markers = call.execute().body();
                } catch (IOException e) {
                    Assert.assertTrue(false);
                }
            }

            @Override
            public void onFailure(Call<List<Marker>> call, Throwable t) {
                try {
                    List<Marker> markers = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
