package com.example.dogapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogapp.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<DogBreed>> dogBreeds;
    private DogsApiService apiService;

    public LiveData<ArrayList<DogBreed>> getDogBreeds(){
        if (dogBreeds == null) {
            dogBreeds = new MutableLiveData<ArrayList<DogBreed>>();
            fetchData();
        }
        return dogBreeds;
    }

    private void fetchData(){
        apiService = new DogsApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> res) {
                        dogBreeds.postValue(new ArrayList<DogBreed>(res));
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG1", e.getMessage());
                    }
                });
    }
}
