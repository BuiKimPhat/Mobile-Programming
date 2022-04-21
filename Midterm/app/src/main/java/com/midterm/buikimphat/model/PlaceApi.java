package com.midterm.buikimphat.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PlaceApi {
    @GET("static/data/data.json")
    public Single<List<Place>> getPlaces();
}
