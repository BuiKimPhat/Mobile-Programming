package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contactapp.databinding.ActivityContactDetailBinding;

public class ContactDetailActivity extends AppCompatActivity {

    private ActivityContactDetailBinding binding;
    private ContactViewModel model;
    private boolean updated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact detail");

        model = new ViewModelProvider(this).get(ContactViewModel.class);

        model.setContact(getIntent().getIntExtra("contactID", -1));

        model.getContact().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                if (contact.getAvatar() != null) {
                    binding.ivAvaDetail.setImageTintList(null);
                    binding.ivAvaDetail.setImageURI(Uri.parse(contact.getAvatar()));
                }

                if (contact.getEmail() != null && !contact.getEmail().equals("")){
                    binding.cvEmail.setVisibility(View.VISIBLE);
                    binding.tvMail.setText(contact.getEmail());
                } else {
                    binding.cvEmail.setVisibility(View.GONE);
                }

                if (contact.getMobile() != null && !contact.getMobile().equals("")){
                    binding.cvPhone.setVisibility(View.VISIBLE);
                    binding.tvPhone.setText(contact.getMobile());
                } else {
                    binding.cvPhone.setVisibility(View.GONE);
                }

                binding.tvName.setText(contact.getName());
            }
        });

        Intent editIntent = new Intent(ContactDetailActivity.this, NewContactActivity.class);
        editIntent.putExtra("mode", 1);
        editIntent.putExtra("contactID", getIntent().getIntExtra("contactID", -1));

        binding.ivAvaDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(editIntent, 333);
            }
        });
        binding.cvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(editIntent, 333);
            }
        });
        binding.cvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(editIntent, 333);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 333) {
            if(resultCode == Activity.RESULT_OK){
                updated = true;
                model.setContact(model.getContact().getValue().getId());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                if (updated) setResult(Activity.RESULT_OK, returnIntent);
                else setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}