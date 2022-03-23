package com.example.contactapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ContactViewModel extends AndroidViewModel {
    private MutableLiveData<Contact> contact;
    private AppDatabase database;
    private ContactDAO contactDAO;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        contactDAO = database.contactDAO();
    }

    public LiveData<Contact> getContact(){
        if (contact == null) {
            contact = new MutableLiveData<Contact>();
            contact.setValue(new Contact("", null, null, null));
        }
        return contact;
    }

    public void setContact(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contact usr = contactDAO.findById(id);
                contact.postValue(usr);
            }
        }).start();
    }

    public void updateName(String name){
        Contact tmp = new Contact(name, contact.getValue().getMobile(), contact.getValue().getEmail(), contact.getValue().getAvatar());
        contact.setValue(tmp);
    }
    public void updatePhone(String phone){
        Contact tmp = new Contact(contact.getValue().getName(), phone, contact.getValue().getEmail(), contact.getValue().getAvatar());
        contact.setValue(tmp);
    }
    public void updateMail(String mail){
        Contact tmp = new Contact(contact.getValue().getName(), contact.getValue().getMobile(), mail, contact.getValue().getAvatar());
        contact.setValue(tmp);
    }
    public void updateAvatar(String avatar){
        Contact tmp = new Contact(contact.getValue().getName(), contact.getValue().getMobile(), contact.getValue().getEmail(), avatar);
        contact.setValue(tmp);
    }
}
