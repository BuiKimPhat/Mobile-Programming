package com.midterm.buikimphat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.midterm.buikimphat.databinding.ActivityMainBinding;
import com.midterm.buikimphat.model.Place;
import com.midterm.buikimphat.viewmodel.MyViewModel;
import com.midterm.buikimphat.viewmodel.PlacesAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyViewModel model;
    private PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        adapter = new PlacesAdapter(new ArrayList<Place>(), this);

        model.getPlaces().observe(this, new Observer<ArrayList<Place>>() {
            @Override
            public void onChanged(ArrayList<Place> places) {
                adapter = new PlacesAdapter(places, MainActivity.this);
                binding.rvList.setAdapter(adapter);
                binding.rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter = new PlacesAdapter(model.searchPlace(binding.etSearch.getText().toString()), MainActivity.this);
                binding.rvList.setAdapter(adapter);
                binding.rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}