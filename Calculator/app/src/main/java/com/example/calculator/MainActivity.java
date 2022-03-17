package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getDisplay().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvDisp.setText(s);
            }
        });
        model.getHistory().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvHist.setText(s);
            }
        });

        binding.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("0");
            }
        });
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("1");
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("2");
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("3");
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("4");
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("5");
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("6");
            }
        });
        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("7");
            }
        });
        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("8");
            }
        });
        binding.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay("9");
            }
        });
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay(" + ");
            }
        });
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay(" - ");
            }
        });
        binding.btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay(" * ");
            }
        });
        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertDisplay(" / ");
            }
        });
        binding.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.clear();
            }
        });
        binding.btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.calculate();
            }
        });
    }
}