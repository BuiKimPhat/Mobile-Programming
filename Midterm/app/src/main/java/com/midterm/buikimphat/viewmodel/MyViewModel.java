package com.midterm.buikimphat.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.midterm.buikimphat.model.Place;
import com.midterm.buikimphat.model.PlaceApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Place>> places;
    private PlaceApiService apiService;

    public LiveData<ArrayList<Place>> getPlaces(){
        if (places == null) {
            places = new MutableLiveData<ArrayList<Place>>();
            fetchData();
        }
        return places;
    }

    private void fetchData(){
        apiService = new PlaceApiService();
        apiService.getPlaces()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Place>>() {
                    @Override
                    public void onSuccess(@NonNull List<Place> res) {
                        places.postValue(new ArrayList<Place>(res));
//                        for (Place p: res){
//                            Log.d("DEBUG1", p.getTitle());
//                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG1", e.getMessage());
                    }
                });
    }

    public ArrayList<Place> searchPlace(String title){
        ArrayList<Place> res = new ArrayList<>();
        for (Place p: places.getValue()) {
            if (p.getTitle().toLowerCase().contains(title.toLowerCase()))
                res.add(p);
        }
        return res;
    }
}

