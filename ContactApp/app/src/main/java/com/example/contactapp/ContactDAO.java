package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getContacts();

    @Query("SELECT * FROM Contact WHERE name LIKE '%' || :name ||  '%'")
    List<Contact> findByName(String name);

    @Insert
    void insertAll(Contact... contacts);
}
