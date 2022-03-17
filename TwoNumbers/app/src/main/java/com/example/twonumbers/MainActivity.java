package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.twonumbers.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private MyViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayList);
        binding.lvHist.setAdapter(arrayAdapter);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                arrayList = new ArrayList<>(strings);
                arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, arrayList);
                binding.lvHist.setAdapter(arrayAdapter);
            }
        });

        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(binding.etA.getText().toString());
                int b = Integer.parseInt(binding.etB.getText().toString());
                model.addItem(a + " + " + b + " = " + (a+b));
            }
        });
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(binding.etA.getText().toString());
                int b = Integer.parseInt(binding.etB.getText().toString());
                model.addItem(a + " - " + b + " = " + (a-b));
            }
        });
        binding.btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(binding.etA.getText().toString());
                int b = Integer.parseInt(binding.etB.getText().toString());
                model.addItem(a + " * " + b + " = " + (a*b));
            }
        });
        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(binding.etA.getText().toString());
                int b = Integer.parseInt(binding.etB.getText().toString());
                model.addItem(a + " / " + b + " = " + (a/b));
            }
        });
    }
}