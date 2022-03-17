package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contacts;
    private ContactAdapter contactAdapter;
    private MyViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        contacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(contacts);

        model.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> ct) {
                contacts = new ArrayList<>(ct);
                contactAdapter = new ContactAdapter(contacts);
                binding.rvContacts.setAdapter(contactAdapter);
                binding.rvContacts.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, 111);
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                model.findByName(binding.etSearch.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            if(resultCode == Activity.RESULT_OK){
                model.fetchContacts();
                Log.i("insert", "inserted");
            }
        }
    }
}