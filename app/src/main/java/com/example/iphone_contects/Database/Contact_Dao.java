package com.example.iphone_contects.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface Contact_Dao {

    //contact
    @Insert
    void insert_contact(Contact contact);

    @Delete
    void delete_contact(Contact contact);

    @Update
    void update_contact(Contact contact);

    @Query("DELETE FROM contacts_table")
    void deleteAll_contact();


    @Query("SELECT * FROM contacts_table ORDER BY id DESC")
    LiveData<List<Contact>> getAll_Contact();

    @Query("SELECT * FROM contacts_table WHERE fav LIKE :fav ")
    LiveData<List<Contact>> getFavContact(boolean fav);
}
