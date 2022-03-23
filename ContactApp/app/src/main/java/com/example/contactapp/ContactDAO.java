package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getContacts();

    @Query("SELECT * FROM Contact WHERE name LIKE '%' || :name ||  '%'")
    List<Contact> findByName(String name);

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact findById(int id);

    @Update
    void updateAll(Contact... contacts);

    @Insert
    void insertAll(Contact... contacts);
}
