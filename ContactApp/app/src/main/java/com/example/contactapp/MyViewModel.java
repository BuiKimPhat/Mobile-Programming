package com.example.contactapp;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contact>> contacts;
    private AppDatabase database;
    private ContactDAO contactDAO;

    public MyViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        contactDAO = database.contactDAO();
    }

    public LiveData<List<Contact>> getContacts(){
        if (contacts == null) {
            contacts = new MutableLiveData<List<Contact>>();
            fetchContacts();
        }
        return contacts;
    }
    public void fetchContacts(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contact> res = contactDAO.getContacts();
                contacts.postValue(res);
            }
        }).start();
    }
    public void findByName(String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contact> res = contactDAO.findByName(name);
                contacts.postValue(res);
            }
        }).start();
    }
}
