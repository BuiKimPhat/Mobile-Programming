package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contactapp.databinding.ActivityNewContactBinding;

public class NewContactActivity extends AppCompatActivity {

    private ActivityNewContactBinding binding;
    private MyViewModel model;
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        appDatabase = AppDatabase.getInstance(this);
        contactDAO = appDatabase.contactDAO();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString();
                String phone = binding.etPhone.getText().toString();
                String mail = binding.etMail.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        contactDAO.insertAll(new Contact(name, phone, mail));
                    }
                }).start();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}