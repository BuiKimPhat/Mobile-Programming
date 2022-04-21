package com.example.dogapp;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dogapp.databinding.FragmentDetailsBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;
    private DogBreed dog;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dog = (DogBreed) getArguments().getSerializable("dogBreed");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setDog(dog);
        View viewRoot = binding.getRoot();
        return viewRoot;
    }
}