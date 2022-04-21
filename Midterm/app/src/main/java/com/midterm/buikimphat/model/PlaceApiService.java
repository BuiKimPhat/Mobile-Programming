package com.midterm.buikimphat.model;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceApiService {
    private static final String BASE_URL = "http://staff.vnuk.edu.vn:5000/";
    private static PlaceApi api;

    public PlaceApiService(){
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(PlaceApi.class);
        }
    }

    public Single<List<Place>> getPlaces(){
        return api.getPlaces();
    }
}
