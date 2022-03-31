package com.example.dogapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.databinding.FragmentListBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsAdapter;
import com.example.dogapp.viewmodel.MyViewModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private MyViewModel model;
    private DogsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MyViewModel.class);

        adapter = new DogsAdapter(new ArrayList<DogBreed>());

        model.getDogBreeds().observe(this, new Observer<ArrayList<DogBreed>>() {
            @Override
            public void onChanged(ArrayList<DogBreed> dogBreeds) {
                adapter = new DogsAdapter(dogBreeds);
                binding.rvDogBreeds.setAdapter(adapter);
                binding.rvDogBreeds.setLayoutManager(new GridLayoutManager(ListFragment.this.getContext(), 2));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}