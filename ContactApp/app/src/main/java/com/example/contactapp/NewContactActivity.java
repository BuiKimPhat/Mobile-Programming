package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityNewContactBinding;

public class NewContactActivity extends AppCompatActivity {

    private ActivityNewContactBinding binding;
    private ContactViewModel model;
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact edit");

        model = new ViewModelProvider(this).get(ContactViewModel.class);

        appDatabase = AppDatabase.getInstance(this);
        contactDAO = appDatabase.contactDAO();

        int mode = getIntent().getIntExtra("mode", -1);
        if (mode == 1)
            model.setContact(getIntent().getIntExtra("contactID", -1));
        Log.d("debug", mode+"");
        Log.d("debug", getIntent().getIntExtra("contactID", -1)+"");

        model.getContact().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                if (contact.getAvatar() != null)
                    binding.ivAvaDetail.setImageURI(Uri.parse(contact.getAvatar()));
                binding.etName.setText(contact.getName());
                binding.etPhone.setText(contact.getMobile());
                binding.etMail.setText(contact.getEmail());
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString();
                String phone = binding.etPhone.getText().toString();
                String mail = binding.etMail.getText().toString();
                String avatar = model.getContact().getValue().getAvatar();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Contact newCon = new Contact(name, phone, mail, avatar);
                        if (mode == 0)
                            contactDAO.insertAll(newCon);
                        else if (mode == 1){
                            newCon.setId(getIntent().getIntExtra("contactID", -1));
                            contactDAO.updateAll(newCon);
                        }
                    }
                }).start();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        binding.ivAvaDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 123);
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getApplicationContext().getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            model.updateAvatar(imageUri.toString());
        } else {
            Toast.makeText(NewContactActivity.this, "Bạn chưa chọn ảnh",Toast.LENGTH_LONG).show();
        }
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