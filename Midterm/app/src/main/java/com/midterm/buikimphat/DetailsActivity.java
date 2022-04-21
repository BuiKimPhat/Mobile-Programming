package com.midterm.buikimphat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.midterm.buikimphat.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Place detail");

        Intent intent = getIntent();
        binding.tvTitle.setText(intent.getStringExtra("title"));
        binding.tvDesc.setText(intent.getStringExtra("desc"));
        binding.tvTime.setText(intent.getStringExtra("timeStamp"));
        binding.tvLat.setText(String.valueOf(intent.getDoubleExtra("lat", 0)));
        binding.tvLng.setText(String.valueOf(intent.getDoubleExtra("lng", 0)));
        binding.tvAddr.setText(intent.getStringExtra("addr"));
        binding.tvE.setText(intent.getStringExtra("e"));
        binding.tvZip.setText(intent.getStringExtra("zip"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}