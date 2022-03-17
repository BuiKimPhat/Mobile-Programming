package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if (intent != null){
            String data = intent.getStringExtra("number");
            binding.etNumber.setText(data);
        }

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("newNumber", binding.etNumber.getText().toString());
                if (intent != null){
                    intent.putExtra("index", intent.getIntExtra("index", 0));
                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}