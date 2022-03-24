package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dogapp.databinding.ActivityMainBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsAdapter;
import com.example.dogapp.viewmodel.DogsApiService;
import com.example.dogapp.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyViewModel model;
    private DogsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        adapter = new DogsAdapter(new ArrayList<DogBreed>());

        model.getDogBreeds().observe(this, new Observer<ArrayList<DogBreed>>() {
            @Override
            public void onChanged(ArrayList<DogBreed> dogBreeds) {
                adapter = new DogsAdapter(dogBreeds);
                binding.rvDogBreeds.setAdapter(adapter);
                binding.rvDogBreeds.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
        });
    }
}