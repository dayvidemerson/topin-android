package br.com.topin.topin.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static String BASE_URL = "https://topin.herokuapp.com/api/";
    private static Retrofit sRetrofit;

    public static Retrofit getRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return sRetrofit;
    }
}
